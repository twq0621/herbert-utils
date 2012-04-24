package net.snake.gamemodel.pet.catchpet;

import net.snake.netio.ServerResponse;


public class CatchHorseDelay50044 extends ServerResponse {
	public CatchHorseDelay50044(int horseid,int delay) {
		setMsgCode(50044);
			writeInt(horseid);
			writeByte(delay);
	}
}
