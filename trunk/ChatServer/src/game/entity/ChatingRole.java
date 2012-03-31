package game.entity;

import org.jboss.netty.channel.Channel;

public class ChatingRole {

	private String uName;

	private String rName;

	private Channel channel;

	public ChatingRole(String uName, String rName, Channel channel) {
		super();
		this.uName = uName;
		this.rName = rName;
		this.channel = channel;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
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

}
