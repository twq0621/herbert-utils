package net.snake.gamemodel.friend.response;

import net.snake.netio.ServerResponse;

public class BlackListDelMsg10360 extends ServerResponse {
	public BlackListDelMsg10360(int delId) {
		this.setMsgCode(10360);
		this.writeInt(delId);
	}
}
