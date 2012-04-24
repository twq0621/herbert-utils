package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;

public class SpeakMsg65531 extends ServerResponse {
	public SpeakMsg65531(String str) {
		this.setMsgCode(65531);
		try {
			this.writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
