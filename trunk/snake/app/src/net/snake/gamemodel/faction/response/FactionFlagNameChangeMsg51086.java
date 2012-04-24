package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionFlagNameChangeMsg51086 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionFlagNameChangeMsg51086.class);

	public FactionFlagNameChangeMsg51086(String bangqiName) {
		this.setMsgCode(51086);
		try {
			this.writeByte(1);
			this.writeUTF(bangqiName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
