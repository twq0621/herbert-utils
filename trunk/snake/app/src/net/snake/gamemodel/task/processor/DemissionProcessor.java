package net.snake.gamemodel.task.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.response.DemissionResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 放弃任务处理 10265
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10265, accessLimit = 500)
public class DemissionProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int taskId = request.getInt();// 任务id
		CharacterTaskController taskController = character.getTaskController();

		CharacterTask terCharacterTask = taskController.getTerminativeTaskById(taskId);
		if (terCharacterTask != null) {
			character.sendMsg(new DemissionResponse(20052, ""));
			return;
		}

		CharacterTask curCharacterTask = taskController.getCurrTaskById(taskId);
		if (curCharacterTask != null) {
			curCharacterTask.getTaskVO().drop();
		}
	}
}
