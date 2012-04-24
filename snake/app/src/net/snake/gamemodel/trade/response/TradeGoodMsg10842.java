package net.snake.gamemodel.trade.response;

import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.netio.ServerResponse;

/**
 * 物品品更新消息
 * 
 * 
 */
public class TradeGoodMsg10842 extends ServerResponse {
	public TradeGoodMsg10842(IMyTradeController mtc) {
		this.setMsgCode(10842);
		this.writeInt(mtc.getCharacter().getId());
		Collection<CharacterGoods> collection = mtc.getGoodsCollection();
		this.writeByte(collection.size());
		for (CharacterGoods cg : collection) {
			this.writeInt(cg.getGoodmodelId());
			this.writeByte(cg.getPingzhiColor());
			this.writeShort(cg.getTradeIndex());
			this.writeShort(cg.getPosition());
			this.writeInt(cg.getCount());
		}

	}
}
