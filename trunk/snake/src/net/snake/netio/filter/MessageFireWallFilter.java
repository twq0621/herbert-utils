package net.snake.netio.filter;

import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * 在这里检查消息的频繁程度。
 * @author serv_dev
 *
 */
public class MessageFireWallFilter extends IoFilterAdapter {
	private static Logger logger = Logger
			.getLogger(MessageFireWallFilter.class);

	public void sessionCreated(NextFilter nextFilter, IoSession session)
			throws Exception {
		new MsgValidManger(session);
		nextFilter.sessionCreated(session);
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		boolean instance=message instanceof RequestMsg;
		
		if (!instance) {
			return ;
		}
		
		RequestMsg requestmsg = (RequestMsg) message;
		int code = requestmsg.getMsgCode();
		MsgValidManger mvm = (MsgValidManger) session
				.getAttribute(MsgValidManger.MSG_FIRE_WALL);
		int result = mvm.isValidMsg(code);
		
		switch (result) {
		case MsgValidManger.OK_Validity:
			nextFilter.messageReceived(session, message);
			break;
		case MsgValidManger.Alert_Validity:
			if (logger.isDebugEnabled()) {
				logger.warn("msg too frently "+code+ "result："+result+" "+session);
			}
			break;
		case MsgValidManger.Frequently_Validity:
			logger.info("防火墙过滤消息，消息频繁 关闭通道："+code+"结果：s"+ result);
			session.close(true);
			break;
		}
//		if (result == 0) {
//			nextFilter.messageReceived(session, message);
//		} else if (result == 1) {
//			if (logger.isDebugEnabled()) {
//				logger.info("防火墙过滤消息，消息频繁 {}结果：{}{}", new Object[] { code,
//						result, session });
//			}
//		}
//		if (result == 2) {
//			logger.info("防火墙过滤消息，消息频繁 关闭通道：" + code + "结果：" + result);
//			session.close(true);
//		}
	}

	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {

		nextFilter.sessionClosed(session);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		super.messageSent(nextFilter, session, writeRequest);
	}
}
