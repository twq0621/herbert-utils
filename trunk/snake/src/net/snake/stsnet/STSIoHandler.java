package net.snake.stsnet;

import java.util.Map;

import net.snake.gmtool.net.Msg;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


public class STSIoHandler implements IoHandler {
	private static Logger logger = Logger.getLogger(STSIoHandler.class);
	
	private Map<String, STSHandler> handlerMap;
	
	public void setHandlerMap(Map<String, STSHandler> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public void sessionCreated(IoSession session) throws Exception {
		logger.info("create: "+session);
	}

	public void sessionOpened(IoSession session) throws Exception {
		logger.info("sessionOpened: "+session);
	}

	public void sessionClosed(IoSession session) throws Exception {
		logger.info("sessionClosed: "+session);
	}
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		Msg tkmessage = (Msg) message;
		int function = tkmessage.getFunction();
		logger.info("cross server recieved a msg ："+function+" msg len ："+tkmessage.getLength());
		STSHandler handler = handlerMap.get(String
				.valueOf(function));
		if (handler != null) {
			//logger.debug("handler:"+logonhandler+" process");
			handler.handleMessage(session, tkmessage);
		}else{
			logger.error("nohandler:"+function);
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
//		causlogger.error(e.getMessage(),e);
//		logger.info("sessionIdle: "+cause.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		if (logger.isDebugEnabled()) {
//			UserSession usersession = sessionManager.getUserSession(session);
//			if (usersession != null) {
				logger.debug(message + " SentOk ");
//			} else {
////				UserInfo userinfo = (UserInfo) usersession
////						.getAttribute(AttrConst.TK_USERINFO);
////				if (userinfo != null) {
////					logger.debug("Send >> " + userinfo + " " + message);
////				}
//			}
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("sessionIdle: "+session);
		session.close(true);
	}
}
