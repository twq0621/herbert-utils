package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionContributeCopperMsg51092 extends ServerResponse {

	public FactionContributeCopperMsg51092() {
		this.setMsgCode(51092);
		this.writeByte(1);
	}
}
