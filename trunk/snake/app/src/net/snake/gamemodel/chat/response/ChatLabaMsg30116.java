package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家喇叭消息广播
 * 
 * @author serv_dev
 * 
 */
public class ChatLabaMsg30116 extends ServerResponse {
	public ChatLabaMsg30116(Hero chatRole, String content, boolean isGm) {
		this.setMsgCode(30116);
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
