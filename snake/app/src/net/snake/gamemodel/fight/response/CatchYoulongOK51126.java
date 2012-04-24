package net.snake.gamemodel.fight.response;

import net.snake.netio.ServerResponse;

public class CatchYoulongOK51126 extends ServerResponse {
	public CatchYoulongOK51126(int horseid) {
		setMsgCode(51126);
		writeByte(1);
	}

	public CatchYoulongOK51126(int horseid, int msgKey) {
		setMsgCode(51126);
		try {
			writeByte(0);
			writeInterUTF(msgKey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
