package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionKickMsg51050 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionKickMsg51050.class);

	public FactionKickMsg51050(int msgKey, String... vars) {
		this.setMsgCode(51050);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public FactionKickMsg51050(FactionCharacter fc) {
		this.setMsgCode(51050);
		try {
			this.writeByte(1);
			this.writeInt(fc.getCharacterId());
			this.writeUTF(fc.getCce().getViewName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
