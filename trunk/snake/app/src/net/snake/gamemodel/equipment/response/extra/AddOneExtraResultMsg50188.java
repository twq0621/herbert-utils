package net.snake.gamemodel.equipment.response.extra;

import net.snake.netio.ServerResponse;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class AddOneExtraResultMsg50188 extends ServerResponse {

	public AddOneExtraResultMsg50188(int result) {
		setMsgCode(50188);
		writeByte(result);
	}
}
