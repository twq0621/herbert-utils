package cn.hxh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hxh.stat.CommonStatManager;

public class MainServer {

	private static Logger logger = LoggerFactory.getLogger(MainServer.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CommonStatManager commonStatManager = factory
				.getBean(CommonStatManager.class);
		int ret = commonStatManager.getNewRole("2012-03-23");
		logger.info("login role={}", ret);
		commonStatManager.testProtoGet();
	}
}
