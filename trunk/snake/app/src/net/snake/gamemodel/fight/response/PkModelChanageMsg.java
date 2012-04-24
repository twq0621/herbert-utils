package net.snake.gamemodel.fight.response;

import net.snake.netio.ServerResponse;

/**
 * 10902 pk模式
 * 
 * @author serv_dev
 * 
 */
public class PkModelChanageMsg extends ServerResponse {

	public PkModelChanageMsg(int pk) {
		setMsgCode(10902);
		writeByte(pk);
	}
}
