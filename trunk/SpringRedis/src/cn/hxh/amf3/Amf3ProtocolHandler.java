package cn.hxh.amf3;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Amf3ProtocolHandler extends SimpleChannelHandler {

	public static Logger logger = LoggerFactory
			.getLogger(Amf3ProtocolHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() != null) {
			// ChannelManager channelManager = PushServerContext
			// .getBean(ChannelManager.class);
			// if (e.getMessage() instanceof CommandMessage) {
			// channelManager.handleMsg((CommandMessage) e.getMessage(),
			// e.getChannel());
			// } else if (e.getMessage() instanceof PushMessage) {
			// channelManager.handleMsg((PushMessage) e.getMessage(),
			// e.getChannel());
			// } else {
			// log.warn("unkown message {}", e);
			// }
		}
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

}
