package net.snake.gamemodel.equipment.response.gem;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class GemTakeoutMsg50198 extends ServerResponse {

	public GemTakeoutMsg50198(int tag) {
		setMsgCode(50198);
		writeByte(tag);
	}
}
