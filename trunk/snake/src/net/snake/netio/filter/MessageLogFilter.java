package net.snake.netio.filter;

import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class MessageLogFilter extends IoFilterAdapter {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(MessageLogFilter.class);

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		super.messageReceived(nextFilter, session, message);
		if (message instanceof RequestMsg) {
			RequestMsg requestmsg = (RequestMsg) message;
			int code = requestmsg.getMsgCode();
			if (code == 11 || code == 40301 || code == 10061) {
				return;
			}
			// if (code == 50131) {
			// return;
			// }
			// if (code == 50101) {
			// return;
			// }
			// if (code == 50125) {
			// return;
			// }
			logger.info("c->s:" + code);
		}
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		super.messageSent(nextFilter, session, writeRequest);

		if (writeRequest.getMessage() instanceof ServerResponse) {
			ServerResponse response = (ServerResponse) writeRequest.getMessage();
			int code = response.getMsgCode();

			if (code == 12) {
				return;
			}

			if (code == 1) {
				return;
			}
			if (code == 10094) {
				return;
			}
			if (code == 10096) {
				return;
			}
			if (code == 10032) {
				return;
			}
			if (code == 10098) {
				return;
			}
			if (code == 49990) {
				return;
			}
			if (code == 10148) {
				return;
			}
			if (code == 10086) {
				return;
			}
			logger.info("s->c:" + response.getMsgCode());
		}
	}
}
