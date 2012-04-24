package net.snake.gamemodel.friend.logic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.formula.DistanceFormula;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.response.hero.EnterSceneRoleSelfInfo10022;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.gamemodel.wedding.persistence.CouplesManager;
import net.snake.gamemodel.wedding.response.WeddingEndSuccessMsg52306;
import net.snake.gamemodel.wedding.response.WeddingMsg52226;
import net.snake.gamemodel.wedding.response.WeddingMsg52228;
import net.snake.gamemodel.wedding.response.WeddingMsg52308;
import net.snake.shell.Options;



/**
 * 夫妻关系管理器
 * 
 * @author serv_dev
 */
public class RoleWedingManager {
	private static Logger logger = Logger.getLogger(RoleWedingManager.class);

	public static final byte weddingRelation = 4;
	private MyFriendManager myFriendManager;
	private CouplesController fuqi;
	private CharacterGoods addPropertyGood;
	private CharacterGoods quanpeiGood;
	private Map<Integer, Long> applyWedingMap = new ConcurrentHashMap<Integer, Long>();
	private Map<Integer, Long> endWedingMap = new ConcurrentHashMap<Integer, Long>();
	private AcrossEtc acrossWedderAe;

	public RoleWedingManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public void init() {
		if (Options.IsCrossServ) {
			AcrossEtc ae = myFriendManager.getCharacter().getCharacterAcrossServerManager().getAce();
			if (ae.getOldWedderId() > 0) {
				this.acrossWedderAe = ae;
			}
			return;
		}
		fuqi = CouplesManager.getInstance().getCouplesController(myFriendManager.getCharacter());
	}

	/**
	 * 角色上线 但还未进入场景之前调用
	 */
	public void onlineInit() {
		try {
			init();
			if (Options.IsCrossServ) {
				createCharacterGoodsPropertyOnAcrossServer();
				return;
			}
			createCharacterGoodsProperty();
			if (!isWedding()) {
				return;
			}
			myFriendManager.getCharacter().getCharacterSkillManager().fuqiSkillReload();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 角色上线进入场景之后调用
	 */
	public void online() {
		if (Options.IsCrossServ) {
			return;
		}
		if (!isWedding()) {
			return;
		}

		this.fuqi.onlineUpdate(myFriendManager.getCharacter());
	}

	/**
	 * 夫妻间关系发生变化时调用此方法
	 */
	public void updateFuqiInfo() {
		createCharacterGoodsProperty();
		Hero character = myFriendManager.getCharacter();
		character.getCharacterSkillManager().fuqiSkillReload();
		character.getEffectController().getSpouseEffectManager().teamReload(character.getMyTeamManager().getMyTeam());
		character.getMyDazuoManager().updateShuangxiuBuffer();
		character.sendMsg(new EnterSceneRoleSelfInfo10022(character, character.getX(), character.getY()));
		character.getDanTianController().updateFloatIco();
	}

	public CharacterGoods getAddPropertyGood() {
		return addPropertyGood;
	}

	public void setAddPropertyGood(CharacterGoods addPropertyGood) {
		this.addPropertyGood = addPropertyGood;
	}

	private void createCharacterGoodsPropertyOnAcrossServer() {
		if (addPropertyGood != null) {
			myFriendManager.getCharacter().getPropertyAdditionController().removeChangeListener(addPropertyGood);
			addPropertyGood = null;
		}
		if (!isWedding()) {
			return;
		}
		int banpeiId = 0;
		WeddingRing wr = acrossWedderAe.getWedderRing();
		String maleName = "";
		String femaleName = "";
		if (myFriendManager.getCharacter().isMale()) {
			banpeiId = wr.getMaleGood();
			maleName = myFriendManager.getCharacter().getName();
			femaleName = acrossWedderAe.getOldWedderName();
		} else {
			banpeiId = wr.getFemaleGood();
			femaleName = myFriendManager.getCharacter().getName();
			maleName = acrossWedderAe.getOldWedderName();
		}
		Goodmodel banpeiGm = GoodmodelManager.getInstance().get(banpeiId);
		addPropertyGood = CharacterGoods.createCharacterGoods(1, banpeiGm, 0, 0);
		addPropertyGood.setCharacterId(myFriendManager.getCharacter().getId());
		addPropertyGood.setMaleName(maleName);
		addPropertyGood.setFemaleName(femaleName);
		addPropertyGood.setCoupleDate(acrossWedderAe.getWedderTime());
		addPropertyGood.setPosition(banpeiGm.getPosition().shortValue());
		myFriendManager.getCharacter().getPropertyAdditionController().addChangeListener(addPropertyGood);

		Goodmodel quanGm = GoodmodelManager.getInstance().get(wr.getRingId());
		quanpeiGood = CharacterGoods.createCharacterGoods(1, wr.getRingId(), 0);
		quanpeiGood.setMaleName(maleName);
		quanpeiGood.setFemaleName(femaleName);
		quanpeiGood.setCoupleDate(acrossWedderAe.getWedderTime());
		quanpeiGood.setPosition(quanGm.getPosition().shortValue());
		quanpeiGood.setCharacterId(myFriendManager.getCharacter().getId());
	}

	private void createCharacterGoodsProperty() {
		if (addPropertyGood != null) {
			myFriendManager.getCharacter().getPropertyAdditionController().removeChangeListener(addPropertyGood);
			addPropertyGood = null;
		}
		if (!isWedding()) {
			return;
		}
		int banpeiId = 0;
		Couples couples = fuqi.getCouples();
		WeddingRing wr = couples.getWr();
		if (myFriendManager.getCharacter().isMale()) {
			banpeiId = wr.getMaleGood();
		} else {
			banpeiId = wr.getFemaleGood();
		}
		Goodmodel banpeiGm = GoodmodelManager.getInstance().get(banpeiId);
		addPropertyGood = CharacterGoods.createCharacterGoods(1, banpeiGm, 0, 0);
		addPropertyGood.setCharacterId(myFriendManager.getCharacter().getId());
		addPropertyGood.setMaleName(couples.getMaleCce().getName());
		addPropertyGood.setFemaleName(couples.getFemaleCce().getName());
		addPropertyGood.setCoupleDate(couples.getWeddingDate());
		addPropertyGood.setPosition(banpeiGm.getPosition().shortValue());
		myFriendManager.getCharacter().getPropertyAdditionController().addChangeListener(addPropertyGood);

		Goodmodel quanGm = GoodmodelManager.getInstance().get(wr.getRingId());
		quanpeiGood = CharacterGoods.createCharacterGoods(1, wr.getRingId(), 0);
		quanpeiGood.setMaleName(couples.getMaleCce().getName());
		quanpeiGood.setFemaleName(couples.getFemaleCce().getName());
		quanpeiGood.setPosition(quanGm.getPosition().shortValue());
		quanpeiGood.setCoupleDate(couples.getWeddingDate());
		quanpeiGood.setCharacterId(myFriendManager.getCharacter().getId());
	}

	public CharacterGoods getQuanpeiGood() {
		return quanpeiGood;
	}

	public void setQuanpeiGood(CharacterGoods quanpeiGood) {
		this.quanpeiGood = quanpeiGood;
	}

	public CouplesController getFuqi() {
		return fuqi;
	}

	public void setFuqi(CouplesController fuqi) {
		this.fuqi = fuqi;
	}

	/**
	 * 获取当前婚佩
	 * 
	 * @return
	 */
	public WeddingRing getFuqiWeddingRing() {
		if (!isWedding()) {
			return null;
		}
		if (Options.IsCrossServ) {
			return acrossWedderAe.getWedderRing();
		}
		return this.fuqi.getCouples().getWr();
	}

	/**
	 * 是否已经结婚 true 表示是
	 * 
	 * @return
	 */
	public boolean isWedding() {
		if (Options.IsCrossServ) {
			if (acrossWedderAe != null) {
				return true;
			} else {
				return false;
			}
		}
		if (this.fuqi == null) {
			return false;
		}
		if (this.fuqi.getCouples() == null) {
			return false;
		}
		return true;
	}

	/**
	 * 是否与某人是伴侣关系
	 * 
	 * @param wedderId
	 * @return
	 */
	public boolean isWeddingWith(int wedderId) {
		if (Options.IsCrossServ) {
			Hero wedder = myFriendManager.getCharacter().getVlineserver().getOnlineManager().getByID(wedderId);
			if (wedder == null) {
				return false;
			}
			AcrossEtc ae = wedder.getCharacterAcrossServerManager().getAce();
			int wedderServerid = ae.getOldAreaId();
			int wedderOldId = ae.getOldCharacterId();
			if (acrossWedderAe.getOldWedderAreaId() == wedderServerid && wedderOldId == ae.getOldWedderId()) {
				return true;
			}
			return false;
		}
		if (this.fuqi == null) {
			return false;
		}
		Couples c = this.fuqi.getCouples();
		if (c == null) {
			return false;
		}
		if (myFriendManager.getCharacter().isMale()) {
			if (c.getFemaleId() == wedderId) {
				return true;
			}
		} else {
			if (c.getMaleId() == wedderId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取配偶id
	 * 
	 * @return
	 */
	public int getWedderId() {
		if (this.fuqi == null) {
			return 0;
		}
		Couples c = this.fuqi.getCouples();
		if (c == null) {
			return 0;
		}
		if (myFriendManager.getCharacter().isMale()) {
			return c.getFemaleId();
		} else {
			return c.getMaleId();
		}
	}

	/**
	 * 获取我对配偶的好感度
	 * 
	 * @return
	 */
	public int getPeiouFavor() {
		if (this.fuqi == null) {
			return 0;
		}
		Couples c = this.fuqi.getCouples();
		if (c == null) {
			return 0;
		}
		if (myFriendManager.getCharacter().isMale()) {
			return c.getMaleFavor();
		} else {
			return c.getFemaleFavor();
		}
	}

	/**
	 * 转发求婚请求
	 * 
	 * @param wr
	 */
	public void onRequestWedding(Hero appllyer, WeddingRing wr) {
		if (!isMayAppllyWeddingOnTime(appllyer.getId())) {
			appllyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17507));
			return;
		}
		myFriendManager.getCharacter().sendMsg(new WeddingMsg52226(appllyer, wr));
	}

	/**
	 * 转发协议离婚请求
	 * 
	 * @param wr
	 */
	public void onRequestEndWedding(Hero appllyer) {
		if (!isMayEndWeddingOnTime(appllyer.getId())) {
			appllyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17542));
			return;
		}
		myFriendManager.getCharacter().sendMsg(new WeddingMsg52308());
	}

	/**
	 * 验证协议离婚申请是否频繁（true 表示不频繁可以发送，false 表示频繁 忽略对方请求）
	 * 
	 * @return
	 */
	public boolean isMayEndWeddingOnTime(int inviteId) {
		long end = System.currentTimeMillis();
		Long intviteteamtim = applyWedingMap.get(inviteId);
		if (intviteteamtim == null) {
			endWedingMap.put(inviteId, end);
			return true;
		}
		long time = end - intviteteamtim;
		if (time > 32000) {
			endWedingMap.put(inviteId, end);
			return true;
		}
		return false;
	}

	/**
	 * 返回true 表示此人是要求离婚
	 * 
	 * @param appId
	 * @return
	 */
	public boolean isHaveEndWedding(int appId) {
		Long apptime = endWedingMap.get(appId);
		if (apptime == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 清楚玩家协议离婚请求
	 * 
	 * @param weddId
	 */
	public void clearEndWeddinglog(int weddId) {
		applyWedingMap.remove(weddId);

	}

	/**
	 * 验证求婚申请是否频繁（true 表示不频繁可以发送，false 表示频繁 忽略对方请求）
	 * 
	 * @return
	 */
	public boolean isMayAppllyWeddingOnTime(int inviteId) {
		long end = System.currentTimeMillis();
		Long intviteteamtim = applyWedingMap.get(inviteId);
		if (intviteteamtim == null) {
			applyWedingMap.put(inviteId, end);
			return true;
		}
		long time = end - intviteteamtim;
		if (time > 32000) {
			applyWedingMap.put(inviteId, end);
			return true;
		}
		return false;
	}

	/**
	 * 返回true 表示此人是求婚者
	 * 
	 * @param appId
	 * @return
	 */
	public boolean isHaveApplyWedding(int appId) {
		Long apptime = applyWedingMap.get(appId);
		if (apptime == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param weddId
	 */
	public void clearApplyWeddinglog(int weddId) {
		applyWedingMap.remove(weddId);

	}

	/**
	 * 与某人建立夫妻关系
	 * 
	 * @param couples
	 * @param wedder
	 * @param wr
	 */
	public void weddingSuccess(CouplesController cc, Hero wedder, WeddingRing wr) {
		Couples couples = cc.getCouples();
		this.fuqi = cc;
		CharacterFriend cf = myFriendManager.getRoleFriendManager().deleteFriend(wedder.getId());
		if (myFriendManager.getCharacter().isMale()) {
			couples.setMaleId(myFriendManager.getCharacter().getId());
			if (cf != null) {
				couples.setMaleFavor(cf.getFavor());
			} else {
				couples.setMaleFavor(0);
			}
		} else {
			couples.setFemaleId(myFriendManager.getCharacter().getId());
			if (cf != null) {
				couples.setFemaleFavor(cf.getFavor());
			} else {
				couples.setFemaleFavor(0);
			}
		}
		myFriendManager.getRoleBlackListManager().deleteBlackList(wedder.getId());
		myFriendManager.getRoleEnemyManager().deleteEmeny(wedder.getId());
		myFriendManager.getCharacter().sendMsg(new WeddingMsg52228(wedder));
		myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17514, wedder.getViewName()));
	}

	/**
	 * 完成结婚流程
	 * 
	 * @param wr
	 */
	public void finishiWedding(Hero wedder, WeddingRing wr) {
		boolean b = CouplesManager.getInstance().createCouples(myFriendManager.getCharacter(), wedder, wr);
		if (!b) {
			return;
		}
		updateFuqiInfo();
		wedder.getMyFriendManager().getRoleWedingManager().updateFuqiInfo();
		myFriendManager.getRoleCouplesSpeakManager().init();
		wedder.getMyFriendManager().getRoleCouplesSpeakManager().init();
		myFriendManager.sendMyInfoAndFuqi();
		wedder.getMyFriendManager().sendMyInfoAndFuqi();
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 17513, myFriendManager.getCharacter().getViewName(), wedder
				.getViewName()));
		myFriendManager.getCharacter().getMyCharacterAchieveCountManger().getFriendCount().weddingFirstCount(1);
		wedder.getMyCharacterAchieveCountManger().getFriendCount().weddingFirstCount(1);
		this.fuqi.updateFuqiFavorCount();
	}

	/**
	 * 升级玉佩
	 * 
	 * @param wr
	 */
	public void changeWeddingRing(WeddingRing wr) {
		if (!isWedding()) {
			return;
		}
		int count = myFriendManager.getCharacter().getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(wr.getRingId());
		if (count < 1) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17574, myFriendManager.getCharacter().getViewName()));
			return;
		}
		this.fuqi.upGradeRing(myFriendManager.getCharacter(), wr);
	}

	/**
	 * 离婚操作
	 */
	public void overWedding(boolean isQiangzhi) {
		fuqi.overWedding(myFriendManager.getCharacter(), isQiangzhi);
	}

	/**
	 * 结束婚姻 初始化数据
	 */
	public void onEndWedding(int otherId) {
		myFriendManager.sendMsgToCharacter(new WeddingEndSuccessMsg52306(otherId));
		this.setFuqi(null);
		updateFuqiInfo();
		myFriendManager.sendMyInfoAndFuqi();
		myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17576));

	}

	/**
	 * 更新好感度
	 */
	public void updateFavorWhenTeam() {
		Hero character = myFriendManager.getCharacter();
		if (!isWedding()) {
			return;
		}
		if (!character.getMyTeamManager().isTeam()) {
			return;
		}
		int scenId = character.getScene();
		int wedderId = getWedderId();
		Hero wedder = character.getMyTeamManager().getMyTeam().getCharacter(wedderId);
		if (wedder == null) {
			return;
		}
		if (wedder.getScene() == scenId && DistanceFormula.getDistanceRound(character.getX(), character.getY(), wedder.getX(), wedder.getY()) < 20) {
			fuqi.updateTeamFavor(character, 1);
		}
	}

	/**
	 * 
	 */
	public void downline() {
		if (Options.IsCrossServ) {
			return;
		}
		if (!isWedding()) {
			return;
		}
		this.fuqi.downlinUpdate(myFriendManager.getCharacter());
	}

	/**
	 * 获取配偶名字
	 * 
	 * @return
	 */
	public String getWedderName() {
		if (Options.IsCrossServ) {
			if (acrossWedderAe != null) {
				return acrossWedderAe.getWedderViewName();
			}
			return "";
		}
		if (this.fuqi == null) {
			return "";
		}
		Couples c = this.fuqi.getCouples();
		if (c == null) {
			return "";
		}
		if (myFriendManager.getCharacter().isMale()) {
			return c.getFemaleName();
		} else {
			return c.getMaleName();
		}
	}

	/**
	 * 获取配偶门派
	 * 
	 * @return
	 */
	public int getWedderMenPai() {
		if (!isWedding()) {
			return -1;
		}
		if (Options.IsCrossServ) {
			if (acrossWedderAe != null) {
				return (int) acrossWedderAe.getOldWedderMenpai();
			}
			return -1;
		}
		CharacterCacheEntry cce = getWedder();
		if (cce != null) {
			return cce.getPopsinger();
		}
		return -1;
	}

	/**
	 * 获取配偶丹田等级
	 * 
	 * @return
	 */
	public int getWedderDanTian() {
		if (!isWedding()) {
			return -1;
		}
		if (Options.IsCrossServ) {
			if (acrossWedderAe != null) {
				return (int) acrossWedderAe.getOldWedderDantian();
			}
			return -1;
		}
		CharacterCacheEntry cce = getWedder();
		if (cce != null) {
			return cce.getDantiangrade();
		}
		return -1;
	}

	/**
	 * 获取配偶(一般在协议中不要轻易调用此方法 ，如果调用需要考虑跨服情况)
	 * 
	 * @return
	 */
	public CharacterCacheEntry getWedder() {
		if (this.fuqi == null) {
			return null;
		}
		Couples c = this.fuqi.getCouples();
		if (c == null) {
			return null;
		}
		if (myFriendManager.getCharacter().isMale()) {
			return c.getFemaleCce();
		} else {
			return c.getMaleCce();
		}
	}
}
