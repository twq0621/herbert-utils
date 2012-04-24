package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 通知客户端摆摊更新
 */
public class FriendBaitanUpdateMsg10322 extends ServerResponse {
	public FriendBaitanUpdateMsg10322(Hero character) {
		this.setMsgCode(10322);
		this.writeInt(character.getId());
		this.writeByte(character.getCharacterStatus());

	}
}
