package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

public class AddHorsePostion60004 extends ServerResponse {
	public AddHorsePostion60004(int type) {
		setMsgCode(60004);
		writeByte(type);
	}
}
