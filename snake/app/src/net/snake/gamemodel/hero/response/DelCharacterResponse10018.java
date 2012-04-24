package net.snake.gamemodel.hero.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;

public class DelCharacterResponse10018 extends ServerResponse {
	private static final int MSGCODE = 10018;

	public DelCharacterResponse10018() {
		this.setMsgCode(MSGCODE);
		writeByte(1);
	}

	public DelCharacterResponse10018(String errormsg) {
		this.setMsgCode(MSGCODE);
		try {
			writeByte(0);
			writeUTF(errormsg);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public DelCharacterResponse10018(int msgKey) {
		this.setMsgCode(MSGCODE);
		try {
			writeByte(0);
			writeInterUTF(msgKey);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
