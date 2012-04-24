package net.snake.gamemodel.pet.catchpet;

import net.snake.netio.ServerResponse;


public class CatchHorseOK50046 extends ServerResponse {
	public CatchHorseOK50046(int horseid) {
		setMsgCode(50046);
//		try {
			writeInt(horseid);
			writeByte(1);
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
	}
}
