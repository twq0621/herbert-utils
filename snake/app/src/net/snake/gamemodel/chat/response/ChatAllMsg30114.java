package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家玩家世界消息消息广播
 * 
 * @author serv_dev
 * 
 */
public class ChatAllMsg30114 extends ServerResponse {
	public ChatAllMsg30114(Hero chatRole, String content, boolean isGm) {
		this.setMsgCode(30114);
		try {
			this.writeInt(chatRole.getId());
			this.writeUTF(chatRole.getViewName());
			this.writeUTF(content);
			this.writeBoolean(isGm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
