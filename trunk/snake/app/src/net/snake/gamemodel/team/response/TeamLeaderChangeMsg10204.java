package net.snake.gamemodel.team.response;


import net.snake.netio.ServerResponse;

/**
 * 转发玩家提升为新队长消息
 *
 */
public class TeamLeaderChangeMsg10204 extends ServerResponse{
	public TeamLeaderChangeMsg10204(int leaderId,String name){
		this.setMsgCode(10204);
		try {
			this.writeInt(leaderId);
			this.writeUTF(name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
