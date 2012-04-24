package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionDisMissMsg51056 extends ServerResponse {
	public FactionDisMissMsg51056(int factionId) {
		this.setMsgCode(51052);
		this.writeInt(factionId);
	}

}
