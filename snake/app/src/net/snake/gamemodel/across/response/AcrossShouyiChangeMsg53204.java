package net.snake.gamemodel.across.response;

import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.netio.ServerResponse;

public class AcrossShouyiChangeMsg53204 extends ServerResponse {
	public AcrossShouyiChangeMsg53204(AcrossIncome ai) {
		this.setMsgCode(53204);
		this.writeDouble(ai.getExp());
		this.writeInt(ai.getShengwang());
		this.writeInt(ai.getCopper());
	}
}
