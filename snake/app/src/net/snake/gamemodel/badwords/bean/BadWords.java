package net.snake.gamemodel.badwords.bean;

import net.snake.ibatis.IbatisEntity;

public class BadWords implements IbatisEntity{

	/**
	 * t_badwords.f_id
	 * 
	 */
	private Integer id;
	/**
	 * t_badwords.f_badwords
	 * 
	 */
	private String badwords;

	/**
	 * t_badwords.f_id
	 * @return  the value of t_badwords.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_badwords.f_id
	 * @param id  the value for t_badwords.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_badwords.f_badwords
	 * @return  the value of t_badwords.f_badwords
	 * 
	 */
	public String getBadwords() {
		return badwords;
	}

	/**
	 * t_badwords.f_badwords
	 * @param badwords  the value for t_badwords.f_badwords
	 * 
	 */
	public void setBadwords(String badwords) {
		this.badwords = badwords;
	}
}
