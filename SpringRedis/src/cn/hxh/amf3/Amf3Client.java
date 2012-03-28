package cn.hxh.amf3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import cn.hxh.core.ClientCallPool;
import cn.hxh.service.ClientGameService;

public class Amf3Client {

	protected NioClientSocketChannelFactory _factory;

	private final ClientBootstrap bootstrap;

	public Amf3Client() {
		_factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		bootstrap = new ClientBootstrap(_factory);
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("connectTimeoutMillis", 10000);
		bootstrap.setOption("soLinger", 0);// by hxh,断开连接立即释放socket资源
		bootstrap.setPipelineFactory(pipelineFactory);
		init();
	}

	private void init() {
		ClientCallPool.init(ClientGameService.class);
	}

	private final ChannelUpstreamHandlerFactory handlerFactory = new ChannelUpstreamHandlerFactory() {
		public ChannelUpstreamHandler getChannelUpstreamHandler() {
			return new Amf3ClientChannelHandler();
		}
	};

	private final ChannelPipelineFactory pipelineFactory = new Amf3PipelineFactory(
			handlerFactory);

	public void shutdown() {
		bootstrap.releaseExternalResources();
	}

	public Channel connect(String host, int port) {
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
				port));
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
