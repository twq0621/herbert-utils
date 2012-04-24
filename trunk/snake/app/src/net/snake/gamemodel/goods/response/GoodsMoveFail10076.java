package net.snake.gamemodel.goods.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;


public class GoodsMoveFail10076 extends ServerResponse {
	public GoodsMoveFail10076(int reason) {
		setMsgCode(10076);
		try {
//			writeUTF(reason);
			writeInterUTF(40000);
		} catch (IOException e) {
		}
	}
}
