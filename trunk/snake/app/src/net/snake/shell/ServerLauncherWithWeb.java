package net.snake.shell;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.snake.GameServer;

/**
 * 游戏应用的外部启动接口。
 * 
 * @author serv_dev
 * 
 */
public class ServerLauncherWithWeb implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		GameServer gameserver = (GameServer) arg0.getServletContext().getAttribute("GameServer");
		ServerConsole.serverStop(gameserver);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		GameServer server = GameServer.getInstance();
		String realpath = arg0.getServletContext().getRealPath("/").replace("\\", "/");
		realpath = realpath + "WEB-INF/";
		ServerConsole.serverStart(server, realpath);
		arg0.getServletContext().setAttribute("GameServer", server);
	}
}
