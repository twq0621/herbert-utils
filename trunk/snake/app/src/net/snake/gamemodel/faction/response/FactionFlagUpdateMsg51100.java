package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.faction.bean.Faction;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class FactionFlagUpdateMsg51100 extends ServerResponse {

	private static final Logger logger = Logger.getLogger(FactionFlagUpdateMsg51100.class);

	public FactionFlagUpdateMsg51100(Faction faction) {
		this.setMsgCode(51100);
		try {
			this.writeByte(faction.getFactionFlag().getfGrade());
			this.writeUTF(faction.getName());
			this.writeByte(faction.getIcoId());
			this.writeUTF(faction.getIcoStr());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
