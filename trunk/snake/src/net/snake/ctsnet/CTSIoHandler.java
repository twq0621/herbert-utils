package net.snake.ctsnet;

import java.util.Map;

import net.snake.gmtool.net.Msg;
import net.snake.stsnet.STSHandler;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.log4j.Logger;
public class CTSIoHandler implements IoHandler {
	private static Logger logger = Logger.getLogger(CTSIoHandler.class);

	private Map<String, STSHandler> handlerMap;

	public void setHandlerMap(Map<String, STSHandler> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public void sessionCreated(IoSession session) throws Exception {
		logger.info("sessionCreated" + session);
	}

	public void sessionOpened(IoSession session) throws Exception {
		logger.info("sessionOpened" + session);
	}

	public void sessionClosed(IoSession session) throws Exception {
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		Msg tkmessage = (Msg) message;
		int function = tkmessage.getFunction();
		STSHandler handler = handlerMap.get(String.valueOf(function));
		if (handler != null) {
			handler.handleMessage(session, tkmessage);
		} else {
			logger.error("nohandler:" + function);
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("sessionIdle" + session);
		session.close(true);
	}
}
