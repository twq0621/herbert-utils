package net.snake.gamemodel.activities.bean;

import net.snake.ibatis.IbatisEntity;

public class Activities implements IbatisEntity{

	/**
	 * t_activities.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 帐号id t_activities.f_account_id
	 * 
	 */
	private Integer accountId;
	/**
	 * 活动类型1是充值120，2是充值200，3是新手卡活动 t_activities.f_type
	 * 
	 */
	private Integer type;
	/**
	 * 领取的次数 t_activities.f_count
	 * 
	 */
	private Integer count;
	/**
	 * 领取限制次数 t_activities.f_count_limit
	 * 
	 */
	private Integer countLimit;
	/**
	 * 充值的钱数 t_activities.f_chongzhiqian
	 * 
	 */
	private Float chongzhiqian;
	/**
	 * t_activities.f_ext
	 * 
	 */
	private String ext;

	/**
	 * t_activities.f_id
	 * @return  the value of t_activities.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_activities.f_id
	 * @param id  the value for t_activities.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 帐号id t_activities.f_account_id
	 * @return  the value of t_activities.f_account_id
	 * 
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 帐号id t_activities.f_account_id
	 * @param accountId  the value for t_activities.f_account_id
	 * 
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 活动类型1是充值120，2是充值200，3是新手卡活动 t_activities.f_type
	 * @return  the value of t_activities.f_type
	 * 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 活动类型1是充值120，2是充值200，3是新手卡活动 t_activities.f_type
	 * @param type  the value for t_activities.f_type
	 * 
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 领取的次数 t_activities.f_count
	 * @return  the value of t_activities.f_count
	 * 
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 领取的次数 t_activities.f_count
	 * @param count  the value for t_activities.f_count
	 * 
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 领取限制次数 t_activities.f_count_limit
	 * @return  the value of t_activities.f_count_limit
	 * 
	 */
	public Integer getCountLimit() {
		return countLimit;
	}

	/**
	 * 领取限制次数 t_activities.f_count_limit
	 * @param countLimit  the value for t_activities.f_count_limit
	 * 
	 */
	public void setCountLimit(Integer countLimit) {
		this.countLimit = countLimit;
	}

	/**
	 * 充值的钱数 t_activities.f_chongzhiqian
	 * @return  the value of t_activities.f_chongzhiqian
	 * 
	 */
	public Float getChongzhiqian() {
		return chongzhiqian;
	}

	/**
	 * 充值的钱数 t_activities.f_chongzhiqian
	 * @param chongzhiqian  the value for t_activities.f_chongzhiqian
	 * 
	 */
	public void setChongzhiqian(Float chongzhiqian) {
		this.chongzhiqian = chongzhiqian;
	}

	/**
	 * t_activities.f_ext
	 * @return  the value of t_activities.f_ext
	 * 
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * t_activities.f_ext
	 * @param ext  the value for t_activities.f_ext
	 * 
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
}
