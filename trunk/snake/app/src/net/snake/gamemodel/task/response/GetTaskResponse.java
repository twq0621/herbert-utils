package net.snake.gamemodel.task.response;

import net.snake.netio.ServerResponse;

/**
 * 接受任务响应
 * 
 * @author dev
 * 
 */
public class GetTaskResponse extends ServerResponse {
	private static final int MSGCODE = 10262;

	public GetTaskResponse(int flag, int msgkey, int taskId, String... vars) {
		this.setMsgCode(MSGCODE);
		try {
			writeByte(flag);
			if (flag > 0) {
				writeInt(taskId);
			} else {
				writeInterUTF(msgkey, vars);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
