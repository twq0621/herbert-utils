package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家普通消息广播
 * 
 * @author serv_dev
 * 
 */
public class ChatSceneMsg30112 extends ServerResponse {
	public ChatSceneMsg30112(Hero chatRole, String content, boolean isGm) {
		this.setMsgCode(30112);
		try {
			this.writeInt(chatRole.getId());
			this.writeUTF(chatRole.getViewName());
			this.writeUTF(content);
			this.writeBoolean(isGm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public ChatSceneMsg30112(String content) {
		this.setMsgCode(30112);
		try {
			this.writeInt(1);
			this.writeUTF("说");
			this.writeUTF(content);
			this.writeBoolean(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
