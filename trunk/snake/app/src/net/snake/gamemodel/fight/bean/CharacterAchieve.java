package net.snake.gamemodel.fight.bean;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.ibatis.IbatisEntity;

public class CharacterAchieve implements IbatisEntity {

	/**
	 * t_character_achieve.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id t_character_achieve.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 角色完成成就id t_character_achieve.f_achieve_id
	 * 
	 */
	private Integer achieveId;
	/**
	 * 角色完成成就加点数 t_character_achieve.f_point
	 * 
	 */
	private Integer point;

	/**
	 * t_character_achieve.f_id
	 * 
	 * @return the value of t_character_achieve.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_achieve.f_id
	 * 
	 * @param id
	 *            the value for t_character_achieve.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_achieve.f_character_id
	 * 
	 * @return the value of t_character_achieve.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_achieve.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_achieve.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 角色完成成就id t_character_achieve.f_achieve_id
	 * 
	 * @return the value of t_character_achieve.f_achieve_id
	 * 
	 */
	public Integer getAchieveId() {
		return achieveId;
	}

	/**
	 * 角色完成成就id t_character_achieve.f_achieve_id
	 * 
	 * @param achieveId
	 *            the value for t_character_achieve.f_achieve_id
	 * 
	 */
	public void setAchieveId(Integer achieveId) {
		this.achieveId = achieveId;
	}

	/**
	 * 角色完成成就加点数 t_character_achieve.f_point
	 * 
	 * @return the value of t_character_achieve.f_point
	 * 
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * 角色完成成就加点数 t_character_achieve.f_point
	 * 
	 * @param point
	 *            the value for t_character_achieve.f_point
	 * 
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	private Achieve achieve;

	public Achieve getAchieve() {
		if (achieve == null) {
			achieve = AchieveManager.getInstance().getAchieveById(achieveId);
		}
		return achieve;
	}

	public String getTitle() {
		Achieve achieve = getAchieve();
		if (achieve == null) {
			return null;
		}
		return achieve.getTitleI18n();
	}
}
