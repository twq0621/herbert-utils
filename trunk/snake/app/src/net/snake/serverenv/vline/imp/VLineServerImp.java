package net.snake.serverenv.vline.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import net.snake.across.faction.AcrossFactionManager;
import net.snake.across.vehicle.AcrossVehicleManager;
import net.snake.ai.poll.FrameUpdateService;
import net.snake.gamemodel.activities.logic.DBActivityManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.RuningInstanceManager;
import net.snake.gamemodel.map.logic.DbMapLoader;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.SceneManager;
import net.snake.gamemodel.team.logic.TeamManager;
import net.snake.gamemodel.wedding.logic.WedFeastManagerVline;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.serverenv.vline.GameServerOnlineManager;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;

public class VLineServerImp implements VLineServer {
	private static final Logger logger = Logger.getLogger(VLineServerImp.class);
	protected int lineid;
	protected FrameUpdateService frameUpdataService;
	/** 副本同步处理服务 */
	// protected CopyFrameService copyFrameService;
	protected SceneManager sceneManager;
	protected GameServerOnlineManager onlineManager;
	protected RuningInstanceManager runingInstanceManager;
	protected TeamManager teammanager;
	private WedFeastManagerVline wedFeastManagerVline;
	protected String linename;
	private DBActivityManager dbActivityManager;

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getLinename() {
		return linename;
	}

	@Override
	public void startUp() {
		try {
			sceneManager.setVlineServer(this);
			DbMapLoader.initAllMapInfo(sceneManager);
			onlineManager = new GameServerOnlineManager();
			wedFeastManagerVline = new WedFeastManagerVline(this);

			frameUpdataService = new FrameUpdateService();// 虚拟和共享的区别在这里
			frameUpdataService.setVlineserver(this);
			frameUpdataService.startFrameupdate();

			// copyFrameService = new CopyFrameService();
			// copyFrameService.setSubline(this);
			// copyFrameService.startFrameService();

			runingInstanceManager = new RuningInstanceManager();
			runingInstanceManager.setVlineserver(this);

			teammanager = new TeamManager();
			dbActivityManager = new DBActivityManager(this);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void kickAllPlayer() {
		// 为了保存运行的过程中，不被改变
		ArrayList<Hero> copy = new ArrayList<Hero>(onlineManager.getCharacterList());
		final CountDownLatch cdl = new CountDownLatch(copy.size());
		for (Hero c : copy) {
			c.getDownLineManager().downLine(new Runnable() {
				@Override
				public void run() {
					cdl.countDown();
				}
			});
		}
		try {
			cdl.await(3, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 先断开所有玩家的连接再停掉循环驱动。
	 */
	@Override
	public void shutDown() {
		kickAllPlayer();
		frameUpdataService.stopFrameupdate();
		// copyFrameService.stopFrameService();
	}

	@Override
	public int getLineid() {
		return lineid;
	}

	@Override
	public SceneManager getSceneManager() {
		return sceneManager;
	}

	@Override
	public GameServerOnlineManager getOnlineManager() {
		return onlineManager;
	}

	@Override
	public void setLineid(int id) {
		lineid = id;
	}

	@Override
	public void addTaskInFrameUpdateThread(Runnable run) {
		frameUpdataService.addTask(run);
	}

	@Override
	public void addTaskToCopyFrameService(Runnable run) {
		// copyFrameService.addTask(run);
	}

	@Override
	public boolean isFrameUpdateThread() {
		return frameUpdataService.isFrameUpdateThread();// ||copyFrameService.inCopyFrameService();
	}

	@Override
	public boolean isInCopyFrameService() {
		// return copyFrameService.inCopyFrameService();
		return false;
	}

	@Override
	public TeamManager getTeamManager() {
		return teammanager;
	}

	@Override
	public int getFrameRate() {
		return frameUpdataService.getFrameRate();
	}

	@Override
	public int getCopyFrameServiceRate() {
		// return copyFrameService.getFrameRate();
		return 0;
	}

	@Override
	public Map<Integer, long[]> getProcessInfo() {
		return frameUpdataService.getProcessInfo();
	}

	@Override
	public RuningInstanceManager getRuningInstanceManager() {
		return runingInstanceManager;
	}

	@Override
	public DBActivityManager getDBActivityManager() {
		return dbActivityManager;
	}

	@Override
	public WedFeastManagerVline getWedFeastManagerVline() {
		return wedFeastManagerVline;
	}

	@Override
	public void runToOnlineCharacter(CharacterRun run) {
		Collection<Hero> characterList = getOnlineManager().getCharacterList();
		for (Hero character : characterList) {
			run.run(character);
		}

	}

	/**
	 * 初始化本线场景中怪物数据信息
	 */
	public void reloadSceneMonster() {
		Collection<Scene> scenes = this.getSceneManager().getWorldSceneList();
		for (Scene scene : scenes) {
			scene.reloadSceneMonster();
		}
	}

	/**
	 * @see net.snake.serverenv.vline.VLineServer#getAcrossFactionManager()
	 */
	@Override
	public AcrossFactionManager getAcrossFactionManager() {
		return null;
	}

	@Override
	public AcrossVehicleManager getAcrossVehicleManager() {
		return null;
	}
}
