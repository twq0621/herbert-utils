package net.snake.gamemodel.friend.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class CharacterOnlineMsg10312 extends ServerResponse {
	public CharacterOnlineMsg10312(Hero character) {
		this.setMsgCode(10312);
		this.writeInt(character.getId());
		this.writeByte(character.getVlineserver().getLineid());
		this.writeByte(character.getCharacterStatus());

	}
}
