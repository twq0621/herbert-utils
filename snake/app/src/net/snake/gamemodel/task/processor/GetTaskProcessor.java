package net.snake.gamemodel.task.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.response.GetTaskResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 接受任务处理 接受任务消息号 10261 返回消息号 10262
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10261, accessLimit = 500)
public class GetTaskProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int taskId = request.getInt();// 任务id
		Task task = TaskManager.getInstance().getCacheTaskMap().get(taskId);
		if (task == null) {
			character.sendMsg(new GetTaskResponse(0, 20000, 0));
			return;
		}

		CharacterTaskController taskController = character.getTaskController();

		taskController.addTask(task);
	}
}
