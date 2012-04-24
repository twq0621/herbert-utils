package net.snake.gamemodel.team.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterTeamfighting implements IbatisEntity {

	/**
	 *  t_character_teamfighting.id
	 * 
	 */
	private Integer id;
	/**
	 *  t_character_teamfighting.character_id
	 * 
	 */
	private Integer characterId;
	/**
	 *  t_character_teamfighting.teamfighting_id
	 * 
	 */
	private Integer teamfightingId;
	/**
	 *  t_character_teamfighting.learn_time
	 * 
	 */
	private Date learnTime;

	/**
	 *  t_character_teamfighting.id
	 * @return  the value of t_character_teamfighting.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 *  t_character_teamfighting.id
	 * @param id  the value for t_character_teamfighting.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *  t_character_teamfighting.character_id
	 * @return  the value of t_character_teamfighting.character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 *  t_character_teamfighting.character_id
	 * @param characterId  the value for t_character_teamfighting.character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 *  t_character_teamfighting.teamfighting_id
	 * @return  the value of t_character_teamfighting.teamfighting_id
	 * 
	 */
	public Integer getTeamfightingId() {
		return teamfightingId;
	}

	/**
	 *  t_character_teamfighting.teamfighting_id
	 * @param teamfightingId  the value for t_character_teamfighting.teamfighting_id
	 * 
	 */
	public void setTeamfightingId(Integer teamfightingId) {
		this.teamfightingId = teamfightingId;
	}

	/**
	 *  t_character_teamfighting.learn_time
	 * @return  the value of t_character_teamfighting.learn_time
	 * 
	 */
	public Date getLearnTime() {
		return learnTime;
	}

	/**
	 *  t_character_teamfighting.learn_time
	 * @param learnTime  the value for t_character_teamfighting.learn_time
	 * 
	 */
	public void setLearnTime(Date learnTime) {
		this.learnTime = learnTime;
	}
}
