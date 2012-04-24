package net.snake.gamemodel.friend.response;

import net.snake.netio.ServerResponse;

public class CharacterDownMsg10314 extends ServerResponse {
	public CharacterDownMsg10314(int id) {
		this.setMsgCode(10314);
		this.writeInt(id);
	}
}
