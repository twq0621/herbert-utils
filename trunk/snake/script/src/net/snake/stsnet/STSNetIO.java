package net.snake.stsnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class STSNetIO {
private static Logger logger = Logger.getLogger(STSNetIO.class);

	private NioSocketAcceptor acceptor;
	private IoHandler iohandler;
	private OrderedThreadPoolExecutor threadpool;
	private boolean listening=false;
	private static void tryBindPort(String host, int port) throws Exception {
		Socket s = new Socket();
		s.setSoLinger(false, 0);
		s.bind(new InetSocketAddress(host, port));
		s.close();		
	}
	
	private static boolean isUsedPort(int port) {
		try {
			tryBindPort("0.0.0.0", port);
//			tryBindPort(InetAddress.getLocalHost().getHostAddress(), port);
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	public OrderedThreadPoolExecutor getThreadpool() {
		return threadpool;
	}
	public void stopNetListen() {
		if(!listening){
			return;
		}
		if (acceptor != null) {
			//不监听
			acceptor.unbind();			
		}
		threadpool.shutdown();
		
	}
	public void destroy(){		
		//关闭执行处理器
		if(!listening){
			return;
		}
		
		if (acceptor != null) {			
			//不监听
			acceptor.dispose();	
		}
	}

	public void startNetListen(int listenPort) {
		if(isUsedPort(listenPort)){
			logger.info(listenPort + "端口已经存在监听未启用新的监听");
			return;
		}
		try {
			listening=true;
			acceptor = new NioSocketAcceptor(1);			
			acceptor.setBacklog(1000); //设置主服务监听端口的监听队列的最大值为5000，如果当前已经有5000个连接，再新的连接来将被服务器拒绝 
			acceptor.setReuseAddress(true);//设置每一个非主监听连接的端口可以重用   
			acceptor.setHandler(iohandler);
			DefaultIoFilterChainBuilder fcb = acceptor.getFilterChain();
			IoFilter protocol = new ProtocolCodecFilter(
					new VSTSEncoder(),new VSTSDecoder());
			fcb.addLast("codec", protocol);
//			fcb.addLast("messageFireWallFilter",new MessageFireWallFilter());
			threadpool=new OrderedThreadPoolExecutor(500);
			
			
			fcb.addLast("threadPool", new ExecutorFilter(threadpool));
			int recsize = 1024*1024*50;
			int sendsize =1024*1024*50;
			int timeout =200;

			SocketSessionConfig sc = acceptor.getSessionConfig();
			sc.setReuseAddress(true);// 设置每一个非主监听连接的端口可以重用
			sc.setReceiveBufferSize(recsize);// 设置输入缓冲区的大小
			sc.setSendBufferSize(sendsize);// 设置输出缓冲区的大小
			sc.setTcpNoDelay(true);// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出   
			sc.setSoLinger(0);
			sc.setIdleTime(IdleStatus.READER_IDLE, timeout);

			acceptor.bind(new InetSocketAddress(listenPort));
			logger.info("在端口开启监听服务成功"+Integer.valueOf(listenPort));
		} catch (IOException e) {
			logger.error("在" + listenPort + "端口开启监听服务失败，退出程序", e);
			System.exit(1);
		}
	}
	
	public void setIoHandler(IoHandler iohandler) {
		this.iohandler=iohandler;
	}

	public STSNetIO() {
	}
}
