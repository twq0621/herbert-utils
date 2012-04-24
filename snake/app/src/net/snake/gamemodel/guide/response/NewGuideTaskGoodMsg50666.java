package net.snake.gamemodel.guide.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class NewGuideTaskGoodMsg50666 extends ServerResponse {
	public NewGuideTaskGoodMsg50666(CharacterGoods characterGoods) {
		setMsgCode(50666);
		writeShort(characterGoods.getPosition());
		writeShort(characterGoods.getQuickbarindex());
		writeInt(characterGoods.getGoodmodelId());
		writeInt(characterGoods.getCount());
	}
}
