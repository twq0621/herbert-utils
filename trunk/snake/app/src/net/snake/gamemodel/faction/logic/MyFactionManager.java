package net.snake.gamemodel.faction.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.across.faction.AcrossFactionContoller;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.Language;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.bean.FactionCityConfig;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.bean.FactionGradeComparator;
import net.snake.gamemodel.faction.bean.FcByPositionComparator;
import net.snake.gamemodel.faction.persistence.FactionCharacterManager;
import net.snake.gamemodel.faction.persistence.FactionCityConfigManager;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.faction.persistence.GongchengDateManager;
import net.snake.gamemodel.faction.response.FactionApplyMsg51040;
import net.snake.gamemodel.faction.response.FactionApplyMsg51042;
import net.snake.gamemodel.faction.response.FactionBoardMsg51068;
import net.snake.gamemodel.faction.response.FactionContributeCopperMsg51092;
import net.snake.gamemodel.faction.response.FactionContributeGoodsMsg51094;
import net.snake.gamemodel.faction.response.FactionCreateMsg51038;
import net.snake.gamemodel.faction.response.FactionFcNameMsg51074;
import net.snake.gamemodel.faction.response.FactionFlagEnterSceneMsg51096;
import net.snake.gamemodel.faction.response.FactionFlagIcoChangeMsg51088;
import net.snake.gamemodel.faction.response.FactionFlagNameChangeMsg51086;
import net.snake.gamemodel.faction.response.FactionFlagUpGradeMsg51090;
import net.snake.gamemodel.faction.response.FactionInviteMsg51044;
import net.snake.gamemodel.faction.response.FactionKickMsg51050;
import net.snake.gamemodel.faction.response.FactionLeaveMsg51058;
import net.snake.gamemodel.faction.response.FactionListMsg51032;
import net.snake.gamemodel.faction.response.FactionMsg51072;
import net.snake.gamemodel.faction.response.FactionMyInfoMsg51036;
import net.snake.gamemodel.faction.response.FactionNoticeMsg51076;
import net.snake.gamemodel.faction.response.FactionOtherInfoMsg51034;
import net.snake.gamemodel.faction.response.factioncity.FactionCity51110;
import net.snake.gamemodel.faction.response.factioncity.FactionCity51114;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51112;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51144;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.response.hero.AvatarChange60000;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;



/**
 * 角色帮会逻辑管理器
 * 
 * @author serv_dev
 * 
 */
public class MyFactionManager implements PropertyChangeListener {
	/**
	 * 帮会发起列表<br>
	 * key值为角色id,记入当前角色邀请过的玩家入帮<br>
	 * 
	 */
	private Map<Integer, Long> inviteMap = new HashMap<Integer, Long>();//

	/**
	 * 帮会发起列表<br>
	 * key值为帮会id,记入了但千角色申请过的帮会
	 */
	private Map<Integer, Long> applyMap = new HashMap<Integer, Long>();

	private static Logger logger = Logger.getLogger(MyFactionManager.class);
	// public static int youlongBufferId=3103;
	public static long infactionTimeMin = 5 * 24 * 60 * 60 * 1000;
	private FactionController factionController;
	private Hero character;
	/** true 准备开始进入帮会或创建帮会中 */
	private boolean startIntoFaction = false; //
	/** 是否自动同意对方的帮会邀请 */
	private boolean accessInviteFaction = true; //
	public int acrossFactionposition = 0;
	private AcrossFactionContoller acrossFactionController;

	public AcrossFactionContoller getAcrossFactionController() {
		return acrossFactionController;
	}

	public void setAcrossFactionController(AcrossFactionContoller acrossFactionController) {
		this.acrossFactionController = acrossFactionController;
	}

	public void destory() {
		inviteMap.clear();
		applyMap.clear();
		inviteMap = null;
		applyMap = null;
		factionController = null;
	}

	/**
	 * 判断其他角色是否与我同帮会
	 * 
	 * @param otherId
	 * @return
	 */
	public boolean isInSameFactionWith(Hero other) {
		if (Options.IsCrossServ) {
			int otherFactionID = other.getMyFactionManager().getFactionId();
			if (otherFactionID != getFactionId()) {
				return false;
			} else {
				return true;
			}
		}
		if (!isFaction()) {
			return false;
		}
		if (other == null || !other.getMyFactionManager().isFaction()) {
			return false;
		}
		int otherFactionId = other.getMyFactionManager().getFactionId();
		if (otherFactionId == getFactionId()) {
			return true;
		}
		return false;
	}

	public MyFactionManager(Hero character2) {
		this.character = character2;
	}

	public FactionController getFactionController() {
		return factionController;
	}

	/**
	 * 帮会信息初始化
	 */
	public void initInAcross(AcrossFactionContoller afc) {
		if (Options.IsCrossServ) {
			this.acrossFactionController = afc;
			return;
		}

	}

	/**
	 * 帮会信息初始化
	 */
	public void init() {
		try {
			if (Options.IsCrossServ) {
				AcrossEtc ae = character.getCharacterAcrossServerManager().getAce();
				if (ae == null) {
					return;
				}
				acrossFactionposition = ae.getIsBangzhu();
				character.getVlineserver().getAcrossFactionManager().addCharacterToFaction(character);
				character.getMycharacterAcrossZhengzuoManager().init();
				return;
			}
			FactionCharacter fc = FactionCharacterManager.getInstance().getFc(character.getId());
			if (fc == null) {
				return;
			}
			this.factionController = FactionManager.getInstance().getFactionControllerByFactionID(fc.getFactionId());
			if (this.factionController != null) {
				FactionCharacter fcs = this.factionController.getFactionCharacterByCharacterId(character.getId());
				character.getMyFactionCityZhengDuoManager().init();
				if (fcs == null) {
					this.factionController = null;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 帮会信息初始化
	 */
	public void init(FactionController factionC) {
		this.factionController = factionC;
		this.factionController.updateFactionJiacheng(character);
	}

	/**
	 * 玩家上线
	 */
	public void online() {
		if (Options.IsCrossServ) {
			// character.sendMsg(new FactionCtMsg51140(null, null));
			AcrossEtc ae = character.getCharacterAcrossServerManager().getAce();
			if (ae == null) {
				return;
			}
			if (acrossFactionController != null) {
				acrossFactionController.updateFactionJiacheng(character);
			}
			if (ae.getIsChengzhu() > 0) {
				takeOnYoulongInKuafuzhan();
			}
			return;
		}
		CharacterCacheEntry cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(character.getId());
		if (cce != null) {
			cce.setIsOnline(CommonUseNumber.byte1);
		}
		// FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		// character.sendMsg(new FactionCtMsg51130());
		// character.sendMsg(new FactionCtMsg51140(factionCity, null));
		// character.sendMsg(new FactionCtMsg51142(factionCity));
		if (this.factionController == null) {
			return;
		}
		factionController.updateFcOnlineState(character, CommonUseNumber.byte1);
		factionController.updateFactionJiacheng(character);
		addFactionCityBuffer();
		takeOnYoulong();
		FactionCharacter fcs = this.factionController.getFactionCharacterByCharacterId(character.getId());
		if (fcs == null) {
			return;
		}
		character.getMyCharacterAchieveCountManger().getFactionCount().factionContributionCount(fcs.getContribution());
	}

	/**
	 * 玩家下线
	 */
	public void downline() {
		if (Options.IsCrossServ) {
			if (acrossFactionController != null) {
				acrossFactionController.downLine(character);
			}

		}
		CharacterCacheEntry cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(character.getId());
		if (cce != null) {
			cce.setIsOnline(CommonUseNumber.byte0);
		}
		if (this.factionController == null) {
			return;
		}
		factionController.updateFcOnlineState(character, CommonUseNumber.byte0);
	}

	/**
	 * 系统设置自动同意接受入帮邀请
	 * 
	 * @param type
	 */
	public void systemAccessInviteFaction(byte type) {
		if (type == 1) {
			this.accessInviteFaction = true;
		} else {
			this.accessInviteFaction = false;
		}
	}

	/**
	 * 系统设置自动是否同意接受申请入帮请求
	 * 
	 * @param type
	 */
	public void systemAccessApplyFaction(byte type) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 686));
			return;
		}
		if (fc.getPosition() != 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 687));
			return;
		}
		factionController.systemAccessApplyFaction(type);
	}

	/**
	 * 获取帮会名字
	 * 
	 * @return
	 */
	public String getFactionName() {
		if (Options.IsCrossServ) {
			return acrossFactionController.getFactionName();
		}

		if (factionController == null) {
			return "";
		}
		return factionController.getFaction().getViewName();
	}

	public int getFactionPosition() {
		if (Options.IsCrossServ) {
			return this.acrossFactionposition;
		}
		if (factionController == null) {
			return 0;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc != null) {
			return fc.getPosition();
		}
		return 0;
	}

	/**
	 * 获取帮会id
	 * 
	 * @return
	 */
	public int getFactionId() {
		if (Options.IsCrossServ) {
			return acrossFactionController.getFactionId();
		}
		if (this.factionController == null) {
			return 0;
		}

		return this.factionController.getFaction().getId();
	}

	public void updatebossKillCount(int count) {
		if (factionController == null) {
			// CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
			return;
		}
		factionController.updatekillbossCount(character, count);
	}

	/**
	 * 更改帮会公告信息
	 * 
	 * @param characterId
	 * @param name
	 */
	public void changFactionNotice(String notice) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 686));
			return;
		}
		if (fc.getPosition() != 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 688));
			return;
		}
		int msg = checkFactionNotice(notice);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		factionController.changFactionNotice(notice);
		character.sendMsg(new FactionNoticeMsg51076());
	}

	/**
	 * 更改玩家称号
	 * 
	 * @param characterId
	 * @param name
	 */
	public void changFcName(int characterId, String name) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 686));
			return;
		}
		if (fc.getPosition() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 688));
			return;
		}
		FactionCharacter namefc = factionController.getFactionCharacterByCharacterId(characterId);
		if (namefc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 689));
			return;
		}
		if (namefc.getCce().getName().equals(name)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 690));
			return;
		}
		int msg = checkFcName(name);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		factionController.changFcName(namefc, name);
		character.sendMsg(new FactionFcNameMsg51074());
	}

	/**
	 * 任命职务
	 * 
	 * @param characterId
	 * @param type
	 */
	public void appointZhiwu(int characterId, byte type) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 689));
			return;
		}
		if (fc.getPosition() != 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 691));
			return;
		}
		if (characterId == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 692));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1351));
			return;
		}
		FactionCharacter appointFc = factionController.getFactionCharacterByCharacterId(characterId);
		if (appointFc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 693));
			return;
		}
		if (type < 0 || type > 5) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 694));
			return;
		}
		if (type == 1) {
			if (appointFc.getCce().getGrade() < 30) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14705, 30));
				return;
			}
			if (GongchengTsMap.isGongchenging) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14533));
				return;
			} else {
				if (isXiangyangchengFaction()) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14535));
					return;
				}
			}
		}
		int msg = factionController.appointZhiwu(characterId, type);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
		}
	}

	/**
	 * 是否是占城帮会
	 * 
	 * @return
	 */
	public boolean isXiangyangchengFaction() {
		if (!isFaction()) {
			return false;
		}
		int factionId = getFactionId();
		FactionCityManager fcm = FactionCityManager.getInstance();
		FactionCity factionCity = fcm.getFactionCity();
		if (factionCity == null) {
			return false;
		}
		if (factionId != factionCity.getFactionId()) {
			return false;
		}
		return true;
	}

	public void dismissFaction() {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1354));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 689));
			return;
		}
		if (fc.getPosition() != 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 695));
			return;
		}
		if (factionController.getFactionCharacterSize() > 10) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 696));
			return;
		}
		factionController.dismissFaction();
	}

	/**
	 * 玩家退出帮会
	 */
	public void leaveFaction() {
		if (!isFaction()) {
			character.sendMsg(new FactionLeaveMsg51058(11028, CommonUseNumber.byte0));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1352));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			character.sendMsg(new FactionLeaveMsg51058(11029, CommonUseNumber.byte0));
			return;
		}
		if (fc.getPosition() == 1) {
			character.sendMsg(new FactionLeaveMsg51058(11030, CommonUseNumber.byte0));
			return;
		}
		int msg = factionController.leaveFaction(character, character.getId(), null);
		if (msg != 0) {
			character.sendMsg(new FactionLeaveMsg51058(msg, CommonUseNumber.byte0));
			return;
		} else {
			character.sendMsg(new FactionLeaveMsg51058(fc.getFactionId()));
		}
	}

	public void clearFactionInfo() {
		if (effect != null) {
			try {
				FightMsgSender.sendCancelSustainEffectMsg(character, effect);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			effect = null;
		}
		factionController.cancelFactionJiacheng(character);
		// character.getVlineserver().getChatSessionManager()
		// .sendGameToChatServerMsg(new ChatToGsMsg614(character.getId()));
		character.getEyeShotManager().sendMsg(
				new FactionBoardMsg51068(character.getId(), factionController.getFaction().getId(), CommonUseNumber.byte0, factionController.getFaction().getViewName()));
		this.factionController = null;
		this.startIntoFaction = false;
		clearFactionCityBuffer();

	}

	/**
	 * 开除某人
	 * 
	 * @param kickId
	 */
	public void kickFaction(int kickId) {
		if (!isFaction()) {
			character.sendMsg(new FactionKickMsg51050(11032));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1352));
			return;
		}
		if (kickId == character.getId()) {
			character.sendMsg(new FactionKickMsg51050(11033));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc.getPosition() == 1) {
			FactionCharacter kicker = factionController.getFactionCharacterByCharacterId(kickId);
			if (kicker == null) {
				character.sendMsg(new FactionKickMsg51050(11031));
				return;
			}
			Hero kick = GameServer.vlineServerManager.getCharacterById(kicker.getCharacterId());
			int msg = factionController.leaveFaction(kick, kickId, character);
			if (msg != 0) {
				character.sendMsg(new FactionKickMsg51050(msg));
				return;
			} else {
				character.sendMsg(new FactionKickMsg51050(kicker));
			}
		} else if (fc.getPosition() == 2) {
			FactionCharacter kicker = factionController.getFactionCharacterByCharacterId(kickId);
			if (kicker == null) {
				character.sendMsg(new FactionKickMsg51050(11031));
				return;
			}
			if (kicker.getPosition() != 0) {
				character.sendMsg(new FactionKickMsg51050(11034));
				return;
			}
			Hero kick = GameServer.vlineServerManager.getCharacterById(kicker.getCharacterId());
			int msg = factionController.leaveFaction(kick, kickId, character);
			if (msg != 0) {
				character.sendMsg(new FactionKickMsg51050(msg));
				return;
			} else {
				if (kick != null) {
					// character.getViewName()+ "帮主将您移出了帮会
					kick.sendMsg(new FactionMsg51072(11020, character.getViewName()));
				}
			}
		} else {
			character.sendMsg(new FactionKickMsg51050(11032));
		}
	}

	public synchronized void requestInviteFaction(int inviteId) {
		if (GongchengTsMap.isGongchenging) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16001));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1352));
			return;
		}
		if (this.factionController == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		if (factionController.getFactionCharacterSize() >= FactionController.FactionCountMax) {
			character.sendMsg(new FactionApplyMsg51042(11016));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc.getPosition() == 1 || fc.getPosition() == 2) {
			Hero inviter = GameServer.vlineServerManager.getCharacterById(inviteId);
			if (inviter == null) {
				character.sendMsg(new FactionApplyMsg51042(11035));
				return;
			}
			if (inviter.getMyFactionManager().isFaction()) {
				character.sendMsg(new FactionApplyMsg51042(11036));
				return;
			}
			if (!inviter.getCharacterLimitConfig().isOtherAbleInviteFaction()) {
				// 该玩家决绝他人向他发起的帮会邀请!
				character.sendMsg(new FactionApplyMsg51042(11037));
				return;
			}
			if (inviter.getMyFactionManager().isAccessInviteFaction()) {
				inviter.getMyFactionManager().meAddFactionByBangzhu(character, factionController, true);
				return;
			}
			if (!isIniviteFactionTime(inviteId)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19302));
				return;
			}
			putInviteFactionTime(inviteId);
			inviter.sendMsg(new FactionInviteMsg51044(character, factionController));
		} else {
			// 只有帮主或副帮主有权限邀请他人入帮，请通知帮主或副帮主进行收人操作
			character.sendMsg(new FactionApplyMsg51042(11038));
		}
	}

	public synchronized void inviterAccessBazhuInviteFaction(int bangzhuOrFuId, byte type) {
		Hero bangzhuOrFu = GameServer.vlineServerManager.getCharacterById(bangzhuOrFuId);
		if (startIntoFaction) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 697));
			return;
		}
		if (this.factionController != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 698));
			return;
		}
		if (bangzhuOrFu == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 699));
			return;
		}
		if (!bangzhuOrFu.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 700));
			return;
		}
		if (type == 0) {
			//
			bangzhuOrFu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 999, character.getViewName() + ""));
			return;
		}

		FactionController factionC = bangzhuOrFu.getMyFactionManager().getFactionController();
		if (factionC.getFactionCharacterSize() >= FactionController.FactionCountMax) {
			character.sendMsg(new FactionApplyMsg51042(11016));
			return;
		}
		FactionCharacter fc = factionC.getFactionCharacterByCharacterId(bangzhuOrFu.getId());
		if (fc.getPosition() == 1 || fc.getPosition() == 2) {
			meAddFactionByBangzhu(bangzhuOrFu, factionC, true);
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 701));
		}
	}

	public boolean isFaction() {
		if (Options.IsCrossServ) {
			if (acrossFactionController != null) {
				return true;
			} else {
				return false;
			}
		}
		if (factionController == null) {
			return false;
		}
		return true;
	}

	public boolean isApplyFactionTime(int factionId) {
		Long appTime = applyMap.get(factionId);
		if (appTime == null) {
			return true;
		}
		return System.currentTimeMillis() - appTime > 1000 * 60;
	}

	public boolean isIniviteFactionTime(int inviteId) {
		Long inviteTime = inviteMap.get(inviteId);
		if (inviteTime == null) {
			return true;
		}
		return System.currentTimeMillis() - inviteTime > 1000 * 60;
	}

	public void putApplyFactionTime(int factionId) {
		applyMap.put(factionId, System.currentTimeMillis());
	}

	public void putInviteFactionTime(int inviteId) {
		inviteMap.put(inviteId, System.currentTimeMillis());
	}

	/**
	 * 没有加入帮会玩家发送加入帮会请求
	 */
	public synchronized void requestAppleFaction(int factionId) {
		if (GongchengTsMap.isGongchenging) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16001));
			return;
		}
		if (startIntoFaction) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 697));
			return;
		}
		if (this.factionController != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 698));
			return;
		}
		FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionID(factionId);
		if (factionC == null) {
			character.sendMsg(new FactionApplyMsg51042(11039));
			return;
		}
		if (factionC.getFactionCharacterSize() >= FactionController.FactionCountMax) {
			character.sendMsg(new FactionApplyMsg51042(11040));
			return;
		}
		if (GongchengDateManager.getInstance().isHaveApplyGongcheng(factionId) && GongchengTsMap.isGongchenging) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16011));
			return;
		}
		if (factionC.getFaction().getAccessInFaction() == 1) {
			meAddFactionByBangzhu(null, factionC, false);
			return;
		}
		FactionCharacter fc = factionC.getBangzhu();
		Hero bangzhu = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
		if (bangzhu == null) {
			fc = factionC.getFuBangzhu();
			if (fc != null) {
				bangzhu = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
			}
			if (bangzhu == null) {
				character.sendMsg(new FactionApplyMsg51042(11041));
				return;
			}
		}
		if (!isApplyFactionTime(factionId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19301));
			return;
		}
		putApplyFactionTime(factionId);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 702));
		bangzhu.sendMsg(new FactionApplyMsg51040(character));
	}

	/**
	 * 玩家通过帮主批准后加入帮会时或邀请接受加入帮会后调用此方法
	 * 
	 * @param bangzhu
	 */
	public synchronized void meAddFactionByBangzhu(Hero bangzhu, FactionController factionC, boolean isInvite) {
		if (startIntoFaction) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 697));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1352));
			return;
		}
		if (this.factionController != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 698));
			return;
		}
		if (factionC == null) {
			return;
		}
		int msg = factionC.addFactionCharacter(character);
		if (msg != 0) {
			character.sendMsg(new FactionApplyMsg51042(msg));
		} else {
			if (isInvite) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1000, factionC.getFaction().getViewName()));
				if (bangzhu != null) {
					//
					bangzhu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1001, character.getViewName()));
				}
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 703));
			}
			character.sendMsg(new FactionApplyMsg51042(factionController));
		}
	}

	/**
	 * 帮主是否同意玩家加入帮会
	 * 
	 * @param characterID
	 * @param type
	 */
	public synchronized void bangzhuAccessOherApplyFaction(int characterID, byte type) {
		Hero other = GameServer.vlineServerManager.getCharacterById(characterID);
		if (other == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 704));
			return;
		}
		if (this.factionController == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		if (other.getMyFactionManager().isFaction()) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 698));
			return;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null || fc.getPosition() == 0 || fc.getPosition() > 2) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 705));
			return;
		}
		if (factionController.getFactionCharacterSize() >= FactionController.FactionCountMax) {
			other.sendMsg(new FactionApplyMsg51042(11040));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 706));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1352));
			return;
		}
		if (type == 0) {
			other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1002, character.getViewName()));
			return;
		}
		other.getMyFactionManager().meAddFactionByBangzhu(character, factionController, false);
		return;

	}

	/**
	 * 检查帮会名字是否可用
	 * 
	 * @param factionName
	 * @return
	 */
	public int checkFactionName(String factionName) {
		int msg = 0;
		int nameLen = factionName.toCharArray().length;
		if (nameLen < 2) {
			msg = 11047;
			return msg;
		}
		if (nameLen > 6) {
			msg = 11048;
			return msg;
		}
		Pattern pattern = Pattern.compile(Options.ChineseChars_6);
		boolean tf = pattern.matcher(factionName).matches();
		if (!tf) {
			msg = 11049;
			return msg;
		}
		if (BadWordsFilter.getInstance().hashBadWords(factionName)) {
			msg = 11050;
			return msg;
		}
		FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionName(factionName);
		if (factionC != null) {
			msg = 11051;
			return msg;
		}
		return msg;
	}

	/**
	 * 检查帮会名字是否可用
	 * 
	 * @param factionName
	 * @return
	 */
	public int checkBangqiName(String bangqiName) {
		int msg = 0;
		int nameLen = bangqiName.toCharArray().length;
		if (nameLen < 2) {
			msg = 11052;
			return msg;
		}
		if (nameLen > 6) {
			msg = 11053;
			return msg;
		}
		Pattern pattern = Pattern.compile(Options.ChineseChars_6);
		boolean tf = pattern.matcher(bangqiName).matches();
		if (!tf) {
			msg = 11054;
			return msg;
		}
		if (BadWordsFilter.getInstance().hashBadWords(bangqiName)) {
			msg = 11055;
			return msg;
		}
		return msg;
	}

	public int checkFcName(String fcName) {
		int msg = 0;

		int nameLen = fcName.toCharArray().length;

		if (nameLen > 6) {
			msg = 997;
			return msg;
		}

		if (BadWordsFilter.getInstance().hashBadWords(fcName)) {
			msg = 998;
			return msg;
		}
		return msg;
	}

	public int checkFactionNotice(String factionNotice) {
		int msg = 0;

		int nameLen = factionNotice.toCharArray().length;

		if (nameLen > 200) {
			msg = 995;
			return msg;
		}

		if (BadWordsFilter.getInstance().hashBadWords(factionNotice)) {
			msg = 996;
			return msg;
		}
		return msg;
	}

	public synchronized void createFaction(String factionName, String factionNotice, String bangqiName, byte icoId, String icoStr) {
		if (startIntoFaction) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 707));
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null && asd.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1353));
			return;
		}
		if (character.getGrade() < 30) {
			character.sendMsg(new FactionCreateMsg51038(11042));
			return;
		}
		if (icoId < 0) {
			character.sendMsg(new FactionCreateMsg51038(11043));
			return;
		}
		if (icoId < 10) {
			icoId = (byte) (10 + icoId);
		}
		if (!isUseBangqiICO(icoId, 1)) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1003, (icoId / 10) + ""));
			return;
		}
		String[] ico = icoStr.split(",");
		if (ico.length != 3) {
			character.sendMsg(new FactionCreateMsg51038(11043));
			return;
		}
		if (this.factionController != null) {
			character.sendMsg(new FactionCreateMsg51038(11044));
			return;
		}
		if (character.getCopper() < FactionController.FactionCreateCopper) {
			// 很抱歉，您需要拥有"
			// + FactionController.FactionCreateCopper
			// + "万铜币才能购买到帮旗，创建帮会
			character.sendMsg(new FactionCreateMsg51038(11045, FactionController.FactionCreateCopper + ""));
			return;
		}
		if (!character.getCharacterGoodController().isEnoughGoods(GoodItemId.BANGZHULING_ID, 1)) {
			character.sendMsg(new FactionCreateMsg51038(11046));
			return;
		}
		int msg = checkFactionName(factionName);
		if (msg != 0) {
			character.sendMsg(new FactionCreateMsg51038(msg));
			return;
		}
		int msg1 = checkFactionNotice(factionNotice);
		if (msg1 != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg1));
			return;
		}
		msg = checkBangqiName(bangqiName);
		if (msg != 0) {
			character.sendMsg(new FactionCreateMsg51038(msg));
			return;
		}
		startIntoFaction = true;
		Faction faction = new Faction();
		faction.setName(factionName);
		faction.setFactionNotice(factionNotice);
		FactionFlag flag = FactionFlagManager.getInstance().getFactionFlagByGrade(1);
		faction.setFactionFlagId(flag.getId());
		faction.setBangqiName(bangqiName);
		faction.setIcoId((int) icoId);
		faction.setIcoStr(icoStr);
		faction.setBangzhuId(character.getId());
		faction.setContribution(0);
		faction.setAccessInFaction(1);
		faction.setBaihuCount(0);
		faction.setCopper(3000000l);
		faction.setDesc("");
		faction.setQinglongCount(0);
		faction.setXuanwuCount(0);
		faction.setZhuquCount(0);
		faction.setBangzhulingCount(0);
		faction.setCreateDate(new Date());
		faction.setServerId(character.getOriginalSid());
		FactionManager.getInstance().createFaction(character, faction);
	}

	/**
	 * 获取自己帮会信息给客户端
	 * 
	 * @param factionId
	 */
	public void sendMyFactionInfo(int nowPage, int pageNum, byte type) {
		if (this.factionController == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		List<FactionCharacter> list = this.factionController.getFactionCharacterListByType(type);
		Collections.sort(list, new FcByPositionComparator());
		int allPage = (list.size() - 1) / pageNum + 1;
		if (nowPage > allPage) {
			nowPage = allPage;
		}
		if (nowPage < 1) {
			nowPage = 1;
		}
		int startIndex = (nowPage - 1) * pageNum;
		List<FactionCharacter> returnList = new ArrayList<FactionCharacter>();
		for (int i = startIndex; i < list.size(); i++) {
			FactionCharacter fc = list.get(i);
			if (fc != null) {
				returnList.add(fc);
			}
			if (returnList.size() >= pageNum) {
				break;
			}
		}
		character.sendMsg(new FactionMyInfoMsg51036(this.factionController, type, pageNum, nowPage, allPage, returnList));

	}

	/**
	 * 根据帮会id 返回帮会信息（角色查询某帮会）
	 * 
	 * @param factionId
	 */
	public void sendOtherFactionInfo(int factionId) {
		FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionID(factionId);
		if (factionC == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 708));
			return;
		}
		character.sendMsg(new FactionOtherInfoMsg51034(factionC, getFactionIsTishiMsg()));
	}

	/**
	 * 获取是否提示跳过
	 * 
	 * @return
	 */
	public int getFactionIsTishiMsg() {
		int isTiaoguo = 0;
		if (isFaction()) {
			FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
			if (fc != null) {
				isTiaoguo = fc.getTishiConfig();
			}
		}
		return isTiaoguo;
	}

	/**
	 * 设置帮会面板是否显示
	 * 
	 * @param type
	 */
	public void changeFactionIsTishiMsg(byte type) {
		if (isFaction()) {
			final FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
			if (fc != null) {
				fc.setTishiConfig((int) type);
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						FactionCharacterManager.getInstance().update(fc);
					}
				});

			}
		}

	}

	/**
	 * 发送帮会列表信息
	 * 
	 * @param nowPage当前页
	 * @param pageNum
	 *            每页显示个数
	 * @param type
	 *            请求类型
	 */
	public void sendFactionList(int nowPage, int pageNum, byte type) {
		List<FactionController> list = getAllFactionListByType(type);
		List<Integer> maxlist = getMaxCount();
		Collections.sort(list, new FactionGradeComparator());
		int allPage = (list.size() - 1) / pageNum + 1;
		if (nowPage > allPage) {
			nowPage = allPage;
		}
		if (nowPage < 1) {
			nowPage = 1;
		}
		int startIndex = (nowPage - 1) * pageNum;
		List<FactionController> returnList = new ArrayList<FactionController>();
		for (int i = startIndex; i < list.size(); i++) {
			FactionController factionCon = list.get(i);
			if (factionCon != null) {
				returnList.add(factionCon);
			}
			if (returnList.size() >= pageNum) {
				break;
			}
		}
		character.sendMsg(new FactionListMsg51032(type, pageNum, nowPage, allPage, maxlist, returnList));
	}

	private List<FactionController> getAllFactionListByType(byte type) {
		List<FactionController> list = new ArrayList<FactionController>();
		Collection<FactionController> collection = FactionManager.getInstance().getAllFactionCollection();
		if (type == 0) {
			list.addAll(collection);
		} else if (type == 1) {
			for (FactionController factionController : collection) {
				if (factionController.getFaction().getAccessInFaction() == 1) {
					list.add(factionController);
				}
			}
		} else if (type == 2) {
			for (FactionController factionController : collection) {
				if (factionController.getFaction().getAccessInFaction() == 0) {
					list.add(factionController);
				}
			}
		}
		return list;
	}

	private List<Integer> getMaxCount() {
		List<Integer> list = new ArrayList<Integer>();
		Collection<FactionController> collection = FactionManager.getInstance().getAllFactionCollection();
		int bangzhuGradeMax = 0;
		int banghuiCountMax = 0;
		int banghuiGradeMax = 0;
		int bangqiGradeMax = 0;
		int bossKill = 0;
		for (FactionController factionController : collection) {
			if (bangzhuGradeMax < factionController.getBangzhu().getCce().getGrade()) {
				bangzhuGradeMax = factionController.getBangzhu().getCce().getGrade();
			}
			if (banghuiCountMax < factionController.getFactionCharacterSize()) {
				banghuiCountMax = factionController.getFactionCharacterSize();
			}
			int factionGrade = factionController.updateFactionGrade();
			if (banghuiGradeMax < factionGrade) {
				banghuiGradeMax = factionGrade;
			}
			if (bangqiGradeMax < factionController.getFaction().getFactionFlag().getfGrade()) {
				bangqiGradeMax = factionController.getFaction().getFactionFlag().getfGrade();
			}
			if (bossKill < factionController.getBosskill()) {
				bossKill = factionController.getBosskill();
			}
		}
		list.add(bangzhuGradeMax);
		list.add(banghuiCountMax);
		list.add(banghuiGradeMax);
		list.add(bangqiGradeMax);
		list.add(bossKill);
		return list;
	}

	public boolean isStartIntoFaction() {
		return startIntoFaction;
	}

	public void setStartIntoFaction(boolean startIntoFaction) {
		this.startIntoFaction = startIntoFaction;
	}

	public boolean isAccessInviteFaction() {
		return accessInviteFaction;
	}

	public void setAccessInviteFaction(boolean accessInviteFaction) {
		this.accessInviteFaction = accessInviteFaction;
	}

	public void killBossMsg(SceneMonster monster) {
		if (!isFaction()) {
			return;
		}
		factionController.sendFactionMsg(new FactionMsg51072(11022, character.getViewName(), character.getVlineserver().getLineid() + "", character.getSceneRef().getShowName(),
				character.getX() + "", character.getY() + "", monster.getMonsterModel().getNameI18n()));
	}

	/**
	 * 我杀死了某人
	 * 
	 * @param dier
	 */
	public void pkKillOther(Hero dier) {
		if (Options.IsCrossServ) {
			return;
		}
		if (!isFaction()) {
			return;
		}

		if (dier.getMyFactionManager().isFaction()) {
			// 本帮成员【"
			// + character.getViewName() + "】在【"
			// + character.getVlineserver().getLineid() + "】线，【"
			// + character.getSceneRef().getShowName() + "地图，坐标："
			// + character.getX() + "，" + character.getY() + "】击败了"
			// + dier.getMyFactionManager().getFactionName() + "帮会成员【"
			// + dier.getViewName() + "】
			factionController.sendFactionMsg(new FactionMsg51072(11023, character.getViewName(), character.getVlineserver().getLineid() + "",
					character.getSceneRef().getShowName(), character.getX() + "", character.getY() + "", dier.getMyFactionManager().getFactionName(), dier.getViewName()));
		} else {
			factionController.sendFactionMsg(new FactionMsg51072(11024, character.getViewName() + "】" + Language.ZAI + "【", character.getVlineserver().getLineid() + "", character
					.getSceneRef().getShowName(), +character.getX() + "", character.getY() + "", dier.getViewName()));
		}
	}

	/**
	 * 我被某人杀死
	 * 
	 * @param killer
	 */
	public void pkOtherKillMe(Hero killer) {
		if (!isFaction()) {
			return;
		}
		if (Options.IsCrossServ) {
			return;
		}
		// 本帮成员【"
		// + character.getViewName() + "】在【"
		// + character.getVlineserver().getLineid() + "】线，【"
		// + character.getSceneRef().getShowName() + "地图，坐标："
		// + character.getX() + "，" + character.getY() + "】被"
		// + killer.getMyFactionManager().getFactionName() + "帮会成员【"
		// + killer.getViewName() + "】击败
		if (killer.getMyFactionManager().isFaction()) {
			factionController.sendFactionMsg(new FactionMsg51072(11025, character.getViewName(), character.getVlineserver().getLineid() + "",
					character.getSceneRef().getShowName(), character.getX() + "", "" + character.getY(), killer.getMyFactionManager().getFactionName(), killer.getViewName()

			));
		} else {
			factionController.sendFactionMsg(new FactionMsg51072(11026, character.getViewName(), character.getVlineserver().getLineid() + "",
					character.getSceneRef().getShowName(), character.getX() + "", "" + character.getY(), killer.getViewName()));
		}
	}

	/**
	 * 玩家pk死亡调用此方法
	 * 
	 * @param attacker
	 */
	public void pkDie(Hero killer) {
		if (killer == null || !isFaction() || !killer.getMyFactionManager().isFaction()) {
			return;
		}
		pkOtherKillMe(killer);
		killer.getMyFactionManager().pkKillOther(character);
	}

	public int getSceneBangqiCount() {
		if (!isFaction()) {
			return 0;
		}
		return factionController.getSceneBangqiCount();
	}

	public void changBangqiName(String bangqiName) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 709));
			return;
		}
		int myId = character.getId();
		if (!factionController.isBangzhu(myId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 709));
			return;
		}
		int msg = checkBangqiName(bangqiName);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		if (factionController.getFaction().getCopper() < 10000000) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 710));
			return;
		}
		msg = factionController.changeBangqiName(bangqiName);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionFlagNameChangeMsg51086(bangqiName));
	}

	/**
	 * 帮旗图标更换
	 * 
	 * @param icoId
	 * @param icoStr
	 */
	public void changBangqiIco(byte icoId, String icoStr) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 711));
			return;
		}
		int myId = character.getId();
		if (!factionController.isBangzhu(myId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 711));
			return;
		}
		if (factionController.getFaction().getCopper() < 20000000) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 712));
			return;
		}
		if (icoId < 10) {
			icoId = (byte) (10 + icoId);
		}
		if (!isUseBangqiICO(icoId, factionController.getFaction().getFactionFlag().getfGrade())) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1003, (icoId / 10) + ""));
			return;
		}
		int msg = factionController.changBangqiIco(icoId, icoStr);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionFlagIcoChangeMsg51088());
	}

	public boolean isUseBangqiICO(byte ico, int bangqiGrade) {
		int maxICO = (bangqiGrade + 1) * 10;
		return ico < maxICO;
	}

	public void changBangqiUpGrade(byte icoId, String icoStr) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 713));
			return;
		}
		int myId = character.getId();
		if (!factionController.isBangzhu(myId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 713));
			return;
		}
		FactionFlag flag = factionController.getFaction().getFactionFlag();
		Faction faction = factionController.getFaction();
		FactionFlag nextFlag = FactionFlagManager.getInstance().getFactionFlagByGrade(flag.getfGrade() + 1);
		if (nextFlag == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14712));
			return;
		}
		if (faction.getCopper() < flag.getfCopperLimite()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 714));
			return;
		}
		if (faction.getBaihuCount() < flag.getfBaihuCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 715));
			return;
		}
		if (faction.getQinglongCount() < flag.getfQinglongCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 716));
			return;
		}
		if (faction.getZhuquCount() < flag.getfZhuquCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 717));
			return;
		}
		if (faction.getXuanwuCount() < flag.getfXuanwuCount()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 718));
			return;
		}
		if (icoId < 10) {
			icoId = (byte) (10 + icoId);
		}
		if (!isUseBangqiICO(icoId, nextFlag.getfGrade())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1003, (icoId / 10) + ""));
			return;
		}
		int msg = factionController.changBangqiUpGrade(icoId, icoStr);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionFlagUpGradeMsg51090());
	}

	public void bangqiEnterScene(int positionId) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		int myId = character.getId();
		if (!factionController.isBangzhu(myId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 719));
			return;
		}
		if (factionController.getFaction().getCopper() < FactionController.BangQiEnterSceneCopper) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 720));
			return;
		}
		boolean isSu = factionController.bangqiToScene(character, positionId);
		if (!isSu) {
			return;
		}
		character.sendMsg(new FactionFlagEnterSceneMsg51096());
	}

	public void bangqiSceneMonsterHpChange(int positionId) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 685));
			return;
		}
		int myId = character.getId();
		if (!factionController.isBangzhu(myId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 721));
			return;
		}
		if (factionController.getFaction().getCopper() < FactionController.BangQiChangeHpCopper) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 722));
			return;
		}
		int msg = factionController.bangqiSceneMonsterHpChange(character, positionId);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionFlagEnterSceneMsg51096());
	}

	public void changeContributeCopper(int copper) {
		if (copper < FactionController.BanggongCopperMin) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 723));
			return;
		}
		if (!this.isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 724));
			return;
		}
		if (copper < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 725));
			return;
		}
		if (character.getCopper() < copper) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 726));
			return;
		}
		int msg = factionController.changeContributeCopper(copper, character);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionContributeCopperMsg51092());
	}

	public void changeContributeGoods(List<CharacterGoods> glist) {
		if (!this.isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 724));
			return;
		}
		if (glist.size() < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 727));
			return;
		}
		int msg = factionController.changeContributeGoods(glist, character);
		if (msg != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		character.sendMsg(new FactionContributeGoodsMsg51094());
	}

	/**
	 * 打怪经验加成 单位是10000
	 * 
	 * @param character
	 * @return
	 */
	public int getMonsterExpJiacheng() {
		if (!this.isFaction()) {
			return 0;
		}
		if (effect == null) {
			return 0;
		} else {
			return factionController.getMonsterExpJiacheng();
		}
	}

	// public boolean isDazuoJingyanJiacheng(){
	// if(!this.isFaction()){
	// return false;
	// }
	// return factionController.isDazuoJingyanJiacheng(character);
	//
	// }
	// /**
	// * 打怪坐真气加成 单位是10000
	// * @param character
	// * @return
	// */
	// public int getDazuoZhenqiJiacheng(){
	// if(!this.isFaction()){
	// return 0;
	// }
	// return factionController.getDazuoZhenqiJiacheng(character);
	// }
	private int updateCount = 0;
	private EffectInfo effect = null;

	public void update() {
		if (!isFaction()) {
			return;
		}
		updateCount++;
		if (updateCount % 6 != 0) {
			return;
		}
		if (Options.IsCrossServ) {
			return;
		}
		if (factionController.isBangqiJiachengRand(character)) {
			if (effect == null) {
				effect = factionController.getExpEffectInfo();
				FightMsgSender.broastSustainEffect(effect, character);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 728));
			}
		} else {
			if (effect != null) {
				try {
					FightMsgSender.sendCancelSustainEffectMsg(character, effect);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				effect = null;
			}
		}
		if (factionCityBuffer == null) {
			addFactionCityBuffer();
		}
	}

	public EffectInfo getEffect() {
		return effect;
	}

	public void setEffect(EffectInfo effect) {
		this.effect = effect;
	}

	public int getDazuoJiachengTime() {
		if (effect == null) {
			return 0;
		} else {
			int time = factionController.getFaction().getFactionFlag().getfJiachengZheqi();
			return time;
		}

	}

	public void updateExpBuffer() {
		EffectInfo buffer = getEffect();
		if (buffer != null) {
			try {
				FightMsgSender.sendCancelSustainEffectMsg(character, buffer);
				setEffect(null);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public EffectInfo youlongEffect;

	/**
	 * 是否持有游龙之刃（判断是否持有游龙之刃）
	 * 
	 * @return
	 */
	public boolean isHaveYoulongBuffer() {
		if (!isFaction()) {
			return false;
		}
		if (youlongEffect != null) {
			return true;
		}
		return false;
	}

	private CharacterGoods youlong;

	public CharacterGoods getYoulongZhiren() {
		if (!isHaveYoulongBuffer()) {
			return null;
		}
		if (youlong == null) {
			youlong = CharacterGoods.createCharacterGoods(1, GoodItemId.Youlongzhiren, 0);
		}
		return youlong;
	}

	public void takeOnYouLongInGongcheng() {
		if (!GongchengTsMap.isGongchenging) {
			return;
		}
		youlongEffect = getYoulongEffect();
		createTeamPropertyChangerListener();
		character.getPropertyAdditionController().addChangeListener(this);
		FightMsgSender.broastSustainEffect(youlongEffect, character);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, GoodItemId.Youlongzhiren, true));
	}

	/**
	 * 脱下
	 */
	public void takeOnYoulong() {
		if (!isXiangyangchengFaction()) {
			return;
		}
		if (!factionController.isBangzhu(character.getId())) {
			return;
		}
		youlongEffect = getYoulongEffect();
		createTeamPropertyChangerListener();
		character.getPropertyAdditionController().addChangeListener(this);
		FightMsgSender.broastSustainEffect(youlongEffect, character);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, GoodItemId.Youlongzhiren, true));
	}

	/**
	 * 
	 */
	public void takeOnYoulongInKuafuzhan() {
		if (!Options.IsCrossServ) {
			return;
		}
		youlongEffect = getYoulongEffect();
		createTeamPropertyChangerListener();
		character.getPropertyAdditionController().addChangeListener(this);
		FightMsgSender.broastSustainEffect(youlongEffect, character);
		character.getEyeShotManager().sendMsg(new AvatarChange60000(character, GoodItemId.Youlongzhiren, true));
	}

	public void takeOffYoulong() {
		if (this.youlongEffect != null) {
			character.getPropertyAdditionController().removeChangeListener(this);
			try {
				FightMsgSender.sendCancelSustainEffectMsg(character, youlongEffect);
				this.youlongEffect = null;
				this.pe = null;
				character.getEyeShotManager().sendMsg(new AvatarChange60000(character, GoodItemId.Youlongzhiren, false));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public EffectInfo getYoulongEffect() {
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(fcc.getChengzhuBuffer());
		EffectInfo effectInfo = new EffectInfo(se);
		this.youlongEffect = effectInfo;
		return this.youlongEffect;
	}

	private PropertyEntity pe = null;//

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.youlong;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		return pe;
	}

	private void createTeamPropertyChangerListener() {
		PropertyEntity pe = new PropertyEntity();
		int attack = 500;
		pe.addExtraProperty(Property.attack, attack);
		int defence = 250;
		pe.addExtraProperty(Property.defence, defence);
		pe.addExtraProperty(Property.crt, 100);
		pe.addExtraProperty(Property.dodge, 100);
		this.pe = pe;
	}

	/**
	 * 发送襄阳城税收面板信息
	 */
	public void sendXiangyangcghengTaxInfo() {
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		character.sendMsg(new FactionCity51110(factionCity));
	}

	/**
	 * 设定襄阳城税收率
	 * 
	 * @param value
	 */
	public void changeXiangyangTaxRate(int value) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11066));
			return;
		}
		if (!factionController.isBangzhu(character.getId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11066));
			return;
		}
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		int factionid = factionController.getFaction().getId();
		if (factionid != factionCity.getFactionId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11066));
			return;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		if (fcc.getTaxrateMin() > value || fcc.getTaxrateMax() < value) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11067, fcc.getTaxrateMin() / 100, fcc.getTaxrateMax() / 100));
		}
		factionCity.setTaxRate(value);
		character.sendMsg(new FactionCityMsg51112());
		// GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51142(factionCity));
	}

	/**
	 * 领取襄阳城税收
	 * 
	 * @param value
	 */
	public void lingquXiangyangTaxs() {
		if (!isXiangyangchengFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11068));
			return;
		}
		if (!factionController.isBangzhu(character.getId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11068));
			return;
		}
		int copper = FactionCityManager.getInstance().lingquTaxs(factionController, character);
		if (copper > 0) {
			character.sendMsg(new FactionCity51114());
		}
	}

	/**
	 * 王邦骨干成员领取物品奖励 （每日领取）
	 */
	public void lingquXiangyangRewardGood() {
		if (!isXiangyangchengFaction()) {
			character.sendMsg(new FactionCityMsg51144(14511));
			return;
		}
		int count = FactionCityManager.getInstance().lingquGoods(factionController, character);
		if (count > 0) {
			character.sendMsg(new FactionCityMsg51144());
		}
	}

	/**
	 * 打怪经验加成 单位 1/10000
	 * 
	 * @return
	 */
	public int xiangyangFactionJiacheng() {
		if (!isFaction()) {
			return 0;
		}
		// int factionId = getFactionId();
		// FactionCityManager fcm = FactionCityManager.getInstance();
		// FactionCity factionCity = fcm.getFactionCity();
		// if (factionCity == null) {
		// return 0;
		// }
		// if (factionId != factionCity.getFactionId()) {
		// return 0;
		// }
		if (factionCityBuffer == null) {
			return 0;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		return fcc.getExpJiacheng();
	}

	/**
	 * 打怪经验加成 单位 1/10000
	 * 
	 * @return
	 */
	public int xiangyangFactionDazuoJiacheng() {
		if (!isFaction()) {
			return 0;
		}
		// int factionId = getFactionId();
		// FactionCityManager fcm = FactionCityManager.getInstance();
		// FactionCity factionCity = fcm.getFactionCity();
		// if (factionCity == null) {
		// return 0;
		// }
		// if (factionId != factionCity.getFactionId()) {
		// return 0;
		// }
		if (factionCityBuffer == null) {
			return 0;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		return fcc.getDazuoJiacheng();
	}

	public EffectInfo factionCityBuffer; // 王邦经验加成buffer

	/**
	 * 清楚占城buffer 操作 客户端不显示
	 */
	public void clearFactionCityBuffer() {
		if (factionCityBuffer == null) {
			return;
		}
		try {
			FightMsgSender.sendCancelSustainEffectMsg(character, factionCityBuffer);
			this.factionCityBuffer = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加占城buffer 操作 客户端显示
	 */
	public void addFactionCityBuffer() {
		if (!isXiangyangchengFaction()) {
			return;
		}
		FactionCharacter factionCharacter = factionController.getFactionCharacterByCharacterId(character.getId());
		if (factionCharacter == null) {
			return;
		}
		// 加入帮会小于某时间 没有王邦加成
		if (System.currentTimeMillis() - factionCharacter.getDate().getTime() < infactionTimeMin) {
			return;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(fcc.getFactioncityBuffer());
		factionCityBuffer = new EffectInfo(se);
		FightMsgSender.broastSustainEffect(factionCityBuffer, character);
	}

	/**
	 * 是否有足够的帮贡
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEnoughFactionContribution(int value) {
		if (character.getContribution() < value) {
			return false;
		}
		return true;
	}

	/**
	 * 扣除帮贡是否成功 0 表示失败
	 * 
	 * @param value
	 * @return
	 */
	public int changeFactionContribution(int value) {
		if (!isEnoughFactionContribution(value)) {
			return 0;
		}
		return CharacterPropertyManager.changeContribution(character, -value);
	}

	/**
	 * 
	 * @param character2
	 */
	public void changXiangYangChengzhu(Hero character2) {
		FactionCity fc = FactionCityManager.getInstance().getFactionCity();
		fc.setFactionId(getFactionId());
		this.factionController.addFactionCityBuffer();
		character2.getMyFactionCityZhengDuoManager().clearcatchHuYoulongBuffer();
		// GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51140(fc, character2));
	}

	/**
	 * 设定襄阳城税收率
	 * 
	 * @param value
	 */
	public void changeXiangyangNotice(String str) {
		if (!isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14528));
			return;
		}
		if (!factionController.isBangzhu(character.getId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14528));
			return;
		}
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		int factionid = factionController.getFaction().getId();
		if (factionid != factionCity.getFactionId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14528));
			return;
		}
		int msg = checkXiangyangNotice(str);
		if (msg > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, msg));
			return;
		}
		factionCity.setNotice(str);
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14531));
	}

	public int checkXiangyangNotice(String factionNotice) {
		int msg = 0;

		int nameLen = factionNotice.toCharArray().length;

		if (nameLen > 200) {
			msg = 14529;
			return msg;
		}
		if (BadWordsFilter.getInstance().hashBadWords(factionNotice)) {
			msg = 14530;
			return msg;
		}
		return msg;
	}

	/**
	 * @return
	 */
	public boolean isBangzhu() {
		if (Options.IsCrossServ) {
			if (acrossFactionposition == 1) {
				return true;
			}
			return false;
		}
		if (!isFaction()) {
			return false;
		}
		return factionController.isBangzhu(character.getId());

	}

	/**
	 * @return
	 */
	public boolean isXiangyangchengZhu() {
		if (!isFaction()) {
			return false;
		}
		if (!isBangzhu()) {
			return false;
		}
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		int factionid = factionController.getFaction().getId();
		if (factionid != factionCity.getFactionId()) {
			return false;
		}
		return true;
	}

	public int getXuanyuanjianJiacheng() {
		if (!Options.IsCrossServ) {
			return 1;
		}
		return character.getMycharacterAcrossZhengzuoManager().getXuanyuanjianJiacheng(this.acrossFactionController);
	}

	public String getXuanyuanjianRoleName() {
		if (!Options.IsCrossServ) {
			return "";
		}
		Hero xuanyuaner = this.acrossFactionController.getCatchXuanYuan();
		if (xuanyuaner == null) {
			return "";
		} else {
			return xuanyuaner.getViewName();
		}
	}

	/**
	 * 帮主要进入跨服服务器 通知帮会其他成员
	 * 
	 * @param as
	 */
	public void bangzhuAcrossNoticOther(AcrossServerDate as) {
		if (!isBangzhu()) {
			return;
		}
		factionController.bangzhuAcrossNoticOther(character, as);
	}

	// private CharacterGoods youlong;
	//
	// public CharacterGoods getYoulongZhiren() {
	// if (!isHaveYoulongBuffer()) {
	// return null;
	// }
	// if (youlong == null) {
	// youlong = CharacterGoods.createCharacterGoods(1,
	// GoodItemId.Youlongzhiren, 0);
	// }
	// return youlong;
	// }
}
