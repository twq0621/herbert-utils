package net.snake.gamemodel.achieve.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class TitleOpenMsg51012 extends ServerResponse {
	public TitleOpenMsg51012(Hero character) {
		this.setMsgCode(51012);
		this.writeInt(character.getId());
		this.writeInt(character.getNowAppellationid());

	}
}
