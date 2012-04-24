package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionFlagIcoChangeMsg51088 extends ServerResponse {

	public FactionFlagIcoChangeMsg51088() {
		this.setMsgCode(51088);
		this.writeByte(1);

	}
}
