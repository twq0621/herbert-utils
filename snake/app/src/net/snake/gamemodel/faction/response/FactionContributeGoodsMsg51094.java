package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionContributeGoodsMsg51094 extends ServerResponse {

	public FactionContributeGoodsMsg51094() {
		this.setMsgCode(51094);
		this.writeByte(1);
	}
}
