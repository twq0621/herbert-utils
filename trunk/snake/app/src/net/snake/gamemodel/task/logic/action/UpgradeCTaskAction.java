package net.snake.gamemodel.task.logic.action;

import java.util.List;

import net.snake.commons.GenerateProbability;
import net.snake.consts.LoopTaskType;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.gamemodel.task.response.GetTaskResponse;

public class UpgradeCTaskAction extends BaseLoopTaskAction {

	@Override
	public boolean acceptCondition(CharacterTask characterTask) {
		if (super.acceptCondition()) {

			if (characterTask.getAcceptNum() >= characterTask.getOrgTask().getLoopmaxcount()) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20015, 0, characterTask.getOrgTask().getLoopmaxcount() + ""));
				return false;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void taskRandomConditionReward(CharacterTask characterTask) {
		int difGrade = 5;
		List<TaskCondition> taskConditionList = TaskConditionManager.getInstance().getTaskConditionByCharacterGradeAndGrade(getCharacter().getGrade(), difGrade,
				getLoopTaskType().getType());
		if (!taskConditionList.isEmpty()) {
			int num = GenerateProbability.randomIntValue(0, taskConditionList.size() - 1);
			TaskCondition taskCondition = taskConditionList.get(num);
			characterTask.setTaskConditionId(taskCondition.getConditionId());
		}

		int _difGrade = 5;
		List<TaskReward> taskRewardList = TaskRewardManager.getInstance().getTaskRewardByCharacterGradeAndGrade(getCharacter().getGrade(), _difGrade, getLoopTaskType().getType(),
				getLoopNum(characterTask));
		if (!taskRewardList.isEmpty()) {
			int num = GenerateProbability.randomIntValue(0, taskRewardList.size() - 1);
			TaskReward taskRward = taskRewardList.get(num);
			taskRewardFillTask(characterTask, taskRward);
		}

		randomNpc(characterTask);
	}

	@Override
	protected void extraReward(CharacterTask finishCharacterTask) throws Exception {
	}

	@Override
	public boolean isOutTime(CharacterTask characterTask) {
		return false;
	}

	@Override
	protected final LoopTaskType getLoopTaskType() {
		return LoopTaskType.upgrade;
	}

	@Override
	public void autoRecover() {

	}

	@Override
	public boolean downRandomCondition(CharacterTask characterTask) {
		return false;
	}

	@Override
	public boolean upRandomReward(CharacterTask characterTask) {
		return false;
	}
}
