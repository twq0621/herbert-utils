package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;
import net.snake.shell.Options;


/**
 * 玩家复活延时
 * @author serv_dev
 *
 */
public class CharacterFuhuoDelayMsg20078 extends ServerResponse {
	public CharacterFuhuoDelayMsg20078(int characterid) {
		setMsgCode(20078);
			writeInt(characterid);
			writeByte(Options.Relive_Timeout);
		
	}
}
