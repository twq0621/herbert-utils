package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class HushenfuJinJieMsg52116 extends ServerResponse {

	public HushenfuJinJieMsg52116(int result, int nextpos) {
		setMsgCode(52116);
		writeByte(result);
		writeShort(nextpos);
	}
}
