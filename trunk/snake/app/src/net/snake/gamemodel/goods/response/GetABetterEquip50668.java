package net.snake.gamemodel.goods.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class GetABetterEquip50668 extends ServerResponse {
	public GetABetterEquip50668(CharacterGoods goods) {
		setMsgCode(50668);
		writeShort(goods.getPosition());
	}
}
