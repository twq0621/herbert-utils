package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;

public class GoodsDelete10178 extends ServerResponse {

	public GoodsDelete10178(short postion, int horseid) {
		setMsgCode(10178);
		writeShort(postion);
		writeInt(horseid);
	}
}
