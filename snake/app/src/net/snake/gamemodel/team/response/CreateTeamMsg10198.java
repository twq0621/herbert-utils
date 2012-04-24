package net.snake.gamemodel.team.response;




import net.snake.netio.ServerResponse;


public class CreateTeamMsg10198 extends ServerResponse{
	/**
	 * 发送创建成功
	 */
public CreateTeamMsg10198(){
	this.setMsgCode(10198);
		this.writeByte(1);
}
/**
 * 发送创建失败
 * @param str
 */
public CreateTeamMsg10198(int key,String ...str){
	this.setMsgCode(10198);
	try {
		this.writeByte(0);
		this.writeInterUTF(key,str);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
}
