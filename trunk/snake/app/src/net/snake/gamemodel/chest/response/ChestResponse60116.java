package net.snake.gamemodel.chest.response;

import net.snake.netio.ServerResponse;

public class ChestResponse60116 extends ServerResponse {
	public ChestResponse60116(int chestType, Short count) {
		setMsgCode(60116);
		writeInt(chestType);
		writeShort(count);
	}
}
