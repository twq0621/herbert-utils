package net.snake.gamemodel.instance.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.ClientConfig;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.response.InstanceRewardSuccessMsg10728;
import net.snake.gamemodel.instance.response.InviteEnterInstanceMsg10704;
import net.snake.gamemodel.instance.response.TeamAgreeEnterInstanceMsg10706;
import net.snake.gamemodel.instance.response.TeamEnterInstanceFailMsg10710;
import net.snake.gamemodel.instance.response.TeamEnterInstanceSuccessMsg10708;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.gamemodel.team.logic.MyTeamManager;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;

/**
 * 角色副本控制器 控制角色进入副本具体行为
 * 
 * @author serv_dev
 * 
 */
public class MyCharacterInstanceManager {
	private static final Logger logger = Logger.getLogger(MyCharacterInstanceManager.class);
	private InviteEnterInstanceTimer invitetimer;
	private TeamEnterInstanceTimer teamEnterInstanceTimer;
	private Hero character;
	private InstanceController instanceController;
	private int instanceflushGrade = 0; // 普通怪物
	private String instanceMsg = null;// 是否可以进入副本原因
	private boolean isAbleEnter = true; // 是否 进行正常传送(true )
	private InstanceController instanceTemp;
	private Scene sceneTemp;
	private boolean isUseFubenLing = false;
	public Map<Integer, Hero> inviteMap = new ConcurrentHashMap<Integer, Hero>();
	private List<CharacterGoods> rewardGoodList = new ArrayList<CharacterGoods>();

	public int getAllObjInHeap() throws Exception {
		int size = inviteMap.size();
		size = size + (instanceController == null ? 0 : instanceController.getInstanceAllCharacters().size());
		size = size + (instanceTemp == null ? 0 : instanceTemp.getInstanceAllCharacters().size());
		return size;
	}

	/**
	 * 清楚邀请进入副本记录
	 */
	public void destroy() {
		if (inviteMap != null) {
			inviteMap.clear();
			inviteMap = null;
		}
		if (rewardGoodList != null) {
			rewardGoodList.clear();
			rewardGoodList = null;
		}

	}

	public InstanceController getInstanceController() {
		return instanceController;
	}

	public void setInstanceController(InstanceController instanceController) {
		this.instanceController = instanceController;
	}

	public MyCharacterInstanceManager(Hero character) {
		this.character = character;
		invitetimer = new InviteEnterInstanceTimer(character);
		teamEnterInstanceTimer = new TeamEnterInstanceTimer(character);
		this.instanceflushGrade = 0;
		this.isUseFubenLing = false;
	}

	/**
	 * 角色登入第一次进入场景 判断角色上一次下线地图是副本地图 特殊处理
	 */
	public void firstEnterScene() {
		Scene scene = character.getVlineserver().getSceneManager().getInstanceScene(character.getScene());
		DownLineRoleInstanceController downLineInstance = GameServer.vlineServerManager.getDownLineRoleInstanceControllerByCharacterId(character.getId());
		if (downLineInstance != null) {
			if (downLineInstance.isExChangeToEnterInstance()) {
				downLineInstance.roleOnlineEnterInstance(character);
				return;
			} else {
				downLineInstance.setLastTime(System.currentTimeMillis());
			}
		}
		int toSceneID = scene.getReliveSceneId();
		Scene enterScene = character.getVlineserver().getSceneManager().getScene(toSceneID);
		if (enterScene != null) {
			short[] point = enterScene.getRandomPoint(enterScene.getReliveX(), enterScene.getReliveY(), 7);
			ExchangeMapTrans.trans(enterScene, point[0], point[1], character);
			return;
		}
		enterScene = character.getVlineserver().getSceneManager().getInstanceScene(toSceneID);
		if (enterScene == null) {
			Scene newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
			short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
			ExchangeMapTrans.trans(newScene, point[0], point[1], character);
			return;
		}
		boolean b = openOneInstance(enterScene, true);
		if (!b) {
			Scene newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
			short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
			ExchangeMapTrans.trans(newScene, point[0], point[1], character);
			return;
		}
	}

	/**
	 * 进入副本请求
	 * 
	 * @param scene
	 */
	public void openInstance(Scene scene, boolean isFree) {
		MyTeamManager myTeamManager = character.getMyTeamManager();
		if (myTeamManager.isTeam()) {
			openTeamInstance(scene, isFree);
		} else {
			openOneInstance(scene, isFree);
		}
	}

	/**
	 * 开启组队副本
	 */
	private void openTeamInstance(Scene scene, boolean isFree) {
		if (!character.getMyTeamManager().isTeamLeader()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 673));
			return;
		}

		int instanceId = scene.getInstanceModelId();
		InstanceController instanceController1 = character.getVlineserver().getSceneManager().getInstanceControllerById(instanceId);
		Team t = character.getMyTeamManager().getMyTeam();
		List<Hero> list = t.getCharacterPlayers();
		int male = 0;
		int female = 0;
		for (Hero teamer : list) {
			if (teamer.isMale()) {
				male++;
			} else {
				female++;
			}
			ServerResponse msg = instanceController1.isOpenCondition(teamer);
			if (msg == null) {
				msg = instanceController1.enterCountCondition(teamer, isFree);
			}
			if (msg != null) {// 有问题，不满足进入副本的条件
				ServerResponse response = msg;
				for (Hero c : list) {// 告诉不在副本的队友
					if (c.getSceneRef() != null && !c.getSceneRef().isInstanceScene()) {
						c.sendMsg(response);
					}
				}
				return;
			}
		}
		if (instanceController1.getInstanceData().getInstanceModelId() == 5) {
			if (male < 1 || female < 1) {
				t.sendTeamMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17550), null);
				return;
			}
		}
		for (Hero teamer : list) {
			teamer.getMyCharacterInstanceManager().requesteTeamEnterInstance(instanceId);
		}
		instanceTemp = instanceController1;
		sceneTemp = scene;
		if (list.size() == 1) {
			t.sendTeamMsg(new TeamEnterInstanceSuccessMsg10708(sceneTemp.getId()), null);
			teamEnterInstanceTimer.start();
			return;
		}
	}

	/**
	 * 开启单人副本
	 */
	private boolean openOneInstance(Scene scene, boolean isFree) {
		int instanceId = scene.getInstanceModelId();
		InstanceController instanceController1 = character.getVlineserver().getSceneManager().getInstanceControllerById(instanceId);
//		ServerResponse msg = instanceController1.isOpenCondition(character);
//		if (msg != null) {
//			character.sendMsg(msg);
//			return false;
//		}
//		msg = instanceController1.enterCountCondition(character, isFree);
//		if (msg != null) {
//			character.sendMsg(msg);
//			return false;
//		}
		this.isUseFubenLing = false;
		oneEnterInstance(instanceController1, scene.getId());
		return true;
	}

	/**
	 * 单人克隆并进入副本
	 * 
	 * @param instanceOne
	 */
	private void oneEnterInstance(InstanceController instanceOne, int sceneId) {
		try {
//			ServerResponse msg = instanceOne.enterInstanceTakeGood(character);
//			if (msg != null) {
//				character.sendMsg(msg);
//				return;
//			}
			InstanceController instance = (InstanceController) instanceOne.clone();
			instance.oneEnterInstance(sceneId, character);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 接受组队队长邀请进入副本消息
	 * 
	 * @param sceneId
	 */
	public void requesteTeamEnterInstance(int sceneId) {
		if (!character.getMyTeamManager().isTeamLeader()) {
			invitetimer.start();
		}
		character.sendMsg(new InviteEnterInstanceMsg10704(sceneId));
	}

	/**
	 * 反馈是否同意组队进入副本
	 * 
	 * @param type
	 */
	public void onResponseEnterTeamInstance(byte type) {
		invitetimer.shutdown();
		if (character.getMyTeamManager().isTeamLeader()) {
			return;
		}
		if (character.getMyTeamManager().isTeam()) {
			this.isUseFubenLing = false;
			Hero leader = character.getMyTeamManager().getMyTeam().getTeamLeader();
			leader.getMyCharacterInstanceManager().otherIsAccessEnterInstance(character, type);
		}
	}

	/**
	 * 队长调用该方法 TYPE==0拒绝 接受队员是否同意组队进入副本 2
	 * 
	 * @param teamer
	 * @param type
	 *            =2，3表示决绝 或队员未响应
	 */
	public void otherIsAccessEnterInstance(Hero teamer, byte type) {
		if (instanceTemp == null) {
			return;
		}
		if (type == 2 || type == 3) {
			character.getMyTeamManager().getMyTeam().sendTeamMsg(new TeamAgreeEnterInstanceMsg10706(teamer, type), null);
			breakTeamEnterInstance(12501, teamer.getViewName());
			return;
		}
		addInviteTeamer(teamer);
		int teamCount = character.getMyTeamManager().getMyTeam().getCharacterPlayers().size();
		if (inviteMap.size() + 1 == teamCount) {
			character.getMyTeamManager().getMyTeam().sendTeamMsg(new TeamEnterInstanceSuccessMsg10708(sceneTemp.getId()), null);

			teamEnterInstanceTimer.start();
		}
	}

	/**
	 * 打断进入副本操作（队员离队 决绝或有其他队员加入队伍 等操作 打断进入副本行为）
	 * 
	 * @param msg
	 */
	public void breakTeamEnterInstance(int msgKey, String... vars) {
		teamEnterInstanceTimer.shutdown();
		inviteMap.clear();
		instanceTemp = null;
		sceneTemp = null;
		// teamEnterInstanceTimer.shutdown();
		character.getMyTeamManager().getMyTeam().sendTeamMsg(new TeamEnterInstanceFailMsg10710(msgKey, vars), null);
	}

	/**
	 * 更新进入副本状态（队员离队 决绝或有其他队员加入队伍 等操作 打断进入副本行为 ），调用打断进入副本
	 * 
	 * @param msg
	 */
	public void updateEnterTeamInstance(int msgkey, String... vars) {
		if (instanceTemp == null) {
			return;
		}
		breakTeamEnterInstance(msgkey, vars);
	}

	/**
	 * 组队进入副本倒计时结束 ， 正常进入副本
	 */
	public void teamInterInstance() {
		if (instanceTemp == null) {
			return;
		}
		try {
			this.isUseFubenLing = false;
			InstanceController instanceZhu = (InstanceController) instanceTemp.clone();
			instanceZhu.teamEnterInstance(sceneTemp.getId(), character.getMyTeamManager().getMyTeam(), character.getVlineserver());
			instanceTemp = null;
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 队长调用此方法 向队友发送邀请进入副本操作
	 * 
	 * @param teamer
	 */
	public void addInviteTeamer(Hero teamer) {
		inviteMap.put(teamer.getId(), teamer);
		character.getMyTeamManager().getMyTeam().sendTeamMsg(new TeamAgreeEnterInstanceMsg10706(teamer, CommonUseNumber.byte1), null);

	}

	/**
	 * 副本结束 ，玩家传出
	 */
	@Deprecated
	public void endInstanceToXiangyang() {
		Scene newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
		ExchangeMapTrans.trans(newScene, point[0], point[1], character);
		// instanceController = null;
	}

	public void exitInstance2World(int scene, int x, int y) {
		Scene newScene = character.getVlineserver().getSceneManager().getScene(scene);
		if (newScene != null) {
			ExchangeMapTrans.trans(newScene, (short) x, (short) y, character);
			return;
		}

		newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
		ExchangeMapTrans.trans(newScene, point[0], point[1], character);
	}

	public InstanceController getInstanceTemp() {
		return instanceTemp;
	}

	public void setInstanceTemp(InstanceController instanceTemp) {
		this.instanceTemp = instanceTemp;
	}

	/**
	 * 玩家请求进入副本地图 调用此方法
	 * 
	 * @param targetScene
	 */
	public void instanceExChangeScene(Scene targetScene) {
		int instance = character.getSceneRef().getInstanceModelId();
		if (instance == targetScene.getInstanceModelId()) {
			getInstanceController().oneEnterInstance(targetScene.getId(), character);
		} else {
			openInstance(targetScene, true);
		}
	}

	/**
	 * 玩家副本地图切换
	 * 
	 * @param teleport
	 * @param isSure
	 *            true 付出代价进入副本
	 */
	public void instanceExChangeScene(Teleport teleport, boolean isSure) {
		Scene scene = character.getSceneRef();
		if (scene.isOpenedTeleport(teleport)) {
			if (isSure) {
				getInstanceController().instanceExChangeSceneSure(character, teleport);
			} else {
				getInstanceController().instanceExChangeScene(character, teleport);
			}
		} else {
			character.sendMsg(SimpleResponse.failReasonMsg(10020, 40025));
		}
	}

	/**
	 * 获取 玩家副本等级（或层数）
	 * 
	 * @return
	 */
	public int getInstanceflushGrade() {
		return instanceflushGrade;
	}

	/**
	 * 更新副本地图等级
	 * 
	 * @param instanceflushGrade
	 */
	public void setInstanceflushGrade(int instanceflushGrade) {
		if (this.instanceflushGrade != instanceflushGrade && instanceflushGrade == 1) {
			character.getMyCharacterAchieveCountManger().getInstanceCount().tianguanYicengCount(instanceController);
		}
		this.instanceflushGrade = instanceflushGrade;
	}

	public String getInstanceMsg() {
		return instanceMsg;
	}

	public void setInstanceMsg(String instanceMsg) {
		this.instanceMsg = instanceMsg;
	}

	public boolean isAbleEnter() {
		return isAbleEnter;
	}

	public void setAbleEnter(boolean isAbleEnter) {
		this.isAbleEnter = isAbleEnter;
	}

	public void changeInstanceEnterInfo(String instanceMsg, boolean isAbleEnter) {
		this.instanceMsg = instanceMsg;
		this.isAbleEnter = isAbleEnter;
	}

	/**
	 * 李开副本进行数据初始化
	 */
	public void leaveInstance() {
		this.instanceController = null;
		this.sceneTemp = null;
		this.instanceflushGrade = 0;
		this.instanceMsg = null;// 是否可以进入副本原因
		this.isAbleEnter = true;
		this.inviteMap.clear();
		this.setRewardGoodList(null);
	}

	public boolean isUseFubenLing() {
		return isUseFubenLing;
	}

	public void setUseFubenLing(boolean isUseFubenLing) {
		this.isUseFubenLing = isUseFubenLing;
	}

	/**
	 * 玩家某副本通关 领取副本通关奖励（只能在副本通关后并且没有离开副本时才能领取）
	 */
	public void lingquInstanceReward() {
		if (getInstanceController() == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 674));
			return;
		}
		if (this.getRewardGoodList() == null || this.getRewardGoodList().size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 675));
			return;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(this.getRewardGoodList())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 676));
			return;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(this.getRewardGoodList());
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 677));
			return;
		}
		// 购买进入包裹效果
		character.sendMsg(new GoodToBadEffectMsg11170((byte) 8, this.getRewardGoodList()));
		this.setRewardGoodList(null);
		character.sendMsg(new InstanceRewardSuccessMsg10728());
	}

	public List<CharacterGoods> getRewardGoodList() {
		return rewardGoodList;
	}

	public void setRewardGoodList(List<CharacterGoods> rewardGoodList) {
		this.rewardGoodList = rewardGoodList;
	}

}
