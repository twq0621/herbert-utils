package net.snake.gamemodel.task.bean;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.snake.commons.BeanUtils;
import net.snake.commons.RegexUtil;
import net.snake.commons.UUIDGenerater;
import net.snake.consts.Position;
import net.snake.gamemodel.friend.logic.RoleFriendManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.logic.action.TaskAction;
import net.snake.gamemodel.task.logic.action.TaskActionFactory;
import net.snake.gamemodel.task.persistence.TaskConditionManager;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.gamemodel.task.response.FinishTaskResponse;
import net.snake.gamemodel.team.logic.MyTeamManager;
import net.snake.ibatis.IbatisEntity;
import org.apache.log4j.Logger;

/**
 * 可以参照charactergoods的方式,对charactertask做封装
 * 
 * @author dev
 */

public class CharacterTask extends TaskDataEntry implements IbatisEntity {

	private static final Logger logger = Logger.getLogger(CharacterTask.class);

	private TaskAction taskVO;
	private List<CharacterGoods> takeCharacterGoods;// 要带走的物品
	private Horse horse;// 要带走的坐骑
	private volatile int tmpConditionGrade = 0;
	private volatile int tmpRewardGrade = 0;

	public int getTmpConditionGrade() {
		return tmpConditionGrade;
	}

	public void setTmpConditionGrade(int tmpConditionGrade) {
		this.tmpConditionGrade = tmpConditionGrade;
	}

	public int getTmpRewardGrade() {
		return tmpRewardGrade;
	}

	public void setTmpRewardGrade(int tmpRewardGrade) {
		this.tmpRewardGrade = tmpRewardGrade;
	}

	public CharacterTask(Task task) {
	}

	public CharacterTask(TaskDataEntry taskDataEntry) {
		BeanUtils.copyProperties(taskDataEntry, this);
	}

	public boolean isOutTime() {
		return getTaskVO().isOutTime(this);
	}

	/**
	 * 是否需要任务物品
	 * 
	 * @param characterGoodController
	 * @param goodModelId
	 * @return true的话需要任务物品
	 */
	public boolean isNeedGood(CharacterGoodController characterGoodController, int goodModelId) {
		// Date completeEndTime = getOrgTask().getCompleteEndTime();
		// if (completeEndTime != null && new Date().after(completeEndTime))
		// {//已过期
		// return false;
		// }

		String targetGoods = getOrgTask().getTargetgoods();

		// if (taskFunType == Task.SHOUJI || taskFunType == Task.FUHE) {
		if (targetGoods != null && targetGoods.length() != 0) {
			String targetGood[] = targetGoods.split(",");
			for (int i = 0; i < targetGood.length; i++) {
				String idAndNum[] = targetGood[i].split("#");
				int _goodModelId = Integer.parseInt(idAndNum[0]);
				if (goodModelId != _goodModelId) {
					continue;
				}
				int num = Integer.parseInt(idAndNum[1]);
				int countnow = characterGoodController.getBagGoodsCountByModelID(goodModelId)
						+ characterGoodController.getStorageGoodsContainer().getGoodsCountByModelID(goodModelId);
				if (countnow < num) {
					return true;
				}
			}
			// }
		}
		return false;
	}

	public Horse getTakeHorse() {
		return horse;
	}

	public void setTakeHorse(Horse horse) {
		this.horse = horse;
	}

	public List<CharacterGoods> getTakeCharacterGoods() {
		return takeCharacterGoods;
	}

	public void setTakeCharacterGoods(List<CharacterGoods> takeCharacterGoods) {
		this.takeCharacterGoods = takeCharacterGoods;
	}

	public boolean isLoop() {
		int type = getOrgTask().getType();

		if (type == Task.Day_TASK || type == Task.Day_YaBiao_TASK || type == Task.Week_TASK || type == Task.Upgrade_TASK) {
			return true;
		} else {
			return false;
		}
	}

	public TaskAction getTaskVO() {
		return taskVO;
	}

	public void setTaskVO(TaskAction taskVO) {
		this.taskVO = taskVO;
	}

	/**
	 * 创建 角色任务
	 * 
	 * @param characterid
	 *            角色id
	 * @param taskid
	 *            任务id
	 * @param date
	 *            日期
	 * @return
	 */
	public static CharacterTask createTask(CharacterTaskController taskController, Task task,TaskAction action) {
		CharacterTask characterTask = new CharacterTask(task);
		characterTask.setId(UUIDGenerater.generate());
		characterTask.setCharacterId(taskController.getCharacter().getId());
		characterTask.setTask(task.getTaskId());
		characterTask.setGettime(new Date());
		characterTask.setAcceptNum(0);
		characterTask.setCompleteNum(0);
		characterTask.setDropNum(0);
		characterTask.setTaskConditionId(0);
		characterTask.setTaskRewardId(0);
		characterTask.setTaskVO(action);
		characterTask.setTargetaction("");
		characterTask.setTargetarea("");
		characterTask.setTargetmonster("");
		characterTask.setTargetnpc("");
		characterTask.setEndnpc(0);
		characterTask.setRewardGoods("");
		characterTask.setTargetrecharge("");
		characterTask.setTargetshopping("");
		characterTask.setTargetmountupgrade("");
		return characterTask;
	}
	public static CharacterTask createTask(CharacterTaskController taskController, Task task) {
		CharacterTask characterTask = new CharacterTask(task);
		characterTask.setId(UUIDGenerater.generate());
		characterTask.setCharacterId(taskController.getCharacter().getId());
		characterTask.setTask(task.getTaskId());
		characterTask.setGettime(new Date());
		characterTask.setAcceptNum(0);
		characterTask.setCompleteNum(0);
		characterTask.setDropNum(0);
		characterTask.setTaskConditionId(0);
		characterTask.setTaskRewardId(0);
		characterTask.setTaskVO(TaskActionFactory.getInstance().createAction(task, taskController));
		characterTask.setTargetaction("");
		characterTask.setTargetarea("");
		characterTask.setTargetmonster("");
		characterTask.setTargetnpc("");
		characterTask.setEndnpc(0);
		characterTask.setRewardGoods("");
		characterTask.setTargetrecharge("");
		characterTask.setTargetshopping("");
		characterTask.setTargetmountupgrade("");
		return characterTask;
	} 

	/**
	 * 目标物品、任务物品、奖励物品
	 * 
	 * @param task
	 * @param character
	 * @param dletecachGoods
	 * @param del_updateGoods
	 * @param goodModelMap
	 * @return
	 */
	public boolean validateGoodsCondition(CharacterGoodController characterGoodController) {

		if (takeCharacterGoods != null) {
			for (Iterator<CharacterGoods> iterator = takeCharacterGoods.iterator(); iterator.hasNext();) {
				CharacterGoods characterGoods = iterator.next();
				if (characterGoods.isInTrade()) {
					characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20048, 0));
					return false;
				}
			}
		}

		if (horse != null) {
			if (getOrgTask().getHorsedrop() == 1) {
				if (!horse.isRemovable()) {
					characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20049, 0));
					return false;
				}
			}
		}

		// 目标物品
		String targetGoods = getOrgTask().getTargetgoods();

		// int taskFunType = getOrgTask().getFunctiontype();
		// if (taskFunType == Task.SHOUJI || taskFunType == Task.FUHE) {
		if (targetGoods != null && targetGoods.length() != 0) {
			String targetGood[] = targetGoods.split(",");
			for (int i = 0; i < targetGood.length; i++) {
				String idAndNum[] = targetGood[i].split("#");
				int goodModelId = Integer.parseInt(idAndNum[0]);
				int num = Integer.parseInt(idAndNum[1]);
				if (!characterGoodController.isEnoughGoods(goodModelId, num)) {
					characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20050, 0));
					return false;
				}
			}
		} else {
			int taskId = getTaskConditionId() == null ? 0 : getTaskConditionId();
			if (taskId > 0) {
				TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskId);
				int goodModelId = taskCondition.getGoodId();
				int num = taskCondition.getGoodNum();
				if (goodModelId > 0) {
					if (!characterGoodController.isEnoughGoods(goodModelId, num)) {
						characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20050, 0));
						return false;
					}
				}
			}
			// }
		}

		// 任务物品
		String taskGoodsDesc = getOrgTask().getTaskgoods();
		if (taskGoodsDesc != null && !"".equals(taskGoodsDesc)) {
			String[] tgdStrings = taskGoodsDesc.split(":");
			int num = 1;
			int taskGoods = Integer.parseInt(tgdStrings[0]);
			int recover = Integer.parseInt(tgdStrings[1]);
			if (taskGoods != 0 && recover == 1) {
				if (!characterGoodController.isEnoughGoods(taskGoods, num)) {
					characterGoodController.getCharacter().sendMsg(new FinishTaskResponse(0, 20050, 0));
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * 目标物品、任务物品、奖励物品
	 * 
	 * @param task
	 * @param character
	 * @param dletecachGoods
	 * @param del_updateGoods
	 * @param goodModelMap
	 * @return
	 */
	public void completeRewardGood(Hero character) {
		CharacterGoodController characterGoodController = character.getCharacterGoodController();

		if (takeCharacterGoods != null) {
			for (Iterator<CharacterGoods> iterator = takeCharacterGoods.iterator(); iterator.hasNext();) {
				CharacterGoods characterGoods = iterator.next();
				characterGoodController.deleteCharacterGoods(characterGoods);
			}
			takeCharacterGoods = null;
		} else {
			if (getTaskConditionId() > 0) {
				TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(getTaskConditionId());
				if (taskCondition.getGoodId() > 0) {
					character.getCharacterGoodController().deleteGoodsFromBag(taskCondition.getGoodId(), taskCondition.getGoodNum());
				}
			}
		}

		if (horse != null) {
			if (getOrgTask().getHorsedrop() == 1) {
				if (horse.isRemovable()) {
					CharacterHorseController characterHorseController = character.getCharacterHorseController();
					characterHorseController.drop(horse);
				}
			}
			horse = null;
		}

		// 目标物品
		String targetGoods = getOrgTask().getTargetgoods();

		// int taskFunType = getOrgTask().getFunctiontype();
		// if (taskFunType == Task.SHOUJI || taskFunType == Task.FUHE) {
		if (targetGoods != null && targetGoods.length() != 0) {
			String targetGood[] = targetGoods.split(",");
			for (int i = 0; i < targetGood.length; i++) {
				String idAndNum[] = targetGood[i].split("#");
				int goodModelId = Integer.parseInt(idAndNum[0]);
				int num = Integer.parseInt(idAndNum[1]);
				if (characterGoodController.isEnoughGoods(goodModelId, num)) {
					characterGoodController.deleteGoodsFromBag(goodModelId, num);
				}
			}
		}
		// }

		// 任务物品
		String taskGoodsDesc = getOrgTask().getTaskgoods();
		if (taskGoodsDesc != null && !"".equals(taskGoodsDesc)) {
			String[] tgdStrings = taskGoodsDesc.split(":");
			int num = 1;
			int taskGoods = Integer.parseInt(tgdStrings[0]);
			int recover = Integer.parseInt(tgdStrings[1]);
			if (taskGoods != 0 && recover == 1) {
				if (characterGoodController.isEnoughGoods(taskGoods, num)) {
					characterGoodController.deleteGoodsFromBag(taskGoods, num);
				}
			}
		}

	}

	/**
	 * 结束任务
	 */
	public void end() {
		setEndtime(new Date());
		setTargetaction("");
		setTargetarea("");
		setTargetmonster("");
		setTargetnpc("");
		setRewardGoods("");
	}

	/**
	 * 重新 设置任务
	 * 
	 * @param task
	 */
	public void resetTask() {
		this.setGettime(new Date());
		this.setEndtime(null);// 设置结束时间
		this.setTargetaction("");
		this.setTargetarea("");
		this.setTargetmonster("");
		this.setTargetnpc("");
	}

	/**
	 * 怪物记录
	 * 
	 * @param characterTask
	 * @param task
	 * @param sceneMonster
	 */
	public boolean monsterRecord(SceneMonster sceneMonster) {

		if (sceneMonster == null)
			return false;

		Task task = this.getOrgTask();
		// 怪物模板id#怪物数量,*
		String monstersRecord = getTargetmonster();
		if ("0".equals(monstersRecord)) {
			setTargetmonster("");
			monstersRecord = "";
		}
		String targetMonsters = task.getTargetmonster();
		if (task.isLoopTask()) {
			TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(getTaskConditionId());
			if (taskCondition == null) {
				logger.error("npcRecord(Npc) - loop task is no condition: characterId:"+getCharacterId()+",task:"+task.getTaskId()); //$NON-NLS-1$
				return false;
			}
			targetMonsters = taskCondition.getMonsterStr();
		}

		if (targetMonsters == null || targetMonsters.length() == 0) {
			return false;
		}
		StringBuilder monstersRecordStr = new StringBuilder();
		int monsterModelid = sceneMonster.getMonsterModel().getSubtype();
		String flag = monsterModelid + "";
		// if (RegexUtil.validateTaskReges2(flag, targetMonsters)) {
		if (RegexUtil.validateTask_monsterCond(flag, targetMonsters)) {
			if (monstersRecord == null || monstersRecord.length() == 0) {
				monstersRecordStr.append(flag + "#1#" + sceneMonster.getMonsterModel().getId());// 记录一类型怪物一个数量
			} else if (RegexUtil.validateTaskReges2(flag, monstersRecord)) {// 记录中是否有该类型的怪物
				String targetMonster[] = targetMonsters.split(",");
				// Task
				int targetMonsterCount = 0;// 获得最大的数量
				for (int i = 0; i < targetMonster.length; i++) {
					String idAndNum[] = targetMonster[i].split("#");
					// int targetMonsterid = Integer.parseInt(idAndNum[idAndNum.length-1]);
					int targetMonsterNum = Integer.parseInt(idAndNum[1]);
					if (String.valueOf(monsterModelid).equals(idAndNum[idAndNum.length - 1])) {
						targetMonsterCount = targetMonsterNum;
						break;
					}
				}
				// characterTask
				String monsters[] = monstersRecord.split(",");
				for (int i = 0; i < monsters.length; i++) {
					String idAndNum[] = monsters[i].split("#");
					int targetMonsterid = Integer.parseInt(idAndNum[0]);
					int targetMonsterNum = Integer.parseInt(idAndNum[1]);
					if (monsterModelid == targetMonsterid) {
						if (targetMonsterNum < targetMonsterCount) {
							targetMonsterNum = targetMonsterNum + 1;
						}
						if (i == 0) {
							monstersRecordStr.append(idAndNum[0] + "#" + targetMonsterNum + "#" + sceneMonster.getMonsterModel().getId());
						} else {
							monstersRecordStr.append("," + idAndNum[0] + "#" + targetMonsterNum + "#" + sceneMonster.getMonsterModel().getId());
						}
					} else {
						if (i == 0) {
							monstersRecordStr.append(monsters[i]);
						} else {
							monstersRecordStr.append("," + monsters[i]);
						}
					}
				}
			} else {
				monstersRecordStr.append(monstersRecord + "," + flag + "#1" + "#" + sceneMonster.getMonsterModel().getId());
			}
			this.setTargetmonster(monstersRecordStr.toString());
			return true;
		}
		return false;
	}

	/**
	 * 动作记录
	 * 
	 * @param characterTask
	 * @param task
	 * @param action
	 *            1组队，2添加好友3使用喇叭 4装备物品 5使用恢复类道具 6道具合成 7宝石镶嵌 8 装备修理 9买入道具 10卖出道具 11秘籍升级 12将技能/道具拖入快捷栏
	 */
	public boolean actionRecord(int action) {
		Task task = this.getOrgTask();
		// 动作id,*
		String actionRecord = this.getTargetaction();
		String targetAction = task.getTargetaction();
		if (targetAction == null || targetAction.length() == 0) {
			return false;
		}
		String flag = action + "";
		if (RegexUtil.validateTaskReges(flag, targetAction)) {
			if (actionRecord == null || actionRecord.length() == 0) {
				this.setTargetaction(flag);
			} else if (!RegexUtil.validateTaskReges(flag, actionRecord))// 没有这个记录
			{
				if (action != 0) {
					if (!"0".equals(actionRecord)) {
						this.setTargetaction(actionRecord + "," + flag);
					} else {
						this.setTargetaction(flag);
					}
				}
			} else {
				return false;// 动作不重复
			}
			return true;
		}
		return false;
	}

	/**
	 * 寻找目标npc 记录
	 * 
	 * @param characterTask
	 * @param task
	 * @param npc
	 */
	public boolean npcRecord(Npc npc) {
		// NPCid,*
		Task task = this.getOrgTask();
		String npcRecord = this.getTargetnpc();
		String targetNpc = task.getTargetnpc();
		if (task.isLoopTask()) {
			TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(getTaskConditionId());
			if (taskCondition == null) {
				logger.error("npcRecord(Npc) - loop task is no condition : characterId:"+getCharacterId()+",task:"+ task.getTaskId()); //$NON-NLS-1$
				return false;
			}
			targetNpc = taskCondition.getNpcStr();
			if (task.getType() == Task.Week_TASK) {
				targetNpc = getEndnpc() + "";
			}
			setTargetnpc("");// 环任务不存在其他npc
			npcRecord = "";
		}

		if (targetNpc == null || targetNpc.length() == 0 || npc == null) {
			return false;
		}
		String flag = npc.getId() + "";
		if (RegexUtil.validateTaskReges(flag, targetNpc)) {
			// TODO 验证 npc与 玩家的距离
			if (npcRecord == null || npcRecord.length() == 0) {
				this.setTargetnpc(flag);
			} else if (!RegexUtil.validateTaskReges(flag, npcRecord))// 没有这个npc记录
			{
				this.setTargetnpc(npcRecord + "," + flag);
			} else {
				// 不记录重复npc
				return false;
			}
			return true;
		}

		return false;
	}

	/**
	 * 寻找区域记录
	 * 
	 * @param characterTask
	 * @param task
	 * @param seceid
	 * @param currx
	 * @param curry
	 */
	public boolean areaRecord(int currSceneid, int currx, int curry) {

		// 场景id#x1_y1_x2_y2,*
		Task task = this.getOrgTask();
		String targetArea = task.getTargetarea();
		String areaRecord = getTargetarea();
		if (targetArea == null || targetArea.length() == 0) {
			return false;
		}
		String areas[] = targetArea.split(",");
		for (int i = 0; i < areas.length; i++) {
			String area[] = areas[i].split("#");
			int scendid = Integer.parseInt(area[0]);
			if (scendid == currSceneid) {
				String region[] = area[1].split("_");
				int x1 = Integer.parseInt(region[0]);
				int y1 = Integer.parseInt(region[1]);
				int x2 = Integer.parseInt(region[2]);
				int y2 = Integer.parseInt(region[3]);
				if (currx >= x1 && currx <= x2 && curry >= y1 && curry <= y2) {
					if (areaRecord == null || areaRecord.length() == 0) {
						this.setTargetarea(areas[i]);
					} else if (!areaRecord.contains(areas[i]))// 没有记录区域
					{
						this.setTargetarea(areaRecord + "," + areas[i]);
					} else {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	public Task getOrgTask() {
		return TaskManager.getInstance().get(getTask());
	}

	public boolean validateLeval(int leval) {
		// 验证结束任务等级
		if (leval < this.getOrgTask().getReceptionlevel()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证npc
	 * 
	 * @return 只要有一个条件不满足，返回false
	 */
	public boolean validateNpc() {
		Task task = this.getOrgTask();
		String targetNpc = task.getTargetnpc();
		String recordNpc = getTargetnpc();
		if (targetNpc != null && targetNpc.length() != 0) {
			if (recordNpc == null || recordNpc.length() == 0) {
				return false;
			} else {
				String npcids[] = targetNpc.split(",");
				for (int i = 0; i < npcids.length; i++) {
					String flag = npcids[i];
					if (!RegexUtil.validateTaskReges(flag, getTargetnpc())) {
						return false;
					}
				}
			}
		} else {
			int taskId = getTaskConditionId() == null ? 0 : getTaskConditionId();
			if (taskId > 0) {

				if (recordNpc == null || recordNpc.length() == 0) {
					TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskId);
					if (taskCondition.getNpcId() > 0) {
						return false;
					}
				} else {
					String npcRecord[] = recordNpc.split(",");
					if (npcRecord.length == 0)
						return false;
					int npcId = Integer.parseInt(npcRecord[0]);
					if (getOrgTask().getType() == Task.Week_TASK) {
						if (!(npcId == getEndnpc()))
							return false;
					} else {
						TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskId);
						if (!(npcId == taskCondition.getNpcId()))
							return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 验证区域
	 * 
	 * @return 只要有一个条件不满足，返回false
	 */
	public boolean validateArea() {
		Task task = this.getOrgTask();
		String targetArea = task.getTargetarea();
		if (targetArea != null && targetArea.length() != 0) {
			if (getTargetarea() == null && getTargetarea().length() == 0) {
				return false;
			} else {
				String areas[] = targetArea.split(",");
				for (int i = 0; i < areas.length; i++) {
					String flag = areas[i] + "";
					if (!RegexUtil.validateTaskReges(flag, getTargetarea())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 判断玩家身上是否有某个BUFF-ID
	 */

	public boolean validateTargetBuff() {
		String buff = getOrgTask().getTargetbuff();
		if (buff != null && !"".equals(buff)) {
			int _buff = Integer.parseInt(buff);
			if (_buff > 0) {
				Hero character = getTaskVO().getCharacter();
				return character.getEffectController().isContains(_buff);
			}
		}
		return true;
	}

	/**
	 * 判断玩家某条经脉是否冲通
	 */

	public boolean validateTargetChannel() {
		String channel = getOrgTask().getTargetchannel();
		if (channel != null && !"".equals(channel)) {
			Hero character = getTaskVO().getCharacter();
			return character.getMyChannelManager().getJinMaiTack(channel.trim());
		}
		return true;
	}

	/**
	 * 判断玩家某个经脉穴位是否冲通
	 */

	public boolean validateTargetPoint() {
		String xuewei = getOrgTask().getTargetpoint();
		if (xuewei != null && !"".equals(xuewei)) {
			Hero character = getTaskVO().getCharacter();
			return character.getMyChannelManager().getJinMaiTack(xuewei.trim());
		}
		return true;
	}

	/**
	 * 判断玩家整体武功境界层数是否大于X
	 * 
	 * @return
	 */
	public boolean validateTargetAllSkillLv() {
		String targetallskilllv = getOrgTask().getTargetallskilllv();
		if (targetallskilllv != null && !"".equals(targetallskilllv)) {
			int wugongGrade = Integer.parseInt(targetallskilllv);
			Hero character = getTaskVO().getCharacter();
			if (wugongGrade > character.getWuxueJingjie()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * "目标武功ID#目标等级" 或 ""
	 * 
	 * @return 1玩家某个武功未学会 2玩家某个武功的等级不符合条件
	 */
	public int validateTargetSkillLv() {
		String targetSkillLv = getOrgTask().getTargetskilllv();
		if (targetSkillLv != null && !"".equals(targetSkillLv)) {
			String[] targetSkillLvStr = targetSkillLv.split("#");
			if (targetSkillLvStr.length == 2) {
				String skillId = targetSkillLvStr[0];
				String skillGrade = targetSkillLvStr[1];
				if (!"".equals(skillId) && !"".equals(skillGrade)) {
					Hero character = getTaskVO().getCharacter();
					int _skillId = Integer.parseInt(skillId);
					if (_skillId > 0) {
						CharacterSkill characterSkill = character.getSkillManager().getCharacterSkillById(_skillId);
						if (characterSkill == null) {
							return 1;
						} else {
							int _skillGrade = Integer.parseInt(skillGrade);
							if (_skillGrade > characterSkill.getGrade()) {
								return 2;
							}
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * "目标部位ID#目标装备ID" 或 ""
	 * 
	 * @return 1某个部位没有佩戴装备 2某个部位没有佩戴了装备ID
	 */
	public int validateTargetEquip() {
		String targetEquip = getOrgTask().getTargetequip();
		if (targetEquip != null && !"".equals(targetEquip)) {
			String[] targetEquipStr = targetEquip.split("#");
			if (targetEquipStr.length == 2) {
				String position = targetEquipStr[0];
				String equipId = targetEquipStr[1];
				if (!"".equals(position) && !"".equals(equipId)) {
					Hero character = getTaskVO().getCharacter();
					short _position = Short.parseShort(position);
					if (_position > 0) {
						if (_position >= Position.POSTION_WUQI && _position <= Position.POSTION_TESHU) {
							CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(_position);
							if (equipment == null) {
								return 1;
							} else {
								int _equipId = Integer.parseInt(equipId);
								if (equipment.getGoodmodelId() != _equipId) {
									return 2;
								}
							}
						}
					}

				}
			}
		}
		return 0;
	}

	/**
	 * 检查好友数量
	 * 
	 * @return
	 */
	public boolean validateTargetFriend() {
		String targetFriend = getOrgTask().getTargetfriend();

		if (targetFriend != null && !"".equals(targetFriend)) {
			Hero character = getTaskVO().getCharacter();
			int friendNum = Integer.parseInt(targetFriend);
			if (friendNum > 0) {// 好友数量
				RoleFriendManager friendMgr = character.getMyFriendManager().getRoleFriendManager();
				if (friendNum > friendMgr.getFriendCount()) {
//					if (logger.isDebugEnabled()) {
//						logger.debug("角色id :{} 任务id :{} 要求的好友数，{}拥有的好友数:{} ", new Object[] { character.getId(), getTask(), friendNum, friendMgr.getFriendCount() });
//					}

					return false;
				}
			}

		}
		return true;
	}

	/**
	 * 检查队伍总人数
	 * 
	 * @return false 标示没有达到一定的队伍人数
	 */
	public boolean validateTargetGroup() {
		String targetGroup = getOrgTask().getTargetgroup();

		if (targetGroup != null && !"".equals(targetGroup)) {
			int teamNum = Integer.parseInt(targetGroup);
			if (teamNum > 1) {// 队伍数>1
				Hero character = getTaskVO().getCharacter();
				MyTeamManager teamManager = character.getMyTeamManager();
				if (teamManager.isTeam()) {
					if (teamNum > teamManager.getMyTeam().getTeamPopulation()) {
//						if (logger.isDebugEnabled()) {
//							logger.debug("角色id :{} 任务id :{} 要求的队友数，{}拥有的队友数:{} ",
//									new Object[] { character.getId(), getTask(), teamNum, teamManager.getMyTeam().getTeamPopulation() });
//						}

						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * 验证是否充值过
	 * 
	 * @return
	 */
	public boolean validateRecharge() {
		String targetRecharge = getOrgTask().getTargetrecharge();
		String recodeRecharge = getTargetrecharge();
		if (targetRecharge != null && targetRecharge.length() != 0) {
			if (recodeRecharge == null || recodeRecharge.length() == 0) {
				return false;

			} else {
				if (!recodeRecharge.trim().equals(targetRecharge.trim())) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 验证是否购买过商城道具
	 * 
	 * @return
	 */
	public boolean validateTargetShopping() {
		String targetShopping = getOrgTask().getTargetshopping();
		String recodeShopping = getTargetshopping();
		if (targetShopping != null && targetShopping.length() != 0) {
			if (recodeShopping == null || recodeShopping.length() == 0) {
				return false;

			} else {
				String targetShoppings[] = targetShopping.split(",");
				for (int i = 0; i < targetShoppings.length; i++) {
					String flag = targetShoppings[i];// (怪物模板id#数量#场景_x_y_距目标几步停止),
					if (!RegexUtil.validateTaskReges2(flag, recodeShopping)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * 验证怪物
	 * 
	 * @return 只要有一个条件不满足，返回false
	 */
	public boolean validateMonster() {
		Task task = this.getOrgTask();
		String recordMonster = getTargetmonster();
		String targetMonster = task.getTargetmonster();
		if (targetMonster != null && targetMonster.length() != 0) {
			if (recordMonster == null || recordMonster.length() == 0) {
				return false;
			} else {
				String monsters[] = targetMonster.split(",");
				for (int i = 0; i < monsters.length; i++) {
					String flag = monsters[i];// (怪物模板id#数量#场景_x_y_距目标几步停止#subtype),
					String[] dataflag = flag.split("#");

					// if (!RegexUtil.validateTaskReges2(dataflag[0] + "#" + dataflag[1], recordMonster)) {
					// return false;
					// }

					if (!RegexUtil.validateTaskReges2(dataflag[dataflag.length - 1] + "#" + dataflag[1], recordMonster)) {
						return false;
					}
				}
			}
		} else {
			int taskId = getTaskConditionId();
			if (taskId > 0) {
				if (recordMonster == null || recordMonster.length() == 0) {
					TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskId);
					if (taskCondition.getMonsterModelId() > 0) {
						return false;
					}
				} else {
					// recordMonster有记录
					TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(taskId);
					if (taskCondition.getMonsterModelId() == null || taskCondition.getMonsterModelId() == 0) {
						return true;
					}
					String monstersStr[] = recordMonster.split(",");
					if (monstersStr.length == 0) {
						return false;
					}
					boolean result = false;
					
					for (int i = 0; i < monstersStr.length; i++) {
						String monsterRec = monstersStr[i];
						// 环任务只有一种怪物需要判断
						String[] monsterAndNum = monsterRec.split("#");
						int monsterId = Integer.parseInt(monsterAndNum[0]);
						int monsterNum = Integer.parseInt(monsterAndNum[1]);
						int subType = MonsterModelManager.getInstance().getFromCache(taskCondition.getMonsterModelId()).getSubtype();
						if ((monsterId == subType && monsterNum >= taskCondition.getMonsterNum())) {
							result = true;
						}
					}
					return result;
				}
			}
		}
		return true;
	}

	/**
	 * 验证动作
	 * 
	 * @return 只要有一个条件不满足，返回false
	 */
	public boolean validateAction() {
		Task task = this.getOrgTask();
		String targetAction = task.getTargetaction();
		if (targetAction != null && targetAction.length() != 0) {
			if (getTargetaction() == null || getTargetaction().length() == 0) {
				return false;
			} else {
				String actions[] = targetAction.split(",");
				for (int i = 0; i < actions.length; i++) {
					String flag = actions[i] + "";
					if (!RegexUtil.validateTaskReges(flag, getTargetaction())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 时间是否超时
	 * 
	 * @return false 超时
	 */
	public boolean validateLimitTime() {

		long runTaskTime = System.currentTimeMillis() - getGettime().getTime();
		long limitTime = this.getOrgTask().getLimittime();
		if (limitTime == -1) {
			return true;
		}
		limitTime = (limitTime * 60 * 1000);
		if (limitTime <= runTaskTime)// 超时
		{
			return false;
		}
		return true;
	}

	/**
	 * 测试使用 将当前任务追填充满
	 */
	public void fillFullTask(Hero character) {
		Task task = this.getOrgTask();
		this.setTargetaction(task.getTargetaction());
		this.setTargetarea(task.getTargetarea());
		this.setTargetmonster(task.getTargetmonster());
		this.setTargetnpc("0".equals(task.getTargetnpc()) ? "" : task.getTargetnpc());
		if (getTaskConditionId() > 0) {
			TaskCondition taskCondition = TaskConditionManager.getInstance().getTaskConditionById(getTaskConditionId());
			this.setTargetmonster(taskCondition.getMonsterModelId() + "#" + taskCondition.getMonsterNum());
			this.setTargetnpc("0".equals(taskCondition.getNpcId() + "") ? "" : taskCondition.getNpcId() + "");
			if (taskCondition.getGoodId() > 0) {
				CharacterGoods goods = CharacterGoods.createCharacterGoods(taskCondition.getGoodNum(), taskCondition.getGoodId(), 100);
				character.getCharacterGoodController().addGoodsToBag(goods);
			}
		}
	}
}
