package cn.hxh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hxh.amf3.Amf3Server;

public class MainServer {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	private Amf3Server amf3Server;

	public MainServer() {
		init();
	}

	private void init() {
		amf3Server = new Amf3Server();
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
		// CommonStatManager commonStatManager = factory
		// .getBean(CommonStatManager.class);
		// int ret = commonStatManager.getNewRole("2012-03-23");
		// logger.info("login role={}", ret);
		// commonStatManager.testProtoGet();
		// CallPool.init(GameService.class);
		// GetNewRole_C2S c2s = new GetNewRole_C2S();
		// c2s.setQueryDay("2012-03-13");
		// CallPool.execute(c2s);
	}
}
