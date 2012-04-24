package net.snake.gamemodel.fight.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class DaypkRecord implements IbatisEntity {

	/**
	 * 胜利者 t_chengzhan_daypk_record.f_winer
	 *
	 */
	private Integer winer;
	/**
	 * 失败者 t_chengzhan_daypk_record.f_failer
	 *
	 */
	private Integer failer;
	/**
	 * 战胜次数 t_chengzhan_daypk_record.f_count
	 *
	 */
	private Integer count;
	/**
	 * 日期 t_chengzhan_daypk_record.f_daytime
	 *
	 */
	private Date daytime;

	/**
	 * 胜利者 t_chengzhan_daypk_record.f_winer
	 * @return  the value of t_chengzhan_daypk_record.f_winer
	 *
	 */
	public Integer getWiner() {
		return winer;
	}

	/**
	 * 胜利者 t_chengzhan_daypk_record.f_winer
	 * @param winer  the value for t_chengzhan_daypk_record.f_winer
	 *
	 */
	public void setWiner(Integer winer) {
		this.winer = winer;
	}

	/**
	 * 失败者 t_chengzhan_daypk_record.f_failer
	 * @return  the value of t_chengzhan_daypk_record.f_failer
	 *
	 */
	public Integer getFailer() {
		return failer;
	}

	/**
	 * 失败者 t_chengzhan_daypk_record.f_failer
	 * @param failer  the value for t_chengzhan_daypk_record.f_failer
	 *
	 */
	public void setFailer(Integer failer) {
		this.failer = failer;
	}

	/**
	 * 战胜次数 t_chengzhan_daypk_record.f_count
	 * @return  the value of t_chengzhan_daypk_record.f_count
	 *
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 战胜次数 t_chengzhan_daypk_record.f_count
	 * @param count  the value for t_chengzhan_daypk_record.f_count
	 *
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 日期 t_chengzhan_daypk_record.f_daytime
	 * @return  the value of t_chengzhan_daypk_record.f_daytime
	 *
	 */
	public Date getDaytime() {
		return daytime;
	}

	/**
	 * 日期 t_chengzhan_daypk_record.f_daytime
	 * @param daytime  the value for t_chengzhan_daypk_record.f_daytime
	 *
	 */
	public void setDaytime(Date daytime) {
		this.daytime = daytime;
	}
}
