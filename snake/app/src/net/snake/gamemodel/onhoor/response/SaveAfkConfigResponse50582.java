package net.snake.gamemodel.onhoor.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class SaveAfkConfigResponse50582 extends ServerResponse {
	/**
	 * 
	 * @param guaji
	 *            0挂机 1不挂机
	 */
	public SaveAfkConfigResponse50582(int guaji) {
		setMsgCode(50582);
		writeByte(guaji);
	}
}
