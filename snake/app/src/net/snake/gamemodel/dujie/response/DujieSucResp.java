package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

public class DujieSucResp extends ServerResponse {
	
	public DujieSucResp(int suc,int process) {
		super();
		setMsgCode(60304);
		writeByte(suc);
		writeInt(process);
		}
}
