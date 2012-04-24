package net.snake.gamemodel.task.response;


import net.snake.netio.ServerResponse;


/**
 * 删除指定任务所有数据
 * @author serv_dev
 *
 */
public class DeleteAllTaskResMsg20020 extends ServerResponse {
	public DeleteAllTaskResMsg20020(int taskId) {
		setMsgCode(20020);
	
//		try {
			writeInt(1);
			writeInt(taskId);
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
	}
}
