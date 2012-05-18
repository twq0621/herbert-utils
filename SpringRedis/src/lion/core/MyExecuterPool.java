package lion.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lion.codec.INotNeedAuthProcessor;
import lion.codec.IServerDispatcher;
import lion.codec.IThreadProcessor;
import lion.codec.MsgProcessor;
import lion.message.MyRequestMsg;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyExecuterPool {

	public static final Logger logger = LoggerFactory.getLogger(MyExecuterPool.class);

	private IServerDispatcher gameServer;

	public static Map<Integer, GamePlayer> gamePlayermap = new ConcurrentHashMap<Integer, GamePlayer>();

	public MyExecuterPool(IServerDispatcher gameServer) {
		this.gameServer = gameServer;
	}

	public void executeIoRequest(final Channel channel, final Object remoteObj) {
		MyRequestMsg request = (MyRequestMsg) remoteObj;
		GamePlayer player = gamePlayermap.get(channel.getId());
		if (player == null) {
			logger.info("player is null");
			return;
		}
		player.setLastTime(System.currentTimeMillis());
		int msgCode = request.getMsgCode();
		// 该消息处理是系统消息，但ip不是授权ip
		if (msgCode < 10000 && !gameServer.checkIP(player.getAddress())) {
			logger.error("invalidate request from ?", player.getAddress());
			return;
		}
		MsgProcessor processor = gameServer.getMsgProcessor(msgCode);
		if (processor == null) {
			return;
		}
		if (!processor.isEnable()) {
			logger.warn("msg processor is closed " + msgCode);
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
	}

	public static void initGamePlayer(Channel channel) {
		gamePlayermap.put(channel.getId(), GamePlayer.newPlayer4Session(channel));
	}

}
