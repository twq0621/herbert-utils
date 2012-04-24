package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

public class FactionNoticeMsg51076 extends ServerResponse {

	public FactionNoticeMsg51076(String str) {
		this.setMsgCode(51076);
		try {
			this.writeByte(0);
			this.writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public FactionNoticeMsg51076() {
		this.setMsgCode(51076);
		this.writeByte(1);
	}
}
