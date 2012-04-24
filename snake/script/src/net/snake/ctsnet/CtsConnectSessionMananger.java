/**
 * 
 */
package net.snake.ctsnet;

import java.net.InetSocketAddress;
import java.util.Map;

import net.snake.gmtool.net.Msg;
import net.snake.stsnet.STSHandler;
import net.snake.stsnet.VSTSDecoder;
import net.snake.stsnet.VSTSEncoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class CtsConnectSessionMananger {

	private static Logger logger = Logger.getLogger(CtsConnectSessionMananger.class);

	private static CtsConnectSessionMananger instance;
	public SocketConnector connector;

	private CtsConnectSessionMananger() {

	}

	public static CtsConnectSessionMananger getInstance() {
		if (instance == null) {
			instance = new CtsConnectSessionMananger();
		}
		return instance;
	}

	public void initSocketConnector(Map<String, STSHandler> handlerMap) {
		connector = new NioSocketConnector();
		CTSIoHandler ioHander = new CTSIoHandler();
		ioHander.setHandlerMap(handlerMap);
		connector.setHandler(ioHander);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new VSTSEncoder(), new VSTSDecoder()));
		connector.getFilterChain().addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor(100)));
		SocketSessionConfig sc = connector.getSessionConfig();
		sc.setReceiveBufferSize(1024 * 1024 * 50);// 设置输入缓冲区的大小
		sc.setSendBufferSize(1024 * 1024 * 50);// 设置输出缓冲区的大小
		sc.setTcpNoDelay(true);
		sc.setSoLinger(0);
		sc.setIdleTime(IdleStatus.WRITER_IDLE, 200);
	}

	/**
	 * 向指定服务器异步发送消息
	 * 
	 * @param ip
	 * @param port
	 * @param msg
	 */
	@SuppressWarnings("rawtypes")
	public void sendMsgToServer(final String ip, int port, final Msg msg) {
		logger.debug("build connect to " + ip + ":" + port);
		ConnectFuture cf = connector.connect(new InetSocketAddress(ip, port));
		cf.addListener(new IoFutureListener() {
			@Override
			public void operationComplete(IoFuture arg0) {
				logger.info("ip=" + ip + "发送消息：" + msg.getFunction() + " 消息长度" + msg.getLength());
				arg0.getSession().write(msg);
			}
		});
	}

}
