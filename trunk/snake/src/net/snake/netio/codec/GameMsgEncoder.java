package net.snake.netio.codec;

import net.snake.netio.message.ResponseMsg;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 消息编码器。将ResponseMsg对象编码成为连续的字节。
 * 
 * @author wutao
 * 
 */
public class GameMsgEncoder extends ProtocolEncoderAdapter {

	public GameMsgEncoder() {
	}

	/** 在此处实现对ResponseMsg的编码工作，并把它写入输出流中 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ResponseMsg value = (ResponseMsg) message;
		IoBuffer buffer = value.entireMsg();
		out.write(buffer);
	}

	public void dispose() throws Exception {
	}
}
