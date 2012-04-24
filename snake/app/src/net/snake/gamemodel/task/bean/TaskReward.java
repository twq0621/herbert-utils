package net.snake.gamemodel.task.bean;

import net.snake.ibatis.IbatisEntity;

public class TaskReward implements IbatisEntity {

	/**
	 * 奖励编号 t_task_reward.f_reward_id
	 */
	private Integer rewardId;
	/**
	 * 玩家等级区间min t_task_reward.f_grade_min
	 */
	private Integer gradeMin;
	/**
	 * 玩家等级区间MAX t_task_reward.f_grade_max
	 */
	private Integer gradeMax;
	/**
	 * 奖励铜钱数量 t_task_reward.f_copper
	 */
	private Integer copper;
	/**
	 * 奖励经验数量 t_task_reward.f_exp
	 */
	private Integer exp;
	/**
	 * 奖励真气数量 t_task_reward.f_zhenqi
	 */
	private Integer zhenqi;
	/**
	 * 奖励物品名称 t_task_reward.f_good_name
	 */
	private String goodName;
	/**
	 * 奖励包裹id t_task_reward.f_good_id
	 */
	private Integer goodId;
	/**
	 * 奖励物品数量 t_task_reward.f_good_num
	 */
	private Integer goodNum;
	/**
	 * 奖励丰厚级别 t_task_reward.f_reward_grade
	 */
	private Integer rewardGrade;
	/**
	 * 0日任务1周任务 t_task_reward.f_type
	 */
	private Integer type;
	/**
	 * 奖励包裹id t_task_reward.f_good2_id
	 */
	private Integer good2Id;
	/**
	 * 奖励物品数量 t_task_reward.f_good2_num
	 */
	private Integer good2Num;
	/**
	 * 环数（周任务使用） t_task_reward.f_loop_num
	 */
	private Integer loopNum;

	/**
	 * 奖励编号 t_task_reward.f_reward_id
	 * 
	 * @return the value of t_task_reward.f_reward_id
	 */
	public Integer getRewardId() {
		return rewardId;
	}

	/**
	 * 奖励编号 t_task_reward.f_reward_id
	 * 
	 * @param rewardId
	 *            the value for t_task_reward.f_reward_id
	 */
	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	/**
	 * 玩家等级区间min t_task_reward.f_grade_min
	 * 
	 * @return the value of t_task_reward.f_grade_min
	 */
	public Integer getGradeMin() {
		return gradeMin;
	}

	/**
	 * 玩家等级区间min t_task_reward.f_grade_min
	 * 
	 * @param gradeMin
	 *            the value for t_task_reward.f_grade_min
	 */
	public void setGradeMin(Integer gradeMin) {
		this.gradeMin = gradeMin;
	}

	/**
	 * 玩家等级区间MAX t_task_reward.f_grade_max
	 * 
	 * @return the value of t_task_reward.f_grade_max
	 */
	public Integer getGradeMax() {
		return gradeMax;
	}

	/**
	 * 玩家等级区间MAX t_task_reward.f_grade_max
	 * 
	 * @param gradeMax
	 *            the value for t_task_reward.f_grade_max
	 */
	public void setGradeMax(Integer gradeMax) {
		this.gradeMax = gradeMax;
	}

	/**
	 * 奖励铜钱数量 t_task_reward.f_copper
	 * 
	 * @return the value of t_task_reward.f_copper
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 奖励铜钱数量 t_task_reward.f_copper
	 * 
	 * @param copper
	 *            the value for t_task_reward.f_copper
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 奖励经验数量 t_task_reward.f_exp
	 * 
	 * @return the value of t_task_reward.f_exp
	 */
	public Integer getExp() {
		return exp;
	}

	/**
	 * 奖励经验数量 t_task_reward.f_exp
	 * 
	 * @param exp
	 *            the value for t_task_reward.f_exp
	 */
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	/**
	 * 奖励真气数量 t_task_reward.f_zhenqi
	 * 
	 * @return the value of t_task_reward.f_zhenqi
	 */
	public Integer getZhenqi() {
		return zhenqi;
	}

	/**
	 * 奖励真气数量 t_task_reward.f_zhenqi
	 * 
	 * @param zhenqi
	 *            the value for t_task_reward.f_zhenqi
	 */
	public void setZhenqi(Integer zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * 奖励物品名称 t_task_reward.f_good_name
	 * 
	 * @return the value of t_task_reward.f_good_name
	 */
	public String getGoodName() {
		return goodName;
	}

	/**
	 * 奖励物品名称 t_task_reward.f_good_name
	 * 
	 * @param goodName
	 *            the value for t_task_reward.f_good_name
	 */
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	/**
	 * 奖励包裹id t_task_reward.f_good_id
	 * 
	 * @return the value of t_task_reward.f_good_id
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 奖励包裹id t_task_reward.f_good_id
	 * 
	 * @param goodId
	 *            the value for t_task_reward.f_good_id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 奖励物品数量 t_task_reward.f_good_num
	 * 
	 * @return the value of t_task_reward.f_good_num
	 */
	public Integer getGoodNum() {
		return goodNum;
	}

	/**
	 * 奖励物品数量 t_task_reward.f_good_num
	 * 
	 * @param goodNum
	 *            the value for t_task_reward.f_good_num
	 */
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	/**
	 * 奖励丰厚级别 t_task_reward.f_reward_grade
	 * 
	 * @return the value of t_task_reward.f_reward_grade
	 */
	public Integer getRewardGrade() {
		return rewardGrade;
	}

	/**
	 * 奖励丰厚级别 t_task_reward.f_reward_grade
	 * 
	 * @param rewardGrade
	 *            the value for t_task_reward.f_reward_grade
	 */
	public void setRewardGrade(Integer rewardGrade) {
		this.rewardGrade = rewardGrade;
	}

	/**
	 * 0日任务1周任务 t_task_reward.f_type
	 * 
	 * @return the value of t_task_reward.f_type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 0日任务1周任务 t_task_reward.f_type
	 * 
	 * @param type
	 *            the value for t_task_reward.f_type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 奖励包裹id t_task_reward.f_good2_id
	 * 
	 * @return the value of t_task_reward.f_good2_id
	 */
	public Integer getGood2Id() {
		return good2Id;
	}

	/**
	 * 奖励包裹id t_task_reward.f_good2_id
	 * 
	 * @param good2Id
	 *            the value for t_task_reward.f_good2_id
	 */
	public void setGood2Id(Integer good2Id) {
		this.good2Id = good2Id;
	}

	/**
	 * 奖励物品数量 t_task_reward.f_good2_num
	 * 
	 * @return the value of t_task_reward.f_good2_num
	 */
	public Integer getGood2Num() {
		return good2Num;
	}

	/**
	 * 奖励物品数量 t_task_reward.f_good2_num
	 * 
	 * @param good2Num
	 *            the value for t_task_reward.f_good2_num
	 */
	public void setGood2Num(Integer good2Num) {
		this.good2Num = good2Num;
	}

	/**
	 * 环数（周任务使用） t_task_reward.f_loop_num
	 * 
	 * @return the value of t_task_reward.f_loop_num
	 */
	public Integer getLoopNum() {
		return loopNum;
	}

	/**
	 * 环数（周任务使用） t_task_reward.f_loop_num
	 * 
	 * @param loopNum
	 *            the value for t_task_reward.f_loop_num
	 */
	public void setLoopNum(Integer loopNum) {
		this.loopNum = loopNum;
	}

	public static final int RewardUpLevel = 5;
}
