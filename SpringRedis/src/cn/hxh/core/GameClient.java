package cn.hxh.core;

import org.jboss.netty.channel.Channel;

import cn.hxh.netty.NettyClient;

public class GameClient {

	private NettyClient amf3Client;

	private Channel channel;

	public GameClient(NettyClient amf3Client) {
		this.amf3Client = amf3Client;
	}

	public void connect(String host, int port) {
		channel = amf3Client.connect(host, port);
	}

	public void send(Object msg) {
		if (channel == null || !channel.isConnected()) {
			System.out.println("channel not ready!");
			return;
		}
		channel.write(msg);
	}

}
