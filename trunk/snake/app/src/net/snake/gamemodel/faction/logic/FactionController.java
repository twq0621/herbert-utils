package net.snake.gamemodel.faction.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.DistanceFormula;
import net.snake.commons.Language;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.response.AcrossTishiMsg10114;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.BangqiPosition;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.faction.persistence.FactionCharacterManager;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.faction.persistence.GongchengDateManager;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.gamemodel.faction.response.FactionBoardMsg51068;
import net.snake.gamemodel.faction.response.FactionFlagUpdateMsg51100;
import net.snake.gamemodel.faction.response.FactionMsg51072;
import net.snake.gamemodel.faction.response.FactionPostMsg51062;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.guide.bean.CharacterMsg;
import net.snake.gamemodel.guide.persistence.CharacterMsgManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;

/**
 * 帮会控制器
 * 
 * @author serv_dev
 * 
 */
public class FactionController implements PropertyChangeListener {
	private static final Logger logger = Logger.getLogger(FactionController.class);
	public static int ROLE_JIACHENG_RAND = 160;
	public static int FactionCreateCopper = 10000000;
	public static int FactionCharacterCopperMax = 2100000000;
	public static long FactionCopperMax = 9000000000l;
	public static int BangqiNameChangeCopper = 10000000;
	public static int BangqiIcoChangeCopper = 20000000;
	public static int BangQiChangeHpCopper = 5000000;
	public static int BangQiEnterSceneCopper = 10000000;
	/** 帮贡捐献最小值 **/
	public static int BanggongCopperMin = 10000; //
	private EffectInfo effectInfo;
	private EffectInfo expEffectInfo;
	private Object lock = new Object();
	public static byte banghuKey = 1;
	public static byte fubanghuKey = 2;
	public static int FactionCountMax = 100;
	private Faction faction;
	/** 加入该帮会玩家 **/
	private Map<Integer, FactionCharacter> factionCharacterMap = new ConcurrentHashMap<Integer, FactionCharacter>();//
	/** <玩家id, */
	private Map<Byte, FactionCharacter> guganMap = new ConcurrentHashMap<Byte, FactionCharacter>(); //
	private Map<Integer, List<SceneBangqiMonster>> bangqiMap = new ConcurrentHashMap<Integer, List<SceneBangqiMonster>>();
	private PropertyEntity pe = null;//
	/** 总boss数 */
	private int bosskill = 0; //
	/** 当前在线角色数 **/
	public int onlineCount = 0; //
	/** 总等级数 **/
	private int factionGrade = 0; //
	private int fcBossKillMax = 0;
	private int fcChengjiuPointMax = 0;
	private int fcWuxueJingjieMax = 0;
	private int fcChannekXueweiMax = 0;
	private int fcPrestigeMax = 0;
	private int fcGradeMax = 0;

	public FactionController() {

	}

	public FactionController(Faction faction, List<FactionCharacter> list) {
		this.faction = faction;
		for (FactionCharacter fc : list) {
			addFactionCharacter(fc);
		}
	}

	public void addBangQiToScene(SceneBangqiMonster bangqi) {
		List<SceneBangqiMonster> list = bangqiMap.get(bangqi.getScene());
		if (list == null) {
			list = new CopyOnWriteArrayList<SceneBangqiMonster>();
			bangqiMap.put(bangqi.getScene(), list);
		}
		list.add(bangqi);
	}

	/**
	 * 帮旗死亡时调用此方法
	 * 
	 * @param character
	 */
	public void bangqiMonsterDie(final SceneBangqiMonster sceneMonster, Hero character) {
		synchronized (lock) {
			final SceneBangqi bangqi = sceneMonster.getSceneBangqi();
			SceneBangqiManager.getInstance().bangqiLeaveScene(bangqi);
			removeBangqiInFaction(sceneMonster);
			Collection<VLineServer> collection = GameServer.vlineServerManager.getLineServersList();
			// sceneMonster.setDropGoods(null);
			// sceneMonster.setGoodscharacter(null);
			sceneMonster.clearHatesetHurtList();
			// sceneMonster.setFirstAttackPlayer(null);
			for (VLineServer line : collection) {
				final VLineServer tempLine = line;
				final Scene scene = line.getSceneManager().getScene(sceneMonster.getScene());
				line.addTaskInFrameUpdateThread(new Runnable() {
					@Override
					public void run() {
						int anchaLine = bangqi.getLine();
						if (anchaLine == tempLine.getLineid()) {
							bangqiMonsterDropGoodToScene(sceneMonster, scene);
						}
						scene.leaveScene(sceneMonster);
						sceneMonster.setX(sceneMonster.getOriginalX());
						sceneMonster.setY(sceneMonster.getOriginalY());
						sceneMonster.setFallDown(false);
					}
				});
			}
			BangqiPosition position = bangqi.getBangqiPosition();
			position.setDieTime(System.currentTimeMillis());
			if (character != null) {
				position.setKillerFactionId(character.getMyFactionManager().getFactionId());
			} else {
				position.setKillerFactionId(0);
			}
			// 我帮在"+ position.getSceneName() + "地图" + position.getX() + ","+ position.getY() + "坐标的帮会旗帜被破坏了
			this.sendFactionMsg(new FactionMsg51072(11018, position.getSceneNameI18n(), position.getX() + "", +position.getY() + ""));
			FactionController otherFc = character.getMyFactionManager().getFactionController();
			// 我帮成员"+ character.getViewName() + "破坏掉了"	+ this.getFaction().getName() + "帮会在"+ position.getSceneName() + "地图" + position.getX() + ","+ position.getY() + "坐标的帮旗
			otherFc.sendFactionMsg(new FactionMsg51072(11019, character.getViewName(), this.getFaction().getName(), position.getSceneNameI18n(), position.getX() + "", position
					.getY() + ""));
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14708, character.getViewName(), this.getFaction().getViewName(),
					position.getSceneNameI18n()));
		}

	}

	public void bangqiMonsterDropGoodToScene(SceneBangqiMonster sceneMonster, Scene scene) {
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(this.getFaction().getDropGoodMonsterModelId());
		if (monstermodel == null) {
			return;
		}
		List<CharacterGoods> dropGoods = monstermodel.dropGoods(null, sceneMonster.getDropGoodJiacheng());
		if (dropGoods.size() != 0) {
			for (CharacterGoods cg : dropGoods) {
				SceneDropGood sceneGoods = new SceneDropGood(cg, null, sceneMonster);
				scene.enterScene(sceneGoods);
			}
		}
	}

	public boolean removeBangqiInFaction(SceneBangqiMonster sceneMonster) {
		List<SceneBangqiMonster> list = bangqiMap.get(sceneMonster.getScene());
		int position = sceneMonster.getSceneBangqi().getBangqiPositionId();
		for (int i = 0; i < list.size(); i++) {
			SceneBangqiMonster monster = list.get(i);
			if (position == monster.getSceneBangqi().getBangqiPositionId()) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * 帮旗作为怪物放入场景
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param defenceJiacheng
	 * @param expJiacheng
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public SceneMonster addBangqiMonsterToSceneAndFaction(final SceneBangqi bangqi) {
		final SceneBangqiMonster sceneMonster = new SceneBangqiMonster();
		BangqiPosition position = bangqi.getBangqiPosition();
		sceneMonster.setScene(position.getSceneId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) position.getX());
		sceneMonster.setY((short) position.getY());
		sceneMonster.setOriginalX((short) position.getX());
		sceneMonster.setOriginalY((short) position.getY());
		sceneMonster.setRelive(0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(this.getFaction().getMonsterModelId());
		if (monstermodel == null) {
			return null;
		}
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.init();
		sceneMonster.setSceneBangqi(bangqi);
		Collection<VLineServer> collection = GameServer.vlineServerManager.getLineServersList();
		for (VLineServer line : collection) {
			final Scene scene = line.getSceneManager().getScene(position.getSceneId());
			final int lineId = line.getLineid();
			line.addTaskInFrameUpdateThread(new Runnable() {
				@Override
				public void run() {
					enterBangqiScene(scene, sceneMonster, lineId);
				}
			});
		}
		addBangQiToScene(sceneMonster);
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14706, this.getFaction().getViewName(), bangqi.getBangqiPosition()
				.getSceneNameI18n()));
		return sceneMonster;
	}

	private void enterBangqiScene(Scene scene, SceneBangqiMonster sceneMonster, int lineId) {
		try {
			SceneBangqiMonster monster = (SceneBangqiMonster) sceneMonster.clone();
			monster.init();
			monster.setLineId(lineId);
			scene.enterScene(monster);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 帮旗插入场景中 只有帮主可以调用此方法
	 */
	public boolean bangqiToScene(Hero bangzhu, int posionId) {
		synchronized (lock) {
			int bangzhuId = bangzhu.getId();
			if (!isBangzhu(bangzhuId)) {
				return false;
			}
			SceneBangqi bangqi = SceneBangqiManager.getInstance().bangqiToScene(posionId, bangzhu);
			if (bangqi == null) {
				return false;
			}
			bangqi.setMaxHp(this.faction.getFactionFlag().getfBangqiMaxhp());
			bangqi.setNowHp(bangqi.getNowHp());
			addBangqiMonsterToSceneAndFaction(bangqi);
			updateFactionShouhuBangqiCount();
			return true;
		}
	}

	/**
	 * 更新帮会在线成员守护帮旗成就
	 */
	public void updateFactionShouhuBangqiCount() {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (character != null) {
					character.getMyCharacterAchieveCountManger().getFactionCount().bangqiShouhuCount(1);
				}
			}
		}
	}

	public List<SceneBangqiMonster> getBangqiMonsterBySceneId(int sceneId) {
		return this.bangqiMap.get(sceneId);
	}

	/**
	 * 发送帮会广播
	 * 
	 * @param msg
	 */
	public void sendFactionMsg(ServerResponse msg) {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			fc.sendMsg(msg);
		}
	}

	public void updateMaxFc(FactionCharacter fc) {
		if (fcChannekXueweiMax < fc.getCce().getChannelXuewei()) {
			fcChannekXueweiMax = fc.getCce().getChannelXuewei();
		}
		if (fcWuxueJingjieMax < fc.getCce().getWuxueJingjie()) {
			fcWuxueJingjieMax = fc.getCce().getWuxueJingjie();
		}
		if (fcChengjiuPointMax < fc.getCce().getChengjiuPoint()) {
			fcChengjiuPointMax = fc.getCce().getChengjiuPoint();
		}
		if (fcBossKillMax < fc.getCce().getBossKill()) {
			fcBossKillMax = fc.getCce().getBossKill();
		}
		if (fcPrestigeMax < fc.getCce().getChenzhanshengwang()) {
			fcPrestigeMax = fc.getCce().getChenzhanshengwang();
		}
		if (fcGradeMax < fc.getCce().getGrade()) {
			fcGradeMax = fc.getCce().getGrade();
		}
	}

	/**
	 * 帮会所有玩家等级和
	 * 
	 * @return
	 */
	public int updateFactionGrade() {
		int grade = 0;
		for (FactionCharacter fc : factionCharacterMap.values()) {
			grade = grade + fc.getCce().getGrade();
		}
		this.factionGrade = grade;
		return grade;
	}

	public int getFactionGrade() {
		if (factionGrade < 30) {
			updateFactionGrade();
		}
		return factionGrade;
	}

	public FactionCharacter getBangzhu() {
		return guganMap.get(banghuKey);
	}

	public boolean isBangzhu(int characterId) {
		if (getBangzhu().getCharacterId() == characterId) {
			return true;
		} else {
			return false;
		}
	}

	public FactionCharacter getFuBangzhu() {
		return guganMap.get(fubanghuKey);
	}

	public FactionController(Faction faction, FactionCharacter fc) {
		this.faction = faction;
		addFactionCharacter(fc);
	}

	public FactionCharacter getFactionCharacterByCharacterId(int characterId) {
		return factionCharacterMap.get(characterId);
	}

	private void addFactionCharacter(FactionCharacter fc) {
		synchronized (lock) {
			factionCharacterMap.put(fc.getCharacterId(), fc);
			if (fc.getPosition() > 0) {
				guganMap.put(fc.getPosition(), fc);
			}
			updateMaxFc(fc);
		}
	}

	public Map<Integer, FactionCharacter> getFactionCharacterMap() {
		return factionCharacterMap;
	}

	public void setFactionCharacterMap(Map<Integer, FactionCharacter> factionCharacterMap) {
		this.factionCharacterMap = factionCharacterMap;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public int getBosskill() {
		return bosskill;
	}

	/**
	 * 周日12点清楚boss计数从头计算
	 */
	public void clearBossKill() {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			fc.getCce().setBossKill(0);
		}
		this.bosskill = 0;
	}

	public void updatekillbossCount(Hero character, int count) {
		synchronized (lock) {
			this.bosskill = this.bosskill + count;
			FactionCharacter fc = factionCharacterMap.get(character.getId());
			fc.updatebossKillCount(character);
		}
	}

	public void updateBossAndOnlineCount() {
		synchronized (lock) {
			int boss = 0;
			int online = 0;
			for (FactionCharacter fc : factionCharacterMap.values()) {
				boss = boss + fc.getCce().getBossKill();
				if (fc.getCce().getIsOnline() > 0) {
					online++;
				}
			}
			this.bosskill = boss;
			if (this.onlineCount != online) {
				this.onlineCount = online;
				// updateFactionJiacheng();
			}
		}
	}

	public void updateFcOnlineState(Hero character, byte state) {
		FactionCharacter fc = factionCharacterMap.get(character.getId());
		if (fc != null) {
			fc.updateOnlineState(state);
			if (state < 1) {
				updateOnlineCount(-1);
			} else {
				updateOnlineCount(1);
			}
		}
	}

	public void updateOnlineCount(int count) {
		synchronized (lock) {
			this.onlineCount = this.onlineCount + count;
			// updateFactionJiacheng();
		}
	}

	public void updateOnlineCountAndJiacheng() {
		synchronized (lock) {
			int online = 0;
			for (FactionCharacter fc : factionCharacterMap.values()) {
				if (fc.getCce().getIsOnline() == 1) {
					online++;
				}
			}
			this.onlineCount = online;
			// updateFactionJiacheng();
		}
	}

	public void updateFactionJiacheng() {
		synchronized (lock) {
			createTeamPropertyChangerListener(true);
			for (FactionCharacter fc : factionCharacterMap.values()) {
				if (fc.getCce().getIsOnline() > 0) {
					Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
					if (character != null) {
						character.getPropertyAdditionController().addChangeListener(this);
						EffectInfo buffer = getEffectInfo();
						if (buffer != null) {
							FightMsgSender.broastSustainEffect(buffer, character);
						}
					}
				}
			}
		}
	}

	public void updateFactionJiacheng(Hero character) {
		createTeamPropertyChangerListener(false);
		character.getPropertyAdditionController().addChangeListener(this);
		EffectInfo buffer = getEffectInfo();
		if (buffer != null) {
			FightMsgSender.broastSustainEffect(buffer, character);
		}
	}

	/**
	 * 取消帮会加成
	 * 
	 * @param character
	 */
	public void cancelFactionJiacheng(Hero character) {
		character.getPropertyAdditionController().removeChangeListener(this);
		EffectInfo buffer = getEffectInfo();
		try {
			if (buffer != null) {
				FightMsgSender.sendCancelSustainEffectMsg(character, buffer);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.bangpai;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		return pe;
	}

	/**
	 * 
	 * @param isOver
	 *            是否覆盖已有的属加成对象
	 */

	private void createTeamPropertyChangerListener(boolean isOver) {
		if (this.pe != null && !isOver) {
			return;
		}
		PropertyEntity pe = new PropertyEntity();
		FactionFlag factionFlag = faction.getFactionFlag();
		if (factionFlag == null) {
			return;
		}
		// int attack = factionFlag.getAttack() * onlineCount;
		// if (attack > factionFlag.getMaxAttackLimite()) {
		int attack = factionFlag.getMaxAttackLimite();
		// }
		pe.addExtraProperty(Property.attack, attack);
		// int defence = factionFlag.getDefence() * onlineCount;
		// if (defence > factionFlag.getMaxDefenceLimite()) {
		int defence = factionFlag.getMaxDefenceLimite();
		// }
		pe.addExtraProperty(Property.defence, defence);
		// int hp = factionFlag.getHp() * onlineCount;
		// if (hp > factionFlag.getMaxHpLimite()) {
		int hp = factionFlag.getMaxHpLimite();
		// }
		pe.addExtraProperty(Property.maxHp, hp);

		// pe.addExtraProperty(Property.crt, factionFlag.getCrt() *
		// onlineCount);
		// pe.addExtraProperty(Property.attackspeed,
		// factionFlag.getAttackspeed()
		// * onlineCount);
		// pe.addExtraProperty(Property.dodge, factionFlag.getDodge()
		// * onlineCount);
		// pe.addExtraProperty(Property.maxMp, factionFlag.getMp()
		// * onlineCount);
		// pe.addExtraProperty(Property.maxSp, factionFlag.getSp()
		// * onlineCount);
		// pe.addExtraProperty(Property.movespeed, factionFlag.getMovespeed()
		// * onlineCount);
		this.pe = pe;
	}

	public int getFactionCharacterSize() {
		return factionCharacterMap.size();
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}

	public Collection<FactionCharacter> getGuganCollection() {
		return guganMap.values();
	}

	public Collection<FactionCharacter> getAllFactionCharacter() {
		return factionCharacterMap.values();
	}

	public void updateAllFcMaxCount() {
		Collection<FactionCharacter> collection = getAllFactionCharacter();
		for (FactionCharacter fc : collection) {
			updateMaxFc(fc);
		}
	}

	public List<FactionCharacter> getFactionCharacterListByType(byte type) {
		List<FactionCharacter> list = new ArrayList<FactionCharacter>();
		Collection<FactionCharacter> collection = getAllFactionCharacter();
		if (type == 2) {
			list.addAll(collection);
		} else if (type == 1) {
			for (FactionCharacter fc : collection) {
				if (fc.getCce().getIsOnline() == 1) {
					list.add(fc);
				}
			}
		} else if (type == 0) {
			for (FactionCharacter fc : collection) {
				if (fc.getCce().getIsOnline() == 0) {
					list.add(fc);
				}
			}
		}
		return list;
	}

	public int getFcBossKillMax() {
		return fcBossKillMax;
	}

	public void setFcBossKillMax(int fcBossKillMax) {
		this.fcBossKillMax = fcBossKillMax;
	}

	public int getFcChengjiuPointMax() {
		return fcChengjiuPointMax;
	}

	public void setFcChengjiuPointMax(int fcChengjiuPointMax) {
		this.fcChengjiuPointMax = fcChengjiuPointMax;
	}

	public int getFcWuxueJingjieMax() {
		return fcWuxueJingjieMax;
	}

	public void setFcWuxueJingjieMax(int fcWuxueJingjieMax) {
		this.fcWuxueJingjieMax = fcWuxueJingjieMax;
	}

	public int getFcChannekXueweiMax() {
		return fcChannekXueweiMax;
	}

	public void setFcChannekXueweiMax(int fcChannekXueweiMax) {
		this.fcChannekXueweiMax = fcChannekXueweiMax;
	}

	public int getFcPrestigeMax() {
		return fcPrestigeMax;
	}

	public void setFcPrestigeMax(int fcPrestigeMax) {
		this.fcPrestigeMax = fcPrestigeMax;
	}

	public int getFcGradeMax() {
		return fcGradeMax;
	}

	public void setFcGradeMax(int fcGradeMax) {
		this.fcGradeMax = fcGradeMax;
	}

	public int addFactionCharacter(final Hero character) {
		synchronized (lock) {
			if (getFactionCharacterSize() >= FactionCountMax) {
				return 11016;
			}
			if (character.getMyFactionManager().isFaction()) {
				return 11017;
			}
			FactionCharacter fc = FactionCharacter.createFactionCharacter(faction, character, CommonUseNumber.byte0, 0);
			fc.getCce().update(character, CommonUseNumber.byte1);
			addFactionCharacter(fc);
			final FactionCharacter fcdb = fc;
			character.getMyFactionManager().init(this);
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					FactionCharacterManager.getInstance().addFactionCharacter(fcdb);
				}
			});
			character.getEyeShotManager().sendMsg(new FactionBoardMsg51068(character.getId(), faction.getId(), CommonUseNumber.byte1, faction.getViewName()));
			// 欢迎"+ character.getViewName() + "玩家加入本帮，我帮变得更强大了
			this.sendFactionMsg(new FactionMsg51072(11015, character.getViewName()));
			updateBossAndOnlineCount();
			character.getMyCharacterAchieveCountManger().getFactionCount().factionAddCount(1);
			return 0;
		}
	}

	public int leaveFaction(Hero kicker, int kickeId, Hero character) {
		synchronized (lock) {
			final FactionCharacter fc = factionCharacterMap.remove(kickeId);
			if (fc == null) {
				return 11031;
			}
			if (fc.getPosition() > 0) {
				guganMap.remove(fc.getPosition());
			}
			FactionCharacterManager.getInstance().removeMemoryFactionCharacter(kickeId);
			if (character == null) {
				if (fc.getCce() != null) {
					// fc.getCce().getViewName() + "玩家离开了我们的帮会"
					this.sendFactionMsg(new FactionMsg51072(11014, fc.getCce().getViewName()));
				}
				if (kicker != null) {
					//
					kicker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 993, faction.getViewName()));
				}
			} else {
				// kicker.sendMsg(new FactionMsg51072("你被帮主踢出了帮会"));
				if (fc.getCce() != null) {
					// fc.getCce().getViewName() + "玩家被移出了我们的帮会"
					this.sendFactionMsg(new FactionMsg51072(11013, fc.getCce().getViewName()));
				}
				if (kicker == null) {
					CharacterMsgManager.getInstance().insertCharacterMsg(new CharacterMsg(fc.getCharacterId(), 11020, character.getViewName()));
				}
			}
			if (kicker != null) {
				kicker.getMyFactionManager().clearFactionInfo();
			}
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					FactionCharacterManager.getInstance().deleteFactionCharacter(fc);
				}
			});
			updateBossAndOnlineCount();
			return 0;
		}
	}

	/**
	 * 清除王邦信息
	 */
	public void dismissFaction() {

		lostFactionCityPosition();
		if (GongchengTsMap.isGongchenging) {
			FactionCharacter fc = getBangzhu();
			if (MyFactionCityZhengDuoManager.monster != null) {
				Hero youlonger = MyFactionCityZhengDuoManager.monster.getYoulongCharacter();
				if (youlonger != null) {
					int id = fc.getCharacterId();
					if (id == youlonger.getId()) {
						youlonger.getMyFactionCityZhengDuoManager().dropYoulongToScene(null);
					}
				}
			}
		}
		GongchengDateManager.getInstance().deleteGongchengDateByFaction(this.faction.getId());

		for (FactionCharacter fc : factionCharacterMap.values()) {
			dismissDeleteFactionCharacter(fc);
		}
		factionCharacterMap.clear();
		guganMap.clear();
		FactionManager.getInstance().delete(this);
		// GameServer.vlineServerManager.getVLineServerByLineID(1)
		// .getChatSessionManager()
		// .sendGameToChatServerMsg(new ChatToGsMsg614(faction.getId()));
		clearFactionAllBangqi();
	}

	public void clearFactionAllBangqi() {
		for (List<SceneBangqiMonster> list : bangqiMap.values()) {
			if (list != null && list.size() > 0) {
				for (SceneBangqiMonster monster : list) {
					clearFactionBangqi(monster);
				}
			}
		}
		bangqiMap.clear();
	}

	/**
	 * 
	 * 
	 * @param character
	 */
	public void clearFactionBangqi(final SceneBangqiMonster sceneMonster) {
		synchronized (lock) {
			SceneBangqi bangqi = sceneMonster.getSceneBangqi();
			SceneBangqiManager.getInstance().bangqiLeaveScene(bangqi);
			Collection<VLineServer> collection = GameServer.vlineServerManager.getLineServersList();
			// sceneMonster.setDropGoods(null);
			// sceneMonster.setGoodscharacter(null);
			sceneMonster.clearHatesetHurtList();
			// sceneMonster.setFirstAttackPlayer(null);
			for (VLineServer line : collection) {
				final Scene scene = line.getSceneManager().getScene(sceneMonster.getScene());
				line.addTaskInFrameUpdateThread(new Runnable() {
					@Override
					public void run() {
						scene.leaveScene(sceneMonster);
						sceneMonster.setX(sceneMonster.getOriginalX());
						sceneMonster.setY(sceneMonster.getOriginalY());
						sceneMonster.setFallDown(false);
					}
				});
			}
		}

	}

	private void dismissDeleteFactionCharacter(FactionCharacter fc) {
		if (fc.getCce().getIsOnline() == 1) {
			Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
			if (character != null) {
				character.getMyFactionManager().clearFactionInfo();
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 994, faction.getViewName()));
			} else {
				CharacterMsgManager.getInstance().insertCharacterMsg(new CharacterMsg(fc.getCharacterId(), 11065, faction.getViewName()));
			}
		} else {

		}
		FactionCharacterManager.getInstance().removeMemoryFactionCharacter(fc.getCharacterId());
	}

	public int appointZhiwu(int characterId, byte type) {
		synchronized (lock) {
			final FactionCharacter fc = guganMap.get(type);
			if (fc != null && fc.getCharacterId() == characterId) {
				return 11010;
			}
			final FactionCharacter renmingFc = factionCharacterMap.get(characterId);
			if (renmingFc == null) {
				return 11011;
			}
			if (renmingFc.getPosition() > 0) {
				guganMap.remove(renmingFc.getPosition());
			}
			if (fc != null) {
				this.sendFactionMsg(new FactionMsg51072(11009, fc.getCce().getViewName(), getZhiwuByteToString(fc.getPosition())));
				fc.setPosition(CommonUseNumber.byte0);
				this.sendFactionMsg(new FactionPostMsg51062(fc));
			}
			if (type == 0) {
				if (renmingFc.getPosition() > 0) {
					// renmingFc.getCce().getViewName()+ "玩家被取消了"+
					// getZhiwuByteToString(renmingFc.getPosition())+ "职务"
					this.sendFactionMsg(new FactionMsg51072(11009, renmingFc.getCce().getViewName(), getZhiwuByteToString(renmingFc.getPosition())));
					renmingFc.setPosition(type);
				} else {
					return 11012;
				}
			} else {
				renmingFc.setPosition(type);
				guganMap.put(type, renmingFc);
				if (type == 1) {
					changBangzhu(fc, renmingFc);
				}
			}
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					FactionCharacterManager.getInstance().update(renmingFc);
					if (fc != null) {
						FactionCharacterManager.getInstance().update(fc);
					}
				}
			});
			this.sendFactionMsg(new FactionPostMsg51062(renmingFc));
			return 0;
		}
	}

	/**
	 * 禅让帮主操作
	 * 
	 * @param old
	 * @param newBangzhu
	 */
	private void changBangzhu(FactionCharacter old, FactionCharacter newBangzhu) {
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		int factionId = this.getFaction().getId();
		if (factionCity == null || factionCity.getFactionId() != factionId) {
			return;
		}
		Hero oldBangzhu = GameServer.vlineServerManager.getCharacterById(old.getCharacterId());
		if (oldBangzhu != null) {
			oldBangzhu.getMyFactionManager().takeOffYoulong();
			oldBangzhu.getCatchYoulongActionController().breakCatch();
		}
		Hero newBang = GameServer.vlineServerManager.getCharacterById(newBangzhu.getCharacterId());
		if (newBang != null) {
			newBang.getMyFactionManager().takeOnYoulong();
		}
//		GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51140(factionCity, newBang));
	}

	public String getZhiwuByteToString(byte type) {
		if (type == 1) {
			return Language.FACTION_BANGZHU;
		} else if (type == 2) {
			return Language.FACTION_FUBANGZHU;
		} else if (type == 3) {
			return Language.FACTION_DAZHANGLAO;
		} else if (type == 4) {
			return Language.FACTION_DASHIXIONG;
		} else if (type == 5) {
			return Language.FACTION_DASHIJIE;
		} else {
			return Language.FACTION_BANGZHONG;
		}
	}

	public void changFcName(final FactionCharacter namefc, String name) {
		synchronized (lock) {
			if (namefc == null) {
				return;
			}
			namefc.setName(name);
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				FactionCharacterManager.getInstance().update(namefc);
			}
		});
		// 恭喜帮会成+ namefc.getCce().getViewName() + "拥有了新的帮中昵称："+ namefc.getName()
		this.sendFactionMsg(new FactionMsg51072(11008, namefc.getCce().getViewName(), namefc.getName()));
	}

	public void changFactionNotice(String notice) {
		faction.setFactionNotice(notice);
		try {
			Faction temp = (Faction) faction.clone();
			FactionManager.getInstance().threadUpdateFaction(temp);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
		this.sendFactionMsg(new FactionMsg51072(11007));
	}

	public void systemAccessApplyFaction(byte type) {
		if (faction.getAccessInFaction() != type) {
			faction.setAccessInFaction((int) type);
		}
		try {
			Faction temp = (Faction) faction.clone();
			FactionManager.getInstance().threadUpdateFaction(temp);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public EffectInfo getEffectInfo() {
		if (effectInfo == null) {
			SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(faction.getFactionFlag().getBufferId());
			if (se == null) {
				return null;
			}
			this.effectInfo = new EffectInfo(se);
		}
		return effectInfo;
	}

	public EffectInfo getExpEffectInfo() {
		if (expEffectInfo == null) {
			SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(faction.getFactionFlag().getExpBufferId());
			if (se == null) {
				return null;
			}
			this.expEffectInfo = new EffectInfo(se);
		}
		return expEffectInfo;
	}

	public int getSceneBangqiCount() {
		int bangqiCount = 0;
		for (List<SceneBangqiMonster> list : bangqiMap.values()) {
			if (list != null) {
				bangqiCount = bangqiCount + list.size();
			}
		}
		return bangqiCount;
	}

	public int changeBangqiName(String bangqiName) {
		synchronized (lock) {
			if (getFaction().getCopper() < BangqiNameChangeCopper) {
				return 11006;
			}
			this.faction.setCopper(this.faction.getCopper() - BangqiNameChangeCopper);
			this.faction.setBangqiName(bangqiName);
			try {
				Faction temp = (Faction) faction.clone();
				FactionManager.getInstance().threadUpdateFaction(temp);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			// 我们的帮主修改了帮旗名称，新名称为："+ bangqiName
			this.sendFactionMsg(new FactionMsg51072(11005, bangqiName));
			this.sendFactionMsg(new FactionFlagUpdateMsg51100(this.faction));
			updateAllSceneBangqiMonster(false);
			return 0;
		}
	}

	/**
	 * 
	 * @param isUpGrade
	 *            是否是帮旗升级引起的场景中帮旗信息更新
	 */
	public void updateAllSceneBangqiMonster(boolean isUpGrade) {
		synchronized (lock) {
			for (List<SceneBangqiMonster> list : bangqiMap.values()) {
				if (list != null && list.size() > 0) {
					for (SceneBangqiMonster monster : list) {
						updateSceneBangqiMonster(monster, isUpGrade);
					}
				}
			}
		}
	}

	public void updateSceneBangqiMonster(SceneBangqiMonster sceneMonster, boolean isUpGrade) {
		sceneMonster.getSceneBangqi().setMaxHp(this.faction.getFactionFlag().getfBangqiMaxhp());
		if (isUpGrade) {
			sceneMonster.getSceneBangqi().nowHpToMax(this.faction.getFactionFlag().getfBangqiMaxhp());
		}
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(this.getFaction().getMonsterModelId());
		sceneMonster.setMonsterModel(monstermodel);
		// sceneMonster.init();
		sceneMonster.getSceneBangqi().updateAllSceneBangqiMonsterBangqi(sceneMonster);
	}

	// public void updateSceneBangqiMonster(final SceneBangqiMonster
	// sceneMonster,
	// boolean isUpGrade) {
	// sceneMonster.getSceneBangqi().setMaxHp(
	// this.faction.getFactionFlag().getfBangqiMaxhp());
	// if (isUpGrade) {
	// sceneMonster.getSceneBangqi().setNowHp(
	// sceneMonster.getSceneBangqi().getMaxHp());
	// }
	// MonsterModel monstermodel = MonsterModelManager.getInstance()
	// .getFromCache(this.getFaction().getMonsterModelId());
	// sceneMonster.setMonsterModel(monstermodel);
	// Collection<VLineServer> collection = GameServer.vlineServerManager
	// .getLineServersList();
	// for (VLineServer line : collection) {
	// final Scene scene = line.getSceneManager().getScene(
	// sceneMonster.getScene());
	// final int lineId = line.getLineid();
	// line.addTaskInFrameUpdateThread(new Runnable() {
	// @Override
	// public void run() {
	// scene.leaveScene(sceneMonster);
	// enterBangqiScene(scene, sceneMonster, lineId);
	// }
	// });
	// }
	// }

	public int changBangqiIco(int icoId, String icoStr) {
		synchronized (lock) {
			if (getFaction().getCopper() < BangqiIcoChangeCopper) {
				return 11004;
			}
			this.faction.setCopper(this.faction.getCopper() - BangqiIcoChangeCopper);
			this.faction.setIcoId(icoId);
			this.faction.setIcoStr(icoStr);
			try {
				Faction temp = (Faction) faction.clone();
				FactionManager.getInstance().threadUpdateFaction(temp);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			// 我们的帮主修改了帮旗造型，请大家予以检阅
			this.sendFactionMsg(new FactionMsg51072(11003));
			this.sendFactionMsg(new FactionFlagUpdateMsg51100(this.faction));
			updateAllSceneBangqiMonster(false);
			return 0;
		}
	}

	public int changBangqiUpGrade(int icoId, String icoStr) {
		synchronized (lock) {
			FactionFlag flag = this.getFaction().getFactionFlag();
			if (getFaction().getCopper() < flag.getfCopperLimite()) {
				return 11063;
			}
			int nextGrade = this.faction.getFactionFlag().getfGrade() + 1;
			FactionFlag nextFlag = FactionFlagManager.getInstance().getFactionFlagByGrade(nextGrade);
			if (nextFlag == null) {
				return 11064;
			}
			this.faction.setCopper(this.faction.getCopper() - flag.getfCopperLimite());
			this.faction.setBaihuCount(this.faction.getBaihuCount() - flag.getfBaihuCount());
			this.faction.setQinglongCount(this.faction.getQinglongCount() - flag.getfQinglongCount());
			this.faction.setZhuquCount(this.faction.getZhuquCount() - flag.getfZhuquCount());
			this.faction.setXuanwuCount(this.faction.getXuanwuCount() - flag.getfXuanwuCount());
			this.faction.setIcoId(icoId);
			this.faction.setIcoStr(icoStr);
			this.faction.setFactionFlagId(nextFlag.getId());
			removeFactionJiacheng();
			this.effectInfo = null;
			this.expEffectInfo = null;
			try {
				Faction temp = (Faction) faction.clone();
				FactionManager.getInstance().threadUpdateFaction(temp);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			// 帮主升级了帮旗级别
			this.sendFactionMsg(new FactionMsg51072(11002));
			this.sendFactionMsg(new FactionFlagUpdateMsg51100(this.faction));
			updateFactionJiacheng();
			updateExpSceneBangqiBufferUpdate();
			updateAllSceneBangqiMonster(true);
			return 0;
		}
	}

	/**
	 * 场景中 buffer 加cheng
	 */
	public void updateExpSceneBangqiBufferUpdate() {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (character != null) {
					character.getMyFactionManager().updateExpBuffer();
				}
			}
		}
	}

	public int bangqiSceneMonsterHpChange(Hero character, int positionId) {
		if (getFaction().getCopper() < BangQiChangeHpCopper) {
			return 11059;
		}
		SceneBangqi bangqi = SceneBangqiManager.getInstance().getSceneBangqiByPositionId(positionId);
		int factionId = this.faction.getId();
		if (bangqi == null || bangqi.getFactionId() != factionId) {
			return 11060;
		}
		int nowHp = bangqi.getNowHp();
		if (nowHp == bangqi.getMaxHp()) {
			return 11061;
		}
		SceneBangqiMonster monster = getSceneBangqiMonster(positionId);
		if (monster == null) {
			return 11062;
		}
		monster.getSceneBangqi().setNowHp(monster.getSceneBangqi().getMaxHp());
		this.faction.setCopper(this.faction.getCopper() - BangQiChangeHpCopper);
		try {
			Faction temp = (Faction) faction.clone();
			FactionManager.getInstance().threadUpdateFaction(temp);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
		updateAllSceneBangqiMonster(false);
		// 帮主修复了帮旗血量
		this.sendFactionMsg(new FactionMsg51072(11001));
		return 0;
	}

	public SceneBangqiMonster getSceneBangqiMonster(int positionId) {
		for (List<SceneBangqiMonster> list : bangqiMap.values()) {
			if (list != null && list.size() > 0) {
				for (SceneBangqiMonster monster : list) {
					if (monster.getSceneBangqi().getBangqiPositionId() == positionId) {
						return monster;
					}
				}
			}
		}
		return null;
	}

	public int changeFactionCopper(int copper, Hero character) {
		synchronized (lock) {
			if ((faction.getCopper() + copper) > FactionCopperMax) {
				character.sendMsg(new FactionMsg51072(11058));
				return 0;
			}
			faction.setCopper(faction.getCopper() + copper);
			try {
				final Faction temp = (Faction) faction.clone();
				FactionManager.getInstance().threadUpdateFaction(temp);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			return copper;
		}
	}

	public int changeContributeCopper(int copper, Hero character) {
		FactionCharacter fc = this.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			return 11056;
		}
		if (fc.getCopper() + copper < 0 || fc.getCopper() + copper > FactionCharacterCopperMax) {
			return 11057;
		}
		synchronized (lock) {

			int addContribution = copperChangeContribute(copper, fc.getCopper());
			if ((faction.getCopper() + copper) > FactionCopperMax || (faction.getCopper() + copper) < 0) {
				return 11058;
			}
			CharacterPropertyManager.changeCopper(character, -copper, CopperAction.CONSUME);
			fc.setCopper(fc.getCopper() + copper);
			fc.setContribution(addContribution + fc.getContribution());
			CharacterPropertyManager.changeContribution(character, addContribution);
			if (addContribution > 0) {
				character.getMyCharacterAchieveCountManger().getFactionCount().factionContributionCount(fc.getContribution());
			}
			faction.setCopper(faction.getCopper() + copper);
			try {
				final Faction temp = (Faction) faction.clone();
				final FactionCharacter tempFc = new FactionCharacter();
				tempFc.setId(fc.getId());
				tempFc.setCopper(fc.getCopper());
				tempFc.setContribution(fc.getContribution());
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						FactionCharacterManager.getInstance().update(tempFc);
						FactionManager.getInstance().update(temp);
					}
				});
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}

			return 0;
		}
	}

	public int copperChangeContribute(int copper, int oldCopper) {
		int syCopper = oldCopper % 300000;
		return (syCopper + copper) / 300000;
	}

	public int changeContributeGoods(List<CharacterGoods> glist, Hero character) {
		FactionCharacter fc = this.getFactionCharacterByCharacterId(character.getId());
		if (fc == null) {
			return 11056;
		}
		int baihu = 0;
		int qinglong = 0;
		int zhuqu = 0;
		int xuanwu = 0;
		int bangzhuling = 0;
		int addcontribution = 0;
		for (int i = 0; i < glist.size(); i++) {
			CharacterGoods cg = glist.get(i);
			if (cg.getGoodmodelId() == GoodItemId.baihuilingId) {
				baihu = baihu + cg.getCount();
			} else if (cg.getGoodmodelId() == GoodItemId.qinglonglingId) {
				qinglong = qinglong + cg.getCount();
			} else if (cg.getGoodmodelId() == GoodItemId.zhuqulingId) {
				zhuqu = zhuqu + cg.getCount();
			} else if (cg.getGoodmodelId() == GoodItemId.xuanwulingId) {
				xuanwu = xuanwu + cg.getCount();
			} else if (cg.getGoodmodelId() == GoodItemId.BANGZHULING_ID) {
				bangzhuling = bangzhuling + cg.getCount();
			}
			character.getCharacterGoodController().deleteCharacterGoods(cg);
		}
		addcontribution = baihu + qinglong + zhuqu + xuanwu + bangzhuling;
		synchronized (lock) {
			fc.setContribution(addcontribution + fc.getContribution());
			CharacterPropertyManager.changeContribution(character, addcontribution);
			if (addcontribution > 0) {
				character.getMyCharacterAchieveCountManger().getFactionCount().factionContributionCount(fc.getContribution());
			}
			fc.setBaihuCount(fc.getBaihuCount() + baihu);
			fc.setQinglongCount(fc.getQinglongCount() + qinglong);
			fc.setZhuquCount(fc.getZhuquCount() + zhuqu);
			fc.setXuanwuCount(fc.getXuanwuCount() + xuanwu);
			fc.setBangzhulingCount(fc.getBangzhulingCount() + bangzhuling);
			faction.setBaihuCount(faction.getBaihuCount() + baihu);
			faction.setQinglongCount(faction.getQinglongCount() + qinglong);
			faction.setZhuquCount(faction.getZhuquCount() + zhuqu);
			faction.setXuanwuCount(faction.getXuanwuCount() + xuanwu);
			faction.setBangzhulingCount(faction.getBangzhulingCount() + bangzhuling);
			try {
				final Faction temp = (Faction) faction.clone();
				final FactionCharacter tempFc = new FactionCharacter();
				tempFc.setId(fc.getId());
				tempFc.setBaihuCount(fc.getBaihuCount());
				tempFc.setQinglongCount(fc.getQinglongCount());
				tempFc.setZhuquCount(fc.getZhuquCount());
				tempFc.setXuanwuCount(fc.getXuanwuCount());
				tempFc.setContribution(fc.getContribution());
				GameServer.executorServiceForDB.execute(new Runnable() {
					@Override
					public void run() {
						FactionCharacterManager.getInstance().update(tempFc);
						FactionManager.getInstance().update(temp);
					}
				});
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			return 0;
		}
	}

	public void removeFactionJiacheng() {
		synchronized (lock) {
			// createTeamPropertyChangerListener();
			EffectInfo buffer = getEffectInfo();
			if (buffer == null) {
				return;
			}
			for (FactionCharacter fc : factionCharacterMap.values()) {
				if (fc.getCce().getIsOnline() > 0) {
					Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
					if (character != null) {
						character.getPropertyAdditionController().removeChangeListener(this);
						try {
							FightMsgSender.sendCancelSustainEffectMsg(character, buffer);
							if (character.getMyFactionManager().getEffect() != null) {
								character.getMyFactionManager().setUpdateCount(1);
								character.getMyFactionManager().setEffect(null);
								FightMsgSender.sendCancelSustainEffectMsg(character, getExpEffectInfo());
							}
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
		}
	}

	/**
	 * 打怪经验加成 单位是10000
	 * 
	 * @param character
	 * @return
	 */
	public int getMonsterExpJiacheng() {
		return this.faction.getFactionFlag().getfJiachengExp();
	}

	public boolean isDazuoJingyanJiacheng(Hero character) {
		List<SceneBangqiMonster> list = getBangqiMonsterBySceneId(character.getScene());
		if (list == null) {
			return false;
		}
		for (SceneBangqiMonster monster : list) {
			if (isJiachengRand(monster, character)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 打怪坐真气加成 单位是10000
	 * 
	 * @param character
	 * @return
	 */
	public int getDazuoZhenqiJiacheng(Hero character) {
		List<SceneBangqiMonster> list = getBangqiMonsterBySceneId(character.getScene());
		if (list == null) {
			return 0;
		}
		for (SceneBangqiMonster monster : list) {
			if (isJiachengRand(monster, character)) {
				return this.faction.getFactionFlag().getfJiachengZheqi();
			}
		}
		return 0;
	}

	public boolean isJiachengRand(SceneBangqiMonster monster, Hero character) {
		if (monster == null) {
			return false;
		}
		if (DistanceFormula.getDistanceRound(character.getX(), character.getY(), monster.getX(), monster.getY()) < ROLE_JIACHENG_RAND) {
			return true;
		}
		return false;
	}

	public boolean isBangqiJiachengRand(Hero character) {
		List<SceneBangqiMonster> list = getBangqiMonsterBySceneId(character.getScene());
		if (list == null) {
			return false;
		}
		for (SceneBangqiMonster monster : list) {
			if (isJiachengRand(monster, character)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 清空游龙之刃
	 */
	public void clearYoulongInfo() {
		FactionCharacter fc = getBangzhu();
		Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
		if (character != null) {
			character.getMyFactionManager().takeOffYoulong();
		}
	}

	/**
	 * 取消帮会buffer
	 */
	public void clearFactionCityBuffer() {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (character != null) {
					character.getMyFactionManager().clearFactionCityBuffer();
				}
			}
		}
	}

	/**
	 * 取消占城帮会信息
	 */
	public void lostFactionCityPosition() {
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		int factionId = this.faction.getId();
		if (factionCity == null || factionCity.getFactionId() != factionId) {
			return;
		}
		factionCity.setYdFactionId(factionCity.getFactionId());
		factionCity.setFactionId(0);
		clearYoulongInfo();
		clearFactionCityBuffer();
//		GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51140(null));
	}

	/**
	 * 添加 帮会buffer
	 */
	public void addFactionCityBuffer() {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (character != null) {
					character.getMyFactionManager().addFactionCityBuffer();
				}
			}
		}
	}

	public void sharafactionCtShouyi(int addPrestige, int addExp) {
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero character = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (character != null) {
					character.getMyFactionCityZhengDuoManager().sharaFactionCtShouyi(addPrestige, addExp);
				}
			}
		}
	}

	public void changeBazhuling(int bangzhulingCount, Hero character) {
		synchronized (lock) {
			this.faction.setBangzhulingCount(this.faction.getBangzhulingCount() + bangzhulingCount);
			try {
				final Faction temp = (Faction) faction.clone();
				FactionManager.getInstance().threadUpdateFaction(temp);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}

		}

	}

	/**
	 * 帮主跨服通知帮会其他成员
	 * 
	 * @param character
	 * @param as
	 */
	public void bangzhuAcrossNoticOther(Hero character, AcrossServerDate as) {
		int bangzhuId = character.getId();
		ServerResponse msg = new AcrossTishiMsg10114(as.getServerId(), (byte) 3, as.getLoginservername());
		for (FactionCharacter fc : factionCharacterMap.values()) {
			if (fc.getCce().getIsOnline() > 0) {
				Hero chengyuan = GameServer.vlineServerManager.getCharacterById(fc.getCharacterId());
				if (chengyuan != null && chengyuan.getGrade() >= 65) {
					if (bangzhuId != chengyuan.getId()) {
						chengyuan.sendMsg(msg);
					}
				}
			}
		}
	}
}
