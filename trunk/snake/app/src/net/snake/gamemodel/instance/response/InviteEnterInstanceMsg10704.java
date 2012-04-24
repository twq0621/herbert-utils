package net.snake.gamemodel.instance.response;

import net.snake.netio.ServerResponse;

/**
 * 发送邀请
 * 
 * @author serv_dev
 * 
 */
public class InviteEnterInstanceMsg10704 extends ServerResponse {
	public InviteEnterInstanceMsg10704(int sceneId) {
		this.setMsgCode(10704);
		this.writeInt(sceneId);
	}

}
