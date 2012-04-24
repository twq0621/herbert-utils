package net.snake.gamemodel.equipment.response.extra;

import net.snake.netio.ServerResponse;

public class ResetExtraPropertyMsg50110 extends ServerResponse {

	public ResetExtraPropertyMsg50110(int flag) {
		setMsgCode(50110);
		writeByte(flag);
	}
}
