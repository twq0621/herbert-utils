package net.snake.shell;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.snake.GameServer;

public class LaunchServlet extends HttpServlet {

	private static final long serialVersionUID = 968367344317488953L;

	private GameServer getServerInstance() {
		ServletContext context = this.getServletContext();
		GameServer gameserver = (GameServer) context.getAttribute("GameServer");
		return gameserver;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		if (op == null) {
			return;
		}
		if (op.equals("")) {
			return;
		}

		GameServer server = getServerInstance();
		if (op.equals("start")) {
			String realpath = getServletContext().getRealPath("/").replace("\\", "/");
			realpath = realpath + "WEB-INF/";
			ServerConsole.serverStart(server, realpath);
		} else if (op.equals("stop")) {
			ServerConsole.serverStop(server);

		}
	}

}
