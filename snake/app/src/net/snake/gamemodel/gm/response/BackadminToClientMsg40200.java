package net.snake.gamemodel.gm.response;

import net.snake.netio.ServerResponse;

public class BackadminToClientMsg40200 extends ServerResponse {
	public BackadminToClientMsg40200(byte type, int money) {
		this.setMsgCode(204);
		writeByte(type);
	}
}
