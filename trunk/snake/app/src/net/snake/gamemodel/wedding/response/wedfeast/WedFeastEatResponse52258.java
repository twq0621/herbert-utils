package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastEatResponse52258 extends ServerResponse{
	public WedFeastEatResponse52258(int flag){
		setMsgCode(52258);
			writeByte(flag);
	}
}
