package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.netio.ServerResponse;

public class DazuoXinfaMsg50632 extends ServerResponse {
	public DazuoXinfaMsg50632(int id ){
		this.setMsgCode(50632);
			this.writeInt(id);
	}

}
