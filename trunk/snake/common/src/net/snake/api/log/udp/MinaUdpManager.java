package net.snake.api.log.udp;

import java.net.InetSocketAddress;

import net.snake.api.log.MaintainStat;
import net.snake.api.log.UdpManager;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DatagramConnector;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class MinaUdpManager implements UdpManager {
	DatagramConnector connector;
	IoSession session;

	@SuppressWarnings("rawtypes")
	public MinaUdpManager(String ip ,int port) {
		MaintainUdpClientHandler handler = new MaintainUdpClientHandler();
		connector = new NioDatagramConnector();
		connector.setHandler(handler);
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new GameProtocolcodecFactory()));
		IoFuture connFuture = connector.connect(new InetSocketAddress(ip,port));
		connFuture.addListener(new IoFutureListener() {
			public void operationComplete(IoFuture future) {
				ConnectFuture connFuture = (ConnectFuture) future;
				if (connFuture.isConnected()) {
					session = future.getSession();
				}
			}
		});
	}

	public void write(MaintainStat stat) {
		session.write(stat);
	}

	public void stop() {
		session.close(true);
		connector.dispose();
	}
}
