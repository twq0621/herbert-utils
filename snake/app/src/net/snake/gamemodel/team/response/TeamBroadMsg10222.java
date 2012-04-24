package net.snake.gamemodel.team.response;





import net.snake.netio.ServerResponse;

/**
 * 广播玩家离队消息
 *
 */
public class TeamBroadMsg10222 extends ServerResponse{
	public TeamBroadMsg10222(int leaverId){
		this.setMsgCode(10222);
			this.writeInt(leaverId);
	}
	
}
