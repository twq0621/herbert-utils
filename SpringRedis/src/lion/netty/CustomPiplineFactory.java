package lion.netty;

import lion.codec.MyCustomDecoder;
import lion.codec.MyCustomEncoder;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class CustomPiplineFactory implements ChannelPipelineFactory {

	public static final int MAX_BUFFER_SIZE = 1048576;

	private final ChannelUpstreamHandlerFactory handlerFactory;

	public CustomPiplineFactory(ChannelUpstreamHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		// 处理coder
		pipeline.addLast("decoder", new MyCustomDecoder(MAX_BUFFER_SIZE));
		// pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("encoder", new MyCustomEncoder());
		//
		pipeline.addLast("handler", handlerFactory.getChannelUpstreamHandler());
		//
		return pipeline;
	}
}
