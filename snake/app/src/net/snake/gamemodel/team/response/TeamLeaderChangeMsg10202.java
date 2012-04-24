package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 通知老对长更换队长操作是否成功
 *
 */
public class TeamLeaderChangeMsg10202 extends ServerResponse{
	public TeamLeaderChangeMsg10202(){
		this.setMsgCode(10202);
		try {
			this.writeByte(CommonUseNumber.byte1);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public TeamLeaderChangeMsg10202(String info){
		this.setMsgCode(10202);
		try {
			this.writeByte(CommonUseNumber.byte0);
			this.writeUTF(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
