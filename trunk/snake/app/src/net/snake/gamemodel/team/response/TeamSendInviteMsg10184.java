package net.snake.gamemodel.team.response;


import net.snake.netio.ServerResponse;

/**
 * 向被邀请者发送组队 或加入队伍请求
 *
 */
public class TeamSendInviteMsg10184 extends ServerResponse{
public TeamSendInviteMsg10184(int id,String name,short grade){
	this.setMsgCode(10184);
	try {
		this.writeInt(id);
		this.writeUTF(name);
		this.writeShort(grade);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
}
