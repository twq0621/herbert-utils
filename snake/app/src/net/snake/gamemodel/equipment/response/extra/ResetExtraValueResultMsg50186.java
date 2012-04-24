package net.snake.gamemodel.equipment.response.extra;


import net.snake.netio.ServerResponse;


/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class ResetExtraValueResultMsg50186 extends ServerResponse {

	public ResetExtraValueResultMsg50186(int result) {
		setMsgCode(50186);
			writeByte(result);
	}
	
}
