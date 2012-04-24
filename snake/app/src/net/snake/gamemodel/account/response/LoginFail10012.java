package net.snake.gamemodel.account.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;

public class LoginFail10012 extends ServerResponse {

	public LoginFail10012(int msgKey) {
		setMsgCode(10012);
		ServerResponse out = this;
		try {
			out.writeByte(0);
			out.writeInterUTF(msgKey);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
