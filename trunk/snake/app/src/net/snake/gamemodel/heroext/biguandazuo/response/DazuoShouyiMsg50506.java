package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.netio.ServerResponse;

/**
 * 发送打坐收益
 * @author serv_dev
 *
 */
public class DazuoShouyiMsg50506 extends ServerResponse {
	public  DazuoShouyiMsg50506(int exp, int zhenqi){
		this.setMsgCode(50506);
			this.writeInt(exp);
			this.writeInt(zhenqi);
	
	}
}
