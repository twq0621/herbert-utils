package net.snake.gamemodel.equipment.response.gem;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class GemActivityTypeExchangeQueryMsg50162 extends ServerResponse {
	/**
	 * @param tag 是否可以
	 * @param pos 新宝石位置
	 * @throws IOException 
	 */
	public GemActivityTypeExchangeQueryMsg50162(byte tag,byte grade) {
		setMsgCode(50162);
			writeByte(tag);
			writeByte(grade);
	}
}
