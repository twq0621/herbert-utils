package net.snake.gamemodel.faction.response.factioncity;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class FactionCityChangeLineMsg51134 extends ServerResponse {
	public FactionCityChangeLineMsg51134(int lineId, String lineName) {
		this.setMsgCode(51134);
		try {
			this.writeByte(lineId);
			this.writeUTF(lineName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
	}
}
