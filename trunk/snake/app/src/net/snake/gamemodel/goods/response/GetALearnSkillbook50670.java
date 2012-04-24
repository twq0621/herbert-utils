package net.snake.gamemodel.goods.response;


import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;


public class GetALearnSkillbook50670 extends ServerResponse {

	public GetALearnSkillbook50670(CharacterGoods goods) {
		setMsgCode(50670);
			writeShort(goods.getPosition());
	}

}
