package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家帮会消息广播
 * 
 * @author serv_dev
 * 
 */
public class ChatFactionMsg30108 extends ServerResponse {
	public ChatFactionMsg30108(Hero chatRole, String content, boolean isGm) {
		this.setMsgCode(30108);
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
