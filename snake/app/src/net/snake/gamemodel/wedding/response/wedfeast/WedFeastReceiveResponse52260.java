package net.snake.gamemodel.wedding.response.wedfeast;


import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class WedFeastReceiveResponse52260 extends ServerResponse {
	public WedFeastReceiveResponse52260(int flag){
			setMsgCode(52260);
			writeByte(flag);
	}

}
