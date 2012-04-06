package lion.codec;

import java.io.ByteArrayInputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;

public class AMF3Decoder extends LengthFieldBasedFrameDecoder {

	public static final Logger logger = LoggerFactory.getLogger(AMF3Decoder.class);

	/**
	 * 
	 * @param maxFrameLength
	 *            包的最大大小
	 * @param lengthFieldOffset
	 *            包头信息，长度的偏移位
	 * @param lengthFieldLength
	 *            包头信息，长度位数
	 */
	public AMF3Decoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	}

	/**
	 * 
	 * @param maxFrameLength
	 */
	public AMF3Decoder(int maxFrameLength) {
		super(maxFrameLength, 0, 4, 0, 0);
	}

	/**
	  * 
	  */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		ChannelBuffer frame = (ChannelBuffer) super.decode(ctx, channel, buffer);
		if (frame == null) {
			return null;
		}
		int dataLength = frame.readInt();
		logger.info("data length={}", dataLength);
		// 读AMF3字节流的内容
		byte[] content = new byte[frame.readableBytes()];
		frame.readBytes(content);
		SerializationContext serializationContext = new SerializationContext();
		Amf3Input amf3Input = new Amf3Input(serializationContext);
		amf3Input.setInputStream(new ByteArrayInputStream(content));
		Object message = amf3Input.readObject();
		return message;
	}

}
