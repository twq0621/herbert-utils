package net.snake.shell;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;


import net.snake.GameServer;

public class ServerConsole {
	private static Logger logger = Logger.getLogger(ServerConsole.class);

	public static void serverStart(GameServer server, String appRoot) {

		InputStream servParam = null;
		try {
			String work = appRoot + "gameservices/";
			GameServer.workDir = work;
			servParam = new FileInputStream(appRoot + "gameservices/serverparam.properties");
			GameServer.serverparam.load(servParam);
			server.startup();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (servParam == null) {
				return;
			}
			try {
				servParam.close();
			} catch (Exception e2) {
			}
		}

	}

	public static void serverStop(GameServer server) {
		server.shutdown();
		try {
			logger.info("游戏应用正在关闭，请稍候。。。");
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
