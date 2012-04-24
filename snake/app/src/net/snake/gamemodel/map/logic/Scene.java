package net.snake.gamemodel.map.logic;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.snake.ai.astar.Point;
import net.snake.api.IAttackListener;
import net.snake.api.IBuffListneer;
import net.snake.api.IHurtListener;
import net.snake.api.ISceneListener;
import net.snake.api.IShockListener;
import net.snake.api.IStateListener;
import net.snake.api.IUseItemListener;
import net.snake.commons.program.Updatable;
import net.snake.commons.script.SScene;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneRefreshMonsterController;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.message.ResponseMsg;


/**
 * 场景的抽象。
 * 
 * @author serv_dev
 * 
 */
public interface Scene extends Updatable, SScene, Cloneable {
	public SceneManager getSceneManager();
	//public IGameSceneManager getGameSceneManager();

	public void setSceneManager(SceneManager sceneManager);
	//public void setGameSceneManager(IGameSceneManager manager);

	public void addToRefreshMonsterController(SceneMonster sm);

	public SceneRefreshMonsterController getRefreshMonsterController();
	
	/**
	 * 跨服战获得复活区
	 * @return
	 */
	public int[][] getFuhuoTiles();
	
	/**
	 * 跨服战获得经验区
	 * @return
	 */
	public int[][] getExpTiles();

	/**
	 * 死亡时是否受到pk保护
	 * 
	 * @return
	 */
	public boolean isPkProtect();

	/**
	 * pk等级保护
	 * 
	 * @return
	 */
	public boolean isPkGradeProtect();

	/**
	 * 复活点x坐标
	 * 
	 * @return
	 */
	public short getReliveX();

	/**
	 * 复活点y坐标
	 * 
	 * @return
	 */
	public short getReliveY();

	/**
	 * 是副本场景吗
	 * 
	 * @return
	 */
	public boolean isInstanceScene();

	/**
	 * 场景id
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * 场景名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 获取场景地图的高
	 * 
	 * @return
	 */
	public short getHeight();

	/**
	 * 获取场景地图的宽
	 * 
	 * @return
	 */
	public short getWidth();

	/**
	 * 给定坐标是否可通行。建议检查是否边界外，是否障碍。
	 * 
	 * @param x
	 * @param y
	 * @return true 0可通行，false 1不可通行
	 */
	public boolean isPermitted(short x, short y);

	/**
	 * 在场景的两点之间寻找一条路径。
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public short[] findWay(short fromX, short fromY, short toX, short toY);
	public List<Point> findWayII(short fromX, short fromY, short toX, short toY);
	/**
	 * 判断是否是传送点
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Teleport getTransprot(short x, short y);

	/**
	 * 是否为已经开放的传送点
	 * 
	 * @param teleport
	 * @return
	 */
	public boolean isOpenedTeleport(Teleport teleport);

	/**
	 * 进入场景,在指定的坐标点出现。<br>
	 * 
	 * @param player
	 * @param x
	 * @param y
	 * @return
	 */
	public void enterScene(Hero player);

	public void enterScene(SceneMonster scenemonster);

	/**
	 * 掉落物品添加到场景里，并且对视野内的玩家进行广播
	 * @param sceneDropGood
	 */
	public void enterScene(SceneDropGood sceneDropGood);

	/**
	 * newScene 用于标明,角表离线,是否是下线,还是切换场景,这两种情况的处理是不同的
	 */
	public void leaveScene(Hero player, Scene newScene);

	/**
	 * 从当前地图区域 和 当前场景中 移除掉怪物
	 * 
	 * @param sceneMonster
	 */
	public void leaveScene(SceneMonster sceneMonster);

	/**
	 * 从当前地图区域 和 当前场景中 移除掉掉落物品
	 * 
	 * @param sceneMonster
	 */
	public void leaveScene(SceneDropGood sceneDropGood);

	/**
	 * 面向场景内所有玩家广播一条消息。
	 * 
	 * @param response
	 * @return
	 */
	public void broadcastMsg(ResponseMsg response);

	/**
	 * 面向场景内所有玩家广播一条消息,但不包括指定的玩家。
	 * 
	 * @param player
	 * @param response
	 * @return
	 */
	public void broadcastMsgExclude(Hero player, ResponseMsg response);

	/**
	 * 获得场景内所有的玩家信息
	 * 
	 * @return
	 */
	public Collection<Hero> getAllCharacters();

//	/**
//	 * 返回地图中所有的怪物掉落物品
//	 * 
//	 * @return
//	 */
//	public Collection<SceneDropGood> getAllSceneDropGood();

	/**
	 * 获得场景中的玩家数,该方法从缓存变量中返回
	 * 
	 * @return
	 */
	public int getCharacterCount();

	/**
	 * 返回该场景的入口点
	 * 
	 * @return
	 */
	public short getEnterX();

	/**
	 * 返回该场景的入口点
	 * 
	 * @return
	 */
	public short getEnterY();

	public Collection<SceneMonster> getAllSceneMonster();

//	public Collection<Horse> getAllHorse();

//	public Collection<NPCFriend> getAllNPCFriend();

	public SceneMonster getSceneMonster(int scenemonsterid);

	public Object clone() throws CloneNotSupportedException;

	public int getInstanceModelId();

	/**
	 * 场景pvp模式  1允许PVP 0不允许PVP
	 */
	public int getPvpModel();

	/**
	 * 是否所有的怪物都死亡了，注意，为了性能，应在有怪物死亡时才检查
	 * 
	 * @return
	 */
	public boolean isAllMonsterDie();

//	/**
//	 * 判断某对像是否第一次到达了某区域
//	 * 
//	 * @param obj
//	 * @param x
//	 * @param y
//	 * @param w
//	 * @param h
//	 * @return
//	 */
//	public boolean isFirstEnterArea(VisibleObject obj, int x, int y, int w,
//			int h);

//	/**
//	 * 该场景是否要监听玩家首次进入某个地点的事件
//	 * 
//	 * @return
//	 */
//	public boolean isEnableLocationMonitor();

	/**
	 * 清除所有场景资源，主要用在副本销毁
	 */
	public void clearScene();

	/**
	 * 获得角色视野限定值 如为 0则为不限定视野
	 * 
	 * @return
	 */
	public int getCharacterEyeShotLimit();

	/**
	 * 打点隐藏传送点
	 * 
	 * @param teleportID
	 */
	public void openTeleport(int teleportID);

	/**
	 * 如果是副本地图,找副本地图到世界地图的传送点
	 * 
	 * @return
	 */
	public Teleport getTeleportInstanceToWorld();

	/**
	 * 使用该地图,创建等级为instanceLevel的副本场景
	 * 
	 * @param instanceLevel
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Scene createInstanceScene(int instanceLevel)
			throws CloneNotSupportedException;

	/**
	 * 在周围8个方向中寻找点
	 * 
	 * @param vo
	 * @param radius
	 * @param trycount
	 * @return
	 */
	public short[] getRandomPath(VisibleObject vo, int radius);
	public short[] getRandomPathII(VisibleObject vo, int radius);
	/**
	 * 获取count个随机分布不重复的的落点,并且排除l中的点 用于全场景同时生成多个怪物
	 * @param l
	 * @param count
	 * @return
	 * @throws Exception 
	 */
	public List<Point> getRandomPoint(List<Point> l,int count) throws Exception;
	
	/**
	 * 获取排除点之外的一个随机落点。返回空则说明没地方了
	 * @param paichu
	 * @return
	 */
	public Point getRandomPoint();
	/**
	 * 在周围8个方向并且该点与生成的点的距离为radius满足,并且不是障碍物
	 * 
	 * @param vo
	 * @param radius
	 * @param trycount
	 * @return
	 */
	public short[] getAroundPoint(short x, short y, int radius);

	/**
	 * 在radius范围内,找到一个随机的不是障碍物的点
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public Point getRandomPoint2(short x, short y, int radius);
	
	/**
	 * 以x、y为圆心，radius为半径，求出这么一个矩形的四个角坐标
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public short[] getRectanglePoint(short x,short y,int radius);
	
	/**
	 * 在radius范围内,找到一个随机的不是障碍物的点
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public short[] getRandomPoint(short x, short y, int radius);
	/**
	 * 在radius范围内,找到一个随机的不是障碍物的点
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public short[] getRandomPoint(int x, int y, int radius);

	public int getMonsterCount();

	public int getHorseCount();

	/**
	 * 获取复活地图id
	 * 
	 * @return
	 */
	public int getReliveSceneId();

	public List<SceneMonster> getAllMonsterWillAddToScene();

	public short[][] getBlockTiles();

	public String getMonsterDesc();

	public String getExerciseDesc();

	public String getBossDesc();

	public String getBossTimeDesc();

	public String getBossDropGoods();

	public Integer getEnterLevelLimit();

	public Set<MapTeleport> getTeleports4set();

	public String getShowName();

	/**
	 * 获取场景掉落物品
	 * 
	 * @param dropGoodId
	 * @return
	 */
	public SceneDropGood getSceneDropGood(int dropGoodId);

	public InstanceController getInstanceController();

	public void setInstanceController(InstanceController instanceController);

	public boolean openHideTeleport();
	
	public boolean closeTeleport();

	public void initInstanceSceneMonster();

	public void initInstanceSceneMonster(int dropJiacheng, int shuxingjiacheng);

	public int getInstanceCount();

	public void setInstanceCount(int instanceCount);

	public void enterSceneToSendHideTeleport(Hero character);

	/**
	 * 重加载怪物场景中怪物属性数据
	 */
	public void reloadSceneMonster();
	
	
	public boolean isClearPkProtect();
	/**
	 * 玩家死亡时调用该方法
	 * @param character 是否守护玩家
	 * @param isGuard
	 * @return true 立即复活
	 */
	public boolean dieAffterRelive(Hero character,boolean isGuard);
	
	/**
	 * 得到所有类型的场景可见对象
	 * @return
	 */
	public <T> Collection<T> getSceneObjByClass(int class1);
	/**
	 * 根据类型和id获得场景对像
	 * @param <T>
	 * @param castType
	 * @param id
	 * @return
	 */
	public <T> T getSceneObj(int castType,int id);
	
	public void addUseItemListener(IUseItemListener listener);
	public List<IUseItemListener> getUseItemListeners();
	public void addSceneListener(ISceneListener listener);
	public List<ISceneListener> getSceneListeners();
	public void addStateListener(IStateListener listener);
	public List<IStateListener> getStateListeners();
	public void addHurtListener(IHurtListener listener);
	public List<IHurtListener> getHurtListeners();
	public void addAttackListener(IAttackListener listener);
	public List<IAttackListener> getAttackListeners();
	public void addShockListener(IShockListener listener);
	public List<IShockListener> getShockListeners();
	public void addBuffListener(IBuffListneer listener);
	public List<IBuffListneer> getBuffListneers();
}
