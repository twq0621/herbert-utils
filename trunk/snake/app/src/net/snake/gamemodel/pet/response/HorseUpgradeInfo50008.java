package net.snake.gamemodel.pet.response;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;

public class HorseUpgradeInfo50008 extends ServerResponse {
	public HorseUpgradeInfo50008(Horse horse) {
		setMsgCode(50008);
		ServerResponse out = this;
		out.writeInt(horse.getId());
		out.writeShort(horse.getCharacterHorse().getGrade());
	}

}
