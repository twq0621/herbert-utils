package lion.codec;

import lion.message.MyResponseMsg;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class MyCustomEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel channel, Object message) throws Exception {
		MyResponseMsg value = (MyResponseMsg) message;
		byte[] msgContent = value.entireMsg();
		ChannelBuffer buffer = ChannelBuffers.buffer(msgContent.length + 4);
		buffer.writeInt(msgContent.length);
		buffer.writeBytes(msgContent);		
		return buffer;
	}
}
