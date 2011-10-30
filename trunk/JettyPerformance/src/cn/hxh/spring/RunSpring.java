package cn.hxh.spring;

import org.eclipse.jetty.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RunSpring {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		Server server = (Server) context.getBean("jettyServer");
		server.start();
		server.join();
	}

}
