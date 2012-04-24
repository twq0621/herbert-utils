package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;

/**
 * 通知游戏服务器更新玩家禁言信息
 * 
 * @author serv_dev
 * 
 */
public class ChatToGsMsg641 extends ServerResponse {

	public ChatToGsMsg641(int id, long time) {
		this.setMsgCode(641);
		this.writeInt(id);
		this.writeLong(time);
	}

}
