package net.snake.gamemodel.equipment.response;

import net.snake.netio.ServerResponse;

public class CailiaoDetailMsg50122 extends ServerResponse {

	public CailiaoDetailMsg50122(int cailiao1, int num1, int cailiao2, int num2, int cailiao3, int num3, int nextGoodModelId) {
		setMsgCode(50122);
		writeInt(cailiao1);
		writeByte(num1);
		writeInt(cailiao2);
		writeByte(num2);
		writeInt(cailiao3);
		writeByte(num3);
		writeInt(nextGoodModelId);

	}
}
