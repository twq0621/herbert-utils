package net.snake.gamemodel.friend.response;

import net.snake.netio.ServerResponse;

public class EnemyDelMsg10344 extends ServerResponse {
	public EnemyDelMsg10344(int delId) {
		this.setMsgCode(10344);
		this.writeInt(delId);
	}
}
