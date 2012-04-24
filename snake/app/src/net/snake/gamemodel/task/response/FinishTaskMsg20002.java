package net.snake.gamemodel.task.response;


import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.netio.ServerResponse;


public class FinishTaskMsg20002 extends ServerResponse {
	
	
	public FinishTaskMsg20002(CharacterTask characterTask) {
		setMsgCode(20002);
			writeByte(1);
			writeInt(characterTask.getTask());
			writeShort(characterTask.getAcceptNum());
	}
}
