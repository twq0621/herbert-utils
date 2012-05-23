package game;

import game.logic.account.LoginGameProcessor10001;
import game.pool.FrameUpdateService;
import lion.codec.IServerDispatcher;
import lion.core.GamePlayer;
import lion.message.MsgDispatcher;
import lion.netty.NettyServer;
import lion.processor.MsgProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主要启动的类
 * 
 * @author hexuhui
 * 
 */
public class GameServer implements IServerDispatcher {

	private static Logger logger = LoggerFactory.getLogger(GameServer.class);

	private NettyServer amf3Server;
	private FrameUpdateService frameUpdataService = null;

	public static MsgDispatcher msgDispatcher = new MsgDispatcher();

	public GameServer() {
		amf3Server = new NettyServer(this);
		amf3Server.initServer();
		amf3Server.startServer(8653);
		addShutDownHook();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		GameServer server = new GameServer();
		logger.info("server init success!,factory={},server={}", factory, server);
		server.onStart();
	}

	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				amf3Server.stop();
				onStop();
				System.exit(0);
			}
		});
	}

	protected void onStop() {
		long leftCount = kickAllPlayer();
		frameUpdataService.stopFrameupdate();
		if (leftCount > 0) {
			try {
				Thread updateThread = frameUpdataService.getFrameUpdateThread();
				long threadId = updateThread.getId();
				logger.info("waiting FrameUpdateThread stop,id=" + threadId);
				updateThread.join();
				logger.info("FrameUpdateThread stop successfully!,id=" + threadId);
			} catch (InterruptedException e) {
				logger.error("", e);
			}
		}
	}

	private long kickAllPlayer() {
		long ret = 0;
		// 为了保存运行的过程中，不被改变
		// ArrayList<Hero> copy = new ArrayList<Hero>(onlineManager.getCharacterList());
		// final CountDownLatch cdl = new CountDownLatch(copy.size());
		// for (Hero c : copy) {
		// c.getDownLineManager().downLine(new Runnable() {
		// @Override
		// public void run() {
		// cdl.countDown();
		// }
		// });
		// }
		// try {
		// cdl.await(3, TimeUnit.MINUTES);
		// ret = cdl.getCount();
		// } catch (InterruptedException e) {
		// logger.error(e.getMessage(), e);
		// }
		return ret;
	}

	protected void onStart() {
		frameUpdataService = new FrameUpdateService();// 开启刷帧的线程
		frameUpdataService.startFrameupdate();
		// 注册消息
		msgDispatcher.addMsgProcessor(10001, new LoginGameProcessor10001());
	}

	@Override
	public MsgDispatcher getMsgDispatcher() {
		return null;
	}

	@Override
	public String getWorkPath() {
		return null;
	}

	@Override
	public String getProccessProp() {
		return null;
	}

	@Override
	public boolean checkIP(String ip) {
		// TODO check ip
		return true;
	}

	@Override
	public MsgProcessor getMsgProcessor(int msgCode) {
		return msgDispatcher.getMsgProcessor(msgCode);
	}

	@Override
	public void sessionClosed(GamePlayer player) {

	}
}
