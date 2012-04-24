package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;

public class CharacterLoseGoods11214 extends ServerResponse {
	public CharacterLoseGoods11214(int modelid, int count) {
		setMsgCode(11214);
		writeInt(modelid);
		writeInt(count);
	}
}
