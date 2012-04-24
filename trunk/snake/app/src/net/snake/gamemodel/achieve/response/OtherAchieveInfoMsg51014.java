package net.snake.gamemodel.achieve.response;

import java.util.List;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveManger;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.fight.bean.CharacterAchieve;
import net.snake.gamemodel.fight.bean.CharacterAchieveCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class OtherAchieveInfoMsg51014 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(OtherAchieveInfoMsg51014.class);

	public OtherAchieveInfoMsg51014(Hero character, int kind) {
		this.setMsgCode(51014);
		try {
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
			this.writeInt(character.getId());
			this.writeUTF(character.getViewName());
			int point = mcam.getChengjiuPoint();
			List<CharacterAchieve> listtitle = mcam.getTitleList();
			this.writeInt(point);
			this.writeShort(listtitle.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
