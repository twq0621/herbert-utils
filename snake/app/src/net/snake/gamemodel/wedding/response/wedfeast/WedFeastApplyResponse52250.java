package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastApplyResponse52250 extends ServerResponse{
	public WedFeastApplyResponse52250(int flag){
		setMsgCode(52250);
			writeByte(flag);
	}
}
