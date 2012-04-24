package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.netio.ServerResponse;

public class BiguanShouyiMsg50554 extends ServerResponse {
	public BiguanShouyiMsg50554(int exp, int zhenqi) {
		this.setMsgCode(50554);
		this.writeInt(exp);
		this.writeInt(zhenqi);
	}

}
