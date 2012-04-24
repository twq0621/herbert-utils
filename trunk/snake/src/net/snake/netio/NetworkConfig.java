package net.snake.netio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;


import net.snake.netio.codec.GameProtocolcodecFactory;
import net.snake.netio.filter.MessageFireWallFilter;
import net.snake.netio.filter.MessageLogFilter;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFactory;

public class NetworkConfig {
	public SocketAddress address;
	public IoHandler handler;
	public int acceptBacklog;
	public int threadPoolSize;
	public ProtocolCodecFactory factory;
	public boolean tcpNoDelay;
	public int readIdleTimeout;
	public int recieveBuffer;
	public int sendBuffer;
	public Map<String, IoFilter> filters = new HashMap<String, IoFilter>();
	
	public NetworkConfig(int port,IoHandler handler){
		address = new InetSocketAddress(port);
		this.handler = handler;
		acceptBacklog=6000;
		threadPoolSize = 500;
		factory  = new GameProtocolcodecFactory();
		tcpNoDelay = true;
		readIdleTimeout = 10;
		recieveBuffer = 5120;
		sendBuffer = 20480;
		
		filters.put("messageLogFilter", new MessageLogFilter());
		filters.put("messageFireWallFilter",new MessageFireWallFilter());
	}
	
}
