package net.snake.gamemodel.task.logic.action;

import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.response.GetTaskResponse;


public class DayYaBiaoTaskAction extends BaseLoopTaskAction {

	public boolean acceptCondition(CharacterTask  characterTask) {
		if (super.acceptCondition()) {
			
			if (characterTask.getAcceptNum() >= Task.Day_Task_YaBiao_Limit) {
				getCharacter().sendMsg(new GetTaskResponse(0,20007,0,Task.Day_Task_YaBiao_Limit + ""));
				return false;
			}
			
			return true;
		} else {
			return false;
		}
	}
	
}
