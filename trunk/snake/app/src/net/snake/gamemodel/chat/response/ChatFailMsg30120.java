package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;

/**
 * 失败消息 1私聊/2帮会/3组队/4普通/5世界
 * 
 * @author serv_dev
 * 
 */
public class ChatFailMsg30120 extends ServerResponse {
	public ChatFailMsg30120(byte channel, int msgKey, String... vars) {
		this.setMsgCode(30120);
		try {
			this.writeByte(channel);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
