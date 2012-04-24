package net.snake.gamemodel.equipment.response.strengthen;

import net.snake.netio.ServerResponse;

public class NextEquipMsg50124 extends ServerResponse {

	public NextEquipMsg50124(int oldModelId, int newModelId) {
		setMsgCode(50124);
		writeInt(oldModelId);
		writeInt(newModelId);

	}
}
