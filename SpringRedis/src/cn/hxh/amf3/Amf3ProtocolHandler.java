package cn.hxh.amf3;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flex.messaging.messages.CommandMessage;

public class Amf3ProtocolHandler extends SimpleChannelHandler {

	public static Logger log = LoggerFactory
			.getLogger(Amf3ProtocolHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() != null) {
//			ChannelManager channelManager = PushServerContext
//					.getBean(ChannelManager.class);
//			if (e.getMessage() instanceof CommandMessage) {
//				channelManager.handleMsg((CommandMessage) e.getMessage(),
//						e.getChannel());
//			} else if (e.getMessage() instanceof PushMessage) {
//				channelManager.handleMsg((PushMessage) e.getMessage(),
//						e.getChannel());
//			} else {
//				log.warn("unkown message {}", e);
//			}
		}
	}

}
