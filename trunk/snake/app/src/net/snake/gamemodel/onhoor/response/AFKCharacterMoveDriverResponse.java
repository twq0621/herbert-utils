package net.snake.gamemodel.onhoor.response;

import net.snake.netio.ServerResponse;

/**
 * 挂机 告诉客户端 怪物位置
 * 
 * @author serv_dev
 * 
 */
public class AFKCharacterMoveDriverResponse extends ServerResponse {
	private static final int MSGCODE = 40110;

	public AFKCharacterMoveDriverResponse(int monsterid, short mx, short my) {
		setMsgCode(MSGCODE);
		this.writeShort(mx);
		this.writeShort(my);
	}

}
