package net.snake.gamemodel.task.logic.action;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.response.DemissionResponse;
import net.snake.gamemodel.task.response.FinishTaskResponse;
import net.snake.gamemodel.task.response.GetTaskResponse;

/**
 * 主线任务与支线任务
 * 
 * 
 */
public class BaseTaskAction extends TaskAction {

	public BaseTaskAction() {
		super();
	}

	public void acceptForTest() {

		CharacterTask characterTask = CharacterTask.createTask(taskController, task);

		taskController.putCurrentTaskMap(characterTask);
		taskController.insertTask_sql(characterTask);
		getCharacter().sendMsg(new GetTaskResponse(1, 0, task.getTaskId()));

		checkWhenAccept(characterTask);// 必须放在接受任务之后
	}

	@Override
	public boolean acceptCondition() {
		if (super.acceptCondition()) {
			int taskType = task.getType();// 任务类型
			CharacterTask terminativeCharacterTask = taskController.getTerminativeTaskById(task.getTaskId());
			// 验证 是否符合 前提任务条件
			if (task.getPremisetask() != 0) {
				CharacterTask premissCharacterTask = taskController.getTerminativeTaskById(task.getPremisetask());
				if (null == premissCharacterTask || (premissCharacterTask.getGettime() == null || premissCharacterTask.getEndtime() == null)) {
					getCharacter().sendMsg(new GetTaskResponse(0, 20001, 0));
					return false;
				}
			}
			// 主线或支线任务不可重复接受
			if ((taskType == Task.MAIN_TASK || taskType == Task.BRANCH_TASK) && terminativeCharacterTask != null) {
				getCharacter().sendMsg(new GetTaskResponse(0, 20002, 0));
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public void accept() {
		if (acceptCondition()) {
			CharacterTask characterTask = CharacterTask.createTask(taskController, task);
			taskController.putCurrentTaskMap(characterTask);
			taskController.insertTask_sql(characterTask);
			getCharacter().sendMsg(new GetTaskResponse(1, 0, task.getTaskId()));
			checkWhenAccept(characterTask);// 必须放在接受任务之后
		}
	}

	/**
	 * 接受任务时主动检测完成条件
	 * 
	 * @param characterTask
	 */
	private void checkWhenAccept(CharacterTask characterTask) {
		Task task = characterTask.getOrgTask();
		if (task.getTaskgoods() != null && !"".equals(task.getTaskgoods())) {
			int taskgoods = Integer.parseInt(task.getTaskgoods().split(":")[0]);// 任务品id
			Goodmodel goodModel = GoodmodelManager.getInstance().get(taskgoods);
			CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
			if (goodModel != null) {
				CharacterGoods tempgoods = CharacterGoods.createCharacterGoods(1, taskgoods, 0);
				tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
				if (characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(tempgoods)) {
					characterGoodController.getBagGoodsContiner().addGoods(tempgoods);
					if (task.getBeginnpc() != null && task.getBeginnpc() != 0) {
						getCharacter().sendMsg(new GoodToBadEffectMsg11170(tempgoods, task.getBeginnpc()));
					}
				}
			}
		}

		CharacterTaskController characterTaskController = getCharacter().getTaskController();
		characterTaskController.checkMountUpGrade(characterTask);
		characterTaskController.checkTargetShopping(characterTask);
		characterTaskController.checkTargetYuanBao(characterTask);
	}

	@Override
	public void complete() throws Exception {
		CharacterTask finishCharacterTask = taskController.getCurrTaskById(task.getTaskId());
		if (completeCondition()) {
			// 将任务从当前列表中 删除 添加到已完成 列表中
			finishCharacterTask.end();
			taskController.removeCurrentTaskFromMap(finishCharacterTask);
			taskController.putTerminativeTaskMap(finishCharacterTask);
			taskController.updateTask_sql(finishCharacterTask);
			getCharacter().sendMsg(new FinishTaskResponse(1, 0, finishCharacterTask.getTask()));
			getCharacter().getMyCharacterAchieveCountManger().getTaskCount().finishTaskCount(task);
		}
	}

	@Override
	public boolean completeCondition() {
		if (super.completeCondition()) {
			CharacterTask finishCharacterTask = taskController.getCurrTaskById(task.getTaskId());
			Horse horse = finishCharacterTask.getTakeHorse();
			if (horse != null) {
				String targetHorseStr = task.getTargethorse();
				if (targetHorseStr == null || targetHorseStr.length() == 0) {
					getCharacter().sendMsg(new FinishTaskResponse(0, 20018, 0));
					return false;
				}
				String[] targetHorse = targetHorseStr.split("#");
				int horseId = Integer.parseInt(targetHorse[0]);
				int horseNum = Integer.parseInt(targetHorse[1]);

				if (horseId != horse.getHorseModel().getId()) {
					getCharacter().sendMsg(new FinishTaskResponse(0, 20019, 0));
					return false;
				}

				if (horseNum > 1) {
					getCharacter().sendMsg(new FinishTaskResponse(0, 20020, 0));
					return false;
				}

				if (getTask().getHorsedrop() == 1 && !horse.isRemovable()) {
					getCharacter().sendMsg(new FinishTaskResponse(0, 20021, 0));
					return false;
				}
			} else {
				if (getTask().getTargethorse() != null && !"".equals(getTask().getTargethorse())) {
					getCharacter().sendMsg(new FinishTaskResponse(0, 20022, 0));
					return false;
				}
			}
			completeRewardsPunishments(finishCharacterTask);
		} else {
			return false;
		}

		return true;
	}

	@Override
	public void drop() {
		if (!dropCondition())
			return;
		CharacterTask characterTask = taskController.getCurrTaskById(task.getTaskId());
		if (characterTask.getEndtime() == null) {// 为未完成任务(已完成的任务不可被放弃)
			taskController.removeCurrentTaskFromMap(characterTask);
			taskController.deleteTaskGoods(task);
			taskController.deleteTask_sql(characterTask);
			getCharacter().sendMsg(new DemissionResponse(characterTask.getTask()));
		}
	}

}
