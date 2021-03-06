package lion.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lion.codec.IServerDispatcher;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

	public static final int MIN_READ_BUFFER_SIZE = 64;

	public static final int INITIAL_READ_BUFFER_SIZE = 16384;

	public static final int MAX_READ_BUFFER_SIZE = 65536;

	public static final int THREAD_POOL_SIZE = 16;

	public static final int CHANNEL_MEMORY_LIMIT = MAX_READ_BUFFER_SIZE * 2;

	public static final long GLOBAL_MEMORY_LIMIT = Runtime.getRuntime().maxMemory() / 3;

	private ServerBootstrap _bootstrap;

	private final NettySeverChannelHandler handler = new NettySeverChannelHandler();

	private final ChannelUpstreamHandlerFactory handlerFactory = new ChannelUpstreamHandlerFactory() {
		public ChannelUpstreamHandler getChannelUpstreamHandler() {
			return handler;
		}
	};

	private final ChannelPipelineFactory pipelineFactory = new CustomPiplineFactory(handlerFactory);

	public NettyServer(IServerDispatcher serviceClass) {
		init(serviceClass);
	}

	private void init(IServerDispatcher serviceClass) {
		handler.init(serviceClass);
	}

	public void initServer() {
		boolean threadPoolDisabled = true;
		int workerCount = Runtime.getRuntime().availableProcessors() * 2 + 1;
		NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), workerCount);
		_bootstrap = new ServerBootstrap(factory);
		if (!threadPoolDisabled) {
			_bootstrap.getPipeline().addLast(
					"executor",
					new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(THREAD_POOL_SIZE, CHANNEL_MEMORY_LIMIT, GLOBAL_MEMORY_LIMIT, 0,
							TimeUnit.MILLISECONDS)));
		}
		_bootstrap.setOption("child.tcpNoDelay", true);
		_bootstrap.setOption("child.keepAlive", true);
		_bootstrap.setOption("child.receiveBufferSizePredictorFactory", new AdaptiveReceiveBufferSizePredictorFactory(MIN_READ_BUFFER_SIZE,
				INITIAL_READ_BUFFER_SIZE, MAX_READ_BUFFER_SIZE));
		_bootstrap.setPipelineFactory(pipelineFactory);
	}

	public boolean startServer(int serverPort) {
		try {
			return _bootstrap.bind(new InetSocketAddress(serverPort)).isBound();
		} catch (ChannelException e) {
			logger.error("", e);
			return false;
		}
	}

	public void stop() {
		_bootstrap.getFactory().releaseExternalResources();
	}

}
