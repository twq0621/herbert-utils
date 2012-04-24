package net.snake.gamemodel.equipment.response.strengthen;

import net.snake.netio.ServerResponse;

public class StrengthenUpgradeMsg50104 extends ServerResponse {

	public StrengthenUpgradeMsg50104(int flag) {
		setMsgCode(50104);
		writeByte(flag);
	}
}
