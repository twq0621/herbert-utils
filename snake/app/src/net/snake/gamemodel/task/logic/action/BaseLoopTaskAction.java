package net.snake.gamemodel.task.logic.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.GenerateProbability;
import net.snake.commons.Language;
import net.snake.commons.TimeExpression;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GameConstant;
import net.snake.consts.LoopTaskType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.persistence.GoodspackgeDateManager;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.guide.response.NewGuideTaskGoodMsg50666;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.gamemodel.task.response.DeleteAllTaskResMsg20020;
import net.snake.gamemodel.task.response.DemissionMsg20004;
import net.snake.gamemodel.task.response.FinishTaskMsg20002;
import net.snake.gamemodel.task.response.FinishTaskResponse;
import net.snake.gamemodel.task.response.GetLoopTaskMsg20000;

import org.apache.log4j.Logger;

/**
 * 每日环任务
 * 
 * @author serv_dev
 * 
 */
public class BaseLoopTaskAction extends TaskAction {

	private static final Logger logger = Logger.getLogger(BaseLoopTaskAction.class);

	protected LoopTaskType getLoopTaskType() {
		return LoopTaskType.day;
	}

	public boolean acceptCondition(CharacterTask characterTask) {
		return super.acceptCondition();
	}

	@Override
	public boolean isOutTime(CharacterTask characterTask) {
		return !TimeExpression.isToday(characterTask.getGettime());
	}

	/**
	 * 
	 * 降低任务难度
	 * 
	 * @param characterTask
	 * 
	 */
	public boolean downRandomCondition(CharacterTask characterTask) {
		int newDifGrade = 1;// GenerateProbability.randomIntValue(1, 3);
		TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(characterTask.getTaskConditionId());
		if (taskCondition != null) {
			int oldDifGrade = taskCondition.getDifficultyDegree();
			if (oldDifGrade == TaskCondition.ConditionFloorLevel) {
				getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 824));
				return false;
			}
			int _difGrade = oldDifGrade - newDifGrade;
			if (_difGrade < 1)
				_difGrade = 1;
			List<TaskCondition> taskConditionList = TaskConditionManager.getInstance().getTaskConditionByCharacterGradeAndGrade(getCharacter().getGrade(), _difGrade,
					getLoopTaskType().getType());
			if (!taskConditionList.isEmpty()) {
				int num = GenerateProbability.randomIntValue(0, taskConditionList.size() - 1);
				taskCondition = taskConditionList.get(num);
				characterTask.setTaskConditionId(taskCondition.getConditionId());
				characterTask.setTargetaction("");
				characterTask.setTargetarea("");
				characterTask.setTargetmonster("");
				characterTask.setTargetnpc("");
				characterTask.setTargetrecharge("");
				characterTask.setTargetshopping("");
				characterTask.setTargetmountupgrade("");
				characterTask.setTmpConditionGrade(oldDifGrade - _difGrade);
				getCharacter().getTaskController().updateTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
				return true;
			} else {

				logger.warn("no config about easy task,task id:" + characterTask.getTask() + ",hero lvl :" + getCharacter().getGrade() + ",difficlut:" + _difGrade);
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 提升奖励回报
	 * 
	 * @param characterTask
	 */
	public boolean upRandomReward(CharacterTask characterTask) {
		int newDifGrade = 1;// GenerateProbability.randomIntValue(1, 3);
		TaskReward taskReard = TaskRewardManager.getInstance().getTaskRewardById(characterTask.getTaskRewardId());
		if (taskReard != null) {
			int oldDifGrade = taskReard.getRewardGrade();
			if (oldDifGrade == TaskReward.RewardUpLevel) {
				getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 825));
				return false;
			}
			int _difGrade = oldDifGrade + newDifGrade;
			if (_difGrade > GameConstant.TASK_DIF_GRADE)
				_difGrade = GameConstant.TASK_DIF_GRADE;
			List<TaskReward> taskRewardList = TaskRewardManager.getInstance().getTaskRewardByCharacterGradeAndGrade(getCharacter().getGrade(), _difGrade,
					getLoopTaskType().getType(), characterTask.getAcceptNum());
			if (!taskRewardList.isEmpty()) {
				int num = GenerateProbability.randomIntValue(0, taskRewardList.size() - 1);
				TaskReward taskRward = taskRewardList.get(num);
				characterTask.setTaskRewardId(taskRward.getRewardId());
				fillTaskReward(characterTask, taskRward);
				characterTask.setTmpRewardGrade(_difGrade - oldDifGrade);
				getCharacter().getTaskController().updateTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
				return true;
			} else {
				logger.warn("不存在任务奖励的配置 任务id:" + characterTask.getTask() + ",角色等级:" + getCharacter().getGrade() + ",难度等级:" + _difGrade + "循环次数：" + characterTask.getAcceptNum());
				return false;
			}
		} else {
			return false;
		}
	}

	public void acceptForTest() {
		CharacterTask characterTask = taskController.getTerminativeTaskById(task.getTaskId());
		if (characterTask != null) {
			if (acceptCondition(characterTask)) {
				// 可接受
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				characterTask.setEndtime(null);
				taskRandomConditionReward(characterTask);
				taskController.removeTerminativeTaskFromMap(characterTask);
				taskController.putCurrentTaskMap(characterTask);
				taskController.updateTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
			}
		} else {// 当前任务与关闭任务都没有的条件
			if (acceptCondition()) {
				characterTask = CharacterTask.createTask(taskController, task);
				taskRandomConditionReward(characterTask);
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				taskController.putCurrentTaskMap(characterTask);
				taskController.insertTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
			}
		}
	}

	@Override
	public void accept() {
		CharacterTask characterTask = taskController.getTerminativeTaskById(task.getTaskId());
		if (characterTask != null) {
			if (acceptCondition(characterTask)) {
				// 可接受
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				characterTask.setEndtime(null);
				taskRandomConditionReward(characterTask);
				taskController.removeTerminativeTaskFromMap(characterTask);
				taskController.putCurrentTaskMap(characterTask);
				taskController.updateTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
				CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
				if (task.getTaskgoods() != null && !"".equals(task.getTaskgoods())) {
					int taskgoods = Integer.parseInt(task.getTaskgoods().split(":")[0]);// 任务品id
					Goodmodel goodModel = GoodmodelManager.getInstance().get(taskgoods);
					if (goodModel != null) {
						CharacterGoods tempgoods = CharacterGoods.createCharacterGoods(1, taskgoods, 0);
						tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
						if (characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(tempgoods)) {
							characterGoodController.getBagGoodsContiner().addGoods(tempgoods);
						}
					}
				}
			}
		} else {// 当前任务与关闭任务都没有的条件
			if (acceptCondition()) {
				characterTask = CharacterTask.createTask(taskController, task, this);
				characterTask.setAcceptNum(characterTask.getAcceptNum() + 1);
				taskRandomConditionReward(characterTask);
				taskController.putCurrentTaskMap(characterTask);
				taskController.insertTask_sql(characterTask);
				getCharacter().sendMsg(new GetLoopTaskMsg20000(characterTask));
				if (task.getTaskgoods() != null && !"".equals(task.getTaskgoods())) {
					int taskgoods = Integer.parseInt(task.getTaskgoods().split(":")[0]);// 任务品id
					Goodmodel goodModel = GoodmodelManager.getInstance().get(taskgoods);
					if (goodModel != null) {
						CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
						CharacterGoods tempgoods = new CharacterGoods();
						tempgoods.setGoodmodelId(taskgoods);
						tempgoods.setCount(1);
						tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定
						if (characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(tempgoods)) {
							characterGoodController.getBagGoodsContiner().addGoods(tempgoods);
						}
					}
				}
			}
		}
	}

	/**
	 * 随机生成条件与奖励给任务
	 * 
	 * @param controller
	 * @param characterTask
	 */
	public void taskRandomConditionReward(CharacterTask characterTask) {
		int difGrade = GenerateProbability.randomIntValue(1, GameConstant.TASK_DIF_GRADE);
		List<TaskCondition> taskConditionList = TaskConditionManager.getInstance().getTaskConditionByCharacterGradeAndGrade(getCharacter().getGrade(), difGrade,
				getLoopTaskType().getType());
		if (!taskConditionList.isEmpty()) {
			int num = GenerateProbability.randomIntValue(0, taskConditionList.size() - 1);
			TaskCondition taskCondition = taskConditionList.get(num);
			characterTask.setTaskConditionId(taskCondition.getConditionId());
		}

		int _difGrade = GenerateProbability.randomIntValue(1, GameConstant.TASK_DIF_GRADE);
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
	public void taskRewardFillTask(CharacterTask characterTask, TaskReward taskRward) {
		characterTask.setTaskRewardId(taskRward.getRewardId());
		fillTaskReward(characterTask, taskRward);
	}

	protected int getLoopNum(CharacterTask characterTask) {
		return 0;
	}

	public void randomNpc(CharacterTask characterTask) {
		characterTask.setEndnpc(getTask().getEndnpc());// 动态endNpc
	}

	/**
	 * 填充奖励的物品
	 * 
	 * @param characterTask
	 * @param taskRward
	 */
	protected final void fillTaskReward(CharacterTask characterTask, TaskReward taskRward) {
		int good1 = taskRward.getGoodId();
		int good1Num = taskRward.getGoodNum();
		String rewardGoodsString = "";
		if (good1 > 0 && good1Num > 0) {
			// TODO
			List<CharacterGoods> list = GoodspackgeDateManager.getInstance().getTaskRewardGoods(good1, good1Num, 3);
			for (Iterator<CharacterGoods> iterator = list.iterator(); iterator.hasNext();) {
				CharacterGoods characterGoods = iterator.next();
				rewardGoodsString = rewardGoodsString + characterGoods.getGoodmodelId() + "," + characterGoods.getCount() + ";";
			}
		}

		int good2 = taskRward.getGood2Id();
		int good2Num = taskRward.getGood2Num();
		if (good2 > 0 && good2Num > 0) {
			List<CharacterGoods> list = GoodspackgeDateManager.getInstance().getTaskRewardGoods(good2, good2Num, 3);
			for (Iterator<CharacterGoods> iterator = list.iterator(); iterator.hasNext();) {
				CharacterGoods characterGoods = iterator.next();
				rewardGoodsString = rewardGoodsString + characterGoods.getGoodmodelId() + "," + characterGoods.getCount() + ";";
			}
		}
		characterTask.setRewardGoods(rewardGoodsString);
	}

	@Override
	public boolean completeCondition() {
		if (super.completeCondition()) {
			CharacterTask finishCharacterTask = taskController.getCurrTaskById(task.getTaskId());
			int taskConditionId = finishCharacterTask.getTaskConditionId();
			TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskConditionId);
			if (taskCondition != null) {
				// 验证回收的物品
				if (taskCondition.getGoodId() > 0) {
					Goodmodel goodmodel = GoodmodelManager.getInstance().get(taskCondition.getGoodId());
					int goodNum = taskCondition.getGoodNum();
					if (goodmodel.isEquipment()) {
						List<CharacterGoods> taskGoods = finishCharacterTask.getTakeCharacterGoods();
						if (taskGoods == null || taskGoods.isEmpty()) {
							getCharacter().sendMsg(new FinishTaskResponse(0, 20023, 0));
							return false;
						}

						int tmpNum = 0;
						for (Iterator<CharacterGoods> iterator = taskGoods.iterator(); iterator.hasNext();) {
							CharacterGoods characterGoods = iterator.next();
							tmpNum = tmpNum + characterGoods.getCount();
						}

						if (tmpNum < goodNum) {
							getCharacter().sendMsg(new FinishTaskResponse(0, 20024, 0));
							return false;
						}
					} else {
						if (!getCharacter().getCharacterGoodController().isEnoughGoods(taskCondition.getGoodId(), goodNum)) {
							getCharacter().sendMsg(new FinishTaskResponse(0, 20024, 0));
							return false;
						}
					}
				}

				if (taskCondition.getHorseId() > 0) {
					Horse horse = finishCharacterTask.getTakeHorse();
					if (horse == null) {
						getCharacter().sendMsg(new FinishTaskResponse(0, 20025, 0));
						return false;
					}
					if (taskCondition.getHorseId().intValue() != horse.getHorseModel().getId()) {
						getCharacter().sendMsg(new FinishTaskResponse(0, 20019, 0));
						return false;
					}

					// getTask().getHorsedrop() == 0
					if (getTask().getHorsedrop() == 1 && !horse.isRemovable()) {
						getCharacter().sendMsg(new FinishTaskResponse(0, 20021, 0));
						return false;
					}
				}
			}

		} else {
			return false;
		}

		return true;
	}

	@Override
	public void completeRewardsPunishments(CharacterTask characterTask) {

		// 奖励 经验 金币 铜币 好感值 帮派贡献 善恶值 称号

		TaskReward taskReward = TaskRewardManager.getInstance().getTaskRewardById(characterTask.getTaskRewardId());
		/**
		 * 
		 * 金币 铜币 真气 奖励一个BUFF 战场声望值
		 * 
		 * 
		 * 个人帮贡值 奖励一个称号 奖励一个系统全服公告
		 */
		if (taskReward != null) {
			StringBuilder msg = new StringBuilder();
			if (taskReward.getExp() > 0) {
				msg.append(Language.EXP + ":").append(taskReward.getExp());
				try {
					CharacterFormula.experienceProcess(getCharacter(), taskReward.getExp());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

			// TODO
			if (taskReward.getCopper() > 0) {
				CharacterPropertyManager.changeCopper(getCharacter(), taskReward.getCopper(), CopperAction.ADD_TASKORINSTANCE);
				msg.append(Language.COPPER + ":").append(taskReward.getCopper());
			}
			if (taskReward.getZhenqi() > 0) {
				CharacterPropertyManager.changeZhenqi(getCharacter(), taskReward.getZhenqi());
				msg.append(Language.ZHENQI + ":").append(taskReward.getZhenqi());
			}

			CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
			CharacterGoods equipGood = null;
			// 环任务动态奖励的物品

			List<CharacterGoods> tmpCG = new ArrayList<CharacterGoods>();

			String rewrdGoods = characterTask.getRewardGoods();
			if (rewrdGoods != null && !"".equals(rewrdGoods)) {
				String[] goodStr = rewrdGoods.split(";");

				if (goodStr.length > 0) {
					for (int i = 0; i < goodStr.length; i++) {
						String[] _goodStrs = goodStr[i].split(",");
						if (_goodStrs.length <= 1)
							break;
						int goodId = Integer.parseInt(_goodStrs[0]);
						int goodNum = Integer.parseInt(_goodStrs[1]);
						CharacterGoods tempgoods = new CharacterGoods();
						tempgoods.setGoodmodelId(goodId);
						tempgoods.setCount(goodNum);
						tempgoods.setBind(CommonUseNumber.byte1);// 需要注明绑定

						if (characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(tempgoods)) {
							Goodmodel goodModel = GoodmodelManager.getInstance().get(goodId);
							CharacterGoods characterGoods = CharacterGoods.createCharacterGoods(goodNum, goodModel, 0, 0);
							characterGoods.setBind(CommonUseNumber.byte1);
							if (goodModel.isEquipment()) {
								CharacterGoods _characterGoods = characterGoodController.getBagGoodsContiner().addAndReturnLast(characterGoods);
								if (_characterGoods != null) {
									equipGood = _characterGoods;
								}
							} else {
								characterGoodController.getBagGoodsContiner().addGoods(characterGoods);
								msg.append(Language.GOODS + ":").append(goodModel.getNameI18n());
							}

							tmpCG.add(characterGoods);
						}
					}
				}
				if (!tmpCG.isEmpty()) {
					if (this instanceof WeekCTaskAction) {
						if (characterTask.getEndnpc() != null && characterTask.getEndnpc() != 0) {
							getCharacter().sendMsg(new GoodToBadEffectMsg11170(tmpCG, characterTask.getEndnpc()));
						}
					} else {
						if (getTask().getEndnpc() != null && getTask().getEndnpc() != 0) {
							getCharacter().sendMsg(new GoodToBadEffectMsg11170(tmpCG, getTask().getEndnpc()));
						}
					}
				}
			}

			if (equipGood != null) {
				getCharacter().sendMsg(new NewGuideTaskGoodMsg50666(equipGood));// 新手导航使用
			}
			/**
			 * 任务交付后，在系统消息聊天频道发送提示： “恭喜您完成任务：XXXX（任务名），获得：（任务收益显示，例如经验：+XXX，真气+XXX，铜钱+XXXX）”
			 */

			if (!"".equals(msg.toString())) {
				getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1101, getTask().getNameI18n(), msg.toString()));
			} else {
				getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1102, getTask().getNameI18n()));
			}

		}
	}

	@Override
	public void complete() throws Exception {
		CharacterTask finishCharacterTask = taskController.getCurrTaskById(task.getTaskId());
		boolean complete = completeCondition();
		if (complete) {
			finishCharacterTask.completeRewardGood(getCharacter());
			completeRewardsPunishments(finishCharacterTask);
			// 额外附加赠送经验：玩家等级*玩家等级*100

			// 将任务从当前列表中 删除 添加到已完成 列表中
			finishCharacterTask.end();
			finishCharacterTask.setCompleteNum(finishCharacterTask.getCompleteNum() + 1);

			extraReward(finishCharacterTask);
			taskController.removeCurrentTaskFromMap(finishCharacterTask);
			taskController.putTerminativeTaskMap(finishCharacterTask);
			taskController.updateTask_sql(finishCharacterTask);
			getCharacter().sendMsg(new FinishTaskMsg20002(finishCharacterTask));
			getCharacter().getMyCharacterAchieveCountManger().getTaskCount().finishTaskCount(task);
		}
	}

	@Override
	public void compelteForTest() {
		// 将任务从当前列表中 删除 添加到已完成 列表中
		CharacterTask finishCharacterTask = taskController.getCurrTaskById(task.getTaskId());
		finishCharacterTask.end();
		finishCharacterTask.setCompleteNum(finishCharacterTask.getCompleteNum() + 1);
		try {
			extraReward(finishCharacterTask);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		taskController.removeCurrentTaskFromMap(finishCharacterTask);
		taskController.putTerminativeTaskMap(finishCharacterTask);
		taskController.updateTask_sql(finishCharacterTask);
		getCharacter().sendMsg(new FinishTaskMsg20002(finishCharacterTask));
	}

	/**
	 * 日环任务的额外奖励
	 * 
	 * @param finishCharacterTask
	 * @throws Exception
	 */
	protected void extraReward(CharacterTask finishCharacterTask) throws Exception {
		if (finishCharacterTask.getCompleteNum() == 10) {
			CharacterFormula.experienceProcess(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskExp10(getCharacter()));
		} else if (finishCharacterTask.getCompleteNum() == 20) {
			CharacterFormula.experienceProcess(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskExp20(getCharacter()));
			CharacterPropertyManager.changeZhenqi(getCharacter(), AppEventCtlor.getInstance().getEvtTaskFormula().taskZhenqi20(getCharacter()));
		}
	}

	@Override
	public void drop() {
		if (!dropCondition())
			return;
		CharacterTask characterTask = taskController.getCurrTaskById(task.getTaskId());
		if (characterTask != null && characterTask.getEndtime() == null) {// 为未完成任务(已完成的任务不可被放弃)
			characterTask.end();
			taskController.removeCurrentTaskFromMap(characterTask);
			taskController.putTerminativeTaskMap(characterTask);
			taskController.updateTask_sql(characterTask);
			getCharacter().sendMsg(new DemissionMsg20004(characterTask));
		}
	}

	/**
	 * 自动回收任务 环任务重新计环
	 */
	@Override
	public void autoRecover() {
		CharacterTask characterTask = taskController.getCurrTaskById(task.getTaskId());
		if (characterTask != null && characterTask.getEndtime() == null) {// 为未完成任务(已完成的任务不可被放弃)
			taskController.removeCurrentTaskFromMap(characterTask);
			taskController.deleteTask_sql(characterTask);
			getCharacter().sendMsg(new DeleteAllTaskResMsg20020(characterTask.getTask()));
		}
		if (characterTask == null) {
			characterTask = taskController.getTerminativeTaskById(task.getTaskId());
			if (characterTask != null) {
				taskController.removeTerminativeTaskFromMap(characterTask);
				taskController.deleteTask_sql(characterTask);
				getCharacter().sendMsg(new DeleteAllTaskResMsg20020(characterTask.getTask()));
			}
		}
	}
}
