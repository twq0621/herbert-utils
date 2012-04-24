package net.snake.gamemodel.achieve.response;

import java.util.List;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveManger;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.fight.bean.CharacterAchieve;
import net.snake.netio.ServerResponse;

public class AchieveTitleListMsg51004 extends ServerResponse {
	public AchieveTitleListMsg51004(MyCharacterAchieveManger mcam) {
		this.setMsgCode(51004);
		int point = mcam.getChengjiuPoint();
		List<CharacterAchieve> list = mcam.getTitleList();
		this.writeInt(point);
		this.writeShort(list.size());
		for (int i = 0; i < list.size(); i++) {
			CharacterAchieve ca = list.get(i);
			this.writeInt(ca.getAchieveId());
		}
		this.writeInt(AchieveManager.getInstance().getMaxPoint());

	}
}
