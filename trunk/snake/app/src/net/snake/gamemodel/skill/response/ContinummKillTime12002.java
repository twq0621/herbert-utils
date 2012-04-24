package net.snake.gamemodel.skill.response;

import net.snake.netio.ServerResponse;

public class ContinummKillTime12002 extends ServerResponse {
	public ContinummKillTime12002(int time) {
		this.setMsgCode(12002);
		this.writeByte(time);

	}

}
