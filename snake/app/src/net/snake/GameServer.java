package net.snake;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import net.snake.api.log.DataLogManager;
import net.snake.commons.message.MsgProcessorLoader;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.events.EventManager;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.across.persistence.CharacterAcrossDateCenterManager;
import net.snake.gamemodel.activities.persistence.ActivationCardManager;
import net.snake.gamemodel.activities.persistence.CharacterActivationCardManager;
import net.snake.gamemodel.activities.persistence.DoubActivityManager;
import net.snake.gamemodel.cache.logic.CacheDataInit;
import net.snake.gamemodel.config.persistence.ConfigParamManager;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.line.bean.ServerEntry;
import net.snake.gamemodel.line.persistence.LineServerDataManager;
import net.snake.gamemodel.line.persistence.ServerListManager;
import net.snake.gamemodel.map.logic.SceneManager;
import net.snake.ibatis.SystemFactory;
import net.snake.netio.GameServerContext;
import net.snake.netio.MsgQueueHandler;
import net.snake.netio.Network;
import net.snake.netio.NetworkConfig;
import net.snake.netio.message.MsgDispatcher;
import net.snake.netio.message.process.IServerDispatcher;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.quartz.QuartzApi;
import net.snake.serverenv.CharacterGCManager;
import net.snake.serverenv.CharacterGCManagerImp;
import net.snake.serverenv.CharacterTaskExcuter;
import net.snake.serverenv.LoginstatusManager;
import net.snake.serverenv.cacheupdate.GameServerCacheRunTimeReload;
import net.snake.serverenv.security.SecurityManage;
import net.snake.serverenv.stall.OnlineStallManager;
import net.snake.serverenv.vline.VLineServerManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

public class GameServer implements IServerDispatcher {

	private static final Logger logger = Logger.getLogger(GameServer.class);
	private static GameServer instance = new GameServer();

	// 静态属性===============================================
	/** 非数据库调用使用的线程池 */
	public static ScheduledExecutorService executorService;
	/** 数据库操作使用的线程池 */
	public static ScheduledExecutorService executorServiceForDB;
	public static ServerEntry serverentry;

	public static String workDir = "";
	public static final Properties serverparam = new Properties();
	public static ConfigParamManager configParamManger;
	public static DoubActivityManager doubActivityManager;
	/** 虚拟分线管理器 */
	public static VLineServerManager vlineServerManager;

	public static LineServerDataManager lineServerDataManager;// 看来是公用一个数据库管理
	// public static ScriptManager scriptManager;
	public static EventManager scriptManager;
	/** 角色切线管理器 */
	public static OnlineStallManager onlineStallManager;
	/** 服务器运行状态,是否关闭了 */
	public static boolean isstartshutdown = false;

	public static volatile AtomicInteger concurrentMsgProcessorcount = new AtomicInteger();
	public static ServerListManager serverlistmanager;
	private static ArrayList<Network> netioarray = new ArrayList<Network>();

	public static SecurityManage securityManage;
	/** 角色相关资源回收线程池 */
	public static CharacterGCManager characterGCManager = new CharacterGCManagerImp();
	/** 定时任务 */
	public static QuartzApi quartzapi;//
	public static MsgDispatcher msgDispatcher = new MsgDispatcher();
	/** 该引用只为了复制新的场景管理器用 */
	public static SceneManager shareSceneManager;// 克隆了镜像
	public static MsgProcessorLoader msgProcessorLoader;
	// =============================================
	public static LoginstatusManager loginstatsuManager = new LoginstatusManager();

	private static GameServerCacheRunTimeReload gameServerCacheRunTimeReload;
	public static ActivationCardManager activationCardManager;

	public static CharacterActivationCardManager characterActivationCardManager;

	public static DataLogManager dataLogManager;
	/** 是否打开process监控 */
	public static boolean isOpenProcessCountInfo = false;//

	private GameServer() {
	}

	public static GameServer getInstance() {
		return instance;
	}

	/**
	 * 配置服务器选项
	 * 
	 */
	private void configServerOptions() {
		String isacross = serverparam.getProperty("server.isacross");
		Options.IsCrossServ = isacross != null && isacross.equalsIgnoreCase("true");
		Options.ServerId = Integer.parseInt(serverparam.getProperty("server.sid"));
		String isopencard = serverparam.getProperty("server.isopencard");
		Options.FresherCard_Check = isopencard == null ? false : isopencard.equals("true");
	}

	/**
	 * 开启服务器应用程序。
	 */
	public void startup() {
		try {
			isstartshutdown = false;
			configServerOptions();
			DataLogManager dataLogManager = new DataLogManager(serverparam);
			GameServer.dataLogManager = dataLogManager;
			// 初始化线程池
			executorService = Executors.newScheduledThreadPool(4);
			executorServiceForDB = Executors.newScheduledThreadPool(50);
			characterGCManager.start();
			// 初始化数据库
			SystemFactory.initialSQLMpClient();

			// 初始化脚本引擎
			scriptManager = new EventManager();
			scriptManager.initial(workDir);

			// 初始化缓存数据
			CacheDataInit.init();
			// 启动数据缓存监听
			gameServerCacheRunTimeReload = new GameServerCacheRunTimeReload();
			gameServerCacheRunTimeReload.addCacheUpdateListener();
			Guards.initNormalGuards();

			// 定时任务启动
			quartzapi = new QuartzApi();// .start();
			quartzapi.start();
			// 启动角色异步任务执行器
			CharacterTaskExcuter.getInstance();
			// 加载消息号处理器
			msgProcessorLoader = new MsgProcessorLoader(this);
			// 初始化网络监听
			startNetListener();
			// 初始化跨服消息处理器
			AcrossServer.initHandlerMap();
			CtsConnectSessionMananger.getInstance().initSocketConnector(AcrossServer.handlerMap);
			if (Options.IsCrossServ) {
				new AcrossServer();// 启动跨服服务
				AcrossEtcManager.getInstance().init();
			} else {
				CharacterAcrossDateCenterManager.getInstance().initDate();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void startNetListener() throws IOException, FileNotFoundException {
		serverentry = serverlistmanager.getServerEntryByID(Options.ServerId);
		String loginPorts = serverentry.getLoginserverport();
		String[] loginPortArrStrings = loginPorts.split(",");
		Set<String> ports = new HashSet<String>();
		for (int i = 0; i < loginPortArrStrings.length; i++) {
			boolean can = ports.add(loginPortArrStrings[i]);
			if (!can) {
				loginPortArrStrings[i] = null;
			}
		}
		try {
			int loginPort = Integer.parseInt(loginPortArrStrings[0].trim());
			Network game = new Network(new NetworkConfig(loginPort, new MsgQueueHandler(this)));// 启动网络IO
			game.startNetListen();
			netioarray.add(game);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	// 服务器的安全停止
	public void shutdown() {

		isstartshutdown = true;
		for (Network netio : GameServer.netioarray) {
			netio.stopNetListen();
			netio.destroy();
		}
		// changeLineManager.shutdown();
		vlineServerManager.shutDown();
		if (!Options.IsCrossServ) {
			SceneBangqiManager.getInstance().update();
			FactionCityManager.getInstance().updateToDb();
		}
		executorService.shutdown();
		executorServiceForDB.shutdown();
		gameServerCacheRunTimeReload.destory();
		quartzapi.destroy();
		dataLogManager.stop();
	}

	@Override
	public MsgDispatcher getMsgDispatcher() {
		return msgDispatcher;
	}

	@Override
	public String getWorkPath() {
		return workDir;
	}

	@Override
	public String getProccessProp() {
		return "processorconfig/processors.properties";
	}

	@Override
	public boolean checkIP(String ip) {
		boolean tf = securityManage.checkManageIP(ip);
		return tf;
	}

	@Override
	public MsgProcessor getMsgProcessor(int msgCode) {
		return msgDispatcher.getMsgProcessor(msgCode);
	}

	@Override
	public void sessionClosed(GamePlayer player) {
		Hero character = player.getCurrentCharacter(Hero.class);
		// 没有角色直接干掉应用会话
		if (character == null) {
			GameServerContext.removePlayer(player.getAccountid());
			return;
		}
		// 如果应用会话是聊天连接，聊天的连接也是gameplayer
		if (player.getSessionType() == GamePlayer.Session_Type_Chat) {
			logger.info("hero name is " + character.getViewName() + ",hero id is " + character.getId() + ",Connection with the chat server has been disconnected ");
		} else {
			GameServerContext.removePlayer(player.getAccountid());
			logger.info("hero name is " + character.getViewName() + ",hero id is " + character.getId() + " ,Connection with the game server has been disconnected");
			character.getDownLineManager().downLine(null);
		}
	}
}