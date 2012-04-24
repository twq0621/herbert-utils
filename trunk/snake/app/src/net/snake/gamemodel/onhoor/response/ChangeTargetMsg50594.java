package net.snake.gamemodel.onhoor.response;

import net.snake.netio.ServerResponse;

public class ChangeTargetMsg50594 extends ServerResponse {

	public ChangeTargetMsg50594(int targetId) {
		setMsgCode(50594);
		writeInt(targetId);
	}
}
