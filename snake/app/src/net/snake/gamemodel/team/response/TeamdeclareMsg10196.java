package net.snake.gamemodel.team.response;


import net.snake.netio.ServerResponse;


public class TeamdeclareMsg10196 extends ServerResponse{

	
	public TeamdeclareMsg10196(int msgKey,String... vars) {
		this.setMsgCode(10196);
		try {
			this.writeByte(0);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public TeamdeclareMsg10196(String name) {
		this.setMsgCode(10196);
		try {
			this.writeByte(1);
			this.writeUTF(name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
