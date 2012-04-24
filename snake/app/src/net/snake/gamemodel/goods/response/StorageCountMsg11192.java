package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;

public class StorageCountMsg11192 extends ServerResponse {
	public StorageCountMsg11192(int count) {
		setMsgCode(11192);
		writeByte(count);
	}
}
