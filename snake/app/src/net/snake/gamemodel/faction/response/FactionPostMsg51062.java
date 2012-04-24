package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.netio.ServerResponse;

public class FactionPostMsg51062 extends ServerResponse {
	public FactionPostMsg51062(FactionCharacter fc) {
		this.setMsgCode(51062);
		try {
			this.writeInt(fc.getCharacterId());
			this.writeUTF(fc.getCce().getViewName());
			this.writeByte(fc.getPosition());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
