package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

public class CharacterShockMsg extends ServerResponse {
	private static final int MSGCODE = 20060; 
	//1 活着 0 死亡
	public CharacterShockMsg(Hero me){
		setMsgCode(MSGCODE);
		writeInt(me.getId());
		long dis = System.currentTimeMillis() - me.getShockMeImg().shockTimestamp;
		writeByte(Options.Shock_Timeout-(int)dis/1000);
	}	
}
