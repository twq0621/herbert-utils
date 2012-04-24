package net.snake.api.log.udp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.log4j.Logger;
public class MaintainUdpClientHandler extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(MaintainUdpClientHandler.class);

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		String msg = obj.toString();
		if (logger.isInfoEnabled()) {
			logger.info("udp receive:"+ msg);
		}
		
		if (msg.startsWith("QOTM: ")) {
			session.close(true);
		}
	}

	@Override
	public void exceptionCaught(IoSession sessin, Throwable cause) throws Exception {
		logger.error("send udp log error:" + cause.getMessage());
	}

	public void sessionClosed(IoSession session) throws Exception {
		logger.error("send udp log session is close");
	}
}