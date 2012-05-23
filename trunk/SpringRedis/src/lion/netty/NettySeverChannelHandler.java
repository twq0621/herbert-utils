package lion.netty;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;

import lion.codec.IServerDispatcher;
import lion.core.ChannelClose_C2S;
import lion.core.LogicExecuterPool;
import lion.core.MyExecuterPool;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettySeverChannelHandler extends SimpleChannelHandler {

	public static Logger logger = LoggerFactory.getLogger(NettySeverChannelHandler.class);

	private MyExecuterPool executerPool = null;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() != null) {
			executerPool.executeIoRequest(e.getChannel(), e.getMessage());
			// LogicExecuterPool.executeIoRequest(e.getChannel(), e.getMessage());
		} else {
			logger.error("", e);
		}
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		MyExecuterPool.removeGamePlayer(e.getChannel());
		super.channelClosed(ctx, e);
	}

	public void init(IServerDispatcher gameServer) {
		executerPool = new MyExecuterPool(gameServer);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		if (e.getCause() instanceof ClosedChannelException) {
			logger.info("ClosedChannelException: {}", e);
		} else if (e.getCause() instanceof IOException) {
			logger.info("IOException: {}", e.getCause().getMessage());
		} else {
			logger.warn("Exception: {}", e.getCause());
		}
		if (e.getChannel().isOpen()) {
			e.getChannel().close();
		}
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelOpen(ctx, e);
		MyExecuterPool.initGamePlayer(ctx.getChannel());
	}

}
