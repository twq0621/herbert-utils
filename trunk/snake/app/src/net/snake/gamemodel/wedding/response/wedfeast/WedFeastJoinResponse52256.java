package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastJoinResponse52256 extends ServerResponse{
	public WedFeastJoinResponse52256(int tag){
			setMsgCode(52256);
			writeByte(tag);
	}
}
