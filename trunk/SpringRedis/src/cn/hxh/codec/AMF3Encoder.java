package cn.hxh.codec;

import java.io.ByteArrayOutputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;

public class AMF3Encoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel arg1,
			Object arg2) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		SerializationContext serializationContext = new SerializationContext();
		Amf3Output amf3Output = new Amf3Output(serializationContext);
		amf3Output.setOutputStream(stream);
		amf3Output.writeObject(arg2);
		byte[] objSe = stream.toByteArray();
		if (objSe != null && objSe.length > 0) {
			ChannelBuffer buffer = ChannelBuffers.buffer(objSe.length + 8);
			// if (arg2 instanceof PushMessage)
			// buffer.writeInt(Constants.MAGIC_NUM_PUSH_MSG);
			// else if (arg2 instanceof CommandMessage)
			// buffer.writeInt(Constants.MAGIC_NUM_COMMAND_MSG);
			buffer.writeInt(objSe.length);
			buffer.writeBytes(objSe);
			return buffer;
		}
		return null;
	}

}
