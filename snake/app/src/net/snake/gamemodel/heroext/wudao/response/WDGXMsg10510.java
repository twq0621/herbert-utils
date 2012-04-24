package net.snake.gamemodel.heroext.wudao.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;

public class WDGXMsg10510 extends ServerResponse {
	public WDGXMsg10510(int cid, int id) throws IOException {
		setMsgCode(10510);
		writeInt(cid);
		writeInt(id);
	}

}
