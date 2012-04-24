package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;

/**
 * 登入成功失败
 * 
 * @author serv_dev
 * 
 */
public class LoginMsg30102 extends ServerResponse {
	public static final int OK = 1;
	public static final int ERR = 0;

	public LoginMsg30102(int isSuccess) {
		this.setMsgCode(30102);
		this.writeByte(isSuccess);
	}
}
