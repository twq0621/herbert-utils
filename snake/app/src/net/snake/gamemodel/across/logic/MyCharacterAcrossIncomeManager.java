/**
 * 
 */
package net.snake.gamemodel.across.logic;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.formula.CharacterFormula;
import net.snake.consts.ClientConfig;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.across.persistence.AcrossIncomeManager;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.persistence.CharacterAcrossDateCenterManager;
import net.snake.gamemodel.across.response.AcrossShouyiChangeMsg53204;
import net.snake.gamemodel.across.response.Acrossinfo53202;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.XuanyuanjianConfig;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.shell.Options;

/**
 * 角色跨服收益表
 */

public class MyCharacterAcrossIncomeManager {
	private static final Logger logger = Logger.getLogger(MyCharacterAcrossIncomeManager.class);
	private Hero character;
	private AcrossIncome ai;
	private boolean isInit = false;
	private long initTime = 0;
	private boolean isOnline = false;
	private int acrossId = 0;

	/**
	 * @param character
	 */
	public MyCharacterAcrossIncomeManager(Hero character) {
		this.character = character;
	}

	public void init(boolean relive) {
		if (Options.IsCrossServ) {
			initAcrossShouyi();
			return;
		}
		if (System.currentTimeMillis() - initTime < 80 * 1000) {
			return;
		}
		long time = CharacterAcrossDateCenterManager.getInstance().getSendAcrossTime(character.getId());
		long timeOut = System.currentTimeMillis() - time;
		if (timeOut > 75 * 1000) {
			CharacterAcrossDateCenterManager.getInstance().removeSendAcross(character.getId());
			CharacterAcrossDateCenterManager.getInstance().updateCharacterAcrossOnLingquShouyi(character.getId(), 0);

		}
		if (CharacterAcrossDateCenterManager.getInstance().isInitAcrossShouyiToAcross(character.getId())) {
			// 向跨服服务器发送请求收益
			initTime = System.currentTimeMillis();
			CharacterAcrossDateCenterManager.getInstance().sendlingquShouyiMsg(character);
			if (relive) {
				Scene newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
				short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
				character.setScene(ClientConfig.Scene_Xianjing);
				character.setX(point[0]);
				character.setY(point[1]);
			}
			return;
		}
		CharacterAcrossDateCenterManager.getInstance().sendlingquShouyiAboutAccountMsg(character);
		if (isInit) {
			return;
		}
		initAcrossShouyi();
	}

	public void online() {
		if (Options.IsCrossServ) {
			character.sendMsg(new AcrossShouyiChangeMsg53204(ai));
		}
		isOnline = true;
	}

	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	/**
	 * 加铜币
	 * 
	 * @param copper
	 */
	public void addCopper(int copper) {
		ai.setCopper(ai.getCopper() + copper);
		character.sendMsg(new AcrossShouyiChangeMsg53204(ai));
	}

	/**
	 * 加经验
	 * 
	 * @param exp
	 */
	public void addExp(long exp) {
		ai.setExp(ai.getExp() + exp);
		character.sendMsg(new AcrossShouyiChangeMsg53204(ai));
	}

	/**
	 * 加胜利次数
	 * 
	 * @param exp
	 */
	public void addWinCnt() {
		ai.setWinCnt(ai.getWinCnt() + 1);
	}

	/**
	 * 加失败次数
	 */
	public void addFailCnt() {
		ai.setFailCnt(ai.getFailCnt() + 1);
	}

	/**
	 * 当前已经获得的声望
	 * 
	 * @return
	 */
	public int getShengWang() {
		return ai.getShengwang();
	}

	/**
	 * 加声望
	 * 
	 * @param shengwang
	 */
	public void addShengWang(int shengwang) {
		ai.setShengwang(ai.getShengwang() + shengwang);
		character.sendMsg(new AcrossShouyiChangeMsg53204(ai));

	}

	public void initAcrossShouyi() {
		try {
			ai = AcrossIncomeManager.getInstance().selectAcrossIncomeByCharacterId(character.getId());
			if (ai == null) {
				ai = new AcrossIncome();
				ai.setCharacterId(character.getId());
				ai.setCopper(0);
				ai.setExp(0l);
				ai.setShengwang(0);
				ai.setXuanyuanjian(0);
				ai.setFailCnt(0);
				ai.setWinCnt(0);
				AcrossIncomeManager.getInstance().insert(ai);
			}
			if (Options.IsCrossServ) {
				this.isInit = true;
				try {
					acrossId = character.getVlineserver().getLineid();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				return;
			}
			try {
				character.getCharacterGoodController().reInitAcrossBag();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			this.isInit = true;
			try {
				CharacterAcross ca = CharacterAcrossDateCenterManager.getInstance().getCharacterAcrossByCharacterId(character.getId());
				if (ca == null) {
					acrossId = 0;
				} else {
					acrossId = ca.getAcrossServerId();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.isInit = true;
		CharacterAcrossDateCenterManager.getInstance().updateCharacterAcrossOnLingquShouyi(character.getId(), 0);
		if (isOnline) {
			int line = character.getVlineserver().getLineid();
			AcrossIncome aincome = getAi();
			long exp = aincome.getExp();
			int shenwang = aincome.getShengwang();
			int copper = aincome.getCopper();
			character.getMycharacterAcrossZhengzuoManager().online();
			Collection<CharacterGoods> acrossbag = character.getCharacterGoodController().getAcrossGoodsContainer().getGoodsList();
			character.sendMsg(new Acrossinfo53202(line, exp, shenwang, copper, acrossbag));
		}
	}

	public void clearShouyi() {
		if (ai == null) {
			return;
		}
		ai.setCopper(0);
		ai.setExp(0l);
		ai.setShengwang(0);
		ai.setXuanyuanjian(0);
		AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
	}

	/**
	 * 添加跨服标记 清楚本地跨服包裹
	 * 
	 * @param as
	 */
	public boolean addAcrossFlagClearBendiShouyi(AcrossServerDate as) {
		clearShouyi();
		character.getCharacterGoodController().getAcrossGoodsContainer().clear();
		CharacterAcrossDateCenterManager.getInstance().addOrUpdateCharacterAcrossFlag(character, as);
		return true;
	}

	public AcrossIncome getAi() {
		return ai;
	}

	public void setAi(AcrossIncome ai) {
		this.ai = ai;
	}

	/**
	 * 
	 */
	public void lingquShouyi() {
		long exp = ai.getExp();
		int shenwang = ai.getShengwang();
		int copper = ai.getCopper();
		if (exp == 0 && shenwang == 0 && copper == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1310));
			return;
		}
		if (copper + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1326));
			return;
		}
		ai.setCopper(0);
		ai.setExp(0l);
		ai.setShengwang(0);
		try {
			CharacterFormula.experienceProcess(character, exp);
			CharacterPropertyManager.changeLunJianShengWang(character, shenwang);
			CharacterPropertyManager.changeCopper(character, copper, CopperAction.ADD_OTHER);
			Collection<CharacterGoods> acrossbag = character.getCharacterGoodController().getAcrossGoodsContainer().getGoodsList();
			character.sendMsg(new Acrossinfo53202(acrossId, 0l, 0, 0, acrossbag));
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
				}
			});
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1311, exp + "", shenwang + "", copper + ""));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void initOnline() {
		init(true);
	}

	public void cacheInComeToDb() {
		if (ai != null) {
			AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
		}
	}

	/**
	 * @param xuanjianConfig
	 */
	public void receiveXuanyuanShenjian(XuanyuanjianConfig xuanjianConfig) {
		ai.setXuanyuanjian(xuanjianConfig.getGoodId());
		Date nextDay = null;
		try {
			AcrossServerDate asd = AcrossServerDateManager.getInstance().getAcrossServerDateById(character.getVlineserver().getLineid());
			nextDay = asd.getNextKuafuzhan();
		} catch (Exception e) {
			// TODO: handle exception
		}
		ai.setXuanyuanjianTime(nextDay);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
			}
		});

	}

	/**
	 * 清空胜率信息（跨服之前） 主轮训中禁止调用此方法
	 * 
	 * @param as
	 */
	public void clearShenglvInfo(AcrossServerDate as) {
		if (ai.getXuanyuanjian() > 0) {
			ai.setWinCnt(0);
			ai.setFailCnt(0);
			ai.setXuanyuanjian(0);
			Date clearTim = new Date();
			ai.setXuanyuanjianTime(clearTim);
			AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
			return;
		}
		if (ai.getXuanyuanjianTime() != null && as.isTimeExpression(ai.getXuanyuanjianTime().getTime())) {
			return;
		}
		long lostTime = System.currentTimeMillis() + 1200000;
		if (ai.getXuanyuanjianTime() != null && ai.getXuanyuanjianTime().getTime() > lostTime) {
			return;
		}
		ai.setWinCnt(0);
		ai.setFailCnt(0);
		ai.setXuanyuanjian(0);
		Date clearTim = new Date();
		ai.setXuanyuanjianTime(clearTim);
		AcrossIncomeManager.getInstance().updateAcrossIncome(ai);
	}
}
