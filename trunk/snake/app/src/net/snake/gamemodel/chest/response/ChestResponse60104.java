package net.snake.gamemodel.chest.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 */
public class ChestResponse60104 extends ServerResponse {

	public ChestResponse60104(byte type, int chestype) {
		setMsgCode(60104);
		writeByte(type);
		writeInt(chestype);
	}
}
