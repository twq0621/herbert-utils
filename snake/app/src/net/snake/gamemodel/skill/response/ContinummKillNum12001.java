package net.snake.gamemodel.skill.response;

import net.snake.netio.ServerResponse;

public class ContinummKillNum12001 extends ServerResponse {
	public ContinummKillNum12001(int num) {
		this.setMsgCode(12001);
		this.writeShort(num);
	}
}
