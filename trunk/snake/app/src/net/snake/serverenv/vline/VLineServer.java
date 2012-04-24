package net.snake.serverenv.vline;

import java.util.Map;

import net.snake.across.faction.AcrossFactionManager;
import net.snake.across.vehicle.AcrossVehicleManager;
import net.snake.gamemodel.activities.logic.DBActivityManager;
import net.snake.gamemodel.instance.logic.RuningInstanceManager;
import net.snake.gamemodel.map.logic.SceneManager;
import net.snake.gamemodel.team.logic.TeamManager;
import net.snake.gamemodel.wedding.logic.WedFeastManagerVline;

/**
 * 上层容器依赖分线号保持对分线服务的引用。 分线服务器维护了场景,在线角色，在线队伍，副本，婚宴管理,双倍活动。 统计了负载数据。
 * 负责循环，驱动消息和状态机，可以添加场景同步任务。 可以对分线中所用角色执行一个任务。 维护了跨服坐骑和不会管理。 虚拟分线服务器[为了一个java进程
 * 启动多个虚拟分线使用]
 * 
 * @author serv_dev
 */
public interface VLineServer {

	public void startUp();

	public void shutDown();

	public int getLineid();

	public void setLineid(int id);

	public void setLinename(String linename);

	public String getLinename();

	// public boolean isWorldUpdate();
	//
	// public void setWorldUpdate(boolean isWorldUpdate);

	// public FrameUpdateService getFrameUpdateService();

	public void setSceneManager(SceneManager sceneManager);

	public SceneManager getSceneManager();

	public GameServerOnlineManager getOnlineManager();

	public RuningInstanceManager getRuningInstanceManager();

	public TeamManager getTeamManager();

	/** 在刷桢线程里执行任务 */
	public void addTaskInFrameUpdateThread(Runnable run);
	
	/** 在副本刷桢线程里执行任务 */
	public void addTaskToCopyFrameService(Runnable run);

	/*** 当前线程是刷桢线程吗 **/
	public boolean isFrameUpdateThread();
	
	public boolean isInCopyFrameService();

	public int getFrameRate();
	public int getCopyFrameServiceRate();

	public Map<Integer, long[]> getProcessInfo();

	// public tea

	// public void setListenPort(String port);
	// public String getListenPort();
	// public String getAddress();
	// public void setAddress(String address);

	// public VLineChatSessionManger getChatSessionManager();

	// public boolean isChatSession();
	//
	// public void setChatSession(boolean isChatSession);
	public DBActivityManager getDBActivityManager();

	public WedFeastManagerVline getWedFeastManagerVline();

	/**
	 * 处理分线所有角色
	 * @param run
	 */
	public void runToOnlineCharacter(CharacterRun run);

	/**
	 * 初始化本线场景中怪物数据信息
	 */
	public void reloadSceneMonster();

	/**
	 * 跨服服务器获取跨服的帮会管
	 * @return
	 */
	public AcrossFactionManager getAcrossFactionManager();

	public AcrossVehicleManager getAcrossVehicleManager();
}
