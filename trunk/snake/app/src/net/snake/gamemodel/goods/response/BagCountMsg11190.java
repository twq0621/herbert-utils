package net.snake.gamemodel.goods.response;


import net.snake.netio.ServerResponse;


public class BagCountMsg11190 extends ServerResponse {
	public BagCountMsg11190(int count) {
		setMsgCode(11190);
//		try {
			writeByte(count);
//		} catch (IOException e) {
//		}
	}
}
