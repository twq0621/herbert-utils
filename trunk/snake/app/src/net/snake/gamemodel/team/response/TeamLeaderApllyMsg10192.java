package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;


public class TeamLeaderApllyMsg10192 extends ServerResponse{
	public TeamLeaderApllyMsg10192(){
		this.setMsgCode(10192);
			this.writeByte(CommonUseNumber.byte1);
	}
	public TeamLeaderApllyMsg10192(int key,String ...info){
		this.setMsgCode(10192);
		try {
			this.writeByte(CommonUseNumber.byte0);
			this.writeInterUTF(key,info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
