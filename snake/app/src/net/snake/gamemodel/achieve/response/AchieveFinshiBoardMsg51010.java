package net.snake.gamemodel.achieve.response;

import org.apache.log4j.Logger;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class AchieveFinshiBoardMsg51010 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(AchieveFinshiBoardMsg51010.class);

	public AchieveFinshiBoardMsg51010(Hero character, Achieve achieve) {
		this.setMsgCode(51010);
		try {
			this.writeUTF(character.getViewName());
			this.writeInt(achieve.getId());
			this.writeUTF(System.currentTimeMillis() + "");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
