package cn.hxh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hxh.netty.NettyServer;

/**
 * 主要启动的类
 * 
 * @author hexuhui
 * 
 */
public class MainServer {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	private NettyServer amf3Server;

	public MainServer() {
		init();
	}

	private void init() {
		amf3Server = new NettyServer();
		amf3Server.initServer();
		amf3Server.startServer(8653);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MainServer server = new MainServer();
		logger.info("server init success!,factory={},server={}", factory,
				server);
	}
}
