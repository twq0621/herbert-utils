package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class XinqingUpdateMsg10374 extends ServerResponse {
	public XinqingUpdateMsg10374(Hero character) {
		this.setMsgCode(10374);
		try {
			this.writeUTF(character.getNowXingqing());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
