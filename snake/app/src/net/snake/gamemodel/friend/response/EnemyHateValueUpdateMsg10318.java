package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;

/**
 * 仇恨值更新消息
 * 
 * @author serv_dev
 * 
 */
public class EnemyHateValueUpdateMsg10318 extends ServerResponse {
	public EnemyHateValueUpdateMsg10318(CharacterFriend cf) {
		this.setMsgCode(10318);
		this.writeInt(cf.getFriendId());
		this.writeInt(cf.getHateValue());
	}
}
