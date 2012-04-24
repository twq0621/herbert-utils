package net.snake.gamemodel.activities.bean;

import net.snake.ibatis.IbatisEntity;

/**
 * 双倍经验真气活动
 * 
 * @author serv_dev
 * 
 */
public class DoubActivity implements IbatisEntity {

	/**
	 * 服务器id t_doub_activity.f_sid
	 * 
	 * 
	 */
	private Integer sid;
	/**
	 * 打怪经验倍率 t_doub_activity.f_daguai_exp
	 * 
	 * 
	 */
	private Integer daguaiExp;
	/**
	 * 打坐经验倍率 t_doub_activity.f_dazuo_exp
	 * 
	 * 
	 */
	private Integer dazuoExp;
	/**
	 * 打怪爆率 t_doub_activity.f_baolv
	 * 
	 * 
	 */
	private Integer baolv;
	/**
	 * 打坐真气倍率 t_doub_activity.f_dazuo_zhenqi
	 * 
	 * 
	 */
	private Integer dazuoZhenqi;

	private String timeExp;

	/**
	 * 服务器id t_doub_activity.f_sid
	 * 
	 * @return the value of t_doub_activity.f_sid
	 * 
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * 服务器id t_doub_activity.f_sid
	 * 
	 * @param sid
	 *            the value for t_doub_activity.f_sid
	 * 
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 打怪经验倍率 t_doub_activity.f_daguai_exp
	 * 
	 * @return the value of t_doub_activity.f_daguai_exp
	 * 
	 */
	public Integer getDaguaiExp() {
		return daguaiExp;
	}

	/**
	 * 打怪经验倍率 t_doub_activity.f_daguai_exp
	 * 
	 * @param daguaiExp
	 *            the value for t_doub_activity.f_daguai_exp
	 * 
	 */
	public void setDaguaiExp(Integer daguaiExp) {
		this.daguaiExp = daguaiExp;
	}

	/**
	 * 打坐经验倍率 t_doub_activity.f_dazuo_exp
	 * 
	 * @return the value of t_doub_activity.f_dazuo_exp
	 * 
	 */
	public Integer getDazuoExp() {
		return dazuoExp;
	}

	/**
	 * 打坐经验倍率 t_doub_activity.f_dazuo_exp
	 * 
	 * @param dazuoExp
	 *            the value for t_doub_activity.f_dazuo_exp
	 * 
	 */
	public void setDazuoExp(Integer dazuoExp) {
		this.dazuoExp = dazuoExp;
	}

	/**
	 * 打怪爆率 t_doub_activity.f_baolv
	 * 
	 * @return the value of t_doub_activity.f_baolv
	 * 
	 */
	public Integer getBaolv() {
		return baolv;
	}

	/**
	 * 打怪爆率 t_doub_activity.f_baolv
	 * 
	 * @param baolv
	 *            the value for t_doub_activity.f_baolv
	 * 
	 */
	public void setBaolv(Integer baolv) {
		this.baolv = baolv;
	}

	/**
	 * 打坐真气倍率 t_doub_activity.f_dazuo_zhenqi
	 * 
	 * @return the value of t_doub_activity.f_dazuo_zhenqi
	 * 
	 */
	public Integer getDazuoZhenqi() {
		return dazuoZhenqi;
	}

	/**
	 * 打坐真气倍率 t_doub_activity.f_dazuo_zhenqi
	 * 
	 * @param dazuoZhenqi
	 *            the value for t_doub_activity.f_dazuo_zhenqi
	 * 
	 */
	public void setDazuoZhenqi(Integer dazuoZhenqi) {
		this.dazuoZhenqi = dazuoZhenqi;
	}

	/**
	 * 时间表达式 t_doub_activity.f_timeexp
	 * 
	 * @return
	 */
	public String getTimeExp() {
		return timeExp;
	}

	/**
	 * 时间表达式 t_doub_activity.f_timeexp
	 * 
	 * @param timeExp
	 */
	public void setTimeExp(String timeExp) {
		this.timeExp = timeExp;
	}

}
