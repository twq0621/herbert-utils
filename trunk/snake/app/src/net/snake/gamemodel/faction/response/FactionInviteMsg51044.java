package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionInviteMsg51044 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionInviteMsg51044.class);

	public FactionInviteMsg51044(Hero character, FactionController factionC) {
		this.setMsgCode(51044);
		try {
			this.writeInt(character.getId());
			this.writeUTF(character.getViewName());
			this.writeShort(character.getGrade());
			this.writeByte(character.getPopsinger());
			this.writeInt(factionC.getFaction().getId());
			this.writeUTF(factionC.getFaction().getViewName());
			this.writeByte(character.getHeadimg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
