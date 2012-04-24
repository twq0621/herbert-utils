package net.snake.gamemodel.activities.bean;

import net.snake.ibatis.IbatisEntity;

public class LinshiActivity implements IbatisEntity {
	/**
	 * 活动ID t_linshi_activity.f_id
	 * 
	 */
	private Integer id;

	/**
	 * 间时表达式 t_linshi_activity.f_time_exp
	 * 
	 */
	private String timeExp;

	/**
	 * 动活描述 t_linshi_activity.f_condition
	 * 
	 */
	private String condition;

	/**
	 * 活动ID t_linshi_activity.f_id
	 * 
	 * @return the value of t_linshi_activity.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 活动ID t_linshi_activity.f_id
	 * 
	 * @param id
	 *            the value for t_linshi_activity.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 间时表达式 t_linshi_activity.f_time_exp
	 * 
	 * @return the value of t_linshi_activity.f_time_exp
	 * 
	 */
	public String getTimeExp() {
		return timeExp;
	}

	/**
	 * 间时表达式 t_linshi_activity.f_time_exp
	 * 
	 * @param timeExp
	 *            the value for t_linshi_activity.f_time_exp
	 * 
	 */
	public void setTimeExp(String timeExp) {
		this.timeExp = timeExp;
	}

	/**
	 * 动活描述 t_linshi_activity.f_condition
	 * 
	 * @return the value of t_linshi_activity.f_condition
	 * 
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * 动活描述 t_linshi_activity.f_condition
	 * 
	 * @param condition
	 *            the value for t_linshi_activity.f_condition
	 * 
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
