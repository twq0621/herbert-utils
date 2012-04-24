package net.snake.gamemodel.chat.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 玩家私聊消息发送
 * 
 * @author serv_dev
 * 
 */
public class ChatPrivateMsg30106 extends ServerResponse {
	public ChatPrivateMsg30106(Hero sendChatRole, String content, Hero accessChatRole, boolean isGm) {
		this.setMsgCode(30106);
		try {
			this.writeInt(sendChatRole.getId());
			this.writeUTF(sendChatRole.getViewName());
			this.writeUTF(content);
			this.writeInt(accessChatRole.getId());
			this.writeUTF(accessChatRole.getViewName());
			this.writeBoolean(isGm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
