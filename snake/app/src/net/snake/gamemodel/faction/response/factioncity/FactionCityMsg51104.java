package net.snake.gamemodel.faction.response.factioncity;

import java.util.Collection;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class FactionCityMsg51104 extends ServerResponse {

	public FactionCityMsg51104(FactionCity factionCity, Hero character) {
		this.setMsgCode(51104);
		try {
			this.writeInt(factionCity.getFactionId());
			FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionID(factionCity.getFactionId());
			this.writeUTF(factionC.getFaction().getName());
			this.writeUTF(factionCity.getNotice());
			Collection<FactionCharacter> collection = factionC.getGuganCollection();
			this.writeByte(collection.size());
			for (FactionCharacter fc : collection) {
				this.writeByte(fc.getPosition());
				this.writeInt(fc.getCharacterId());
				this.writeUTF(fc.getCce().getName());
			}
			int fanctionId = character.getMyFactionManager().getFactionId();
			if (fanctionId == 0 || fanctionId != factionCity.getFactionId()) {
				this.writeByte(0);
			} else {
				int type = FactionCityManager.getInstance().getLingQuRewardType(character);
				this.writeByte(type);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
