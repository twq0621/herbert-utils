package net.snake.gamemodel.vip.response;

import net.snake.gamemodel.vip.bean.CharacterVip;
import net.snake.netio.ServerResponse;

public class CharacterVipBufferMsg11150 extends ServerResponse {

	public CharacterVipBufferMsg11150(CharacterVip cv) {
		this.setMsgCode(11150);
		this.writeInt(cv.getCharacterId());
		this.writeInt(cv.getBufferId());
		this.writeDouble(cv.getEndTime().getTime());
	}
}
