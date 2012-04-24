package net.snake.gamemodel.heroext.lianti.logic;

import java.util.Date;

import net.snake.across.util.AcrossUtil;
import net.snake.commons.GenerateProbability;
import net.snake.commons.message.SimpleResponse;
import net.snake.commons.program.Updatable;
import net.snake.consts.BuffId;
import net.snake.consts.GoodItemId;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.hero.persistence.LiantiDataProvider;
import net.snake.gamemodel.hero.response.CharacterAttributesMsg49992;
import net.snake.gamemodel.heroext.lianti.bean.CharacterLiantiDataEntry;
import net.snake.gamemodel.heroext.lianti.bean.Lianti;
import net.snake.gamemodel.heroext.lianti.persistence.CharacterLianTiDataProvider;
import net.snake.gamemodel.heroext.lianti.response.TupoLianTiResultFail53006;
import net.snake.gamemodel.heroext.lianti.response.TupoLianTiResultOK53006;

import org.apache.log4j.Logger;

/**
 * 炼体管理
 * 
 * @author serv_dev
 * 
 */
public class CharacterLianTiController extends CharacterController implements PropertyChangeListener, Updatable {
	private static Logger logger = Logger.getLogger(CharacterLianTiController.class);

	private int liantiJingjieId;

	private int hp;// 生命上限最大加成
	private int atk;// 攻击最大加成
	private int def;// 防御最大加成
	private int mp;// 内力上限最大加成
	private int sp;// 体力上限最大加成
	private int atkSpeed;// 攻速最大加成
	private int moveSpeed;// 移速最大加成
	private int aqJv;// 暗器发动几率最大加成（万分比）
	private int fjsh;// 反弹伤害最大加成（百分比）
	private int shjm;// 伤害减免最大加成（百分比）

	private int tupoCount;
	private int zhufu;
	private Date zhufuStarttime = new Date(0);
	private int usegoodcount = 0;

	private int putiCardUsecount = 0;

	private Lianti lianTiData;
	private Lianti previousLianTiData;

	public int getPutiCardUsecount() {
		return putiCardUsecount;
	}

	public void init() {
		try {
			CharacterLiantiDataEntry characterLiantidataentry = CharacterLianTiDataProvider.getInstance().getLianTiDataByCharacterId(character.getId());
			getCharacterLiantiData(characterLiantidataentry);
			lianTiData = LiantiDataProvider.getInstance().getLianTiByJingjie(liantiJingjieId);
			previousLianTiData = LiantiDataProvider.getInstance().getLianTiByJingjie(liantiJingjieId - 1);
			if (previousLianTiData == null) {
				previousLianTiData = createZeroLianTi();
			}
			character.getPropertyAdditionController().addChangeListener(this);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public synchronized void setPuticardUseCount(int count) {
		putiCardUsecount = count;
		saveputicardData();
	}

	public synchronized void tupo(int type) {
		AcrossUtil.checkNoAcross();
		if (liantiJingjieId >= LiantiDataProvider.getInstance().getMaxLiantiJingjie()) {
			return;
		}
		if (tupoCount < lianTiData.getTupoNeedMinCount()) {
			tupoFailProcess(type);
			return;
		}
		if (tupoCount >= lianTiData.getTupoNeedMinCount() && tupoCount < lianTiData.getTupoNeedMaxCount()) {
			boolean issuccess = GenerateProbability.isGenerate(lianTiData.getTupoRealProbability(), 10000);
			if (issuccess) {
				tupoSuccessProcess(type);
				return;
			} else {
				tupoFailProcess(type);
				return;
			}
		}
		if (tupoCount >= lianTiData.getTupoNeedMaxCount()) {
			tupoSuccessProcess(type);
		}
	}

	private boolean deletePuti(int type) {
		// 如果有菩提卡效果
		if (liantiJingjieId >= 4 && character.getEffectController().getBufferInBufferListByBufferId(BuffId.free_roushentuotaihuangu) != null) {
			if (putiCardUsecount > 0) {
				putiCardUsecount--;
				saveputicardData();
				if (putiCardUsecount == 0) {
					character.getEffectController().removeBuffById(BuffId.free_roushentuotaihuangu);
				}
				return true;
			}
		}
		if (type == 0) {
			boolean isok = character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(lianTiData.getTupoNeedGoodsmodel(), lianTiData.getTupoNeedGoodscount());
			if (!isok) {
				// 正常情况是不应该产生这种问题的，但如果产生这种问题的话，我们就不回应了
				return false;
			}
		} else if (type == 1) {
			int putijingcuiCount = 1;
			int putidanCount = lianTiData.getTupoNeedGoodscount() - putijingcuiCount;

			boolean isok = character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(lianTiData.getTupoNeedGoodsmodel(), putidanCount);
			if (!isok) {
				// 正常情况是不应该产生这种问题的，但如果产生这种问题的话，我们就不回应了
				return false;
			}
			isok = character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(GoodItemId.PUTIJINGCUI, putijingcuiCount);
			if (!isok) {
				// 正常情况是不应该产生这种问题的，但如果产生这种问题的话，我们就不回应了
				return false;
			}
		} else if (type == 2) {
			boolean isok = character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(GoodItemId.SHENJIPUTIDAN, 1);
			if (!isok) {
				return false;
			}
		}
		return true;
	}

	private void tupoFailProcess(int type) {
		// character.getCharacterGoodController().addGoodsToBag(good)
		// 扣除对应数量的菩提丹；
		// 记录本次操作的时间；
		// 祝福值增加该境界对应幅度的祝福值；
		// 返回消息提示：
		// “很遗憾，本次肉身境界提升操作失败了，但祝福值增加了XX点，再接再厉哦”
		//
		// boolean isok = character
		// .getCharacterGoodController()
		// .getBagGoodsContiner()
		// .deleteGoodsByModel(lianTiData.getTupoNeedGoodsmodel(),
		// lianTiData.getTupoNeedGoodscount());
		if (!deletePuti(type)) {
			// 正常情况是不应该产生这种问题的，但如果产生这种问题的话，我们就不回应了
			return;
		}
		zhufuStarttime = new Date();
		zhufu = zhufu + lianTiData.getZhufuOnfail();
		if (zhufu > lianTiData.getZhufuMax()) {
			zhufu = lianTiData.getZhufuMax();
		}
		tupoCount++;
		saveTuPoJingjieData();
		character.sendMsg(new TupoLianTiResultFail53006(liantiJingjieId, 60018, "" + lianTiData.getZhufuOnfail()));// 提升失败
	}

	// 提升练体境界成功时的的数据变化
	private void tupoSuccessProcess(int type) {
		// //扣除对应数量的菩提丹；
		// 在开始提升按钮处播放烟花特效；
		// 清空祝福值；
		// 人物肉身境界+1；
		// 肉身境界各项属性的最大值变大；
		// 返回消息提示：
		// “恭喜您，肉身境界成功提升至【XXXX】境界，属性加成上限值得到了扩展。”
		// Goodmodel
		// goodmodel=GoodmodelManager.getInstance().get(lianTiData.getTupoNeedGoodsmodel());
		if (!deletePuti(type)) {
			// 正常情况是不应该产生这种问题的，但如果产生这种问题的话，我们就不回应了
			return;
		}

		zhufu = 0;
		zhufuStarttime = new Date(0);
		tupoCount = 0;
		usegoodcount = 0;
		int t = liantiJingjieId + 1;
		if (t <= LiantiDataProvider.getInstance().getMaxLiantiJingjie()) {
			previousLianTiData = lianTiData;
			lianTiData = LiantiDataProvider.getInstance().getLianTiByJingjie(t);
			liantiJingjieId = t;
			saveTuPoJingjieData();
			character.sendMsg(new TupoLianTiResultOK53006(liantiJingjieId - 1));
		}
	}

	/**
	 * 是否达到了当前境界的最高线
	 * 
	 * @return
	 */
	public boolean isMaxProperties() {
		if (hp == lianTiData.getHp().intValue()) {
			return true;
		}
		return false;
	}

	public int addProperty(float percent, int base, int nextmax) {
		int value = (int) (base + (nextmax - base) * percent);
		if (value > nextmax) {
			value = nextmax;
		}
		return value;
	}

	/**
	 * 通过吃食物提升属性
	 * 
	 * @param percent
	 *            　提升的百分比
	 * @return
	 */

	public synchronized void promoteProperties() {
		if (isMaxProperties()) {
			// 正常情况下，物品使用时应该已经调用过了
			return;
		}
		usegoodcount++;

		// 应该加成到的增量的进度
		float percent = usegoodcount * lianTiData.getFoodAddproperty() / 10000f;
		hp = addProperty(percent, previousLianTiData.getHp(), lianTiData.getHp());
		atk = addProperty(percent, previousLianTiData.getAtk(), lianTiData.getAtk());
		def = addProperty(percent, previousLianTiData.getDef(), lianTiData.getDef());
		mp = addProperty(percent, previousLianTiData.getMp(), lianTiData.getMp());
		sp = addProperty(percent, previousLianTiData.getSp(), lianTiData.getSp());
		atkSpeed = addProperty(percent, previousLianTiData.getAtkSpeed(), lianTiData.getAtkSpeed());
		moveSpeed = addProperty(percent, previousLianTiData.getMoveSpeed(), lianTiData.getMoveSpeed());
		aqJv = addProperty(percent, previousLianTiData.getAqJv(), lianTiData.getAqJv());
		fjsh = addProperty(percent, previousLianTiData.getFjsh(), lianTiData.getFjsh());
		shjm = addProperty(percent, previousLianTiData.getShjm(), lianTiData.getShjm());

		if (hp == lianTiData.getHp()) {// 如果有一项已经是最大的了，就把其他项都设成最大,防止因为float问题显示不一样
			atk = lianTiData.getAtk();
			def = lianTiData.getDef();
			mp = lianTiData.getMp();
			sp = lianTiData.getSp();
			atkSpeed = lianTiData.getAtkSpeed();
			moveSpeed = lianTiData.getMoveSpeed();
			aqJv = lianTiData.getAqJv();
			fjsh = lianTiData.getFjsh();
			shjm = lianTiData.getShjm();
		}
		savePropertiesData();
		character.getPropertyAdditionController().addChangeListener(this);
		character.sendMsg(new CharacterAttributesMsg49992(character));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60022));
		character.sendMsg(SimpleResponse.onlyMsgCodeMsg(53008));

	}

	public Date getZhufuStarttime() {
		return zhufuStarttime;
	}

	public Lianti getLianTiData() {
		return lianTiData;
	}

	public int getLiantiJingjieId() {
		return liantiJingjieId;
	}

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public int getMp() {
		return mp;
	}

	public int getSp() {
		return sp;
	}

	public int getDef() {
		return def;
	}

	public int getAtkSpeed() {
		return atkSpeed;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public int getAqJv() {
		return aqJv;
	}

	public int getFjsh() {
		return fjsh;
	}

	public int getShjm() {
		return shjm;
	}

	public int getTupoCount() {
		return tupoCount;
	}

	public int getZhufu() {
		return zhufu;
	}

	public CharacterLianTiController(Hero character) {
		super(character);
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.lianti;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe = new PropertyEntity();
		pe.setMaxHp(hp);
		pe.setAttack(atk);
		pe.setDefend(def);
		pe.setMaxMp(mp);
		pe.setMaxSp(sp);
		pe.setAttackSpeed(atkSpeed);
		pe.setMoveSpeed(moveSpeed);
		pe.setAqJv(aqJv);
		pe.setFtsh(fjsh);
		pe.setShjm(shjm);
		return pe;
	}

	// 从数据库中读取数据到内存
	private void getCharacterLiantiData(CharacterLiantiDataEntry dataentry) {
		liantiJingjieId = dataentry.getLiantiJingjieId();
		hp = dataentry.getHp();
		atk = dataentry.getAtk();
		def = dataentry.getDef();
		mp = dataentry.getMp();
		sp = dataentry.getSp();
		atkSpeed = dataentry.getAtkSpeed();
		moveSpeed = dataentry.getMoveSpeed();
		aqJv = dataentry.getAqJv();
		fjsh = dataentry.getFjsh();
		shjm = dataentry.getShjm();

		zhufu = dataentry.getZhufu();
		tupoCount = dataentry.getTupoCount();
		zhufuStarttime = dataentry.getZhufuStarttime();
		usegoodcount = dataentry.getUsegoodCount();
		putiCardUsecount = dataentry.getPutiCardUsecount();
		if (zhufuStarttime == null) {
			zhufuStarttime = new Date(0);
		}
	}

	// 突破境界后保存数据库
	private void saveTuPoJingjieData() {
		CharacterLiantiDataEntry data = new CharacterLiantiDataEntry();
		data.setCharacterId(character.getId());
		data.setLiantiJingjieId(liantiJingjieId);
		data.setTupoCount(tupoCount);
		data.setZhufu(zhufu);
		data.setZhufuStarttime(zhufuStarttime);
		data.setUsegoodCount(usegoodcount);
		data.setPutiCardUsecount(putiCardUsecount);
		CharacterLianTiDataProvider.getInstance().asynUpdateCharacterLiantiData(character, data);
	}

	private void saveputicardData() {
		CharacterLiantiDataEntry data = new CharacterLiantiDataEntry();
		data.setCharacterId(character.getId());
		data.setPutiCardUsecount(putiCardUsecount);
		CharacterLianTiDataProvider.getInstance().asynUpdateCharacterLiantiData(character, data);
	}

	// 将炼体数据保存在数据库中
	private void savePropertiesData() {
		CharacterLiantiDataEntry data = new CharacterLiantiDataEntry();
		data.setCharacterId(character.getId());
		data.setHp(hp);
		data.setAtk(atk);
		data.setDef(def);
		data.setMp(mp);
		data.setSp(sp);
		data.setAtkSpeed(atkSpeed);
		data.setMoveSpeed(moveSpeed);
		data.setAqJv(aqJv);
		data.setFjsh(fjsh);
		data.setShjm(shjm);
		data.setUsegoodCount(usegoodcount);
		data.setPutiCardUsecount(putiCardUsecount);
		CharacterLianTiDataProvider.getInstance().asynUpdateCharacterLiantiData(character, data);

	}

	// 创建一个所有属性为0的数据，用于获得第一等级和（上一等级之间的差值)
	private Lianti createZeroLianTi() {
		Lianti zerolianti = new Lianti();
		zerolianti.setHp(0);
		zerolianti.setAtk(0);
		zerolianti.setDef(0);
		zerolianti.setMp(0);
		zerolianti.setSp(0);
		zerolianti.setAtkSpeed(0);
		zerolianti.setMoveSpeed(0);
		zerolianti.setAqJv(0);
		zerolianti.setFjsh(0);
		zerolianti.setShjm(0);
		return zerolianti;
	}

	// public static void main(String[] args) {
	// Date a = new Date(0);
	// // System.out.println(a);
	// }

	@Override
	public void update(long now) {
		if (zhufu == 0) {
			return;
		}
		if (zhufuStarttime == null || zhufuStarttime.getTime() == 0) {
			return;
		}
		if (System.currentTimeMillis() - zhufuStarttime.getTime() > 24 * 60 * 60 * 1000) {
			zhufu = 0;
			zhufuStarttime = new Date(0);
			saveTuPoJingjieData();
		}

		// 在肉身境界提升失败时，记录玩家的操作时间，
		// 该时间之后的24小时，祝福值自动清零。
		// 在24小时之内，当玩家有提升肉身境界操作行为时，不论成败，祝福值清零时间均重新开始从24小时计时。
		//
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}
}
