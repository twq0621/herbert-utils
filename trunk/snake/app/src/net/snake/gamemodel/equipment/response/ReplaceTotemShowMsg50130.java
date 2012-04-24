package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class ReplaceTotemShowMsg50130 extends ServerResponse {

	public ReplaceTotemShowMsg50130(int pos, String oldtotem, String newtotem) {
		setMsgCode(50130);
		try {
			writeShort(pos);
			writeUTF(oldtotem);
			writeUTF(newtotem);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
