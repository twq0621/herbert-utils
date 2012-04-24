package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家组队消息广播
 * 
 * @author serv_dev
 * 
 */
public class ChatTeamMsg30110 extends ServerResponse {
	public ChatTeamMsg30110(Hero chatRole, String content, boolean isGm) {
		this.setMsgCode(30110);
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
