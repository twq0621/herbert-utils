package net.snake.gamemodel.chest.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class ChestResponse60120 extends ServerResponse {

	public ChestResponse60120(int WeiZhiSuoYin) {
		setMsgCode(60120);
		writeShort(WeiZhiSuoYin);
	}

}
