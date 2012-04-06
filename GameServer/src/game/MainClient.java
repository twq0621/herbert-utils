package game;

import game.dto.CreateRole_C2S;
import game.dto.Login_C2S;
import game.service.ClientServiceEnter;
import lion.core.GameClient;
import lion.netty.NettyClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClient {

	private static Logger logger = LoggerFactory.getLogger(MainClient.class);

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("spring init success!,factory={}", factory);
		NettyClient amf3Client = new NettyClient(ClientServiceEnter.class);
		GameClient sampleClient = new GameClient(amf3Client);
		sampleClient.connect("127.0.0.1", 8653);
		//登录
		Login_C2S msg = new Login_C2S();
		msg.setName("wujian");
		msg.setPwd("123456");

		CreateRole_C2S msg2 = new CreateRole_C2S();
		msg2.setRoleName("吴剑");
		msg2.setUserId(1);
		msg2.setGender(0);
		msg2.setCharacterId(0);
		sampleClient.send(msg2);
	}

}
