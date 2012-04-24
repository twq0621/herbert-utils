package net.snake.gamemodel.onhoor.response;

import net.snake.netio.ServerResponse;

public class BeginAfkReseponse50584 extends ServerResponse {
	public BeginAfkReseponse50584(int flag) {
		setMsgCode(50584);
		writeByte(flag);
	}
}
