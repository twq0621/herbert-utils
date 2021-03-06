package lion.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import lion.core.ClientCallPool;
import lion.core.IGameService;

public class NettyClient {

	protected NioClientSocketChannelFactory _factory;

	private final ClientBootstrap bootstrap;

	public NettyClient(Class<? extends IGameService> serviceClass) {
		_factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		bootstrap = new ClientBootstrap(_factory);
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("connectTimeoutMillis", 10000);
		bootstrap.setOption("soLinger", 0);// by hxh,断开连接立即释放socket资源
		bootstrap.setPipelineFactory(pipelineFactory);
		init(serviceClass);
	}

	private void init(Class<? extends IGameService> serviceClass) {
		ClientCallPool.init(serviceClass);
	}

	private final ChannelUpstreamHandlerFactory handlerFactory = new ChannelUpstreamHandlerFactory() {
		public ChannelUpstreamHandler getChannelUpstreamHandler() {
			return new NettyClientChannelHandler();
		}
	};

	// private final ChannelPipelineFactory pipelineFactory = new Amf3PipelineFactory(handlerFactory);
	private final ChannelPipelineFactory pipelineFactory = new CustomPiplineFactory(handlerFactory);

	public void shutdown() {
		bootstrap.releaseExternalResources();
	}

	public Channel connect(String host, int port) {
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
		future.awaitUninterruptibly();
		assert future.isDone();
		if (future.isCancelled()) {
			System.out.println("cancelled");
			return null;
		} else if (!future.isSuccess()) {
			System.out.println("failed");
			return null;
		}
		Channel channel = future.getChannel();
		return channel;
	}

}
