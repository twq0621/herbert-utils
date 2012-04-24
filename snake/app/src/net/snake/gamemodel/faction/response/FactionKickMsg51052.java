package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionKickMsg51052 extends ServerResponse {

	public FactionKickMsg51052(int factionId) {
		this.setMsgCode(51052);
		this.writeInt(factionId);
	}

}
