package net.snake.gamemodel.task.logic.action;

import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.response.GetTaskResponse;

public class DayCTaskAction extends BaseLoopTaskAction {

	@Override
	public boolean acceptCondition(CharacterTask characterTask) {
		if (super.acceptCondition()) {
			if (characterTask.getAcceptNum() >= Task.Day_Task_Limit) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20006, 0, Task.Day_Task_Limit + ""));
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
