package net.snake.gamemodel.equipment.response.strengthen;

import net.snake.netio.ServerResponse;

public class ResetStrengthenUpgrade50108 extends ServerResponse {

	public ResetStrengthenUpgrade50108(int flag) {
		setMsgCode(50108);
		writeByte(flag);
	}
}
