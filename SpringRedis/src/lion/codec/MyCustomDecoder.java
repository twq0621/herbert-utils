package lion.codec;

import java.io.UnsupportedEncodingException;

import lion.message.ClientRequestII;
import lion.message.MyRequestMsg;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCustomDecoder extends LengthFieldBasedFrameDecoder {

	public static final Logger logger = LoggerFactory.getLogger(AMF3Decoder.class);

	private String securityXml = null;
	private byte[] securityXmlcontent;

	/**
	 * 
	 * @param maxFrameLength
	 *            包的最大大小
	 * @param lengthFieldOffset
	 *            包头信息，长度的偏移位
	 * @param lengthFieldLength
	 *            包头信息，长度位数
	 */
	public MyCustomDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		StringBuilder policeFile = new StringBuilder();
		policeFile.append("<cross-domain-policy>\n").append("<site-control permitted-cross-domain-policies=\"all\"/>\n");
		policeFile.append("<allow-access-from domain=\"*\" to-ports=\"*\" />\n").append("</cross-domain-policy>\0");
		securityXml = policeFile.toString();
		try {
			securityXmlcontent = securityXml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param maxFrameLength
	 */
	public MyCustomDecoder(int maxFrameLength) {

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
		// 读字节流的内容
		byte[] content = new byte[frame.readableBytes()];
		frame.readBytes(content);
		MyRequestMsg request = new ClientRequestII(content);
		return request;
	}

}
