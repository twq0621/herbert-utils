package cn.hxh.amf3;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import cn.hxh.codec.AMF3Decoder;
import cn.hxh.codec.AMF3Encoder;

public class Amf3ServerPipelineFactory implements ChannelPipelineFactory {

	public static final int MAX_BUFFER_SIZE = 65536;

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		// 处理coder
		pipeline.addLast("decoder", new AMF3Decoder(MAX_BUFFER_SIZE));
		pipeline.addLast("encoder", new AMF3Encoder());
		//
		pipeline.addLast("handler", new Amf3ProtocolHandler());
		//
		return pipeline;
	}

}
