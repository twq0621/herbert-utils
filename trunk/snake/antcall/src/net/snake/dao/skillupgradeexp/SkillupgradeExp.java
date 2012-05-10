package net.snake.dao.skillupgradeexp;

public class SkillupgradeExp {

	/**
	 * t_skillupgrade_exp.Id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer id;
	/**
	 * 锟斤拷锟斤拷ID t_skillupgrade_exp.f_skill_id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer skillId;
	/**
	 * 锟斤拷前锟饺硷拷 t_skillupgrade_exp.f_skill_grade
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer skillGrade;
	/**
	 * 锟斤拷钱锟斤拷锟�t_skillupgrade_exp.f_exp_cash
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer expCash;
	/**
	 * 锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_exp_zhengqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer expZhengqi;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷示锟斤拷为1时锟斤拷锟斤拷示锟斤拷锟戒功锟节该等硷拷锟斤拷锟斤拷瓶锟斤拷锟斤拷 t_skillupgrade_exp.f_pinjin
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer pinjin;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟狡�{锟斤拷品id,锟斤拷锟斤拷;}*) t_skillupgrade_exp.f_target_goods
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private String targetGoods;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_po_need_zhenqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer poNeedZhenqi;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟酵拷锟�t_skillupgrade_exp.f_po_need_copper
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer poNeedCopper;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷(1/10000) t_skillupgrade_exp.f_po_need_lv
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer poNeedLv;
	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟饺词憋拷锟�锟斤拷位小时) t_skillupgrade_exp.f_po_cooltime
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	private Integer poCooltime;

	/**
	 * t_skillupgrade_exp.Id
	 * @return  the value of t_skillupgrade_exp.Id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_skillupgrade_exp.Id
	 * @param id  the value for t_skillupgrade_exp.Id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 锟斤拷锟斤拷ID t_skillupgrade_exp.f_skill_id
	 * @return  the value of t_skillupgrade_exp.f_skill_id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * 锟斤拷锟斤拷ID t_skillupgrade_exp.f_skill_id
	 * @param skillId  the value for t_skillupgrade_exp.f_skill_id
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 锟斤拷前锟饺硷拷 t_skillupgrade_exp.f_skill_grade
	 * @return  the value of t_skillupgrade_exp.f_skill_grade
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getSkillGrade() {
		return skillGrade;
	}

	/**
	 * 锟斤拷前锟饺硷拷 t_skillupgrade_exp.f_skill_grade
	 * @param skillGrade  the value for t_skillupgrade_exp.f_skill_grade
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setSkillGrade(Integer skillGrade) {
		this.skillGrade = skillGrade;
	}

	/**
	 * 锟斤拷钱锟斤拷锟�t_skillupgrade_exp.f_exp_cash
	 * @return  the value of t_skillupgrade_exp.f_exp_cash
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getExpCash() {
		return expCash;
	}

	/**
	 * 锟斤拷钱锟斤拷锟�t_skillupgrade_exp.f_exp_cash
	 * @param expCash  the value for t_skillupgrade_exp.f_exp_cash
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setExpCash(Integer expCash) {
		this.expCash = expCash;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_exp_zhengqi
	 * @return  the value of t_skillupgrade_exp.f_exp_zhengqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getExpZhengqi() {
		return expZhengqi;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_exp_zhengqi
	 * @param expZhengqi  the value for t_skillupgrade_exp.f_exp_zhengqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setExpZhengqi(Integer expZhengqi) {
		this.expZhengqi = expZhengqi;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷示锟斤拷为1时锟斤拷锟斤拷示锟斤拷锟戒功锟节该等硷拷锟斤拷锟斤拷瓶锟斤拷锟斤拷 t_skillupgrade_exp.f_pinjin
	 * @return  the value of t_skillupgrade_exp.f_pinjin
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getPinjin() {
		return pinjin;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷示锟斤拷为1时锟斤拷锟斤拷示锟斤拷锟戒功锟节该等硷拷锟斤拷锟斤拷瓶锟斤拷锟斤拷 t_skillupgrade_exp.f_pinjin
	 * @param pinjin  the value for t_skillupgrade_exp.f_pinjin
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setPinjin(Integer pinjin) {
		this.pinjin = pinjin;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟狡�{锟斤拷品id,锟斤拷锟斤拷;}*) t_skillupgrade_exp.f_target_goods
	 * @return  the value of t_skillupgrade_exp.f_target_goods
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public String getTargetGoods() {
		return targetGoods;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟狡�{锟斤拷品id,锟斤拷锟斤拷;}*) t_skillupgrade_exp.f_target_goods
	 * @param targetGoods  the value for t_skillupgrade_exp.f_target_goods
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setTargetGoods(String targetGoods) {
		this.targetGoods = targetGoods;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_po_need_zhenqi
	 * @return  the value of t_skillupgrade_exp.f_po_need_zhenqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getPoNeedZhenqi() {
		return poNeedZhenqi;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�t_skillupgrade_exp.f_po_need_zhenqi
	 * @param poNeedZhenqi  the value for t_skillupgrade_exp.f_po_need_zhenqi
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setPoNeedZhenqi(Integer poNeedZhenqi) {
		this.poNeedZhenqi = poNeedZhenqi;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟酵拷锟�t_skillupgrade_exp.f_po_need_copper
	 * @return  the value of t_skillupgrade_exp.f_po_need_copper
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getPoNeedCopper() {
		return poNeedCopper;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟酵拷锟�t_skillupgrade_exp.f_po_need_copper
	 * @param poNeedCopper  the value for t_skillupgrade_exp.f_po_need_copper
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setPoNeedCopper(Integer poNeedCopper) {
		this.poNeedCopper = poNeedCopper;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷(1/10000) t_skillupgrade_exp.f_po_need_lv
	 * @return  the value of t_skillupgrade_exp.f_po_need_lv
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getPoNeedLv() {
		return poNeedLv;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷(1/10000) t_skillupgrade_exp.f_po_need_lv
	 * @param poNeedLv  the value for t_skillupgrade_exp.f_po_need_lv
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setPoNeedLv(Integer poNeedLv) {
		this.poNeedLv = poNeedLv;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟饺词憋拷锟�锟斤拷位小时) t_skillupgrade_exp.f_po_cooltime
	 * @return  the value of t_skillupgrade_exp.f_po_cooltime
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public Integer getPoCooltime() {
		return poCooltime;
	}

	/**
	 * 突锟斤拷瓶锟斤拷锟斤拷锟斤拷锟饺词憋拷锟�锟斤拷位小时) t_skillupgrade_exp.f_po_cooltime
	 * @param poCooltime  the value for t_skillupgrade_exp.f_po_cooltime
	 * @ibatorgenerated  Fri May 06 17:22:36 CST 2011
	 */
	public void setPoCooltime(Integer poCooltime) {
		this.poCooltime = poCooltime;
	}
}
