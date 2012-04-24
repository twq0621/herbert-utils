package net.snake.gamemodel.team.response;








import net.snake.netio.ServerResponse;

/**
 * 组队广播玩家下线
 *
 */
public class TeamBroadMsg10232 extends ServerResponse{
	public TeamBroadMsg10232(int cId){
		this.setMsgCode(10232);
		try {
			this.writeInt(cId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}
	
}
