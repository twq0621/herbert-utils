package net.snake.gamemodel.map.response;

import net.snake.gamemodel.map.logic.MapTeleport;
import net.snake.netio.ServerResponse;

public class TeleportStatusOpen10300 extends ServerResponse {
	public TeleportStatusOpen10300(MapTeleport teleport, byte state) {
		setMsgCode(10300);
		writeInt(teleport.getScenId());
		writeInt(teleport.getId());
		writeByte(state);
	}
}
