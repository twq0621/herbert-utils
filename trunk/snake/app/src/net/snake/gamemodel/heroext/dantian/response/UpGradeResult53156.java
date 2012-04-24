package net.snake.gamemodel.heroext.dantian.response;

import net.snake.netio.ServerResponse;

public class UpGradeResult53156 extends ServerResponse {
	public UpGradeResult53156(int tag) {
		setMsgCode(53156);
		writeByte(tag);

	}

}
