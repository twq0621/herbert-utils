package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionNameCheckMsg51070 extends ServerResponse {

	public FactionNameCheckMsg51070(String factionName, int msgkey, String... vars) {
		this.setMsgCode(51070);
		try {
			this.writeUTF(factionName);
			this.writeByte(0);
			this.writeInterUTF(msgkey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public FactionNameCheckMsg51070(String factionName) {
		this.setMsgCode(51070);
		try {
			this.writeUTF(factionName);
			this.writeByte(1);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
