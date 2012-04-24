package net.snake.gamemodel.task.response;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.netio.ServerResponse;

/**
 * 已接任务数量（byte）{charTask*},已完成任务数量（short）{charEndTask*}, 动态改变的taskRes数量（int）{taskResUpdate*}
 * 
 * charTask 角色当前任务(str格式同任务数据同步中对应属性的格式，不含目标区域) 任务id（int）,当前时间-接受任务时间 秒(int),目标怪物(str), 目标Npc(str),目标区域(str)，目标动作(srt),
 * 
 * 当前环数(short,非环任务则此值为0) charEndTask 角色完成任务 任务id（int）,完成环数(short,非环任务则此值为0)
 * 
 * 
 * 10260
 * 
 * 
 */
public class InitTaskResponse extends ServerResponse {
	public InitTaskResponse(Collection<CharacterTask> conllectionCurrTask, Collection<CharacterTask> conllectionTerminativeTask) {
		setMsgCode(10260);
		try {
			List<CharacterTask> resCharacterTaskList = null;
			if (!conllectionCurrTask.isEmpty()) {
				writeByte(conllectionCurrTask.size());
				Iterator<CharacterTask> iteratorCurrTask = conllectionCurrTask.iterator();
				while (iteratorCurrTask.hasNext()) {
					CharacterTask characterTask = iteratorCurrTask.next();
					currTask(characterTask);
					if (characterTask.isLoop()) {
						if (resCharacterTaskList == null) {
							resCharacterTaskList = new ArrayList<CharacterTask>();
						}
						resCharacterTaskList.add(characterTask);
					}
				}
			} else {
				writeByte(0);
			}

			if (!conllectionTerminativeTask.isEmpty()) {
				writeShort(conllectionTerminativeTask.size());
				Iterator<CharacterTask> iteratorTerminativeTask = conllectionTerminativeTask.iterator();
				while (iteratorTerminativeTask.hasNext()) {
					CharacterTask characterTask = iteratorTerminativeTask.next();
					writeInt(characterTask.getTask());
					writeShort(characterTask.getAcceptNum());
					if (characterTask.isLoop()) {
						if (resCharacterTaskList == null) {
							resCharacterTaskList = new ArrayList<CharacterTask>();
						}
						resCharacterTaskList.add(characterTask);
					}
				}
			} else {
				writeShort(0);
			}

			/*
			 * 任务id（int）, 目标难度级别（int）, 目标怪物(str), 目标Npc(str), 目标物品(str)， 目标坐骑(srt), 奖励丰厚度（int），奖励铜钱（int）, 奖励经验（int）, 奖励真气（int）, 奖励物品（str）
			 */
			if (resCharacterTaskList != null) {
				writeInt(resCharacterTaskList.size());
				for (Iterator<CharacterTask> iterator = resCharacterTaskList.iterator(); iterator.hasNext();) {
					CharacterTask characterTask = iterator.next();
					// getOut().writeInt(characterTask.getTask());
					// getOut().writeShort(characterTask.getAcceptNum());
					// getOut().writeShort(characterTask.getCompleteNum());
					writeInt(characterTask.getTask());
					TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(characterTask.getTaskConditionId());
					if (taskCondition != null) {
						writeInt(taskCondition.getDifficultyDegree());
						writeUTF(taskCondition.getMonsterModelId() == 0 ? "" : taskCondition.getMonsterStr());

						if (characterTask.getOrgTask().getType() == Task.Week_TASK) {
							writeUTF(characterTask.getEndnpc() == 0 ? "" : characterTask.getEndnpc() + "");
						} else {
							writeUTF(taskCondition.getNpcId() == 0 ? "" : taskCondition.getNpcId() + "");
						}
						writeUTF(taskCondition.getGoodId() == 0 ? "" : taskCondition.getGoodStr());
						writeUTF(taskCondition.getHorseId() == 0 ? "" : taskCondition.getHorseStr());
					} else {
						writeInt(0);
						writeUTF("");
						writeUTF("");
						writeUTF("");
						writeUTF("");
					}
					TaskReward taskReward = TaskRewardManager.getInstance().getTaskRewardById(characterTask.getTaskRewardId());
					if (taskReward != null) {
						writeInt(taskReward.getRewardGrade());
						writeInt(taskReward.getCopper());
						writeInt(taskReward.getExp());
						writeInt(taskReward.getZhenqi());
						String rewardGoods = characterTask.getRewardGoods();
						String[] goodStr = rewardGoods.split(";");
						String rewardGoodMsg = "";
						if (goodStr.length > 0 && !"".equals(goodStr[0].length())) {
							for (int i = 0; i < goodStr.length; i++) {
								String[] _goodStrs = goodStr[i].split(",");
								if (_goodStrs.length <= 1)
									break;
								int goodId = Integer.parseInt(_goodStrs[0]);
								int goodNum = Integer.parseInt(_goodStrs[1]);
								if (goodId > 0) {
									// Goodmodel goodmodel = GoodmodelManager
									// .getInstance().get(goodId);
									rewardGoodMsg = rewardGoodMsg + goodId + "#" + goodNum + "#0";
									if (i != (goodStr.length - 1)) {
										rewardGoodMsg = rewardGoodMsg + ",";
									}
								}
							}
						}
						writeUTF(rewardGoodMsg);
					} else {
						writeInt(0);
						writeInt(0);
						writeInt(0);
						writeInt(0);
						writeUTF("");
					}
					if (characterTask.getOrgTask().getType() == Task.Week_TASK) {
						writeInt(characterTask.getEndnpc());
					} else {
						writeInt(characterTask.getOrgTask().getEndnpc());
					}
				}
			} else {
				writeInt(0);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 任务id（int）,当前时间-接受任务时间 秒(int),怪物(str),Npc(str),目标区域(str)，指定动作(srt) 任务id（int）,当前时间-接受任务时间 秒(int),目标怪物(str), 目标Npc(str),目标区域(str)，目标动作(srt)，目标坐骑(str)
	 * 
	 * @param task
	 * @throws IOException
	 * @throws ParseException
	 */
	private void currTask(CharacterTask task) throws Exception {
		this.writeInt(task.getTask());
		Task task2 = task.getOrgTask();
		int limitTime = task2.getLimittime();
		if (limitTime == -1) {
			this.writeInt(limitTime);// -1没有时间限制
		} else {
			long runTaskTime = System.currentTimeMillis() - task.getGettime().getTime();
			long limitTime1 = (limitTime * 60 * 1000) - runTaskTime;
			if (limitTime1 <= 0) {
				this.writeInt(0);
			} else {
				this.writeInt((int) (limitTime1 / 1000));// 秒
			}
		}
		String monsterCond = task.getTargetmonster();
		if (monsterCond == null ||monsterCond.trim().equals("")) {
			this.writeUTF("");
		}else {
			String[] subCond = monsterCond.split(",");// 子类型#数量#怪物id
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < subCond.length; i++) {
				String[] cont = subCond[i].split("#");
				if (i == 0) {
					sb.append("0#" + cont[1] + "#0#0#" + cont[0]);
				} else {
					sb.append(",0#" + cont[1] + "#0#0#" + cont[0]);
				}
			}
			this.writeUTF(sb.toString());
		}
		
		
		this.writeUTF(task.getTargetnpc() == null ? "" : task.getTargetnpc());
		this.writeUTF(task.getTargetarea() == null ? "" : task.getTargetarea());
		this.writeUTF(task.getTargetaction() == null ? "" : task.getTargetaction());
		this.writeUTF(task.getTargetshopping() == null ? "" : task.getTargetshopping());
		this.writeUTF(task.getTargetrecharge() == null ? "" : task.getTargetrecharge());
		this.writeUTF(task.getTargetmountupgrade() == null ? "" : task.getTargetmountupgrade());
		this.writeShort(task.getAcceptNum());
	}
}
