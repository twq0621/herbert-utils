package net.snake.gamemodel.fight.response;

import net.snake.netio.ServerResponse;

public class VehicleMsg51120 extends ServerResponse {

	public VehicleMsg51120(int time) {
		this.setMsgCode(51120);
		this.writeByte(time);
	}
}
