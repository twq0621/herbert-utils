package net.snake.gamemodel.map.response.goods;


import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.ServerResponse;


public class GoodsAutoPickUpMsg11166 extends ServerResponse {
	public GoodsAutoPickUpMsg11166(SceneDropGood goods) {
		setMsgCode(11166);
			writeInt(goods.getId());
	}
}
