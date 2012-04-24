package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class CailiaoCombining50120 extends ServerResponse {

	public CailiaoCombining50120(int flag, int position) {
		setMsgCode(50120);
		writeByte(flag);
		if (flag == 1) {
			writeShort(position);
		}

	}

}
