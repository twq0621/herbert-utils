package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class HushenfuJinJieMsg52124 extends ServerResponse {

	public HushenfuJinJieMsg52124(int result, int nextpos) {
		setMsgCode(52124);
		writeByte(result);
		writeShort(nextpos);
	}
}
