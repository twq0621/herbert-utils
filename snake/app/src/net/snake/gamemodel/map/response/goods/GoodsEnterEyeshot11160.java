package net.snake.gamemodel.map.response.goods;

import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.ServerResponse;

public class GoodsEnterEyeshot11160 extends ServerResponse {
	public GoodsEnterEyeshot11160(SceneDropGood goods) {
		setMsgCode(11160);
		writeInt(goods.getId());
		writeShort(goods.getX());
		writeShort(goods.getY());
		writeInt(goods.getCg().getGoodmodelId());
		writeInt(goods.getCg().getCount());
		writeByte(goods.getCg().getPingzhiColor());

		// 场景id(int)，掉落包ID（int）,掉落像素X(int),掉落像素Y(int),物品id(int)
		// 说明：物品为铜币时，物品ID为-1

	}
}
