package net.snake.gamemodel.team.response;


import net.snake.netio.ServerResponse;


/**
 * 广播队伍消息消息
 * 
 * 
 */
public class TeamBroadMsg10228 extends ServerResponse {
	public TeamBroadMsg10228(int msgKey, String... vars) {
		this.setMsgCode(10228);
		try {
				this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
