package net.snake.ai.fight.response;

import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

public class MonsterShockMsg extends ServerResponse {
	private static final int MSGCODE = 20066; 
	//1 活着 0 死亡
	public MonsterShockMsg(SceneMonster me) {
		setMsgCode(MSGCODE);
		writeInt(me.getId());
		long dis = System.currentTimeMillis()
				- me.getShockMeImg().shockTimestamp;
		writeByte(Options.Shock_Timeout_Monster - (int) dis / 1000);
	}
}
