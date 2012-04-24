package net.snake.gamemodel.equipment.response.gem;

import net.snake.netio.ServerResponse;

public class ReplaceGemstoneWin50114 extends ServerResponse {

	public ReplaceGemstoneWin50114(String[] gemsArr) {
		setMsgCode(50114);
		if (gemsArr == null || gemsArr.length == 0 || "".equals(gemsArr[0])) {
			writeByte(0);
		} else {
			writeByte(gemsArr.length);
			for (int i = 0; i < gemsArr.length; i++) {
				writeInt(Integer.parseInt(gemsArr[i]));
			}
		}
	}
}
