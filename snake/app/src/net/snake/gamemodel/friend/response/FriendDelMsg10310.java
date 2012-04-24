package net.snake.gamemodel.friend.response;


import net.snake.netio.ServerResponse;

/**
 * 删除某玩家成功
 * @author serv_dev
 *
 */
public class FriendDelMsg10310 extends ServerResponse {

	public FriendDelMsg10310(int delId) {
		this.setMsgCode(10310);
			this.writeInt(delId);
	}

}
