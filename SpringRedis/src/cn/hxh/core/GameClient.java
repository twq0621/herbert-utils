package cn.hxh.core;

import org.jboss.netty.channel.Channel;

public class GameClient {

	private Channel channel;

	public GameClient(Channel channel) {
		this.channel = channel;
	}

	public String getIP() {
		return channel.getRemoteAddress().toString();
	}

	public int getChannelId() {
		return channel.getId();
	}

	public Channel getChannel() {
		return channel;
	}

	public void send(Object obj) {
		if (obj == null || !channel.isConnected()) {
			return;
		}
		channel.write(obj);
	}

	public void send(byte[] bytes) {
		if (bytes == null || !channel.isConnected()) {
			return;
		}
		channel.write(bytes);
	}

}
