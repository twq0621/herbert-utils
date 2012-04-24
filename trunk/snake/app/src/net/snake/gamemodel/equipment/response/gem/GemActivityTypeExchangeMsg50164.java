package net.snake.gamemodel.equipment.response.gem;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class GemActivityTypeExchangeMsg50164 extends ServerResponse {
	public GemActivityTypeExchangeMsg50164(byte tag,short position){
		setMsgCode(50164);
			writeByte(tag);
			writeShort(position);
		
		
	}
}
