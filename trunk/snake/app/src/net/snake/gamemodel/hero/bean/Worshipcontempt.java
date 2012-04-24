package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class Worshipcontempt implements IbatisEntity {
	/**
	 * t_worship_contempt.f_character_id
	 * 
	 */
	private Integer characterId;

	/**
	 * t_worship_contempt.f_worship_id
	 * 
	 */
	private String worshipId;

	/**
	 * t_worship_contempt.f_contempt_id
	 * 
	 */
	private String contemptId;

	/**
	 * t_worship_contempt.f_time
	 * 
	 */
	private String time;

	/**
	 * t_worship_contempt.f_character_id
	 * 
	 * @return the value of t_worship_contempt.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * t_worship_contempt.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_worship_contempt.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * t_worship_contempt.f_worship_id
	 * 
	 * @return the value of t_worship_contempt.f_worship_id 我崇拜的人
	 */
	public String getWorshipId() {
		return worshipId;
	}

	/**
	 * t_worship_contempt.f_worship_id
	 * 
	 * @param worshipId
	 *            the value for t_worship_contempt.f_worship_id
	 * 
	 */
	public void setWorshipId(String worshipId) {
		this.worshipId = worshipId;
	}

	/**
	 * t_worship_contempt.f_contempt_id
	 * 
	 * @return the value of t_worship_contempt.f_contempt_id 我鄙视的人
	 */
	public String getContemptId() {
		return contemptId;
	}

	/**
	 * t_worship_contempt.f_contempt_id
	 * 
	 * @param contemptId
	 *            the value for t_worship_contempt.f_contempt_id
	 * 
	 */
	public void setContemptId(String contemptId) {
		this.contemptId = contemptId;
	}

	/**
	 * t_worship_contempt.f_time
	 * 
	 * @return the value of t_worship_contempt.f_time
	 * 
	 */
	public String getTime() {
		return time;
	}

	/**
	 * t_worship_contempt.f_time
	 * 
	 * @param time
	 *            the value for t_worship_contempt.f_time
	 * 
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
