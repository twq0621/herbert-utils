package lion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hxh.core.IGameService;
import cn.hxh.netty.NettyServer;
import cn.hxh.service.ServerGameService;

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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		MainServer server = new MainServer(ServerGameService.class);
		logger.info("server init success!,factory={},server={}", factory, server);
	}
}
