package net.snake.gamemodel.friend.response;


import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.netio.ServerResponse;



public class LatelyLinkUpdateMsg10320 extends ServerResponse {
	public LatelyLinkUpdateMsg10320(CharacterFriend cf) {
		this.setMsgCode(10320);
			this.writeInt(cf.getFriendId());

	}
}
