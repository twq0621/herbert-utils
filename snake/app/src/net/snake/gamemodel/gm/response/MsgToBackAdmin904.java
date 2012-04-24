package net.snake.gamemodel.gm.response;

import net.snake.netio.ServerResponse;

public class MsgToBackAdmin904 extends ServerResponse {
	public MsgToBackAdmin904(byte tag) {
		this.setMsgCode(904);
		writeByte(tag);
	}
}
