package game;

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
		//stop work
	}
}
