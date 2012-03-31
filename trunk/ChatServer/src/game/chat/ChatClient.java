package game.chat;

import game.chat.dto.ConnectChat_C2S;
import game.chat.service.ChatClientEnter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		sampleClient.connect("127.0.0.1", 8650);
		ConnectChat_C2S reqMsg = new ConnectChat_C2S();
		reqMsg.setSid("ddj3lslsl");
		reqMsg.setUserName("机灰哥");
		reqMsg.setRoleName("huige");
		sampleClient.send(reqMsg);
		int maxThread = 10;
		ExecutorService threadPool = Executors.newFixedThreadPool(maxThread);
		for (int i = 0; i < maxThread; i++) {
			final GameClient chatClient = new GameClient(amf3Client);
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					chatClient.connect("127.0.0.1", 8650);
					ConnectChat_C2S reqMsg = new ConnectChat_C2S();
					reqMsg.setSid("ddj3lslsl");
					reqMsg.setUserName("机灰哥");
					reqMsg.setRoleName("huige");
					chatClient.send(reqMsg);
				}
			});
		}
	}

}
