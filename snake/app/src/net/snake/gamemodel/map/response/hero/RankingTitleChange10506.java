package net.snake.gamemodel.map.response.hero;

import java.io.IOException;

import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


public class RankingTitleChange10506 extends ServerResponse {
	public RankingTitleChange10506(Hero character) {
		setMsgCode(10506);
		try {
			writeInt(character.getId());
			CharacterPropertyResponse10108.writeRankTitle(this, character);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

	}
}
