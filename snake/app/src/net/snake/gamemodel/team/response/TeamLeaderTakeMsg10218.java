package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 返回给对长踢人是否成功
 *
 */
public class TeamLeaderTakeMsg10218 extends ServerResponse{
	public TeamLeaderTakeMsg10218(){
		this.setMsgCode(10218);
			this.writeByte(CommonUseNumber.byte1);
	}
	public TeamLeaderTakeMsg10218(String info){
		this.setMsgCode(10218);
		try {
			this.writeByte(CommonUseNumber.byte0);
			this.writeUTF(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
