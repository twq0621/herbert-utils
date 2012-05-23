package game;

import game.logic.account.LoginRequest;
import game.service.ClientServiceEnter;
import lion.core.GameClient;
import lion.netty.NettyClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameClientV2 {

	private static Logger logger = LoggerFactory.getLogger(GameClientV2.class);

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("spring init success!,factory={}", factory);
		NettyClient amf3Client = new NettyClient(ClientServiceEnter.class);
		GameClient sampleClient = new GameClient(amf3Client);
		sampleClient.connect("127.0.0.1", 8653);

		LoginRequest lr = new LoginRequest(15, 16);
		sampleClient.send(lr);
	}

}
