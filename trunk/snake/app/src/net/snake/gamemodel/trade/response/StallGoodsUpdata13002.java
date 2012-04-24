package net.snake.gamemodel.trade.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


public class StallGoodsUpdata13002 extends ServerResponse {
	public StallGoodsUpdata13002(CharacterGoods goods) {
		setMsgCode(13002);
			//摊位索引(short)、物品ID(int)、数量(short)、铜钱（int)、元宝(int)
			writeShort(goods.getPosition());
			writeInt(goods.getGoodmodelId());
			writeInt(goods.getCount());
			writeInt(goods.getStallCopper());
			writeInt(goods.getStallIngot());
			writeByte(goods.getPingzhiColor());//X
			writeByte(goods.getBind());
	}
}
