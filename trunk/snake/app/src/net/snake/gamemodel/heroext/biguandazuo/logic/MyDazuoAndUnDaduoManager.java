package net.snake.gamemodel.heroext.biguandazuo.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.api.IStateListener;
import net.snake.commons.TimeExpression;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.MaxLimit;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fabao.response.RideFabaoResponse;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.heroext.biguandazuo.response.DazuoBoardMsg50520;
import net.snake.gamemodel.heroext.biguandazuo.response.DazuoXinfaMsg50632;
import net.snake.gamemodel.heroext.biguandazuo.response.LiaoshangBoardMsg50522;
import net.snake.gamemodel.heroext.biguandazuo.response.ShuangxiuMsg50512;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 打坐管理以及非打坐状态回血蓝管理器
 * 
 * 
 */
public class MyDazuoAndUnDaduoManager {
	private static Logger logger = Logger.getLogger(MyDazuoAndUnDaduoManager.class);
	public EffectInfo ef;
	/** 与玩家双休距离 */
	public final static int ROLE_SHUANGXIU_LANG = 3; //
	public final static byte dazuoState = 1;
	public final static int DAZUO_ADD_EXP = 10;
	public final static int DAZUO_ADD_ZHENQI = 2;
	public final static int DAZUO_ADD_HP = 150;
	public final static int DAZUO_ADD_MP = 60;
	public final static int DAZUO_ADD_SP = 30;
	public final static int UNDAZUO_ADD_HP = 75;
	public final static int UNDAZUO_ADD_MP = 30;
	public final static int UNDAZUO_ADD_SP = 15;
	public boolean isXinFa = false;
	private long idleStartTime = System.currentTimeMillis();
	/** 打坐经验当前加成时间 */
	public long dazuotime; //
	/** 获得收益（经验真气）当前打坐获得加成次数 */
	/** 是否处于打坐状态 */
	private boolean isDazuo = false; //
	private Hero character;
	/** 打坐经验计算间隔时间 */
	private int jiangeTime = 20 * 1000; //
	private boolean isShuangXiu = false;
	/** 加成情况 （基数10000） */
	private int jiacheng = 10000; //
	public CharacterSkill cs;
	private static final int hpUpdateTime = 8 * 1000;
	private static final int mpUpdateTime = 3 * 1000;
	private long hpUpdateLastTime = 0;
	private long mpUpdateLastTime = 0;
	private static final float hpUpdatePreValue = 0.1f;
	private static final float mpUpdatePreValue = 0.1f;
	private Hero shuangXiu;
	/** 非打坐状态是否进行回复属性操作 */
	private boolean isUndazuoStart = false; //
	private Map<Integer, Long> shuanxiuInviteMap = new HashMap<Integer, Long>();// 组队发起列表
	private Map<Integer, Long> liaoshangInviteMap = new HashMap<Integer, Long>();// 组队发起列表

	public void destory() {
		if (shuanxiuInviteMap != null) {
			shuanxiuInviteMap.clear();
			shuanxiuInviteMap = null;
		}
		if (liaoshangInviteMap != null) {
			liaoshangInviteMap.clear();
			liaoshangInviteMap = null;
		}

	}

	public MyDazuoAndUnDaduoManager(Hero character) {
		this.character = character;
	}

	public void setXinFa(int skillId) {
		cs = character.getCharacterSkillManager().getCharacterSkillById(skillId);
		if (cs == null) {
			return;
		}
		character.setDazuoSkill(skillId);
		character.sendMsg(new DazuoXinfaMsg50632(skillId));
	}

	public void autoChangeDazuoState(long now) {
		if (Options.IsCrossServ) {
			return;
		}

		Scene scene = character.getSceneRef();
		if (scene != null) {
			List<IStateListener> listeners = scene.getStateListeners();
			int size = listeners.size();
			for (int i = 0; i < size; i++) {
				boolean goon = listeners.get(i).beforeNewState(character, VisibleObjectState.Dazuo);
				if (!goon) {
					return;
				}
			}
		}

		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			this.idleStartTime = now;
			return;
		}
		if (character.getObjectState() == VisibleObjectState.Attack) {
			return;
		}
		if (character.getObjectState() == VisibleObjectState.Idle) {
			if (now - idleStartTime < 80 * 1000) {
				return;
			}
			if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
				if (now - idleStartTime < 310 * 1000) {
					return;
				}
				character.getCharacterHorseController().unRide();
			}
			openDazuo();
		}
	}

	public void autoDazuoOrUndatduoUpate(long now) {
		autoChangeDazuoState(now);
		if (isDazuo()) {
			sendDazuoShouYi(now);
		} else {
			sendUnDazuoShoyi(now);
		}
	}

	public void init() {
		if (character.getDazuoSkill() != null && character.getDazuoSkill() >= 1) {
			cs = character.getCharacterSkillManager().getCharacterSkillById(character.getDazuoSkill());
		}
		if (character.getState() == dazuoState) {
			isDazuo = true;
			dazuotime = System.currentTimeMillis();
			character.setObjectState(VisibleObjectState.Dazuo);
		} else {
			this.idleStartTime = System.currentTimeMillis();
		}
	}

	/**
	 * 非打坐状态下属性恢复
	 */
	public void sendUnDazuoShoyi(long now) {
		if (isDazuo) {
			return;
		}
		if (character.getObjectState() == VisibleObjectState.Shock) {// 濒死状态下就甭恢复了
			return;
		}
		if (!isRenewHpMpSp() || character.getNowHp() <= 0) {// 不需要恢复
			isUndazuoStart = false;
			return;
		}
		if (!isUndazuoStart) {// 打开开关
			isUndazuoStart = true;
			return;
		}
		renewHpMp(now);
	}

	/**
	 * 打坐状态下属性恢复
	 * 
	 * @param nowTime
	 */
	public void sendDazuoShouYi(long nowTime) {
		if (!isDazuo) {
			return;
		}
		int time = (int) (nowTime - dazuotime);
		renewExpZhenqi(time);
		updateLiaoshang();
		if (!isRenewHpMpSp()) {
			return;
		}
		renewHpMp(nowTime);
	}

	/**
	 * 在非战斗状态下恢复hp、mp
	 * 
	 * @param time
	 */
	private void renewHpMp(long nowtime) {
		if (this.character.getObjectState() == VisibleObjectState.Attack || this.character.getObjectState() == VisibleObjectState.BeAttacked) {
			hpUpdateLastTime = 0;
			mpUpdateLastTime = 0;
			return;
		}
		if (hpUpdateLastTime == 0) {
			hpUpdateLastTime = nowtime;
		} else {
			int time = (int) (nowtime - hpUpdateLastTime);
			if (time >= hpUpdateTime) {
				hpUpdateLastTime = nowtime;
				int tmphp = (int) (hpUpdatePreValue * character.getPropertyAdditionController().getExtraMaxHp());
				CharacterPropertyManager.changeNowHp(character, tmphp);
				if (character.getNowHp() == character.getPropertyAdditionController().getExtraMaxHp()) {
					hpUpdateLastTime = 0;
				}
			}
		}

		if (mpUpdateLastTime == 0) {
			mpUpdateLastTime = nowtime;
		} else {
			int time = (int) (nowtime - mpUpdateLastTime);
			if (time >= mpUpdateTime) {

				mpUpdateLastTime = nowtime;
				int tmpmp = (int) (mpUpdatePreValue * character.getPropertyAdditionController().getExtraMaxMp());
				CharacterPropertyManager.changeNowMp(character, tmpmp);
				if (character.getNowMp() == character.getPropertyAdditionController().getExtraMaxMp()) {
					mpUpdateLastTime = 0;
				}
			}
		}
	}

	/**
	 * 打坐经验真气更新间隔时间 time 为本次打坐的时间总和
	 */
	private void renewExpZhenqi(int time) {
		int countTime = getDazuoJiageTime();
		if (time < countTime) {
			return;
		}
		int jiecount = 1;
		try {
			this.dazuotime = System.currentTimeMillis();
			int yuanshi_exp = jiecount * (DAZUO_ADD_EXP + character.getGrade());// 原始经验
			int xishu_exp = GameServer.doubActivityManager.getCacheDoubActivity().getDazuoExp();// 系数
			if (character.getEffectController().isDoubleExp()) {// 人物开启双倍经验活动是影响打坐的
				xishu_exp = xishu_exp + 1;
			}
			if (character.getMyCharacterVipManger().isVipYueka()) {
				xishu_exp = xishu_exp + 1;
			}
			int exp = xishu_exp * yuanshi_exp;
			exp = (int) (exp * character.getAntiAddictedSystemPlusScale());
			CharacterFormula.experienceProcess(character, exp);
			int yuanshi_zhenqi = jiecount * DAZUO_ADD_ZHENQI;// 原始经验
			if (isJiachengTime()) {
				yuanshi_zhenqi = jiecount * (DAZUO_ADD_ZHENQI + 1);// 原始经验
			}
			int xishu_zhenqi = GameServer.doubActivityManager.getCacheDoubActivity().getDazuoZhenqi();// 系数
			if (character.getEffectController().isDoubleZhenqi()) {// 人物开启双倍经验活动是影响打坐的
				xishu_zhenqi = xishu_zhenqi + 1;
			}
			if (character.getMyCharacterVipManger().isVipYueka()) {
				xishu_zhenqi = xishu_zhenqi + 1;
			}
			int zhenqi = xishu_zhenqi * yuanshi_zhenqi;
			zhenqi = (int) (zhenqi * character.getAntiAddictedSystemPlusScale());
			zhenqi = CharacterPropertyManager.changeZhenqi(character, zhenqi);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		character.getCharacterSkillManager().xinfaSkillUpMastery(cs);
	}

	public boolean isJiachengTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		if (hours < 8) {
			return true;
		}
		return false;
	}

	public int getDazuoJiageTime() {
		int countTime = jiangeTime * 10000 / jiacheng;
		countTime = countTime - character.getMyFactionManager().getDazuoJiachengTime();
		countTime = countTime - character.getMyFactionManager().xiangyangFactionDazuoJiacheng();
		if (isShuangXiu) {
			if (ef != null) {
				countTime = countTime - 1000;
			}
		}
		return countTime;
	}

	/**
	 * 是否恢复
	 * 
	 * @return
	 */
	private boolean isRenewHpMpSp() {
		if (character.getNowHp() >= character.getPropertyAdditionController().getExtraMaxHp() && character.getNowMp() >= character.getPropertyAdditionController().getExtraMaxMp()
				&& character.getNowSp() >= character.getPropertyAdditionController().getExtraMaxSp()) {
			return false;
		}
		return true;
	}

	public void closeDazuo() {
		if (!isDazuo) {
			return;
		}
		long now = System.currentTimeMillis();
		sendDazuoShouYi(now);
		if (isShuangXiu) {
			shuangXiu.getMyDazuoManager().endShuangXiuAndMsg();
			endShuangXiu();
		}
		endLiaoshang();
		dazuotime = now;
		isDazuo = false;
		character.setState((short) 0);
		this.idleStartTime = now;
		character.getEyeShotManager().sendMsg(new DazuoBoardMsg50520(character, null), null);
	}

	public byte getCharacterState() {
		if (isShuangXiu) {
			return 2;
		}
		if (isDazuo) {
			return 1;
		}
		return 0;
	}

	public void requestChangeDazuoState() {
		if (isDazuo) {
			closeDazuo();
			return;
		}
		openDazuoAndMsg();
	}

	/**
	 * 开启打坐失败 发送消息
	 */
	public void openDazuoAndMsg() {
		if (isDazuo) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 510));
			return;
		}
		int msg = openDazuo();
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
		}
	}

	private int openDazuo() {
		if (isDazuo) {
			return 0;
		}
		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			return 511;
		}
		if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
			return 512;
		}
		character.getCatchXuanyuanJianActionController().breakCatch();
		isDazuo = true;
		dazuotime = System.currentTimeMillis();
		character.setState((short) 1);
		character.setObjectState(VisibleObjectState.Dazuo);
		character.getEyeShotManager().sendMsg(new DazuoBoardMsg50520(character, null), null);
		return 0;
	}

	/**
	 * 挂机调用接口 安全回城并打坐
	 */
	public void autoHuichengDazuo() {
		try {
			CharacterResurrect.huichengFuhuo(character);
			// openDazuo();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private boolean ckeckMyShuangXiuCondition(Hero other) {
		if (other == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 513));
			return false;
		}
		int id = character.getId();
		if (id == other.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 514));
			return false;
		}
		if (isShuangXiu) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 515));
			return false;
		}
		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 511));
			return false;
		}
		if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 512));
			return false;

		}
		if (other.getMyDazuoManager().isShuangXiu()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 516));
			return false;
		}
		if (other.getCharacterOnHoorController().isAutoOnHoor()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 517));
			return false;
		}
		if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 518));
			return false;

		}
		if (DistanceFormula.getDistanceRound(character.getX(), character.getY(), other.getX(), other.getY()) > ROLE_SHUANGXIU_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 519));
			return false;
		}
		return true;
	}

	/**
	 * 向某人发送双休请求
	 * 
	 * @param other
	 */
	public void requestShuangXiuWith(Hero other) {
		if (!ckeckMyShuangXiuCondition(other)) {
			return;
		}
		if (other.getMyDazuoManager().isDazuo) {
			startShuangxiuWhith(other);
			return;
		}
		other.getMyDazuoManager().onRequestShuangXiuWith(character);
	}

	/**
	 * 接受某人双休请求
	 * 
	 * @param other
	 */
	public void onRequestShuangXiuWith(Hero other) {
		if (isShuangXiu) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 520));
			return;
		}
		if (!isInviteShuangxiu(other.getId())) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 521));
			return;
		}
		character.sendMsg(new ShuangxiuMsg50512(CommonUseNumber.byte1, other));
		other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 522));
	}

	public void accessShuangXiuWith(Hero other) {
		if (!ckeckMyShuangXiuCondition(other)) {
			return;
		}
		other.getMyDazuoManager().onAccessShuangxiuWith(character);
	}

	public void onAccessShuangxiuWith(Hero other) {
		if (isShuangXiu) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 523));
			return;
		}
		startShuangxiuWhith(other);
	}

	private void startShuangxiuWhith(Hero other) {
		CharacterGoods fabaogoods = character.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (fabaogoods != null && fabaogoods.getIsUse() == 1) {
			fabaogoods.setIsUse(CommonUseNumber.byte0);
			character.getEquipmentController().changeProperty(character, fabaogoods, TakeMethod.off);
			character.getEyeShotManager().sendMsg(new RideFabaoResponse(character.getId(), CommonUseNumber.byte0, fabaogoods.getGoodmodelId()));
		}
		CharacterGoods otherFabaogoods = other.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (otherFabaogoods != null && otherFabaogoods.getIsUse() == 1) {
			otherFabaogoods.setIsUse(CommonUseNumber.byte0);
			other.getEquipmentController().changeProperty(other, otherFabaogoods, TakeMethod.off);
			other.getEyeShotManager().sendMsg(new RideFabaoResponse(other.getId(), CommonUseNumber.byte0, otherFabaogoods.getGoodmodelId()));
		}
		startShuangXiu(other);
		other.getMyDazuoManager().startShuangXiu(character);
		other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 524));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 525));
	}

	/**
	 * 结束双方双休状态
	 * 
	 * @param other
	 */
	public void endShuangXiuWhenDownLine() {
		if (isShuangXiu) {
			shuangXiu.getMyDazuoManager().endShuangXiuAndMsg();
			endShuangXiu();
		}
		if (isDazuo) {
			character.setObjectState(VisibleObjectState.Idle);
			character.setState((short) 1);
		}
	}

	public void startShuangXiu(Hero other) {
		long now = System.currentTimeMillis();
		sendDazuoShouYi(now);
		int msg = openDazuo();
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		if (getShoushanger() != null) {
			endLiaoshang();
		}
		dazuotime = now;
		isShuangXiu = true;
		jiacheng = 12000;
		this.shuangXiu = other;
		character.getEyeShotManager().sendMsg(new DazuoBoardMsg50520(character, other), null);
		updateShuangxiuBuffer();
	}

	/**
	 * 更新双休buffer
	 */
	public void updateShuangxiuBuffer() {
		if (isShuangXiu) {
			if (!character.getMyFriendManager().getRoleWedingManager().isWeddingWith(shuangXiu.getId())) {
				if (ef != null) {
					try {
						FightMsgSender.sendCancelSustainEffectMsg(character, ef);
						this.ef = null;
						return;
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					ef = null;
				}
				return;
			}
			Skill skill = SkillManager.getInstance().getShuangXiuHeJiSkill();
			if (character.getGrade() < skill.getCharLevel()) {
				return;
			}
			SkillEffect se = skill.getEffect();
			if (ef != null) {
				return;
			}
			EffectInfo info = new EffectInfo(se);
			ef = info;
			FightMsgSender.broastSustainEffect(ef, character);
		} else {
			if (ef != null) {
				try {
					FightMsgSender.sendCancelSustainEffectMsg(character, ef);
					this.ef = null;
					return;
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

		}
	}

	public void endShuangXiuAndMsg() {
		if (!isShuangXiu) {
			return;
		}
		sendDazuoShouYi(System.currentTimeMillis());
		endShuangXiu();
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 526));
		character.getEyeShotManager().sendMsg(new DazuoBoardMsg50520(character, null), null);
	}

	public void endShuangXiu() {
		dazuotime = System.currentTimeMillis();
		isShuangXiu = false;
		jiacheng = 10000;
		this.shuangXiu = null;
		updateShuangxiuBuffer();
	}

	public long getDazuotime() {
		return dazuotime;
	}

	public void setDazuotime(long dazuotime) {
		this.dazuotime = dazuotime;
	}

	public boolean isDazuo() {
		return isDazuo;
	}

	// public void setDazuo(boolean isDazuo) {
	// this.isDazuo = isDazuo;
	// }

	public Hero getCharacter() {
		return character;
	}

	public void setCharacter(Hero character) {
		this.character = character;
	}

	public Hero getShuangXiu() {
		return shuangXiu;
	}

	public void setShuangXiu(Hero shuangXiu) {
		this.shuangXiu = shuangXiu;
	}

	public long getIdleStartTime() {
		return idleStartTime;
	}

	public void setIdleStartTime(long idleStartTime) {
		this.idleStartTime = idleStartTime;
	}

	public void updateIdelTime() {
		this.idleStartTime = System.currentTimeMillis();

	}

	/**
	 * 接受邀请消息间隔 true表示可以接受
	 * 
	 * @return
	 */
	public boolean isInviteShuangxiu(int inviteId) {
		long end = System.currentTimeMillis();
		Long intviteteamtim = shuanxiuInviteMap.get(inviteId);
		if (intviteteamtim == null) {
			shuanxiuInviteMap.put(inviteId, end);
			return true;
		}
		long time = end - intviteteamtim;
		if (time > 32000) {
			shuanxiuInviteMap.put(inviteId, end);
			return true;
		}
		return false;
	}

	public void removeIniviteShuangxiuTime(int inviteId) {
		this.shuanxiuInviteMap.remove(inviteId);
	}

	public boolean isShuangXiu() {
		return isShuangXiu;
	}

	public void setShuangXiu(boolean isShuangXiu) {
		this.isShuangXiu = isShuangXiu;
	}

	// 疗伤
	private Hero liaoshanger;
	private Hero shoushanger;
	public long liaoshangTime;
	public static int LiaoshangJiageTime = 10 * 1000;

	public Hero getLiaoshanger() {
		return liaoshanger;
	}

	public void setLiaoshanger(Hero liaoshanger) {
		this.liaoshanger = liaoshanger;
	}

	public Hero getShoushanger() {
		return shoushanger;
	}

	public void setShoushanger(Hero shoushanger) {
		this.shoushanger = shoushanger;
	}

	public void updateLiaoshang() {
		if (liaoshanger == null) {
			return;
		}
		long time = System.currentTimeMillis() - liaoshangTime;
		if (time < LiaoshangJiageTime) {
			return;
		}
		liaoshangTime = System.currentTimeMillis();
		String shoushang = character.getMyChannelManager().getFailChannelId();
		if (shoushang == null || shoushang.length() == 0) {
			endLiaoshang();
			return;
		}
		Hero liao = liaoshanger;
		String[] jingmais = shoushang.split(",");
		int debuffId = character.getMyChannelManager().getChannelBebuffId(jingmais[0]);
		boolean b = character.getEffectController().removeBuffById(debuffId);
		if (b) {
			int grade = liao.getGrade();
			try {
				if (liao.getTodayLiaoshangTime() != null) {
					if (!TimeExpression.isToday(liao.getTodayLiaoshangTime())) {
						liao.setTodayLiaoshangTime(new Date());
						liao.setTodayLiaoshangExp(0);
					}
				} else {
					liao.setTodayLiaoshangTime(new Date());
				}
				long exp = MaxLimit.LiaoShang_Exp - liao.getTodayLiaoshangExp();
				long gainExp = getLiaoshangExp(grade);
				if (exp > 0) {
					if (gainExp > exp) {
						gainExp = exp;
					}
					liao.setTodayLiaoshangExp(liao.getTodayLiaoshangExp() + gainExp);
					CharacterFormula.experienceProcess(liao, gainExp);
				} else {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20166));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private long getLiaoshangExp(int grade) {
		return grade * grade * 100;
	}

	public void endLiaoshang() {
		if (this.liaoshanger != null) {
			this.liaoshanger.getMyDazuoManager().clearLiaoshangInfo();
			clearLiaoshangInfo();
		}
		if (this.shoushanger != null) {
			this.shoushanger.getMyDazuoManager().clearLiaoshangInfo();
			clearLiaoshangInfo();
		}
	}

	public void clearLiaoshangInfo() {
		this.liaoshanger = null;
		this.shoushanger = null;
		character.getEyeShotManager().sendMsg(new LiaoshangBoardMsg50522(character, null));
	}

	/**
	 * 向某人发送疗伤请求
	 * 
	 * @param hisCharacter
	 */
	public void requestLiaoshanWith(Hero hisCharacter) {
		boolean b = ckeckMyLiaoshanCondition(hisCharacter);
		if (!b) {
			return;
		}
		if (hisCharacter.getMyDazuoManager().isDazuo) {
			startLiaoshangBySender(hisCharacter);
			return;
		}
		hisCharacter.getMyDazuoManager().onRequestLiaoshanWith(character);
	}

	/**
	 * 接受某人双休请求
	 * 
	 * @param other
	 */
	public void onRequestLiaoshanWith(Hero other) {

		if (!isInviteLiaoshang(other.getId())) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1400));
			return;
		}
		other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1400));
		character.sendMsg(new ShuangxiuMsg50512((byte) 2, other));
	}

	private boolean isInviteLiaoshang(Integer inviteId) {
		long end = System.currentTimeMillis();
		Long intviteteamtim = liaoshangInviteMap.get(inviteId);
		if (intviteteamtim == null) {
			liaoshangInviteMap.put(inviteId, end);
			return true;
		}
		long time = end - intviteteamtim;
		if (time > 32000) {
			liaoshangInviteMap.put(inviteId, end);
			return true;
		}
		return false;
	}

	/**
	 * 开始进入疗伤状态（此方法发起了伤者调用（受伤者不可调用））
	 * 
	 * @param hisCharacter
	 */
	public void startLiaoshangBySender(Hero shousher) {
		startMeLiaoshanOther(shousher);
		shousher.getMyDazuoManager().startOtherLiaoshangMe(character);
	}

	public void startMeLiaoshanOther(Hero shousher) {
		if (isShuangXiu) {
			shuangXiu.getMyDazuoManager().endShuangXiuAndMsg();
			endShuangXiuAndMsg();
		}
		if (!isDazuo) {
			openDazuo();
		}
		this.shoushanger = shousher;
		this.liaoshangTime = System.currentTimeMillis();
		character.getEyeShotManager().sendMsg(new LiaoshangBoardMsg50522(character, shousher));
	}

	public void startOtherLiaoshangMe(Hero liaoshanger) {
		if (!isDazuo) {
			openDazuo();
		}
		this.liaoshanger = liaoshanger;
		this.liaoshangTime = System.currentTimeMillis();
		character.getEyeShotManager().sendMsg(new LiaoshangBoardMsg50522(liaoshanger, character));
	}

	private boolean ckeckMyLiaoshanCondition(Hero other) {
		if (other == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1401));
			return false;
		}
		int id = character.getId();
		if (id == other.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1402));
			return false;
		}
		String shouShang = other.getMyChannelManager().getFailChannelId();
		if (shouShang == null || shouShang.length() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1403));
			return false;
		}
		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1404));
			return false;
		}
		if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1405));
			return false;

		}
		if (other.getCharacterHorseController().getCurrentRideHorse() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1406));
			return false;
		}
		if (DistanceFormula.getDistanceRound(character.getX(), character.getY(), other.getX(), other.getY()) > ROLE_SHUANGXIU_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1407));
			return false;
		}
		if (getLiaoshanger() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1408));
			return false;
		}
		if (getShoushanger() != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1409));
			return false;
		}
		if (other.getMyDazuoManager().isLiaoshangState()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1410));
			return false;
		}
		return true;
	}

	private boolean ckeckMyShoushangCondition(Hero other) {
		if (other == null) {
			return false;
		}
		int id = character.getId();
		if (id == other.getId()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1402));
			return false;
		}
		if (character.getCharacterOnHoorController().isAutoOnHoor()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1411));
			return false;
		}
		if (character.getCharacterHorseController().getCurrentRideHorse() != null) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1412));
			return false;

		}
		if (other.getCharacterHorseController().getCurrentRideHorse() != null) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1413));
			return false;
		}
		if (DistanceFormula.getDistanceRound(character.getX(), character.getY(), other.getX(), other.getY()) > ROLE_SHUANGXIU_LANG) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1414));
			return false;
		}
		if (isLiaoshangState()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1415));
			return false;
		}
		if (other.getMyDazuoManager().isLiaoshangState()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1416));
			return false;
		}
		return true;
	}

	public boolean isLiaoshangState() {
		if (this.liaoshanger != null) {
			return true;
		}
		if (this.shoushanger != null) {
			return true;
		}
		return false;
	}

	/**
	 * 接受疗伤
	 * 
	 * @param other
	 */
	public void accessLiaoshangWith(Hero other) {
		boolean b = ckeckMyShoushangCondition(other);
		if (!b) {
			return;
		}
		other.getMyDazuoManager().startLiaoshangBySender(character);
	}

	public void removeIniviteLiaoshangTime(int characterId) {
		this.liaoshangInviteMap.remove(characterId);
	}
}
