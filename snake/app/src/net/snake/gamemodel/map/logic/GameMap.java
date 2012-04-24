package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import net.snake.ai.fight.response.CharacterFuhuoDelayMsg20078;
import net.snake.api.IAttackListener;
import net.snake.api.IBuffListneer;
import net.snake.api.IHurtListener;
import net.snake.api.ISceneListener;
import net.snake.api.IShockListener;
import net.snake.api.IStateListener;
import net.snake.api.IUseItemListener;
import net.snake.commons.NetTool;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SInstance;
import net.snake.consts.ClientConfig;
import net.snake.consts.CommonUseNumber;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.persistence.SceneMonsterManager;
import net.snake.gamemodel.map.response.TeleportStatusOpen10300;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.gamemodel.monster.logic.SceneFeastMonster;
import net.snake.gamemodel.monster.logic.SceneRefreshMonsterController;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.message.ResponseMsg;
import net.snake.shell.Options;

/**
 * 地图
 * 
 * @author serv_dev
 * 
 */
@SuppressWarnings("unchecked")
public class GameMap extends BaseMapInfo implements Cloneable {

	private static Logger logger = Logger.getLogger(GameMap.class);

	// 当场景中没有人时，每10秒才执行一次场景刷新
	// protected SafeTimer ignoretimeWhennotExistCharacter = new
	// SafeTimer(10000);
	protected SafeTimer goodsupdatetimer = new SafeTimer(1000);
	protected SceneRefreshMonsterController refreshMonsterController = new SceneRefreshMonsterController(this);
	// private Instance instance;
	private InstanceController instanceController;
	private Map<String, Object> scriptPropertiesMap = new HashMap<String, Object>();
	private static final int[] heedSceneObjects = { SceneObj.SceneObjType_Hero, SceneObj.SceneObjType_Horse, SceneObj.SceneObjType_MonsterScene,
			SceneObj.SceneObjType_MonsterBangqi, SceneObj.SceneObjType_MonsterFactionCt, SceneObj.SceneObjType_DropedGood, SceneObj.SceneObjType_MonsterFeast,
			SceneObj.SceneObjType_MonsterXuanyuan };
	private SceneObjManager sceneObjManager = new SceneObjManager(heedSceneObjects);

	/** 由隐藏变为非隐藏的传送点 */
	private Set<MapTeleport> teleportsHideToOpens = new HashSet<MapTeleport>();// 所有的传送点
	// private LocationMonitor locationMonitor = new LocationMonitor();
	// 当前角色数，因为concurrenthashmap.size()方法比较耗时，所以加此变量,进入场景的调用都在游戏循环中，所以离开场景不会有同步的问题
	// private volatile int playercount = 0;
	// 最后一个玩家离开场景的时间
	private SceneManager sceneManager;
	// private IGameSceneManager gameSceneManager;
	// 是否清除pk保护
	private boolean clearPkProtect = false;

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public SceneRefreshMonsterController getRefreshMonsterController() {
		return refreshMonsterController;
	}

	/**
	 * 获取地图线路
	 * 
	 * @return
	 */
	public int getLineId() {
		return sceneManager.getVlineServer().getLineid();
	}

	/** 创建副本场景，其实就是复制 */
	public Object clone() throws CloneNotSupportedException {
		GameMap t = (GameMap) super.clone();// 用于备份BaseMapInfo里的东西
		// ================
		t.setInstanceCount(0);
		t.refreshMonsterController = new SceneRefreshMonsterController(t);
		t.teleportsHideToOpens = new HashSet<MapTeleport>();
		// // =================
		// t.sceneMonsterMap = new ConcurrentHashMap<Integer, SceneMonster>();
		// t.characterMap = new ConcurrentHashMap<Integer, Character>();
		// t.sceneDropGoodMap = new ConcurrentHashMap<Integer, SceneDropGood>();
		// t.horseMap = new ConcurrentHashMap<Integer, Horse>();
		sceneObjManager = new SceneObjManager(heedSceneObjects);
		// ==================================
		t.sceneManager = null;
		// t.locationMonitor = new LocationMonitor();
		// t.playercount = 0;
		t.scriptPropertiesMap = new HashMap<String, Object>();
		// t.ignoretimeWhennotExistCharacter=new SafeTimer(10000);
		t.goodsupdatetimer = new SafeTimer(1000);
		t.clearPkProtect = false;

		t.useItemListeners = new ArrayList<IUseItemListener>();

		t.sceneListeners = new ArrayList<ISceneListener>();

		t.stateListeners = new ArrayList<IStateListener>();

		t.hurtListeners = new ArrayList<IHurtListener>();

		t.attackListeners = new ArrayList<IAttackListener>();
		t.shockListeners = new ArrayList<IShockListener>();
		t.buffListneers = new ArrayList<IBuffListneer>();

		SceneListenerFactory.setSceneUseItemListener(t);
		return t;
	}

	public Scene createInstanceScene(int instanceLevel) throws CloneNotSupportedException {
		GameMap t = (GameMap) this.clone();
		t.sceneManager = this.sceneManager;
		for (SceneMonster sceneMonster : allMonsterWillAddToScene) {
			t.enterScene((SceneMonster) sceneMonster.clone());
		}
		return t;
	}

	public void initInstanceSceneMonster() {
		if (allMonsterWillAddToScene == null || allMonsterWillAddToScene.size() == 0)
			return;
		for (SceneMonster sceneMonster : allMonsterWillAddToScene) {
			try {
				SceneMonster monster = (SceneMonster) sceneMonster.clone();
				monster.init();
				this.enterScene(monster);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 初始化对boss有加成属性的怪物
	 * 
	 * @param dropJiacheng
	 * @param shuxingjiacheng
	 */
	public void initInstanceSceneMonster(int dropJiacheng, int shuxingjiacheng) {
		int value = shuxingjiacheng / 10000;
		for (SceneMonster sceneMonster : allMonsterWillAddToScene) {
			try {
				SceneMonster monster = (SceneMonster) sceneMonster.clone();
				monster.init();
				this.enterScene(monster);
				if (monster.isBoss()) {
					monster.setDropGoodJiacheng(dropJiacheng);
					monster.setAtkColdtime(monster.getAtkColdtime() * value);
					monster.setCrt(monster.getCrt() * value);
					monster.setAttack(monster.getAttack() * value);
					monster.setDefence(monster.getDefence() * value);
					monster.setDodge(monster.getDodge() * value);
					monster.setMoveSpeed(monster.getMoveSpeed() * value);
				}
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 全局广播消息
	 */
	@Override
	public void broadcastMsg(ResponseMsg response) {
		Collection<Hero> connection = sceneObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(connection, response);
	}

	/**
	 * 全局广播消息
	 */
	@Override
	public void broadcastMsgExclude(Hero player, ResponseMsg response) {
		Collection<Hero> connection = sceneObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(connection, player, response);
	}

	@Override
	public void enterScene(Hero player) {
		player.onEnterScene(this);
		// 补充隐藏传送点
		if (teleportsHideToOpens.size() > 0) {
			for (MapTeleport telport : teleportsHideToOpens) {
				player.sendMsg(new TeleportStatusOpen10300(telport, CommonUseNumber.byte1));
			}
		}
		sceneObjManager.addVisibleObj(player);
		// playercount = sceneObjManager.getVisibleObjsCount(Character.class);
		// characterMap.put(player.getId(), player);
		// playercount = characterMap.size();
		// player.getFollowMeController().onMyselfEnterScene(this);
		if (this.getInstanceModelId() != 0) {
			this.getInstanceController().onEnterInstance(player);
		}

		// 每个场景都有特殊的逻辑,当有人进来时就开始吧。
		int size = this.sceneListeners.size();
		for (int i = 0; i < size; i++) {
			sceneListeners.get(i).onEnterScene(player, this);
		}
	}

	@Override
	public void leaveScene(Hero character, Scene newScene) {
		int x = character.getX();
		int y = character.getY();

		if (!sceneObjManager.containsVisibleObj(character)) {
			return;
		}
		character.onLeaveScene(this, newScene);
		// characterMap.remove(character.getId());
		sceneObjManager.removeVisibleObj(character);
		int size = sceneListeners.size();
		for (int i = 0; i < size; i++) {
			sceneListeners.get(i).onLeaveScene(character, this, x, y);
		}
	}

	@Override
	public Collection<Hero> getAllCharacters() {
		// return characterMap.values();
		return sceneObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_Hero);
	}

	public void enterScene(SceneMonster scenemonster) {
		scenemonster.onEnterScene(this);
		sceneObjManager.addVisibleObj(scenemonster);
	}

	public void addToRefreshMonsterController(SceneMonster sm) {
		refreshMonsterController.addSceneMonster(sm);
	}

	@Override
	public SceneMonster getSceneMonster(int scenemonsterid) {
		return sceneObjManager.getVisibleObjsByClazzAndId(SceneObj.SceneObjType_MonsterScene, scenemonsterid);
		// return sceneMonsterMap.get(scenemonsterid);
	}

	public void leaveScene(SceneMonster sceneMonster) {
		sceneMonster.onLeaveScene(this, null);
		sceneObjManager.removeVisibleObj(sceneMonster);
		// sceneMonsterMap.remove(sceneMonster.getId());
	}

	@Override
	public void update(long now) {
		try {
			Collection<Hero> cs = getSceneObjByClass(SceneObj.SceneObjType_Hero);
			for (Hero character : cs) {
				if (ClientConfig.HEART_BEAT_CHECK) {
					if ((now - character.lastHeatBeat) > ClientConfig.HeatBeatInterval2) {
						// 超过2分钟的玩家直接剔除,目前客户端每30秒发一次心跳,正常情况下其实不会执行到这里,这是一个双重保护措施
						if (character.isEnableHeatBeat()) {
							character.getDownLineManager().sceneTimeoutDownLine();
							sceneObjManager.removeVisibleObj(character);
							continue;
						}
					}
				}
				character.update(now);
			}

			Collection<SceneMonster> ms = getSceneObjByClass(SceneObj.SceneObjType_MonsterScene);
			for (SceneMonster scenemonster : ms) {
				scenemonster.update(now);
			}
			Collection<SceneFeastMonster> fms = getSceneObjByClass(SceneObj.SceneObjType_MonsterFeast);
			for (SceneFeastMonster scenemonster : fms) {
				scenemonster.update(now);
			}
			Collection<SceneBangqiMonster> bms = getSceneObjByClass(SceneObj.SceneObjType_MonsterBangqi);
			for (SceneBangqiMonster scenemonster : bms) {
				scenemonster.update(now);
			}
			Collection<SceneFactionCtMonster> fcms = getSceneObjByClass(SceneObj.SceneObjType_MonsterFactionCt);
			for (SceneFactionCtMonster scenemonster : fcms) {
				scenemonster.update(now);
			}

			Collection<SceneXuanyuanMonster> xms = getSceneObjByClass(SceneObj.SceneObjType_MonsterXuanyuan);
			for (SceneXuanyuanMonster scenemonster : xms) {
				scenemonster.update(now);
			}

			if (goodsupdatetimer.isIntervalOK(now)) {
				// 物品只为了消失用，一秒一次
				Collection<SceneDropGood> gs = getSceneObjByClass(SceneObj.SceneObjType_DropedGood);
				for (SceneDropGood goods : gs) {
					goods.update(now);
				}

			}
			if (refreshMonsterController != null) {
				refreshMonsterController.update(now);
			}
			try {
				// ScriptEventCenter.getInstance().onEvtSceneLoop(null, this);
				AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_SceneLoop, new Object[] { this });
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Collection<SceneMonster> getAllSceneMonster() {
		return sceneObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_MonsterScene);
	}

	@SuppressWarnings({ "rawtypes" })
	public Collection getAllCurrentSceneMonster() {
		return sceneObjManager.getVisibleObjsCollection(SceneObj.SceneObjType_MonsterScene);
	}

	/**
	 * 是否存在准备进入场景的怪物
	 * 
	 * @return
	 */
	public boolean isHaveRefreshMonster() {
		return refreshMonsterController.isHaveRefreshMonster();
	}

	@Override
	public int getCharacterCount() {
		return sceneObjManager.getVisibleObjsCount(SceneObj.SceneObjType_Hero);
	}

	@Override
	public boolean isAllMonsterDie() {
		for (SceneMonster sceneMonster : getAllSceneMonster()) {
			if (sceneMonster.getObjectState() != VisibleObjectState.Die) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clearScene() {
		sceneObjManager.clear();
		// sceneMonsterMap.clear();
		// refreshMonsterController = null;
		// locationMonitor.clearHistory();
		// characterMap.clear();
		// playercount = 0;
	}

	@Override
	public void openTeleport(int portid) {
		for (MapTeleport teleport : teleports4set) {
			if (teleport.getId() == portid) {
				if (!teleportsHideToOpens.contains(teleport)) {
					teleportsHideToOpens.add(teleport); // 添加
					broadcastMsg(new TeleportStatusOpen10300(teleport, CommonUseNumber.byte1));
				}
			}
		}
	}

	public boolean closeTeleport() {

		boolean b = false;
		for (Teleport teleport : getTeleports4set()) {
			if (teleport.isHideTelePort() && this.isOpenedTeleport(teleport)) {
				teleportsHideToOpens.remove((MapTeleport) teleport); // 添加
				broadcastMsg(new TeleportStatusOpen10300((MapTeleport) teleport, CommonUseNumber.byte0));
				b = true;
			}
		}
		return b;
	}

	/** 初始化地图上的怪物 */
	public void initSceneMonster() {
		if (!isInstanceScene()) {
			long now = System.currentTimeMillis();
			for (SceneMonster sceneMonster : allMonsterWillAddToScene) {
				if (sceneMonster.isReadyShow(now)) {
					enterScene(sceneMonster);
				} else {
					addToRefreshMonsterController(sceneMonster);
				}
			}
		}
	}

	@Override
	public boolean isOpenedTeleport(Teleport teleport) {
		// TODO
		if (teleport.isHideTelePort()) {// 有,但是是隐藏传送点到
			if (teleportsHideToOpens.contains(teleport)) {// 还没有打开
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void setAttribute(String key, Object value) {
		scriptPropertiesMap.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return scriptPropertiesMap.get(key);
	}

	@Override
	public Object removeAttribute(String key) {
		return scriptPropertiesMap.remove(key);
	}

	@Override
	public void removeAllAttribute() {
		scriptPropertiesMap.clear();
	}

	@Override
	public void leaveScene(SceneDropGood sceneDropGood) {
		sceneDropGood.onLeaveScene(this, null);
		sceneObjManager.removeVisibleObj(sceneDropGood);
	}

	@Override
	public void enterScene(SceneDropGood sceneDropGood) {
		sceneDropGood.onEnterScene(this);
		sceneObjManager.addVisibleObj(sceneDropGood);
	}

	@Override
	public SceneDropGood getSceneDropGood(int dropGoodId) {
		return sceneObjManager.getVisibleObjsByClazzAndId(SceneObj.SceneObjType_DropedGood, dropGoodId);
	}

	@Override
	public int getMonsterCount() {
		return sceneObjManager.getVisibleObjsCount(SceneObj.SceneObjType_MonsterScene);
	}

	@Override
	public int getPvpModel() {
		return pvpModel;
	}

	@Override
	public int getHorseCount() {
		return sceneObjManager.getVisibleObjsCount(SceneObj.SceneObjType_Horse);
		// return horseMap.size();
	}

	public InstanceController getInstanceController() {
		if (instanceController == null) {
			this.instanceController = getSceneManager().getInstanceControllerById(instanceModelId);
		}
		return instanceController;
	}

	public void setInstanceController(InstanceController instanceController) {
		this.instanceController = instanceController;
	}

	public SInstance getInstance() {
		return this.instanceController;
	}

	@Override
	public boolean openHideTeleport() {
		boolean b = false;
		for (Teleport teleport : getTeleports4set()) {
			if (teleport.isHideTelePort()) {
				if (!teleportsHideToOpens.contains(teleport)) {
					teleportsHideToOpens.add((MapTeleport) teleport); // 添加
					broadcastMsg(new TeleportStatusOpen10300((MapTeleport) teleport, CommonUseNumber.byte1));
					b = true;
				}
			}
		}
		return b;
	}

	public void enterSceneToSendHideTeleport(Hero character) {
		if (teleportsHideToOpens.size() == 0) {
			return;
		}
		for (MapTeleport teleport : teleportsHideToOpens) {
			character.sendMsg(new TeleportStatusOpen10300(teleport, CommonUseNumber.byte1));
		}
	}

	/**
	 * 重加载怪物场景中怪物属性数据
	 */
	public void reloadSceneMonster() {
		Collection<SceneMonster> monsters = this.getAllMonsterWillAddToScene();
		if (monsters == null || monsters.size() == 0) {
			return;
		}
		SceneMonsterManager smm = SceneMonsterManager.getInstance();
		for (SceneMonster monster : monsters) {
			SceneMonster sm = smm.getSceneMonsterById(monster.getId());
			if (sm != null) {
				monster.setIs(sm.getIs());
			}
			monster.reloadMonster();
		}
	}

	public void setClearPkProtect(boolean clearPkProtect) {
		this.clearPkProtect = clearPkProtect;
	}

	@Override
	public boolean isClearPkProtect() {
		return clearPkProtect;
	}

	@Override
	public boolean dieAffterRelive(Hero character, boolean isGuard) {

		if (isGuard) {
			try {
				/*
				 * BUFF作用：令自己的配偶获得一个BUFF，该BUFF的作用是当配偶血量变为0时， 立即原地复活（不读倒计时，倒地后立即站起），恢复一定的血量值，免除死亡惩罚一次，并获得3秒钟的无敌时间。 重生后的血量值=夫妻之间的友好度（最低为1000，最大为10000）
				 */
				int minHp = 1000;
				int maxHp = 10000;
				int recoverHp = character.getMyFriendManager().getRoleWedingManager().getPeiouFavor();
				if (recoverHp < minHp) {
					recoverHp = minHp;
				} else if (recoverHp > maxHp) {
					recoverHp = maxHp;
				}
				CharacterResurrect.yuandiFreeResurrectProcess(character, recoverHp);
				character.setObjectState(VisibleObjectState.Idle);
				character.getEffectController().addUnWithstandBuff(3000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return true;
		}

		if (!character.getCharacterOnHoorController().isAutoOnHoor()) {
			character.getFuhuotimer().start();
			return false;
		}
		if (!character.getCharacterOnHoorController().getCharacterOnHoorConfig().getBackUseRose()) {
			character.getFuhuotimer().start();
			// character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
			return false;
		}
		int idmeiguihu = CharacterResurrect.getHaveMeiguiIdInBad(character);
		if (idmeiguihu <= 0) {
			character.getFuhuotimer().start();
			return false;
		}
		character.getEyeShotManager().sendMsg(new CharacterFuhuoDelayMsg20078(character.getId()));
		final Hero character1 = character;
		character1.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterResurrect.yuandiResurrectProcess(character1);
					character1.setObjectState(VisibleObjectState.Idle);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}, (Options.Relive_Timeout + 1) * 1000);
		return false;
	}

	@Override
	public <T> Collection<T> getSceneObjByClass(int class1) {
		return sceneObjManager.getVisibleObjsCollection(class1);
	}

	@Override
	public <T> T getSceneObj(int castType, int id) {
		return sceneObjManager.getVisibleObjsByClazzAndId(castType, id);
	}

	@Override
	public void clearRefreshMonsterController() {
		this.refreshMonsterController.getScenemonsterList().clear();
	}

	@Override
	public short[] getRectanglePoint(short x, short y, int radius) {
		short[] rectanglePoints = new short[4];
		int x1 = x - radius;
		int x2 = x + radius;
		int y1 = y - radius;
		int y2 = y + radius;
		if (x1 < 0) {
			x1 = 0;
		}
		if (y1 < 0) {
			y1 = 0;
		}
		if (x2 > this.getHeight()) {
			x2 = this.getHeight();
		}
		if (y2 > this.getWidth()) {
			y2 = this.getWidth();
		}
		rectanglePoints[0] = (short) x1;
		rectanglePoints[1] = (short) x2;
		rectanglePoints[2] = (short) y1;
		rectanglePoints[3] = (short) y2;
		return rectanglePoints;
	}

	private List<IUseItemListener> useItemListeners = null;// new ArrayList<IUseItemListener>();

	@Override
	public void addUseItemListener(IUseItemListener listener) {
		useItemListeners.add(listener);
	}

	@Override
	public List<IUseItemListener> getUseItemListeners() {
		return useItemListeners;
	}

	private List<ISceneListener> sceneListeners = null;//

	public void addSceneListener(ISceneListener listener) {
		sceneListeners.add(listener);
	}

	public List<ISceneListener> getSceneListeners() {
		return sceneListeners;
	}

	private List<IStateListener> stateListeners = null;// new ArrayList<IStateListener>();

	@Override
	public void addStateListener(IStateListener listener) {
		stateListeners.add(listener);
	}

	@Override
	public List<IStateListener> getStateListeners() {
		return stateListeners;
	}

	private List<IHurtListener> hurtListeners = null;// new ArrayList<IHurtListener>();

	@Override
	public void addHurtListener(IHurtListener listener) {
		hurtListeners.add(listener);
	}

	@Override
	public List<IHurtListener> getHurtListeners() {
		return hurtListeners;
	}

	private List<IAttackListener> attackListeners = null;// new ArrayList<IAttackListener>();

	@Override
	public void addAttackListener(IAttackListener listener) {
		attackListeners.add(listener);
	}

	@Override
	public List<IAttackListener> getAttackListeners() {
		return attackListeners;
	}

	private List<IShockListener> shockListeners = null;

	@Override
	public void addShockListener(IShockListener listener) {
		shockListeners.add(listener);
	}

	@Override
	public List<IShockListener> getShockListeners() {
		return shockListeners;
	}

	private List<IBuffListneer> buffListneers = null;

	@Override
	public void addBuffListener(IBuffListneer listener) {
		buffListneers.add(listener);
	}

	@Override
	public List<IBuffListneer> getBuffListneers() {
		return buffListneers;
	}

}
