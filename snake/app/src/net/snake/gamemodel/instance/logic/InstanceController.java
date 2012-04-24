package net.snake.gamemodel.instance.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.commons.TimeExpression;
import net.snake.commons.program.IntId;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.consts.GoodItemId;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.dujie.logic.DujiePlugin;
import net.snake.gamemodel.dujie.persistence.TianshenDataTbl;
import net.snake.gamemodel.dujie.response.ExistingHufas;
import net.snake.gamemodel.dujie.response.FightingHufazhenResp;
import net.snake.gamemodel.dujie.response.HufaCurrencyResp;
import net.snake.gamemodel.dujie.response.HufaPageListResp;
import net.snake.gamemodel.dujie.response.StartCountdownResp;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.instance.bean.InstanceDataRef;
import net.snake.gamemodel.instance.persistence.FubenrankingManager;
import net.snake.gamemodel.instance.response.InstanceEnterMsg10714;
import net.snake.gamemodel.instance.response.InstanceJishiMsg10724;
import net.snake.gamemodel.instance.response.InstanceRewardMsg10726;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.gamemodel.map.response.EnterMapTishiMsg10052;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;

/**
 * 
 * @author serv_dev
 * 
 */
public class InstanceController implements SInstance {
	private static final Logger logger = Logger.getLogger(InstanceController.class);
	private static IntId intId = new IntId();
	private InstanceDataRef instanceData;
	private List<Scene> list;
	private List<Scene> listInstance;
	// private Map<Integer, int[]> worldPos = new HashMap<Integer, int[]>();

	private int num = intId.getNextId();
	/** 进入副本开始计时时间 */
	private long startEnterTime = 0; //
	/** 单位 秒 0表示没有通关 */
	private int tongguanTime = 0; //
	private long endTime = 0;
	private VLineServer vlineServer;
	/** 倒计时提示 */
	private boolean tishi = false;//
	public boolean isUpdate = false;
	private Map<Integer, Hero> characterMap = new ConcurrentHashMap<Integer, Hero>();
	/** 普通怪物 */
	private int monsterflushCount = 0; //
	/** boss刷新次数 */
	private int bossflushCount = 0;//
	private boolean isTeam = true;
	private Map<Object, Object> propertisMap = new ConcurrentHashMap<Object, Object>();
	private boolean isloop = true;

	private InstancePlugin plugin = null;

	public InstanceController(InstanceDataRef instanceData, List<Scene> list) {
		this.instanceData = instanceData;
		this.list = list;

		int model = instanceData.getInstanceModelId();
		if (model == 9) {
			this.setPlugin(new DujiePlugin());
		}
	}

	public InstanceDataRef getInstanceData() {
		return instanceData;
	}

	public void setInstanceData(InstanceDataRef instanceData) {
		this.instanceData = instanceData;
	}

	public List<Scene> getList() {
		return list;
	}

	public void setList(List<Scene> list) {
		this.list = list;
	}

	/**
	 * 是否符合打开副本的条件
	 * 
	 * @param character
	 *            角色
	 * @return
	 */
	public ServerResponse isOpenCondition(Hero character) {
		if (instanceData.getEnable().equals(0)) {// 是否是可用的副本
			// 发送副本未开启的消息 确认消息内容
			// character.sendMsg(MsgHelper.failReasonMsg(10020, ));
			return new PrompMsg(1044);
		}
		String opentime = instanceData.getOpenTimeLimite();
		if (opentime != null && opentime.length() > 0) {
			TimeExpression time = new TimeExpression(instanceData.getOpenTimeLimite());
			if (!time.isExpressionTime(System.currentTimeMillis())) {
				if (instanceData.getInstanceopentimedescripI18n() != null && instanceData.getInstanceopentimedescripI18n().length() > 0) {
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1066, instanceData.getInstanceopentimedescripI18n() + "");// 副本开启时间全天
				} else {
					return new PrompMsg(1044);// 未到副本开启时间
				}
			}
		}
		if (instanceData.getTeamLimite() == 2) {// 限制单人副本
			if (!character.getMyTeamManager().isTeam()) {
				return new PrompMsg(1045);// 很抱歉,该副本必须组队才能开启,单人无法开启
			}
			if (character.getMyTeamManager().getMyTeam().getTeamPopulation() < instanceData.getPopulationLowerLimit()) {
				//
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1046, instanceData.getPopulationLowerLimit() + "");
			}
			return teamOpenCondition(character);
		} else if (instanceData.getTeamLimite() == 1) {// 限制组队副本
			if (character.getMyTeamManager().isTeam()) {
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1065);// 组队玩家无法进入该副本
			}
			return oneOpenCondition(character);
		} else {
			if (character.getMyTeamManager().isTeam()) {
				return teamOpenCondition(character);
			} else {
				return oneOpenCondition(character);
			}
		}
	}

	/**
	 * 组队时是否打开副本条件
	 * 
	 * @param character
	 * @return
	 */
	private ServerResponse teamOpenCondition(Hero character) {
		if (character.getGrade() < instanceData.getLevelLowerLimit()) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1047, character.getViewName(), instanceData.getLevelLowerLimit() + "");// 组队成员等级限制
		}
		Scene scene = character.getSceneRef();
		if (scene != null && scene.isInstanceScene()) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1048, character.getViewName());// 队员已经在副本中
		}
		if (instanceData.getTaskList() != null && instanceData.getTaskList().size() > 0) {
			List<Task> taskList = instanceData.getTaskList();
			CharacterTaskController taskController = character.getTaskController();
			for (int i = 0; i < taskList.size(); i++) {
				Task task = taskList.get(i);
				int taskId = task.getTaskId();
				if (!taskController.isCurrentTasksContain(taskId)) {
					if (taskController.isTerminativeTasksContain(taskId)) {
						return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1049, character.getViewName(), task.getNameI18n());
					}
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1050, character.getViewName(), task.getNameI18n());
				}
			}
		}
		// 进入次数限制

		List<CharacterGoods> goodList = instanceData.getGoodList();
		if (goodList != null && goodList.size() > 0) {
			for (int i = 0; i < goodList.size(); i++) {
				CharacterGoods cg = goodList.get(i);
				int count = character.getCharacterGoodController().getBagGoodsCountByModelID(cg.getGoodmodelId());
				if (count < cg.getCount()) {
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1051, character.getViewName(), cg.getCount() + "", cg.getGoodModel().getNameI18n());
				}
			}
		}
		return null;
	}

	/**
	 * 单人进入副本条件
	 * 
	 * @param character
	 * @return
	 */
	private ServerResponse oneOpenCondition(Hero character) {
		if (character.getGrade() < instanceData.getLevelLowerLimit()) {
			//
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1052, instanceData.getLevelLowerLimit() + "");
		}
		Scene scene = character.getSceneRef();
		if (scene != null && scene.isInstanceScene()) {
			return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1053);
		}
		if (instanceData.getTaskList() != null && instanceData.getTaskList().size() > 0) {
			List<Task> taskList = instanceData.getTaskList();
			CharacterTaskController taskController = character.getTaskController();
			for (int i = 0; i < taskList.size(); i++) {
				Task task = taskList.get(i);
				int taskId = task.getTaskId();
				if (!taskController.isCurrentTasksContain(taskId)) {
					if (taskController.isTerminativeTasksContain(taskId)) {
						// 很抱歉,队伍内的成员$,已达成了任务$,无法再次进入该副本
						return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1054, task.getNameI18n());
					}
					// 很抱歉,队伍内的成员$,未接取任务:$
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1055, task.getNameI18n());
				}
			}
		}
		List<CharacterGoods> goodList = instanceData.getGoodList();// 很抱歉,进入该副本需要$个$,您还没有备齐
		if (goodList != null && goodList.size() > 0) {
			for (int i = 0; i < goodList.size(); i++) {
				CharacterGoods cg = goodList.get(i);
				int count = character.getCharacterGoodController().getBagGoodsCountByModelID(cg.getGoodmodelId());
				if (count < cg.getCount()) {
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1056, cg.getCount() + "", cg.getGoodModel().getNameI18n());
				}
			}
		}
		return null;
	}

	public boolean isIsloop() {
		return isloop;
	}

	public void setIsloop(boolean isloop) {
		this.isloop = isloop;
	}

	/**
	 * 是否免费进入 true 是
	 * 
	 * @param character
	 * @param isConmus
	 * @return
	 */
	public ServerResponse enterCountCondition(Hero character, boolean isFree) {
		// 进入次数限制
		if (instanceData.getEnterCountLimite() == 0) {
			return null;
		}
		int count = character.getMyInstanceDayStatManager().getTodayResetCount(instanceData.getInstanceModelId());
		if (!isFree) {
			int countSheyu = getEnterFreeMaxCount(character) - count;
			if (countSheyu > 0) {
				return null;
			} else {
				int fubenlingCount = character.getMyInstanceDayStatManager().getTodayFubenLingCount(instanceData.getInstanceModelId());
				if (instanceData.getFubenlingLimite() <= fubenlingCount) {
					if (instanceData.getFubenlingLimite() == 0) {
						// 该副本不允许使用副本令进入
						return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1057);
					}
					// 该副本今日可使用副本令最多进入$次
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1058, instanceData.getFubenlingLimite() + "");
				}
				int goodCount = getuseGoodCount(fubenlingCount);
				int cgCount = character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(GoodItemId.FUBENLING_ID);
				if (cgCount < goodCount) {
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1059);
				}
				return null;
			}
		}
		int i = 0;
		if (character.getMyCharacterVipManger().isVipYueka()) {// vip总是比别人多一次
			i = 1;
			count--;
		}
		if (count >= instanceData.getEnterCountLimite()) {
			if (character.getMyTeamManager().isTeam()) {
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1060, character.getViewName());// 很抱歉,队伍内的成员$,已经超过今日免费进入次数
			}
			if (instanceData.getInstanceModelId() == 9) {
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60064, "2");
			} else {
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1061, (instanceData.getEnterCountLimite() + i) + "");
			}

		}
		return null;
	}

	/**
	 * 进入副本是否消耗物品 如果消耗物品 扣除物品
	 * 
	 * @param character
	 * @param isConmus
	 * @return
	 */
	public ServerResponse enterInstanceTakeGood(Hero character) {
		// 进入次数限制
		int enterCount = character.getMyInstanceDayStatManager().getTodayResetCount(instanceData.getInstanceModelId());
		int freeCount = getEnterFreeMaxCount(character);
		if (freeCount == 0) {
			return null;
		}
		if (enterCount < freeCount) {
			return null;
		} else {
			int fubenlingCount = character.getMyInstanceDayStatManager().getTodayFubenLingCount(instanceData.getInstanceModelId());
			if (instanceData.getFubenlingLimite() <= fubenlingCount) {
				if (instanceData.getFubenlingLimite() == 0) {
					return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1062);
				}
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1063, instanceData.getFubenlingLimite() + "");
			}
			int goodCount = getuseGoodCount(fubenlingCount);
			boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(GoodItemId.FUBENLING_ID, goodCount);
			if (!b) {
				return new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1064);
			}
			character.getMyCharacterInstanceManager().setUseFubenLing(b);
			return null;
		}
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Object clone() throws CloneNotSupportedException {
		InstanceController t = new InstanceController(this.instanceData, this.list);
		t.setUpdate(false);
		List<Scene> newlist = new ArrayList<Scene>();
		t.setListInstance(newlist);
		if (plugin != null) {
			t.setPlugin(plugin.newAnother());
		}
		return t;
	}

	public void setListInstance(List<Scene> listInstance) {
		this.listInstance = listInstance;
	}

	/**
	 * 根据地图id获取玩家副本中地图（如果玩家副本中没有该地图 则进行初始化该地图(此地图只在本副本中存在 不与其他副本共享)）
	 * 
	 * @param sceneId
	 * @return
	 */
	public Scene getSceneBySceneId(int sceneId) {
		if (listInstance.size() != 0) {
			for (Scene scene : listInstance) {
				if (scene.getId() == sceneId) {
					return scene;
				}
			}
		}
		for (Scene scene : list) {
			if (scene.getId() == sceneId) {
				try {
					Scene tempScene = (Scene) scene.clone();
					tempScene.setInstanceController(this);
					listInstance.add(tempScene);
					return tempScene;
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	/**
	 * 最对进入副本 调用此方法 进行进入副本操作
	 * 
	 * @param sceneId
	 * @param team
	 * @param vlineserver
	 */
	public void teamEnterInstance(int sceneId, Team team, VLineServer vlineserver) {
		List<Hero> list = team.getCharacterPlayers();
		Scene scene = this.getSceneBySceneId(sceneId);
		if (scene == null) {
			team.sendTeamMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 671), null);
			return;
		}
		initInstance(vlineserver);
		for (Hero character : list) {
			enterInstance(scene, character);
		}

	}

	/**
	 * 单人进入副本调用此方法 进行进入副本操（单人进入副本前的准备操作）
	 * 
	 * @param sceneId
	 * @param character
	 */
	public void oneEnterInstance(int sceneId, Hero character) {
		Scene scene = this.getSceneBySceneId(sceneId);
		if (scene == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 671));
			return;
		}
		character.sendMsg(new InstanceEnterMsg10714());
		initInstance(character.getVlineserver());
		enterInstance(scene, character);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceInit, new Object[] { this });
	}

	/**
	 * 初始化进入副本数据
	 */
	private void initInstance(VLineServer vlineserver) {
		endTime = System.currentTimeMillis() + instanceData.getLifecycle() * 1000;
		this.vlineServer = vlineserver;

		if (instanceData.getLifecycle() <= 600) {
			tishi = true;
		}
	}

	/**
	 * 通过此方法调用切换场景方法 进入副本操作(进入副本场景前 通知客户端切换场景)
	 * 
	 * @param scene
	 * @param character
	 */
	private void enterInstance(Scene scene, Hero character) {

		short[] point = scene.getRandomPoint(scene.getEnterX(), scene.getEnterY(), 7);
		character.getMyCharacterInstanceManager().setInstanceTemp(null);
		ExchangeMapTrans.trans(scene, point[0], point[1], character);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getSceneCollection() {
		return listInstance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getInstanceAllCharacters() {
		return characterMap.values();
	}

	/**
	 * 离开副本调用此方法 清楚角色该副本中信息
	 * 
	 * @param character
	 */
	public void onleaveInstance(Hero character) {
		characterMap.remove(character.getId());
		character.getMyCharacterInstanceManager().leaveInstance();
		isColseInstance();

	}

	/**
	 * 角色进入副本 进入地图后 调用此方法
	 * 
	 * @param character
	 */
	public void onEnterInstance(Hero character) {
		characterMap.put(character.getId(), character);
		DownLineRoleInstanceController downLine = vlineServer.getRuningInstanceManager().getDownLineRoleInstanceController(character.getId());
		if (downLine != null) {
			checkReenter(character);
			downLine.changeToInstnaceLoop(character);
			// long time = System.currentTimeMillis() - startEnterTime;
			// danrenEnterFubenTimeMsg(character, (int) time);
		} else {
			if (!isloop && isUpdate) {
				character.getMyCharacterInstanceManager().firstEnterScene();
				return;
			}
		}

		character.getMyCharacterInstanceManager().setInstanceController(this);
		scripInitScene(character);
		if (!isUpdate) {
			isUpdate = true;
			startEnterTime = System.currentTimeMillis();
			vlineServer.getRuningInstanceManager().addInstanceController(this);
			initIsDanrenFuben(character);
			// danrenEnterFubenTimeMsg(character, 0);
		}

		if (this.getAttribute("isFirstEnter" + character.getId()) == null) {
			character.getMyInstanceDayStatManager().setInstanceID(this.getInstanceId(), character.getMyCharacterInstanceManager().isUseFubenLing());
			this.setAttribute("isFirstEnter" + character.getId(), false);
		}
	}

	public void initIsDanrenFuben(Hero character) {
		if (!character.getMyTeamManager().isTeam()) {
			isTeam = false;
		}
	}

	/**
	 * 单人进入副本 副本通关计时开始
	 * 
	 * @param character
	 */
	private void danrenEnterFubenTimeMsg(Hero character, int time) {
		if (this.instanceData.getInstanceModelId() == 9 || this.instanceData.getInstanceModelId() == 16) {
			return;
		}
		if (!isTeam) {
			Fubenranking fuben = character.getMyFubenTimeRecordManager().getFubenrankingByInstanceId(instanceData.getInstanceModelId());
			Fubenranking one = FubenrankingManager.getInstance().getFubenRankingTopOne(this.getInstanceId());
			character.sendMsg(new InstanceJishiMsg10724(this, fuben, one, character, time));
		}
	}

	/**
	 * 角色进入副本地图 脚本调用
	 * 
	 * @param character
	 */
	public void scripInitScene(Hero character) {
		// TODO 年轮地图允许重复进入
		if (character.getSceneRef().getInstanceCount() < 1 || this.getInstanceId() == 23) {
			// ScriptEventCenter.getInstance().onInstanceSceneInit(null, this,
			// character.getSceneRef());
			AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneInit, new Object[] { this, character.getSceneRef() });
		}
		// ScriptEventCenter.getInstance().onInstanceSceneEnter(null, this,
		// character.getSceneRef(), character);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneEnter, new Object[] { this, character.getSceneRef(), character });
		character.getSceneRef().enterSceneToSendHideTeleport(character);
		character.getSceneRef().setInstanceCount(character.getSceneRef().getInstanceCount() + 1);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * 副本轮循更新方法
	 * 
	 * @param now
	 */
	public void update(long now) {
		int instanceType = instanceData.getInstanceModelId();
		if (instanceType != 16) {
			long chatime = endTime - now;
			if (chatime <= 0) {
				instanceClose();
				return;
			}
			if (chatime < 1000 * 60 * 10 && !tishi) {
				tishi = true;
				tishi();
			}
		}

		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceLoop, new Object[] { this });
		if (listInstance.isEmpty()) {
			isColseInstance();
			return;
		}
		for (Scene scene : listInstance) {
			int count = scene.getAllCharacters().size();
			if (count > 0) {
				AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneLoop, new Object[] { this, scene });
				scene.update(now);

			}
		}
		if (plugin != null) {
			plugin.flush(now, this);
		}

		// if (chatime < 1000 * 60 * 10 && !tishi) {
		// tishi = true;
		// tishi();
		// }
		isColseInstance();
		return;
	}

	/**
	 * 副本结束10分钟提示消息发送
	 */
	private void tishi() {
		for (Scene scene : listInstance) {
			Collection<Hero> collection = scene.getAllCharacters();
			if (collection.size() == 0) {
				continue;
			}
			for (Hero c : collection) {
				if (c != null) {
					c.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 672));
				}
			}
		}
	}

	/**
	 * 调用副本是否关闭副本方法
	 */
	private void isColseInstance() {
		if (characterMap.size() <= 0) {
			instanceClose();
		}
	}

	/**
	 * 副本关闭方法
	 */
	private void instanceClose() {
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceDestory, new Object[] { this });
		for (Hero character : characterMap.values()) {
			character.getMyCharacterInstanceManager().exitInstance2World(character.worldPos[0], character.worldPos[1], character.worldPos[2]);
			// character.getMyCharacterInstanceManager().endInstanceToXiangyang();
		}
		if (vlineServer != null) {
			characterMap.clear();
			vlineServer.getRuningInstanceManager().removeInstanceController(num);
		}
	}

	@Override
	public void setAttribute(String key, Object value) {
		propertisMap.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return propertisMap.get(key);

	}

	@Override
	public Object removeAttribute(String key) {
		propertisMap.remove(key);
		return null;
	}

	@Override
	public void removeAllAttribute() {
		propertisMap.clear();
	}

	/**
	 * 副本中地图切换
	 * 
	 * @param character
	 * @param teleport
	 */
	public void instanceExChangeScene(Hero character, Teleport teleport) {
		Scene scene = this.getSceneBySceneId(teleport.getTargetSceneID());
		if (scene == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 671));
			return;
		}
		// ScriptEventCenter.getInstance().onEvtInstanceSceneExChange(null,
		// this,
		// scene, character, false, teleport);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneExChange, new Object[] { this, scene, character, false, teleport });
		String instanceMsg = character.getMyCharacterInstanceManager().getInstanceMsg();
		if (instanceMsg != null && !instanceMsg.equals("")) {
			character.sendMsg(new EnterMapTishiMsg10052(teleport.getX(), teleport.getY(), instanceMsg));
			character.getMyCharacterInstanceManager().changeInstanceEnterInfo(null, true);
			return;
		}
		if (!character.getMyCharacterInstanceManager().isAbleEnter()) {
			character.getMyCharacterInstanceManager().setAbleEnter(true);
			return;
		}
		short[] point = scene.getRandomPoint(teleport.getTargetX(), teleport.getTargetY(), 7);
		ExchangeMapTrans.trans(scene, point[0], point[1], character);
	}

	/**
	 * 确认可以扣除进入地图条件的代价
	 * 
	 * @param character
	 * @param teleport
	 */
	public void instanceExChangeSceneSure(Hero character, Teleport teleport) {
		Scene scene = this.getSceneBySceneId(teleport.getTargetSceneID());
		if (scene == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 671));
			return;
		}
		// ScriptEventCenter.getInstance().onEvtInstanceSceneExChange(null,
		// this,
		// scene, character, true, teleport);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneExChange, new Object[] { this, scene, character, true, teleport });

		String instanceMsg = character.getMyCharacterInstanceManager().getInstanceMsg();
		if (instanceMsg != null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 982, instanceMsg));
			character.getMyCharacterInstanceManager().changeInstanceEnterInfo(null, true);
			return;
		}
		if (!character.getMyCharacterInstanceManager().isAbleEnter()) {
			character.getMyCharacterInstanceManager().setAbleEnter(true);
			return;
		}
		short[] point = scene.getRandomPoint(teleport.getTargetX(), teleport.getTargetY(), 7);
		ExchangeMapTrans.trans(scene, point[0], point[1], character);
	}

	/**
	 * 副本中怪物死亡调用此方法
	 * 
	 * @param monster
	 */
	public void monsterDie(final SceneMonster monster) {
		// ScriptEventCenter.getInstance().onEvtInstanceMonsterDie(null, this,
		// monster);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceMonsterDie, new Object[] { this, monster });
	}

	public int getMonsterflushCount() {
		return monsterflushCount;
	}

	public void setMonsterflushCount(int monsterflushCount) {
		this.monsterflushCount = monsterflushCount;
	}

	public int getBossflushCount() {
		return bossflushCount;
	}

	public void setBossflushCount(int bossflushCount) {
		this.bossflushCount = bossflushCount;
	}

	public void changeBossflushCount(int count) {
		this.bossflushCount = this.bossflushCount + count;
	}

	public void changeMonsterflushCount(int count) {
		this.monsterflushCount = this.monsterflushCount + count;
	}

	public int getInstanceMonsterId(int type, int grade) {
		return getInstanceMonsterId(type, grade, 0);
	}

	@Override
	public int getInstanceId() {
		return instanceData.getInstanceModelId();
	}

	/**
	 * 每日免费进入最大次数
	 * 
	 * @param character
	 * @return
	 */
	public int getEnterFreeMaxCount(Hero character) {
		if (character.getMyCharacterVipManger().isVipYueka()) {
			return instanceData.getEnterCountLimite() + 1;
		}
		return instanceData.getEnterCountLimite();
	}

	/**
	 * 根据进入次数计算物品使用个数
	 * 
	 * @param freeCount
	 *            允许免费进入次数
	 * @param enterCount
	 *            已经进入次数
	 * @return
	 */
	public int getuseGoodCount(int fubenlingCount) {
		return fubenlingCount + 1;
	}

	/**
	 * 副本通关，发送通关奖励（全部通关）
	 */
	public void finishiInstance() {
		for (Hero character : characterMap.values()) {
			finishiInstance(character);
		}
	}

	/**
	 * 角色是否通关
	 * 
	 * @param character
	 * @return
	 */
	public boolean isTongGuan(SRole character) {
		String tongguanRole = character.getId() + "_tongguan";
		Object tongGuan = propertisMap.get(tongguanRole);
		if (tongGuan != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加角色通关标识
	 */
	public void addRoleTongguan(SRole roleC) {
		String tongguanRole = roleC.getId() + "_tongguan";
		propertisMap.put(tongguanRole, System.currentTimeMillis());
	}

	/**
	 * 副本通关，发送通关奖励,一个玩家.
	 */
	public void finishiInstance(SRole role) {
		Hero roleC = characterMap.get(role.getId());

		if (roleC == null) {
			return;
		}
		if (isTongGuan(roleC)) {
			return;
		}
		addRoleTongguan(roleC);
		if (!isTeam) {
			long time = System.currentTimeMillis() - startEnterTime;
			tongguanTime = (int) (time / 1000);
			roleC.getMyFubenTimeRecordManager().addOrUpdateFubenRanking(this.getInstanceData().getInstanceModelId(), tongguanTime);
		}
		roleC.getDayInCome().dealFinshInstance(1);
		createRewardGoods(roleC);
	}

	/**
	 * 产生通关奖励物品
	 */
	public void createRewardGoods(Hero character) {
		List<CharacterGoods> reward = instanceData.getRewardGoodList();
		if (reward == null || reward.size() <= 0) {
			return;
		}
		if (character != null) {
			character.getMyCharacterInstanceManager().setRewardGoodList(reward);
			character.sendMsg(new InstanceRewardMsg10726(reward, tongguanTime * 1000));
			character.getMyCharacterAchieveCountManger().getInstanceCount().instanceFinishiCount(this);
		}
	}

	public long getStartEnterTime() {
		return startEnterTime;
	}

	// public void setStartEnterTime(long startEnterTime) {
	// this.startEnterTime = startEnterTime;
	// }

	public int getTongguanTime() {
		return tongguanTime;
	}

	// public void setTongguanTime(int tongguanTime) {
	// this.tongguanTime = tongguanTime;
	// }

	/**
	 * 副本内地图传送
	 * 
	 * @param sceneid
	 * @param x
	 * @param y
	 * @param role
	 */
	public void transToInstanceScene(int sceneid, short x, short y, Hero role) {
		Scene scene = this.getSceneBySceneId(sceneid);
		if (scene == null) {
			role.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 671));
			return;
		}
		short[] point = scene.getRandomPoint(x, y, 7);
		ExchangeMapTrans.trans(scene, point[0], point[1], role);
	}

	/**
	 * 下线离开副本
	 * 
	 * @param character
	 */
	public void onleaveInstanceWhenDownLine(Hero character) {
		onleaveInstance(character);
		DownLineRoleInstanceController downLine = new DownLineRoleInstanceController(this, character, this.vlineServer);
		vlineServer.getRuningInstanceManager().addDownLineRoleInstanceController(downLine);
	}

	public void flushMonster(SScene sscene, int dropAdd, int propAdd) {
		Scene scene = (Scene) sscene;
		scene.initInstanceSceneMonster(dropAdd, propAdd);
	}

	public void setPlugin(InstancePlugin plugin) {
		this.plugin = plugin;
	}

	public void missionComplete() {
		for (Hero character : characterMap.values()) {
			finishiInstance(character);
		}
	}

	public void missionFailed() {
		// for (Hero character : characterMap.values()) {
		// character.getMyCharacterInstanceManager().endInstanceToXiangyang();
		// }

		for (Hero character : characterMap.values()) {
			character.getMyCharacterInstanceManager().exitInstance2World(character.worldPos[0], character.worldPos[1], character.worldPos[2]);
		}
	}

	public InstancePlugin getPlugin() {
		return this.plugin;
	}

	public void checkReenter(Hero hero) {
		if (this.getInstanceId() != 9) {
			return;
		}
		Object flushed = this.getAttribute("flushed");
		if (flushed == null) {
			long now = System.currentTimeMillis();
			long diff = now - startEnterTime;
			if (diff < 300000) {
				StartCountdownResp countdownResp = new StartCountdownResp((int) (300000 - diff), 1);
				hero.sendMsg(countdownResp);
			}

			HufaCurrencyResp currencyResp = new HufaCurrencyResp();
			hero.sendMsg(currencyResp);

			Hufazhen zhen = plugin.getHufazhen();
			int zhenId = 0;
			if (zhen != null) {
				zhenId = zhen.getId();
			}
			GuardImg[] guardImgs = plugin.getAllGuardImgs();
			ExistingHufas existingHufas = new ExistingHufas();
			try {
				existingHufas.setData(zhenId, guardImgs);
				hero.sendMsg(existingHufas);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// super
			List<Tianshen> ts = TianshenDataTbl.getInstance().getPagedTianshens(1);
			HufaPageListResp resp = new HufaPageListResp(1);
			resp.setTianshens(GuardImg.Guards_Super, ts);
			hero.sendMsg(resp);
			// advance
			List<GuardImg> adv = plugin.advanceGuards();
			if (adv != null) {
				HufaPageListResp resp2 = new HufaPageListResp(1);
				resp2.setHeros(GuardImg.Guards_Advanced, adv, hero.getDujieCtrlor().currentTianjieNum());
				hero.sendMsg(resp2);
			}
			// normal
			List<GuardImg> normals = plugin.normalGuards();
			if (normals != null) {
				HufaPageListResp resp3 = new HufaPageListResp(1);
				resp3.setHeros(GuardImg.Guards_Normal, normals, hero.getDujieCtrlor().currentTianjieNum());
				hero.sendMsg(resp3);
			}
		} else {
			GuardImg[] guardImgs = plugin.getAllGuardImgs();
			FightingHufazhenResp fightingHufazhenResp = new FightingHufazhenResp();
			try {
				int zhenfaId = 0;
				Hufazhen zhen = plugin.getHufazhen();
				if (zhen != null) {
					zhenfaId = zhen.getId();
				}
				fightingHufazhenResp.setData(guardImgs, zhenfaId);
				hero.sendMsg(fightingHufazhenResp);

				Object buffObject = this.getAttribute("zhenBuff");
				if (buffObject == null) {
					return;
				}
				hero.getPropertyAdditionController().addChangeListener((PropertyChangeListener) buffObject);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public int getInstanceMonsterId(int type, int grade, int category) {
		MonsterModel mm = MonsterModelManager.getInstance().getNearestMoster(instanceData.getInstanceModelId(), type, grade, category);
		if (mm == null) {
			return 0;
		}
		return mm.getId();
	}
}
