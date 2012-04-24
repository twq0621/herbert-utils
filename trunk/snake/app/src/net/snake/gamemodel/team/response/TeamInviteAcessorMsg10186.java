package net.snake.gamemodel.team.response;


import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 组队 返回被邀请者接受组队邀请后是否组队成功消息
 *
 */
public class TeamInviteAcessorMsg10186 extends ServerResponse{
public TeamInviteAcessorMsg10186(){
	this.setMsgCode(10186);
	try {
		this.writeByte(CommonUseNumber.byte1);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
public TeamInviteAcessorMsg10186(int key,String ...info){
	this.setMsgCode(10186);
	try {
		this.writeByte(CommonUseNumber.byte0);
		this.writeInterUTF(key,info);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
}
