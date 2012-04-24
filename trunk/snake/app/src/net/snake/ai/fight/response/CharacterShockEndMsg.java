package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class CharacterShockEndMsg extends ServerResponse {
	private static final int MSGCODE = 20064;
	public CharacterShockEndMsg(Hero me){
		setMsgCode(MSGCODE);
		writeInt(me.getId());
	}	
}
