package net.snake.gamemodel.task.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class TaskDataEntry implements IbatisEntity {

	/**
	 * 主键 t_character_task.f_id
	 *
	 */
	private String id;
	/**
	 * 角色拥有的任务id t_character_task.f_task
	 *
	 */
	private Integer task;
	/**
	 * 角色id t_character_task.f_character_id
	 *
	 */
	private Integer characterId;
	/**
	 * 获取任务的时间 t_character_task.f_getTime
	 *
	 */
	private Date gettime;
	/**
	 * 完成时间 t_character_task.f_endTime
	 *
	 */
	private Date endtime;
	/**
	 * 打死的目标怪数量记录 同任务表中的f_targetMonster格式一致 t_character_task.f_targetMonster
	 *
	 */
	private String targetmonster;
	/**
	 * 目标NPC记录  同task 表中 t_targetNpc 一致 t_character_task.f_targetNpc
	 *
	 */
	private String targetnpc;
	/**
	 * 是否到达指定区域 同f_targetArea 一致 t_character_task.f_targetArea
	 *
	 */
	private String targetarea;
	/**
	 * 是否完成制定的 动作 同f_targetAction 一致 t_character_task.f_targetAction
	 *
	 */
	private String targetaction;
	/**
	 * 任务接受次数(环任务) t_character_task.f_accept_num
	 *
	 */
	private Integer acceptNum;
	/**
	 * 任务完成次数(环任务) t_character_task.f_complete_num
	 *
	 */
	private Integer completeNum;
	/**
	 * 任务放弃次数(环任务) t_character_task.f_drop_num
	 *
	 */
	private Integer dropNum;
	/**
	 * 任务条件id t_character_task.f_task_condition_id
	 *
	 */
	private Integer taskConditionId;
	/**
	 * 任务奖励id t_character_task.f_task_reward_id
	 *
	 */
	private Integer taskRewardId;
	/**
	 * 周任务的结束npcId t_character_task.f_endNpc
	 *
	 */
	private Integer endnpc;
	/**
	 * 任务奖励的物品(物品id,数量;*)(环任务动态奖励的物品) t_character_task.f_reward_goods
	 *
	 */
	private String rewardGoods;
	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID t_character_task.f_targetShopping
	 *
	 */
	private String targetshopping;
	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_character_task.f_targetRecharge
	 *
	 */
	private String targetrecharge;
	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_character_task.f_targetMountUpgrade
	 *
	 */
	private String targetmountupgrade;

	/**
	 * 主键 t_character_task.f_id
	 * @return  the value of t_character_task.f_id
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键 t_character_task.f_id
	 * @param id  the value for t_character_task.f_id
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 角色拥有的任务id t_character_task.f_task
	 * @return  the value of t_character_task.f_task
	 *
	 */
	public Integer getTask() {
		return task;
	}

	/**
	 * 角色拥有的任务id t_character_task.f_task
	 * @param task  the value for t_character_task.f_task
	 *
	 */
	public void setTask(Integer task) {
		this.task = task;
	}

	/**
	 * 角色id t_character_task.f_character_id
	 * @return  the value of t_character_task.f_character_id
	 *
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_task.f_character_id
	 * @param characterId  the value for t_character_task.f_character_id
	 *
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 获取任务的时间 t_character_task.f_getTime
	 * @return  the value of t_character_task.f_getTime
	 *
	 */
	public Date getGettime() {
		return gettime;
	}

	/**
	 * 获取任务的时间 t_character_task.f_getTime
	 * @param gettime  the value for t_character_task.f_getTime
	 *
	 */
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}

	/**
	 * 完成时间 t_character_task.f_endTime
	 * @return  the value of t_character_task.f_endTime
	 *
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * 完成时间 t_character_task.f_endTime
	 * @param endtime  the value for t_character_task.f_endTime
	 *
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * 打死的目标怪数量记录 同任务表中的f_targetMonster格式一致 t_character_task.f_targetMonster
	 * @return  the value of t_character_task.f_targetMonster
	 *
	 */
	public String getTargetmonster() {
		return targetmonster;
	}

	/**
	 * 打死的目标怪数量记录 同任务表中的f_targetMonster格式一致 t_character_task.f_targetMonster
	 * @param targetmonster  the value for t_character_task.f_targetMonster
	 *
	 */
	public void setTargetmonster(String targetmonster) {
		this.targetmonster = targetmonster;
	}

	/**
	 * 目标NPC记录  同task 表中 t_targetNpc 一致 t_character_task.f_targetNpc
	 * @return  the value of t_character_task.f_targetNpc
	 *
	 */
	public String getTargetnpc() {
		return targetnpc;
	}

	/**
	 * 目标NPC记录  同task 表中 t_targetNpc 一致 t_character_task.f_targetNpc
	 * @param targetnpc  the value for t_character_task.f_targetNpc
	 *
	 */
	public void setTargetnpc(String targetnpc) {
		this.targetnpc = targetnpc;
	}

	/**
	 * 是否到达指定区域 同f_targetArea 一致 t_character_task.f_targetArea
	 * @return  the value of t_character_task.f_targetArea
	 *
	 */
	public String getTargetarea() {
		return targetarea;
	}

	/**
	 * 是否到达指定区域 同f_targetArea 一致 t_character_task.f_targetArea
	 * @param targetarea  the value for t_character_task.f_targetArea
	 *
	 */
	public void setTargetarea(String targetarea) {
		this.targetarea = targetarea;
	}

	/**
	 * 是否完成制定的 动作 同f_targetAction 一致 t_character_task.f_targetAction
	 * @return  the value of t_character_task.f_targetAction
	 *
	 */
	public String getTargetaction() {
		return targetaction;
	}

	/**
	 * 是否完成制定的 动作 同f_targetAction 一致 t_character_task.f_targetAction
	 * @param targetaction  the value for t_character_task.f_targetAction
	 *
	 */
	public void setTargetaction(String targetaction) {
		this.targetaction = targetaction;
	}

	/**
	 * 任务接受次数(环任务) t_character_task.f_accept_num
	 * @return  the value of t_character_task.f_accept_num
	 *
	 */
	public Integer getAcceptNum() {
		return acceptNum;
	}

	/**
	 * 任务接受次数(环任务) t_character_task.f_accept_num
	 * @param acceptNum  the value for t_character_task.f_accept_num
	 *
	 */
	public void setAcceptNum(Integer acceptNum) {
		this.acceptNum = acceptNum;
	}

	/**
	 * 任务完成次数(环任务) t_character_task.f_complete_num
	 * @return  the value of t_character_task.f_complete_num
	 *
	 */
	public Integer getCompleteNum() {
		return completeNum;
	}

	/**
	 * 任务完成次数(环任务) t_character_task.f_complete_num
	 * @param completeNum  the value for t_character_task.f_complete_num
	 *
	 */
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}

	/**
	 * 任务放弃次数(环任务) t_character_task.f_drop_num
	 * @return  the value of t_character_task.f_drop_num
	 *
	 */
	public Integer getDropNum() {
		return dropNum;
	}

	/**
	 * 任务放弃次数(环任务) t_character_task.f_drop_num
	 * @param dropNum  the value for t_character_task.f_drop_num
	 *
	 */
	public void setDropNum(Integer dropNum) {
		this.dropNum = dropNum;
	}

	/**
	 * 任务条件id t_character_task.f_task_condition_id
	 * @return  the value of t_character_task.f_task_condition_id
	 *
	 */
	public Integer getTaskConditionId() {
		return taskConditionId;
	}

	/**
	 * 任务条件id t_character_task.f_task_condition_id
	 * @param taskConditionId  the value for t_character_task.f_task_condition_id
	 *
	 */
	public void setTaskConditionId(Integer taskConditionId) {
		this.taskConditionId = taskConditionId;
	}

	/**
	 * 任务奖励id t_character_task.f_task_reward_id
	 * @return  the value of t_character_task.f_task_reward_id
	 *
	 */
	public Integer getTaskRewardId() {
		return taskRewardId;
	}

	/**
	 * 任务奖励id t_character_task.f_task_reward_id
	 * @param taskRewardId  the value for t_character_task.f_task_reward_id
	 *
	 */
	public void setTaskRewardId(Integer taskRewardId) {
		this.taskRewardId = taskRewardId;
	}

	/**
	 * 周任务的结束npcId t_character_task.f_endNpc
	 * @return  the value of t_character_task.f_endNpc
	 *
	 */
	public Integer getEndnpc() {
		return endnpc;
	}

	/**
	 * 周任务的结束npcId t_character_task.f_endNpc
	 * @param endnpc  the value for t_character_task.f_endNpc
	 *
	 */
	public void setEndnpc(Integer endnpc) {
		this.endnpc = endnpc;
	}

	/**
	 * 任务奖励的物品(物品id,数量;*)(环任务动态奖励的物品) t_character_task.f_reward_goods
	 * @return  the value of t_character_task.f_reward_goods
	 *
	 */
	public String getRewardGoods() {
		return rewardGoods;
	}

	/**
	 * 任务奖励的物品(物品id,数量;*)(环任务动态奖励的物品) t_character_task.f_reward_goods
	 * @param rewardGoods  the value for t_character_task.f_reward_goods
	 *
	 */
	public void setRewardGoods(String rewardGoods) {
		this.rewardGoods = rewardGoods;
	}

	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID t_character_task.f_targetShopping
	 * @return  the value of t_character_task.f_targetShopping
	 *
	 */
	public String getTargetshopping() {
		return targetshopping;
	}

	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID t_character_task.f_targetShopping
	 * @param targetshopping  the value for t_character_task.f_targetShopping
	 *
	 */
	public void setTargetshopping(String targetshopping) {
		this.targetshopping = targetshopping;
	}

	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_character_task.f_targetRecharge
	 * @return  the value of t_character_task.f_targetRecharge
	 *
	 */
	public String getTargetrecharge() {
		return targetrecharge;
	}

	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_character_task.f_targetRecharge
	 * @param targetrecharge  the value for t_character_task.f_targetRecharge
	 *
	 */
	public void setTargetrecharge(String targetrecharge) {
		this.targetrecharge = targetrecharge;
	}

	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_character_task.f_targetMountUpgrade
	 * @return  the value of t_character_task.f_targetMountUpgrade
	 *
	 */
	public String getTargetmountupgrade() {
		return targetmountupgrade;
	}

	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_character_task.f_targetMountUpgrade
	 * @param targetmountupgrade  the value for t_character_task.f_targetMountUpgrade
	 *
	 */
	public void setTargetmountupgrade(String targetmountupgrade) {
		this.targetmountupgrade = targetmountupgrade;
	}
}
