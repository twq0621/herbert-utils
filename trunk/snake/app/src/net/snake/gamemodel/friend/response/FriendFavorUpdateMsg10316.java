package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;

/**
 * 好感度值更新消息
 * @author serv_dev
 *
 */
public class FriendFavorUpdateMsg10316 extends ServerResponse {
	public FriendFavorUpdateMsg10316(CharacterFriend cf) {
		this.setMsgCode(10316);
			this.writeInt(cf.getFriendId());	
			this.writeInt(cf.getFavor());
	}
}
