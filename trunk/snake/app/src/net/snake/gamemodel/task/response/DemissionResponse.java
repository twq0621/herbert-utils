package net.snake.gamemodel.task.response;

import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

/**
 * 任务放弃响应 10266
 * 
 * @author dev
 * 
 */
public class DemissionResponse extends ServerResponse {
	private static final int MSGCODE = 10266;

	public DemissionResponse(int taskid) {
		this.setMsgCode(MSGCODE);
		writeByte(CommonUseNumber.byte1);
		writeInt(taskid);
	}

	public DemissionResponse(int msgkey, String... vars) {
		this.setMsgCode(MSGCODE);
		try {
			writeByte(CommonUseNumber.byte0);
			writeInterUTF(msgkey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
