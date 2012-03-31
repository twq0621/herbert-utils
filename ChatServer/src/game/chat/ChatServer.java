package game.chat;

import game.chat.service.ChatServerEnter;
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
public class ChatServer {

	private static Logger logger = LoggerFactory.getLogger(ChatServer.class);

	private NettyServer amf3Server;

	public ChatServer(Class<? extends IGameService> serviceClass) {
		amf3Server = new NettyServer(serviceClass);
		amf3Server.initServer();
		amf3Server.startServer(8650);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		ChatServer server = new ChatServer(ChatServerEnter.class);
		logger.info("server init success!,factory={},server={}", factory, server);
	}
}
