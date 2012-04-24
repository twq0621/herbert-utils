package net.snake.gamemodel.task.response;

import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskRewardManager;
import net.snake.netio.ServerResponse;

/**
 * 
 * 0失败{原因（str）}/1成功{任务id(int)，当前环数（short）, 周期内完成次数(short)， 任务id（int）,
 * 目标难度级别（int）, 目标怪物(str) 怪物模板id#数量#场景_x_y_距目标几步停止, 目标Npc(str), 目标物品(str)，
 * 物品id#物品数量#场景id_x_y_距离 目标坐骑(srt) (坐骑id#数量#场景_x_y_距离), 奖励丰厚度（int），奖励铜钱（int）,
 * 奖励经验（int）, 奖励真气（int）, 奖励物品（str） targetMonster 怪物类别ID#数量#名称#sceneId_x_y_dis
 * targetGoods 物品类别ID#数量#名称#sceneId_x_y_dis targetNpc NPC ID targetArea
 * 场景ID#x0_y0_x1_y1#sceneId_x_y_dis targetAction 动作ID targetHorse
 * 坐骑类别ID#数量#名称#sceneId_x_y_dis goods 物品种类ID#数量#门派ID
 * 
 * 
 */
public class GetLoopTaskMsg20000 extends ServerResponse {
	public GetLoopTaskMsg20000(CharacterTask characterTask) {
		setMsgCode(20000);
		try {
			writeByte(1);
			writeInt(characterTask.getTask());
			writeShort(characterTask.getAcceptNum());
			writeShort(characterTask.getCompleteNum());
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
				writeUTF(

				taskCondition.getHorseId() == 0 ? "" : taskCondition.getHorseStr());
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public GetLoopTaskMsg20000(int msgkey) {
		setMsgCode(20000);
		try {
			writeByte(0);
			writeInterUTF(msgkey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
