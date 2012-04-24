package net.snake.gamemodel.gm.response;

import net.snake.netio.ServerResponse;

public class MsgToUpLoad904 extends ServerResponse {
	public MsgToUpLoad904(byte tag) {
		this.setMsgCode(904);
		writeByte(tag);
	}
}
