package net.snake.gamemodel.common.response;

import net.snake.netio.ServerResponse;

/**
 * 防沉迷系统消息
 */

public class AntiAddictedMsg10070 extends ServerResponse {
	public AntiAddictedMsg10070(int msgkey, String... vars) {
		this.setMsgCode(10070);
		try {
			this.writeInterUTF(msgkey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
