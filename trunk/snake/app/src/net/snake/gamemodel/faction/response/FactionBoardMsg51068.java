package net.snake.gamemodel.faction.response;

import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

/**
 * 帮会广播消息
 * @author serv_dev
 *
 */
public class FactionBoardMsg51068 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(FactionBoardMsg51068.class);
	public FactionBoardMsg51068(int characterID,int factionId,byte state,String factionName) {
		this.setMsgCode(51068);
		try {
			this.writeInt(characterID);
			this.writeByte(state);
			this.writeInt(factionId);
			this.writeUTF(factionName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
}
