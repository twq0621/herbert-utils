package net.snake.gamemodel.skill.response.hiddenweapon;


import net.snake.netio.ServerResponse;


public class OpenHiddenPropsMsg52020 extends ServerResponse {
	public OpenHiddenPropsMsg52020(boolean hidden) {
		setMsgCode(52020);
		writeBoolean(hidden);
	}
}
