package net.snake.gamemodel.equipment.response.shelizi;

import net.snake.netio.ServerResponse;

public class ResetShenliziMsg52118 extends ServerResponse {

	public ResetShenliziMsg52118(int result, int nextpos) {
		setMsgCode(52118);
		writeByte(result);
		writeShort(nextpos);
	}
}
