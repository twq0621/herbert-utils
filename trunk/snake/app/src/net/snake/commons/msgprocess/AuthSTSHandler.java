package net.snake.commons.msgprocess;

import org.apache.mina.core.session.IoSession;

import net.snake.gmtool.net.Msg;
import net.snake.stsnet.STSHandler;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public abstract class AuthSTSHandler implements STSHandler {

	@Override
	public void handleMessage(IoSession session, Msg message) throws Exception {
		handleAuthMessage(session, message);
	}

	public abstract void handleAuthMessage(IoSession session, Msg message) throws Exception;

}
