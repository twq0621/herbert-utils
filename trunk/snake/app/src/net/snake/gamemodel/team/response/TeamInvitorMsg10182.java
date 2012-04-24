package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 组队 返回邀请者邀请结果
 *
 */
public class TeamInvitorMsg10182 extends ServerResponse{
public TeamInvitorMsg10182(){
	this.setMsgCode(10182);
		this.writeByte(CommonUseNumber.byte1);
}
public TeamInvitorMsg10182(int key,String ...info){
	this.setMsgCode(10182);
	try {
		this.writeByte(CommonUseNumber.byte0);
		this.writeInterUTF(key,info);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
}
