package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class ZhanWenKeHuaResultMsg50180 extends ServerResponse {

	public ZhanWenKeHuaResultMsg50180(int result) {
		setMsgCode(50180);
		writeByte(result);
	}
}
