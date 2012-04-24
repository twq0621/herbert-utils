package net.snake.gamemodel.heroext.dantian.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterDanTian implements IbatisEntity{

	/**
	 * 角色id t_character_dantian.f_characterid
	 * 
	 */
	private Integer characterid;
	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * 
	 */
	private Integer dantianid;
	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * 
	 */
	private String skillitem;
	/**
	 * 幸运值 t_character_dantian.f_luck
	 * 
	 */
	private Integer luck;
	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * 
	 */
	private Integer faildcount;
	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * 
	 */
	private Date luckLastclear;
	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * 
	 */
	private Integer beforeLuck;

	/**
	 * 角色id t_character_dantian.f_characterid
	 * @return  the value of t_character_dantian.f_characterid
	 * 
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * 角色id t_character_dantian.f_characterid
	 * @param characterid  the value for t_character_dantian.f_characterid
	 * 
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * @return  the value of t_character_dantian.f_dantianid
	 * 
	 */
	public Integer getDantianid() {
		return dantianid;
	}

	/**
	 * 丹田ID t_character_dantian.f_dantianid
	 * @param dantianid  the value for t_character_dantian.f_dantianid
	 * 
	 */
	public void setDantianid(Integer dantianid) {
		this.dantianid = dantianid;
	}

	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * @return  the value of t_character_dantian.f_skillitem
	 * 
	 */
	public String getSkillitem() {
		return skillitem;
	}

	/**
	 * 学会的技能 t_character_dantian.f_skillitem
	 * @param skillitem  the value for t_character_dantian.f_skillitem
	 * 
	 */
	public void setSkillitem(String skillitem) {
		this.skillitem = skillitem;
	}

	/**
	 * 幸运值 t_character_dantian.f_luck
	 * @return  the value of t_character_dantian.f_luck
	 * 
	 */
	public Integer getLuck() {
		return luck;
	}

	/**
	 * 幸运值 t_character_dantian.f_luck
	 * @param luck  the value for t_character_dantian.f_luck
	 * 
	 */
	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * @return  the value of t_character_dantian.f_faildcount
	 * 
	 */
	public Integer getFaildcount() {
		return faildcount;
	}

	/**
	 * 失败次数 t_character_dantian.f_faildcount
	 * @param faildcount  the value for t_character_dantian.f_faildcount
	 * 
	 */
	public void setFaildcount(Integer faildcount) {
		this.faildcount = faildcount;
	}

	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * @return  the value of t_character_dantian.f_luck_lastclear
	 * 
	 */
	public Date getLuckLastclear() {
		return luckLastclear;
	}

	/**
	 * 上次清零时间 t_character_dantian.f_luck_lastclear
	 * @param luckLastclear  the value for t_character_dantian.f_luck_lastclear
	 * 
	 */
	public void setLuckLastclear(Date luckLastclear) {
		this.luckLastclear = luckLastclear;
	}

	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * @return  the value of t_character_dantian.f_before_luck
	 * 
	 */
	public Integer getBeforeLuck() {
		return beforeLuck;
	}

	/**
	 * 清零前祝福值 t_character_dantian.f_before_luck
	 * @param beforeLuck  the value for t_character_dantian.f_before_luck
	 * 
	 */
	public void setBeforeLuck(Integer beforeLuck) {
		this.beforeLuck = beforeLuck;
	}
}
