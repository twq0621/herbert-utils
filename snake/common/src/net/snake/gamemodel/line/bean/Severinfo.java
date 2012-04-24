package net.snake.gamemodel.line.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class Severinfo implements IbatisEntity {

	/**
	 * t_server_info.f_id
	 */
	private Integer id;
	/**
	 * 开服时间 t_server_info.f_kaifu_time
	 */
	private Date kaifuTime;

	/**
	 * t_server_info.f_id
	 * 
	 * @return the value of t_server_info.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_server_info.f_id
	 * 
	 * @param id
	 *            the value for t_server_info.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 开服时间 t_server_info.f_kaifu_time
	 * 
	 * @return the value of t_server_info.f_kaifu_time
	 */
	public Date getKaifuTime() {
		return kaifuTime;
	}

	/**
	 * 开服时间 t_server_info.f_kaifu_time
	 * 
	 * @param kaifuTime
	 *            the value for t_server_info.f_kaifu_time
	 */
	public void setKaifuTime(Date kaifuTime) {
		this.kaifuTime = kaifuTime;
	}
}
