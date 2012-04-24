package net.snake.gamemodel.heroext.channel.response;

import net.snake.netio.ServerResponse;

public class ChannelResponse50210 extends ServerResponse {

	public ChannelResponse50210(byte type) {
		setMsgCode(50210);
		writeByte(type);
	}

}
