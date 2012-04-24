package net.snake.gamemodel.team.response;








import net.snake.netio.ServerResponse;

/**
 * 组队广播玩家上线
 *
 */
public class TeamBroadMsg10230 extends ServerResponse{
	public TeamBroadMsg10230(int onlineCId){
		this.setMsgCode(10230);
			this.writeInt(onlineCId);
	
	}
	
}
