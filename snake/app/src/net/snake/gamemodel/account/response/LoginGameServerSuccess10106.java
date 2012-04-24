package net.snake.gamemodel.account.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author wutao
 * 
 */
public class LoginGameServerSuccess10106 extends ServerResponse {
	private static final int MSGCODE = 10106;

	public LoginGameServerSuccess10106() {
		setMsgCode(MSGCODE);
		writeByte(1);
	}

}
