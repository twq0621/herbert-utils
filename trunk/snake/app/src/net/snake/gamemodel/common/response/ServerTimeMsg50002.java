package net.snake.gamemodel.common.response;

import net.snake.netio.ServerResponse;

public class ServerTimeMsg50002 extends ServerResponse {

	public ServerTimeMsg50002(String str) {
		setMsgCode(50002);
		try {
			writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
