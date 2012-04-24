package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 离队是否成功
 *
 */
public class TeamLeaveMsg10200 extends ServerResponse{
	public TeamLeaveMsg10200(){
		this.setMsgCode(10200);
		try {
			this.writeByte(CommonUseNumber.byte1);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public TeamLeaveMsg10200(String info){
		this.setMsgCode(10200);
		try {
			this.writeByte(CommonUseNumber.byte0);
			this.writeUTF(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
