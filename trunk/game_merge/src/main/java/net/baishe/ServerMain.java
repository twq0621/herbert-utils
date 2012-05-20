package net.baishe;

import net.baishe.service.ServerMerge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerMain {

	private static Logger logger = LoggerFactory.getLogger(ServerMain.class);

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("init successfully!");
		if (args.length != 3) {
			System.out.println("请输入3个参数：目标db名 源db1 源db2");
			System.exit(1);
		}
		ServerMerge serverMerge = factory.getBean(ServerMerge.class);
		serverMerge.startMerge(args[0], args[1], args[2]);
	}

}
