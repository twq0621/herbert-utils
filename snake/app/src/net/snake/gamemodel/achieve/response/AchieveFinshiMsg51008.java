package net.snake.gamemodel.achieve.response;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.netio.ServerResponse;

public class AchieveFinshiMsg51008 extends ServerResponse {
	public AchieveFinshiMsg51008(Achieve achieve) {
		this.setMsgCode(51008);
		this.writeInt(achieve.getId());
	}
}
