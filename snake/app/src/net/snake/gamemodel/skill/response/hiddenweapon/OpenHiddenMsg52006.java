package net.snake.gamemodel.skill.response.hiddenweapon;

import net.snake.netio.ServerResponse;

public class OpenHiddenMsg52006 extends ServerResponse {
	public OpenHiddenMsg52006(boolean hidden) {
		setMsgCode(52006);
		writeBoolean(hidden);
	}
}
