package net.snake.gamemodel.map.response.goods;


import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.ServerResponse;


public class GoodsLeaveEyeshot11164 extends ServerResponse {
	public GoodsLeaveEyeshot11164(SceneDropGood goods) {
		setMsgCode(11164);
			writeInt(goods.getId());
	}
}
