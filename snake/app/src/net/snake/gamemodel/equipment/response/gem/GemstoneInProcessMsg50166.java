package net.snake.gamemodel.equipment.response.gem;

import net.snake.netio.ServerResponse;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class GemstoneInProcessMsg50166 extends ServerResponse {
	public GemstoneInProcessMsg50166(int result, int pos) {
		setMsgCode(50166);
		writeByte(result);
		writeShort(pos);
	}
}
