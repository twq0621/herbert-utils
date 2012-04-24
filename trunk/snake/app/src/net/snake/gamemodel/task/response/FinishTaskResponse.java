package net.snake.gamemodel.task.response;


import net.snake.netio.ServerResponse;


/**
 * 交任务 返回协议 10264
 * 
 * @author dev
 * 
 */
public class FinishTaskResponse extends ServerResponse {
	private static final int MSGCODE = 10264;

	public FinishTaskResponse(int flag,int msgkey,int taskId,String... vars) {
		setMsgCode(MSGCODE);
		try {
			writeByte(flag);
			if (flag > 0) {
				writeInt(taskId);
			} else {
				writeInterUTF(msgkey,vars);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
