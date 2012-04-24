package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionLeaveMsg51058 extends ServerResponse {

	public FactionLeaveMsg51058(int msgKey, byte isSuccess, String... vars) {
		this.setMsgCode(51058);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public FactionLeaveMsg51058(int factionID) {
		this.setMsgCode(51058);
		this.writeByte(1);
		this.writeInt(factionID);
	}

}
