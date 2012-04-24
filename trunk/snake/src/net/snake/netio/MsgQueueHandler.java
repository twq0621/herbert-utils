package net.snake.netio;

import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IServerDispatcher;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
/**
 * 将收到的消息加入GamePlayer请求队列的队尾，在轮询执行器中执行。
 * 
 * @author serv_dev
 */
public class MsgQueueHandler extends IoHandlerAdapter {
	private static final Logger logger = Logger.getLogger(MsgQueueHandler.class);
	private IServerDispatcher gameServer;

	public MsgQueueHandler(IServerDispatcher gameServer) {
		this.gameServer = gameServer;
	}

	/**
	 * 此函数仅仅在sessin对象被创建时调用，并不能保证连接的建立。
	 */
	@Override
	public void sessionCreated(IoSession session) {
		logger.info("have new session,ip="+session.getRemoteAddress());
		GamePlayer.newPlayer4Session(session);
	}

	@Override
	public void sessionOpened(IoSession session) {
		// 可能open和receive有被同时调用的可能,因此先转到create上
	}

	/**
	 * 将请求消息分发到合适的处理器处理。<br>
	 * 当请求消息解码结束，此函数被调用。
	 */
	@Override
	public void messageReceived(IoSession session, Object msg) {
		RequestMsg request = (RequestMsg) msg;
		request.BitReversion();
		GamePlayer player = GamePlayer.getGamePlayer(session);
		if (player == null) {
			logger.info("player is null");
			return;
		}
		player.setLastTime(System.currentTimeMillis());
		int msgCode = request.getMsgCode();
		// 该消息处理是系统消息，但ip不是授权ip
		if (msgCode < 10000 && !gameServer.checkIP(player.getAddress())) {
			logger.error("invalidate request from "+ player.getAddress());
			return;
		}
		MsgProcessor processor = gameServer.getMsgProcessor(msgCode);
		if (processor == null) {
			return;
		}
		if (!processor.isEnable()) {
			logger.warn("msg processor is closed "+msgCode);
			return;
		}
		if (!(processor instanceof INotNeedAuthProcessor) && !player.isValidate()) {
			return;
		}
		// GameServer.concurrentMsgProcessorcount.incrementAndGet();
		// 该消息处理不需要会话通过验证，或者会话已经通过验证
		if (player.getSessionType() == GamePlayer.Session_Type_Chat) {
			try {
				processor.process(player, request);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else if (processor instanceof IThreadProcessor) {
			try {
				processor.process(player, request);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			player.addRequest(request);
		}
		// GameServer.concurrentMsgProcessorcount.decrementAndGet();
	}

	@Override
	public void exceptionCaught(IoSession sessin, Throwable cause) {
	}

	@Override
	public void messageSent(IoSession session, Object msg) {
	}

	@Override
	public void sessionClosed(IoSession session) {
		// 如果需要重用角色,不想执行该方法,需要在踢角色前调用player.removeIOSessionMap();
		// 这样就找到到会话对应的player
		logger.info("session closed");
		GamePlayer player = GamePlayer.getGamePlayer(session);
		if (player == null) {
			return;
		}
		gameServer.sessionClosed(player);
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	}
}
