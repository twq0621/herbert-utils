package net.snake.gamemodel.task.response;


import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.netio.ServerResponse;


/**
 * 任务放弃响应 10266
 * 
 * @author dev
 * 
 */
public class DemissionMsg20004 extends ServerResponse {
	public DemissionMsg20004(CharacterTask characterTask) {
		setMsgCode(20004);
//		try {
			writeByte(1);
			writeInt(characterTask.getTask());
			writeShort(characterTask.getAcceptNum());
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
	}
}
