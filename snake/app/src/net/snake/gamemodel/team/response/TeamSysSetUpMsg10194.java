package net.snake.gamemodel.team.response;


import net.snake.netio.ServerResponse;

/**
 * 返回组队系统设置是否成功
 *
 */
public class TeamSysSetUpMsg10194 extends ServerResponse {

	public TeamSysSetUpMsg10194(byte type,byte vlaue) {
		this.setMsgCode(10194);
		try {
			this.writeByte(1);
			this.writeByte(type);
			this.writeByte(vlaue);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}
	public TeamSysSetUpMsg10194(int key,String ...str) {
		this.setMsgCode(10194);
		try {
			this.writeByte(0);
			this.writeInterUTF(key,str);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}

}
