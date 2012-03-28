package cn.hxh.amf3;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import cn.hxh.codec.AMF3Decoder;
import cn.hxh.codec.AMF3Encoder;

public class Amf3PipelineFactory implements ChannelPipelineFactory {

	public static final int MAX_BUFFER_SIZE = 1048576;

	private final ChannelUpstreamHandlerFactory handlerFactory;

	public Amf3PipelineFactory(ChannelUpstreamHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		// 处理coder
		pipeline.addLast("decoder", new AMF3Decoder(MAX_BUFFER_SIZE));
//		pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("encoder", new AMF3Encoder());
		//
		pipeline.addLast("handler", handlerFactory.getChannelUpstreamHandler());
		//
		return pipeline;
	}

}
