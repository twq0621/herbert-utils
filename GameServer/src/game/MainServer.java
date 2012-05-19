package game;

import game.pool.FrameUpdateService;
import game.service.ServerServiceEnter;
import lion.core.IGameService;
import lion.netty.NettyServer;

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
public class MainServer {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	private NettyServer amf3Server;
	private FrameUpdateService frameUpdataService = null;

	public MainServer(Class<? extends IGameService> serviceClass) {
		amf3Server = new NettyServer(serviceClass);
		amf3Server.initServer();
		amf3Server.startServer(8653);
		addShutDownHook();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		MainServer server = new MainServer(ServerServiceEnter.class);
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
	}
}
