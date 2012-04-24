package net.snake.gamemodel.equipment.response.shelizi;


import net.snake.netio.ServerResponse;


/**
 * 舍利子溶血
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class SLZRongXueMsg50182 extends ServerResponse {
	
	public SLZRongXueMsg50182(boolean result) {
			setMsgCode(50182);
			writeBoolean(result);
	}
}
