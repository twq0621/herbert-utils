package net.snake.gamemodel.skill.bow.bean;

import net.snake.ibatis.IbatisEntity;

public class BowDataEntry implements IbatisEntity{

	/**
	 * t_character_bow.f_characterid
	 * 
	 */
	private Integer characterid;
	/**
	 * t_character_bow.f_bowmodelid
	 * 
	 */
	private Integer bowmodelid;
	/**
	 * t_character_bow.f_bag1type
	 * 
	 */
	private Integer bag1type;
	/**
	 * t_character_bow.f_bag1count
	 * 
	 */
	private Integer bag1count;
	/**
	 * t_character_bow.f_bag2type
	 * 
	 */
	private Integer bag2type;
	/**
	 * t_character_bow.f_bag2count
	 * 
	 */
	private Integer bag2count;
	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * 
	 */
	private String skillitems;
	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * 
	 */
	private Integer faildcount;
	/**
	 * t_character_bow.f_bag1bind
	 * 
	 */
	private Integer bag1bind;
	/**
	 * t_character_bow.f_bag2bind
	 * 
	 */
	private Integer bag2bind;
	/**
	 * t_character_bow.f_enable
	 * 
	 */
	private Boolean enable;
	/**
	 * 运幸值 t_character_bow.f_luck
	 * 
	 */
	private Integer luck;

	/**
	 * t_character_bow.f_characterid
	 * @return  the value of t_character_bow.f_characterid
	 * 
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * t_character_bow.f_characterid
	 * @param characterid  the value for t_character_bow.f_characterid
	 * 
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * t_character_bow.f_bowmodelid
	 * @return  the value of t_character_bow.f_bowmodelid
	 * 
	 */
	public Integer getBowmodelid() {
		return bowmodelid;
	}

	/**
	 * t_character_bow.f_bowmodelid
	 * @param bowmodelid  the value for t_character_bow.f_bowmodelid
	 * 
	 */
	public void setBowmodelid(Integer bowmodelid) {
		this.bowmodelid = bowmodelid;
	}

	/**
	 * t_character_bow.f_bag1type
	 * @return  the value of t_character_bow.f_bag1type
	 * 
	 */
	public Integer getBag1type() {
		return bag1type;
	}

	/**
	 * t_character_bow.f_bag1type
	 * @param bag1type  the value for t_character_bow.f_bag1type
	 * 
	 */
	public void setBag1type(Integer bag1type) {
		this.bag1type = bag1type;
	}

	/**
	 * t_character_bow.f_bag1count
	 * @return  the value of t_character_bow.f_bag1count
	 * 
	 */
	public Integer getBag1count() {
		return bag1count;
	}

	/**
	 * t_character_bow.f_bag1count
	 * @param bag1count  the value for t_character_bow.f_bag1count
	 * 
	 */
	public void setBag1count(Integer bag1count) {
		this.bag1count = bag1count;
	}

	/**
	 * t_character_bow.f_bag2type
	 * @return  the value of t_character_bow.f_bag2type
	 * 
	 */
	public Integer getBag2type() {
		return bag2type;
	}

	/**
	 * t_character_bow.f_bag2type
	 * @param bag2type  the value for t_character_bow.f_bag2type
	 * 
	 */
	public void setBag2type(Integer bag2type) {
		this.bag2type = bag2type;
	}

	/**
	 * t_character_bow.f_bag2count
	 * @return  the value of t_character_bow.f_bag2count
	 * 
	 */
	public Integer getBag2count() {
		return bag2count;
	}

	/**
	 * t_character_bow.f_bag2count
	 * @param bag2count  the value for t_character_bow.f_bag2count
	 * 
	 */
	public void setBag2count(Integer bag2count) {
		this.bag2count = bag2count;
	}

	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * @return  the value of t_character_bow.f_skillitems
	 * 
	 */
	public String getSkillitems() {
		return skillitems;
	}

	/**
	 * 己激活的技能 t_character_bow.f_skillitems
	 * @param skillitems  the value for t_character_bow.f_skillitems
	 * 
	 */
	public void setSkillitems(String skillitems) {
		this.skillitems = skillitems;
	}

	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * @return  the value of t_character_bow.f_faildcount
	 * 
	 */
	public Integer getFaildcount() {
		return faildcount;
	}

	/**
	 * 败失次数 t_character_bow.f_faildcount
	 * @param faildcount  the value for t_character_bow.f_faildcount
	 * 
	 */
	public void setFaildcount(Integer faildcount) {
		this.faildcount = faildcount;
	}

	/**
	 * t_character_bow.f_bag1bind
	 * @return  the value of t_character_bow.f_bag1bind
	 * 
	 */
	public Integer getBag1bind() {
		return bag1bind;
	}

	/**
	 * t_character_bow.f_bag1bind
	 * @param bag1bind  the value for t_character_bow.f_bag1bind
	 * 
	 */
	public void setBag1bind(Integer bag1bind) {
		this.bag1bind = bag1bind;
	}

	/**
	 * t_character_bow.f_bag2bind
	 * @return  the value of t_character_bow.f_bag2bind
	 * 
	 */
	public Integer getBag2bind() {
		return bag2bind;
	}

	/**
	 * t_character_bow.f_bag2bind
	 * @param bag2bind  the value for t_character_bow.f_bag2bind
	 * 
	 */
	public void setBag2bind(Integer bag2bind) {
		this.bag2bind = bag2bind;
	}

	/**
	 * t_character_bow.f_enable
	 * @return  the value of t_character_bow.f_enable
	 * 
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * t_character_bow.f_enable
	 * @param enable  the value for t_character_bow.f_enable
	 * 
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * 历史运幸值 t_character_bow.f_luck
	 * @return  the value of t_character_bow.f_luck
	 * 
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * 历史运幸值 t_character_bow.f_luck
	 * @param luck  the value for t_character_bow.f_luck
	 * 
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}
}
