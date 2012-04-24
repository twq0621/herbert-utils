package net.snake.gamemodel.faction.response.factioncity;

import net.snake.netio.ServerResponse;

public class FactionCityMsg51144 extends ServerResponse {
	public FactionCityMsg51144() {
		this.setMsgCode(51144);
		this.writeByte(1);
	}

	public FactionCityMsg51144(int msgKey, String... vars) {
		this.setMsgCode(51144);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
