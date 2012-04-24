package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

public class SkillUpgradeExp implements IbatisEntity {

	/**
	 * t_skillupgrade_exp.Id
	 */
	private Integer id;
	/**
	 * 技能ID t_skillupgrade_exp.f_skill_id
	 */
	private Integer skillId;
	/**
	 * 当前等级 t_skillupgrade_exp.f_skill_grade
	 */
	private Integer skillGrade;
	/**
	 * 金钱消耗 t_skillupgrade_exp.f_exp_cash
	 */
	private Integer expCash;
	/**
	 * 真气消耗 t_skillupgrade_exp.f_exp_zhengqi
	 */
	private Integer expZhengqi;
	/**
	 * 突破瓶颈标示（为1时，表示该武功在该等级遇到瓶颈） t_skillupgrade_exp.f_pinjin
	 */
	private Integer pinjin;
	/**
	 * 突破瓶颈所需的物品({物品id,数量;}*) t_skillupgrade_exp.f_target_goods
	 */
	private String targetGoods;
	/**
	 * 突破瓶颈所需的真气 t_skillupgrade_exp.f_po_need_zhenqi
	 */
	private Integer poNeedZhenqi;
	/**
	 * 突破瓶颈所需的铜币 t_skillupgrade_exp.f_po_need_copper
	 */
	private Integer poNeedCopper;
	/**
	 * 突破瓶颈几率(1/10000) t_skillupgrade_exp.f_po_need_lv
	 */
	private Integer poNeedLv;
	/**
	 * 突破瓶颈后的冷却时间(单位小时) t_skillupgrade_exp.f_po_cooltime
	 */
	private Integer poCooltime;
	/**
	 * 技能升级等级限制 t_skillupgrade_exp.f_limit_grade
	 */
	private Integer limitGrade;
	/**
	 * t_skillupgrade_exp.Id
	 * 
	 * @return the value of t_skillupgrade_exp.Id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_skillupgrade_exp.Id
	 * 
	 * @param id
	 *            the value for t_skillupgrade_exp.Id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 技能ID t_skillupgrade_exp.f_skill_id
	 * 
	 * @return the value of t_skillupgrade_exp.f_skill_id
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * 技能ID t_skillupgrade_exp.f_skill_id
	 * 
	 * @param skillId
	 *            the value for t_skillupgrade_exp.f_skill_id
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 当前等级 t_skillupgrade_exp.f_skill_grade
	 * 
	 * @return the value of t_skillupgrade_exp.f_skill_grade
	 */
	public Integer getSkillGrade() {
		return skillGrade;
	}

	/**
	 * 当前等级 t_skillupgrade_exp.f_skill_grade
	 * 
	 * @param skillGrade
	 *            the value for t_skillupgrade_exp.f_skill_grade
	 */
	public void setSkillGrade(Integer skillGrade) {
		this.skillGrade = skillGrade;
	}

	/**
	 * 金钱消耗 t_skillupgrade_exp.f_exp_cash
	 * 
	 * @return the value of t_skillupgrade_exp.f_exp_cash
	 */
	public Integer getExpCash() {
		return expCash;
	}

	/**
	 * 金钱消耗 t_skillupgrade_exp.f_exp_cash
	 * 
	 * @param expCash
	 *            the value for t_skillupgrade_exp.f_exp_cash
	 */
	public void setExpCash(Integer expCash) {
		this.expCash = expCash;
	}

	/**
	 * 真气消耗 t_skillupgrade_exp.f_exp_zhengqi
	 * 
	 * @return the value of t_skillupgrade_exp.f_exp_zhengqi
	 */
	public Integer getExpZhengqi() {
		return expZhengqi;
	}

	/**
	 * 真气消耗 t_skillupgrade_exp.f_exp_zhengqi
	 * 
	 * @param expZhengqi
	 *            the value for t_skillupgrade_exp.f_exp_zhengqi
	 */
	public void setExpZhengqi(Integer expZhengqi) {
		this.expZhengqi = expZhengqi;
	}

	/**
	 * 突破瓶颈标示（为1时，表示该武功在该等级遇到瓶颈） t_skillupgrade_exp.f_pinjin
	 * 
	 * @return the value of t_skillupgrade_exp.f_pinjin
	 */
	public Integer getPinjin() {
		return pinjin;
	}

	/**
	 * 突破瓶颈标示（为1时，表示该武功在该等级遇到瓶颈） t_skillupgrade_exp.f_pinjin
	 * 
	 * @param pinjin
	 *            the value for t_skillupgrade_exp.f_pinjin
	 */
	public void setPinjin(Integer pinjin) {
		this.pinjin = pinjin;
	}

	/**
	 * 突破瓶颈所需的物品({物品id,数量;}*) t_skillupgrade_exp.f_target_goods
	 * 
	 * @return the value of t_skillupgrade_exp.f_target_goods
	 */
	public String getTargetGoods() {
		return targetGoods;
	}

	/**
	 * 突破瓶颈所需的物品({物品id,数量;}*) t_skillupgrade_exp.f_target_goods
	 * 
	 * @param targetGoods
	 *            the value for t_skillupgrade_exp.f_target_goods
	 */
	public void setTargetGoods(String targetGoods) {
		this.targetGoods = targetGoods;
	}

	/**
	 * 突破瓶颈所需的真气 t_skillupgrade_exp.f_po_need_zhenqi
	 * 
	 * @return the value of t_skillupgrade_exp.f_po_need_zhenqi
	 */
	public Integer getPoNeedZhenqi() {
		return poNeedZhenqi;
	}

	/**
	 * 突破瓶颈所需的真气 t_skillupgrade_exp.f_po_need_zhenqi
	 * 
	 * @param poNeedZhenqi
	 *            the value for t_skillupgrade_exp.f_po_need_zhenqi
	 */
	public void setPoNeedZhenqi(Integer poNeedZhenqi) {
		this.poNeedZhenqi = poNeedZhenqi;
	}

	/**
	 * 突破瓶颈所需的铜币 t_skillupgrade_exp.f_po_need_copper
	 * 
	 * @return the value of t_skillupgrade_exp.f_po_need_copper
	 */
	public Integer getPoNeedCopper() {
		return poNeedCopper;
	}

	/**
	 * 突破瓶颈所需的铜币 t_skillupgrade_exp.f_po_need_copper
	 * 
	 * @param poNeedCopper
	 *            the value for t_skillupgrade_exp.f_po_need_copper
	 */
	public void setPoNeedCopper(Integer poNeedCopper) {
		this.poNeedCopper = poNeedCopper;
	}

	/**
	 * 突破瓶颈几率(1/10000) t_skillupgrade_exp.f_po_need_lv
	 * 
	 * @return the value of t_skillupgrade_exp.f_po_need_lv
	 */
	public Integer getPoNeedLv() {
		return poNeedLv;
	}

	/**
	 * 突破瓶颈几率(1/10000) t_skillupgrade_exp.f_po_need_lv
	 * 
	 * @param poNeedLv
	 *            the value for t_skillupgrade_exp.f_po_need_lv
	 */
	public void setPoNeedLv(Integer poNeedLv) {
		this.poNeedLv = poNeedLv;
	}

	/**
	 * 突破瓶颈后的冷却时间(单位小时) t_skillupgrade_exp.f_po_cooltime
	 * 
	 * @return the value of t_skillupgrade_exp.f_po_cooltime
	 */
	public Integer getPoCooltime() {
		return poCooltime;
	}

	/**
	 * 突破瓶颈后的冷却时间(单位小时) t_skillupgrade_exp.f_po_cooltime
	 * 
	 * @param poCooltime
	 *            the value for t_skillupgrade_exp.f_po_cooltime
	 */
	public void setPoCooltime(Integer poCooltime) {
		this.poCooltime = poCooltime;
	}

	/**
	 * 技能升级等级限制
	 * @return
	 */
	public Integer getLimitGrade() {
		return limitGrade;
	}

	public void setLimitGrade(Integer limitGrade) {
		this.limitGrade = limitGrade;
	}
	
}
