package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterSkillData implements IbatisEntity {

	/**
	 * t_character_skill.f_id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 人物id t_character_skill.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 技能id t_character_skill.f_skill_id
	 * 
	 * 
	 */
	private Integer skillId;
	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * 
	 * 
	 */
	private Integer grade;
	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * 
	 * 
	 */
	private Short position;
	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * 
	 * 
	 */
	private Integer quickbarindex;
	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * 
	 * 
	 */
	private Long startcd;
	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * 
	 * 
	 */
	private Integer mastery;
	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * 
	 * 
	 */
	private Integer maxMastery;
	/**
	 * 是否已突破瓶颈 t_character_skill.f_po
	 * 
	 * 
	 */
	private Boolean po;

	/** 1马的技能 2角色的技能 */
	private int skilltype;

	/**
	 * t_character_skill.f_id
	 * 
	 * @return the value of t_character_skill.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_skill.f_id
	 * 
	 * @param id
	 *            the value for t_character_skill.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 人物id t_character_skill.f_character_id
	 * 
	 * @return the value of t_character_skill.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物id t_character_skill.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_skill.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 技能id t_character_skill.f_skill_id
	 * 
	 * @return the value of t_character_skill.f_skill_id
	 * 
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * 技能id t_character_skill.f_skill_id
	 * 
	 * @param skillId
	 *            the value for t_character_skill.f_skill_id
	 * 
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * 
	 * @return the value of t_character_skill.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * 
	 * @param grade
	 *            the value for t_character_skill.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * 
	 * @return the value of t_character_skill.f_position
	 * 
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * 
	 * @param position
	 *            the value for t_character_skill.f_position
	 * 
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * 
	 * @return the value of t_character_skill.f_quickbarindex
	 * 
	 */
	public Integer getQuickbarindex() {
		return quickbarindex;
	}

	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * 
	 * @param quickbarindex
	 *            the value for t_character_skill.f_quickbarindex
	 * 
	 */
	public void setQuickbarindex(Integer quickbarindex) {
		this.quickbarindex = quickbarindex;
	}

	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * 
	 * @return the value of t_character_skill.f_startCD
	 * 
	 */
	public Long getStartcd() {
		return startcd;
	}

	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * 
	 * @param startcd
	 *            the value for t_character_skill.f_startCD
	 * 
	 */
	public void setStartcd(Long startcd) {
		this.startcd = startcd;
	}

	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * 
	 * @return the value of t_character_skill.f_mastery
	 * 
	 */
	public Integer getMastery() {
		return mastery;
	}

	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * 
	 * @param mastery
	 *            the value for t_character_skill.f_mastery
	 * 
	 */
	public void setMastery(Integer mastery) {
		this.mastery = mastery;
	}

	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * 
	 * @return the value of t_character_skill.f_max_mastery
	 * 
	 */
	public Integer getMaxMastery() {
		return maxMastery;
	}

	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * 
	 * @param maxMastery
	 *            the value for t_character_skill.f_max_mastery
	 * 
	 */
	public void setMaxMastery(Integer maxMastery) {
		this.maxMastery = maxMastery;
	}

	/**
	 * 是否已突破瓶颈 t_character_skill.f_po
	 * 
	 * @return the value of t_character_skill.f_po
	 * 
	 */
	public Boolean getPo() {
		return po;
	}

	/**
	 * 是否已突破瓶颈 t_character_skill.f_po
	 * 
	 * @param po
	 *            the value for t_character_skill.f_po
	 * 
	 */
	public void setPo(Boolean po) {
		this.po = po;
	}

	/**
	 * 1马的技能 2角色的技能
	 * @return
	 */
	public int getSkilltype() {
		return skilltype;
	}

	/**
	 * 1马的技能 2角色的技能
	 * @param skilltype
	 */
	public void setSkilltype(int skilltype) {
		this.skilltype = skilltype;
	}
	
}
