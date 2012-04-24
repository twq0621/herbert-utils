package net.snake.gamemodel.gift.response;


import net.snake.netio.ServerResponse;

public class GiftPacksUpdate50798 extends ServerResponse {
	public GiftPacksUpdate50798(int type) {
		this.setMsgCode(50798);
		this.writeByte(type);
	}
}
