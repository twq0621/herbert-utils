package net.snake.gamemodel.pet.response;

import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;

public class GetHorseNeidanResponse  extends ServerResponse {

	public GetHorseNeidanResponse(Horse horse,byte b) {
		setMsgCode(60028);
		writeInt(horse.getCharacterHorse().getId());
		writeByte(b);
	}
}
