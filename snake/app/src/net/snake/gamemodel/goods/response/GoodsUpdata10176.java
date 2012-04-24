package net.snake.gamemodel.goods.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class GoodsUpdata10176 extends ServerResponse {

	public GoodsUpdata10176(CharacterGoods goods) {
		setMsgCode(10176);
		writeShort(goods.getPosition());
		writeShort(goods.getQuickbarindex());
		writeInt(goods.getGoodmodelId());
		writeInt(goods.getCount());
		writeByte(goods.getPingzhiColor());// X
		writeByte(goods.getBind());
		writeInt(goods.getCurrDurability());
	}
}
