package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionMsg51072 extends ServerResponse {

	public FactionMsg51072(int msgKey, String... vars) {
		this.setMsgCode(51072);
		try {
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
