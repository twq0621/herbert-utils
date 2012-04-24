package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

/**
 * 离开副本是不是主动要求传送出来的。
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public class LeavDujieFuben extends ServerResponse {
	public LeavDujieFuben(int transout) {
		super();
		setMsgCode(60308);
		writeByte(transout);
	}
	
}
