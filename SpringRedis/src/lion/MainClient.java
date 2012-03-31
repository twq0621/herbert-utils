package lion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lion.core.GameClient;
import lion.dto.Login_C2S;
import lion.netty.NettyClient;
import lion.service.ClientGameService;

public class MainClient {

	private static Logger logger = LoggerFactory.getLogger(MainClient.class);

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("spring init success!,factory={}", factory);
		NettyClient amf3Client = new NettyClient(ClientGameService.class);
		GameClient sampleClient = new GameClient(amf3Client);
		sampleClient.connect("127.0.0.1", 8653);
		//		GetNewRole_C2S msg = new GetNewRole_C2S();
		//		msg.setQueryDay("2012-03-14");
		//		sampleClient.send(msg);
		//		GetOnlineNames_C2S msg2 = new GetOnlineNames_C2S();
		Login_C2S msg = new Login_C2S();
		msg.setName("wujian");
		msg.setPwd("123456");
		sampleClient.send(msg);
	}

}
