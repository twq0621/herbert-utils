package net.snake.gamemodel.achieve.response;

import java.util.List;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveManger;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.fight.bean.CharacterAchieveCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class AchieveListMsg51002 extends ServerResponse {
	public AchieveListMsg51002(Hero character, int kind) {
		this.setMsgCode(51002);
		this.writeByte(kind);
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByKind(kind);
		this.writeShort(list.size());
		MyCharacterAchieveCountManger mcac = character.getMyCharacterAchieveCountManger();
		MyCharacterAchieveManger mcam = character.getMyCharacterAchieveManger();
		for (Achieve achieve : list) {
			this.writeInt(achieve.getId());
			Achieve a = mcam.getFinishAchieve(achieve.getId());
			if (a != null) {
				this.writeInt(achieve.getAchieveCount());
			} else {
				CharacterAchieveCount cac = mcac.getAchieveCountAllBychildKind(achieve.getChildKind());
				if (cac != null) {
					this.writeInt(cac.getAchieveCount());
				} else {
					this.writeInt(0);
				}
			}
		}
	}
}
