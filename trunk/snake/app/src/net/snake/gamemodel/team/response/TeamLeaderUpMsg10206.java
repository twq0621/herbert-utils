package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 返回给接受提升者提升队长是否成功
 *
 */
public class TeamLeaderUpMsg10206 extends ServerResponse{
	public TeamLeaderUpMsg10206(){
		this.setMsgCode(10206);
		try {
			this.writeByte(CommonUseNumber.byte1);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public TeamLeaderUpMsg10206(String info){
		this.setMsgCode(10206);
		try {
			this.writeByte(CommonUseNumber.byte0);
			this.writeUTF(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
