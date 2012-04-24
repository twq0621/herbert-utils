package net.snake.gamemodel.task.response;

import net.snake.netio.ServerResponse;

public class TaskRunResponse extends ServerResponse {
	private static final int MSGCODE = 10268;

	public TaskRunResponse(int taskid, int triggerType, String data) {
		setMsgCode(MSGCODE);
		try {
			writeInt(taskid);
			writeByte(triggerType);
			if (triggerType != 1) {
				writeUTF(data);
				return;
			}
			// id#数量#怪物名称#场景信息#子类型
			String[] subCond = data.split(",");// 子类型#数量#怪物id
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < subCond.length; i++) {
				String[] cont = subCond[i].split("#");
				if (i == 0) {
					sb.append("0#" + cont[1] + "#0#0#" + cont[0]);
				} else {
					sb.append(",0#" + cont[1] + "#0#0#" + cont[0]);
				}
			}
			writeUTF(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
