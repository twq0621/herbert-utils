package net.snake.gamemodel.chest.response;

import net.snake.netio.ServerResponse;

public class ChestResponse60108 extends ServerResponse {

	public ChestResponse60108(byte type, int chestype) {
		setMsgCode(60108);
		writeByte(type);
		writeInt(chestype);
	}
}
