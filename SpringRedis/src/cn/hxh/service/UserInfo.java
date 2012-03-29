package cn.hxh.service;

import org.jboss.netty.channel.Channel;

public class UserInfo {

	private Channel channel;

	private String userName;

	private String currentRoleName;

	public UserInfo(Channel channel, String userName) {
		this.channel = channel;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void send(Object obj) {
		if (obj == null || !channel.isConnected()) {
			return;
		}
		channel.write(obj);
	}

	public String getCurrentRoleName() {
		return currentRoleName;
	}

	public void setCurrentRoleName(String currentRoleName) {
		this.currentRoleName = currentRoleName;
	}

}
