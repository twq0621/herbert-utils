package net.snake.gamemodel.pet.bean;

import net.snake.ibatis.IbatisEntity;

public class HorseGrade implements IbatisEntity {
	/**
	 * t_horse_grade.f_level_id
	 * 
	 * 
	 */
	private Integer levelId;

	/**
	 * t_horse_grade.f_level_experience
	 * 
	 * 
	 */
	private Integer levelExperience;

	/**
	 * column t_horse_grade.f_level_id
	 * 
	 * @return the value of t_horse_grade.f_level_id
	 * 
	 * 
	 */
	public Integer getLevelId() {
		return levelId;
	}

	/**
	 * t_horse_grade.f_level_id
	 * 
	 * @param levelId
	 *            the value for t_horse_grade.f_level_id
	 * 
	 * 
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	/**
	 * column t_horse_grade.f_level_experience
	 * 
	 * @return the value of t_horse_grade.f_level_experience
	 * 
	 * 
	 */
	public Integer getLevelExperience() {
		return levelExperience;
	}

	/**
	 * t_horse_grade.f_level_experience
	 * 
	 * @param levelExperience
	 *            the value for t_horse_grade.f_level_experience
	 * 
	 * 
	 */
	public void setLevelExperience(Integer levelExperience) {
		this.levelExperience = levelExperience;
	}
}
