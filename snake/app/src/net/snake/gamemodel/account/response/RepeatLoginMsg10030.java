package net.snake.gamemodel.account.response;

import net.snake.netio.ServerResponse;

/**
 * 通知客户端重复登入
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */

public class RepeatLoginMsg10030 extends ServerResponse {
	public RepeatLoginMsg10030() {
		this.setMsgCode(10030);
	}

}
