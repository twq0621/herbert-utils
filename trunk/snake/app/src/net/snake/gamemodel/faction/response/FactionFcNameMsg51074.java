package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


public class FactionFcNameMsg51074 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionFcNameMsg51074.class);
	public FactionFcNameMsg51074(String str) {
		this.setMsgCode(51074);
		try {
			this.writeByte(0);
			this.writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public FactionFcNameMsg51074() {
		this.setMsgCode(51074);
			this.writeByte(1);

	}
}
