package net.snake.gamemodel.fight.response;

import net.snake.netio.ServerResponse;

public class CatchYoulongDelay51124 extends ServerResponse {
	public CatchYoulongDelay51124(int monsterId, int delay) {
		setMsgCode(51124);
		writeByte(delay);
	}
}
