package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

/**
 * 当前肉身状态
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieRoushenValResp extends ServerResponse {
	public DujieRoushenValResp(int value) {
		super();
		setMsgCode(60306);
		writeInt(value);
	}
}
