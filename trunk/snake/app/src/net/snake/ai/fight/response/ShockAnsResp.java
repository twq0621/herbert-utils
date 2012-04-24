package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class ShockAnsResp extends ServerResponse{
	private static final int MSGCODE = 20062;
	public ShockAnsResp(Hero me,byte ans){
		setMsgCode(MSGCODE);
		writeInt(me.getId());
		writeByte(ans);
	}	
}
