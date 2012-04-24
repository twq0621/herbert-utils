package net.snake.gamemodel.team.response;





import net.snake.netio.ServerResponse;

/**
 * 广播玩家新队长消息
 *
 */
public class TeamBroadMsg10224 extends ServerResponse{
	public TeamBroadMsg10224(int leaderId){
		this.setMsgCode(10224);
		try {
			this.writeInt(leaderId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
