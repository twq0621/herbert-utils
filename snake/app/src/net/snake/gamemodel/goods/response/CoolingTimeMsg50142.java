package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;

public class CoolingTimeMsg50142 extends ServerResponse {
	public CoolingTimeMsg50142(int goodId) {
		this.setMsgCode(50142);
		this.writeInt(goodId);
	}
}
