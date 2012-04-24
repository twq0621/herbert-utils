package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.netio.ServerResponse;


/**
 *  50552发送闭关时间
 * @author serv_dev
 *
 */
public class BiguanTimeMsg50552 extends ServerResponse{
public BiguanTimeMsg50552(int time){
	this.setMsgCode(50552);
		this.writeInt(time);
}
}
