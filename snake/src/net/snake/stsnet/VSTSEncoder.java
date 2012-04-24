package net.snake.stsnet;

import net.snake.gmtool.net.Msg;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class VSTSEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession session) throws Exception {	

	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		Msg tkmessage = (Msg) message;
		//tkmessage.setContent(zip(tkmessage.getContent()));

		IoBuffer buf = IoBuffer.allocate(6 + tkmessage.getLength())
				.setAutoExpand(true);
		buf.putShort((short)tkmessage.getFunction());
		buf.putInt(tkmessage.getLength());
		if (tkmessage.getContent() != null) {
			buf.put(tkmessage.getContent());
		}
		buf.flip();
		out.write(buf);
	}
}
