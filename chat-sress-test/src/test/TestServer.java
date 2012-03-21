package test;

import gear.amf3.Amf3CP;
import gear.robot.IRobotHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("[host] [port] [threadCount] [chatCount]");
			System.exit(1);
		}

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();
		String host = args[0];
		int port = Integer.valueOf(args[1]);
		int maxThreadCount = Integer.valueOf(args[2]);
		int maxMsgCount = Integer.valueOf(args[3]);
		Amf3CP cp = new Amf3CP(host, port);
		ActHandler handler = new ActHandler(maxThreadCount, maxMsgCount);
		ExecutorService threadPool = Executors
				.newFixedThreadPool(maxThreadCount);
		for (int i = 1; i <= maxThreadCount; i++) {
			final ChatRobot robot = new ChatRobot(handler, cp, "xlm",
					i);
			robot.setMaxMsgCount(maxMsgCount);
			robot.setMaxThreadCount(maxThreadCount);
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					robot.think();
				}
			});
		}
	}
}
