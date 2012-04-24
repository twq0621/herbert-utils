package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionFlagUpGradeMsg51090 extends ServerResponse {

	public FactionFlagUpGradeMsg51090() {
		this.setMsgCode(51090);
		this.writeByte(1);

	}
}
