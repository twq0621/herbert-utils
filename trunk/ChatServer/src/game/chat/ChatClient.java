package game.chat;

import game.chat.dto.ConnectChat_C2S;
import game.chat.service.ChatClientEnter;
import lion.core.GameClient;
import lion.netty.NettyClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChatClient {

	private static Logger logger = LoggerFactory.getLogger(ChatClient.class);

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("spring init success!,factory={}", factory);
		NettyClient amf3Client = new NettyClient(ChatClientEnter.class);
		GameClient sampleClient = new GameClient(amf3Client);
		sampleClient.connect("127.0.0.1", 8653);
		ConnectChat_C2S reqMsg = new ConnectChat_C2S();
		reqMsg.setRoleName("huige");
		sampleClient.send(reqMsg);
	}

}
