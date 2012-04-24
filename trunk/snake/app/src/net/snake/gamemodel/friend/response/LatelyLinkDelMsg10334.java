package net.snake.gamemodel.friend.response;


import net.snake.netio.ServerResponse;


public class LatelyLinkDelMsg10334 extends ServerResponse {
	public LatelyLinkDelMsg10334(int delId) {
		this.setMsgCode(10334);
			this.writeInt(delId);
	}
}
