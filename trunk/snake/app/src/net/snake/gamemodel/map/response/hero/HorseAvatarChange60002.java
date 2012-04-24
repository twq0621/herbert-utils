package net.snake.gamemodel.map.response.hero;

import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;


public class HorseAvatarChange60002 extends ServerResponse {
	public HorseAvatarChange60002(Horse horse,boolean ridestate) {
		setMsgCode(60002);
		ServerResponse out=this;
	
			out.writeInt(horse.getCharacter().getId());
			out.writeBoolean(ridestate);
			out.writeInt(horse.getHorseModel().getId());
	}
}
