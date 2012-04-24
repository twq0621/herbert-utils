package net.snake.stsnet;

import net.snake.gmtool.net.Msg;

import org.apache.mina.core.session.IoSession;

public interface STSHandler {
	/**
	 * @param session
	 *            　代表用户的socket连接
	 * @param message
	 * @throws Exception
	 */
	public void handleMessage(IoSession session, Msg message) throws Exception;
}
