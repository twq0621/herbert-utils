package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;

public class BackAdmin902 extends ServerResponse {
	public BackAdmin902(int type) {
		this.setMsgCode(902);
		writeByte(type);
	}
}
