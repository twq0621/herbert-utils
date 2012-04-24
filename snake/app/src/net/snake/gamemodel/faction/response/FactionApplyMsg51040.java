package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


public class FactionApplyMsg51040 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionApplyMsg51040.class);
	public FactionApplyMsg51040(Hero character) {
		this.setMsgCode(51040);
		try {
			this.writeInt(character.getId());
			this.writeUTF(character.getViewName());
			this.writeShort(character.getGrade());
			this.writeByte(character.getPopsinger());
			this.writeByte(character.getHeadimg());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
