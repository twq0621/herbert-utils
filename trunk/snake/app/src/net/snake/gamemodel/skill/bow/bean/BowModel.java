package net.snake.gamemodel.skill.bow.bean;

import net.snake.ibatis.IbatisEntity;

public class BowModel implements IbatisEntity {

	/**
	 * t_bowmodel.f_id
	 * 
	 */
	private Integer id;
	/**
	 * t_bowmodel.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 箭弓名称 t_bowmodel.f_name
	 * 
	 */
	private String name;
	/**
	 * 阶数 t_bowmodel.f_level
	 * 
	 */
	private Integer level;
	/**
	 * 来历描述 t_bowmodel.f_desc
	 * 
	 */
	private String desc;
	/**
	 * t_bowmodel.f_desc_i18n
	 * 
	 */
	private String descI18n;
	/**
	 * 特效ID t_bowmodel.f_effect_id
	 * 
	 */
	private Integer effectId;
	/**
	 * 增加攻击 t_bowmodel.f_addattack
	 * 
	 */
	private Integer addattack;
	/**
	 * 增加防御 t_bowmodel.f_adddefence
	 * 
	 */
	private Integer adddefence;
	/**
	 * 增加暴击 t_bowmodel.f_addcrt
	 * 
	 */
	private Integer addcrt;
	/**
	 * 加增闪避 t_bowmodel.f_adddodge
	 * 
	 */
	private Integer adddodge;
	/**
	 * 增加生命上限 t_bowmodel.f_addmaxhp
	 * 
	 */
	private Integer addmaxhp;
	/**
	 * 加增内力上限 t_bowmodel.f_addmaxmp
	 * 
	 */
	private Integer addmaxmp;
	/**
	 * 增加体力上限 t_bowmodel.f_addmaxsp
	 * 
	 */
	private Integer addmaxsp;
	/**
	 * 加增攻击速度 t_bowmodel.f_addattackspeed
	 * 
	 */
	private Integer addattackspeed;
	/**
	 * 增加移动速度 t_bowmodel.f_addmovespeed
	 * 
	 */
	private Integer addmovespeed;
	/**
	 * 攻击增强比例 t_bowmodel.f_ratio_addattack
	 * 
	 */
	private Integer ratioAddattack;
	/**
	 * 视忽防御比例 t_bowmodel.f_ratio_ignoredefence
	 * 
	 */
	private Integer ratioIgnoredefence;
	/**
	 * 定绑的特殊技能ID ；分隔 t_bowmodel.f_bindskill
	 * 
	 */
	private String bindskill;
	/**
	 * 升阶消耗物品ID t_bowmodel.f_upconsume_goodid
	 * 
	 */
	private Integer upconsumeGoodid;
	/**
	 * 升阶消耗物品数量 t_bowmodel.f_upconsume_goodcount
	 * 
	 */
	private Integer upconsumeGoodcount;
	/**
	 * 升阶消耗铜币 t_bowmodel.f_upconsume_copper
	 * 
	 */
	private Integer upconsumeCopper;
	/**
	 * 升阶成功几率 t_bowmodel.f_up_probability
	 * 
	 */
	private Integer upProbability;
	/**
	 * 显示成功几率 t_bowmodel.f_up_probability_show
	 * 
	 */
	private Integer upProbabilityShow;
	/**
	 * 升阶合成需最小成功次数 t_bowmodel.f_up_needmincount
	 * 
	 */
	private Integer upNeedmincount;
	/**
	 * 升阶合成需最大成功次数 t_bowmodel.f_up_needmaxcount
	 * 
	 */
	private Integer upNeedmaxcount;
	/**
	 * 对应的物品模型 t_bowmodel.f_goodmodel_id
	 * 
	 */
	private Integer goodmodelId;

	/**
	 * t_bowmodel.f_id
	 * 
	 * @return the value of t_bowmodel.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_bowmodel.f_id
	 * 
	 * @param id
	 *            the value for t_bowmodel.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_bowmodel.f_name_i18n
	 * 
	 * @return the value of t_bowmodel.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * t_bowmodel.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_bowmodel.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 箭弓名称 t_bowmodel.f_name
	 * 
	 * @return the value of t_bowmodel.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 箭弓名称 t_bowmodel.f_name
	 * 
	 * @param name
	 *            the value for t_bowmodel.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 阶数 t_bowmodel.f_level
	 * 
	 * @return the value of t_bowmodel.f_level
	 * 
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 阶数 t_bowmodel.f_level
	 * 
	 * @param level
	 *            the value for t_bowmodel.f_level
	 * 
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 来历描述 t_bowmodel.f_desc
	 * 
	 * @return the value of t_bowmodel.f_desc
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 来历描述 t_bowmodel.f_desc
	 * 
	 * @param desc
	 *            the value for t_bowmodel.f_desc
	 * 
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * t_bowmodel.f_desc_i18n
	 * 
	 * @return the value of t_bowmodel.f_desc_i18n
	 * 
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * t_bowmodel.f_desc_i18n
	 * 
	 * @param descI18n
	 *            the value for t_bowmodel.f_desc_i18n
	 * 
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 特效ID t_bowmodel.f_effect_id
	 * 
	 * @return the value of t_bowmodel.f_effect_id
	 * 
	 */
	public Integer getEffectId() {
		return effectId;
	}

	/**
	 * 特效ID t_bowmodel.f_effect_id
	 * 
	 * @param effectId
	 *            the value for t_bowmodel.f_effect_id
	 * 
	 */
	public void setEffectId(Integer effectId) {
		this.effectId = effectId;
	}

	/**
	 * 增加攻击 t_bowmodel.f_addattack
	 * 
	 * @return the value of t_bowmodel.f_addattack
	 * 
	 */
	public Integer getAddattack() {
		return addattack;
	}

	/**
	 * 增加攻击 t_bowmodel.f_addattack
	 * 
	 * @param addattack
	 *            the value for t_bowmodel.f_addattack
	 * 
	 */
	public void setAddattack(Integer addattack) {
		this.addattack = addattack;
	}

	/**
	 * 增加防御 t_bowmodel.f_adddefence
	 * 
	 * @return the value of t_bowmodel.f_adddefence
	 * 
	 */
	public Integer getAdddefence() {
		return adddefence;
	}

	/**
	 * 增加防御 t_bowmodel.f_adddefence
	 * 
	 * @param adddefence
	 *            the value for t_bowmodel.f_adddefence
	 * 
	 */
	public void setAdddefence(Integer adddefence) {
		this.adddefence = adddefence;
	}

	/**
	 * 增加暴击 t_bowmodel.f_addcrt
	 * 
	 * @return the value of t_bowmodel.f_addcrt
	 * 
	 */
	public Integer getAddcrt() {
		return addcrt;
	}

	/**
	 * 增加暴击 t_bowmodel.f_addcrt
	 * 
	 * @param addcrt
	 *            the value for t_bowmodel.f_addcrt
	 * 
	 */
	public void setAddcrt(Integer addcrt) {
		this.addcrt = addcrt;
	}

	/**
	 * 加增闪避 t_bowmodel.f_adddodge
	 * 
	 * @return the value of t_bowmodel.f_adddodge
	 * 
	 */
	public Integer getAdddodge() {
		return adddodge;
	}

	/**
	 * 加增闪避 t_bowmodel.f_adddodge
	 * 
	 * @param adddodge
	 *            the value for t_bowmodel.f_adddodge
	 * 
	 */
	public void setAdddodge(Integer adddodge) {
		this.adddodge = adddodge;
	}

	/**
	 * 增加生命上限 t_bowmodel.f_addmaxhp
	 * 
	 * @return the value of t_bowmodel.f_addmaxhp
	 * 
	 */
	public Integer getAddmaxhp() {
		return addmaxhp;
	}

	/**
	 * 增加生命上限 t_bowmodel.f_addmaxhp
	 * 
	 * @param addmaxhp
	 *            the value for t_bowmodel.f_addmaxhp
	 * 
	 */
	public void setAddmaxhp(Integer addmaxhp) {
		this.addmaxhp = addmaxhp;
	}

	/**
	 * 加增内力上限 t_bowmodel.f_addmaxmp
	 * 
	 * @return the value of t_bowmodel.f_addmaxmp
	 * 
	 */
	public Integer getAddmaxmp() {
		return addmaxmp;
	}

	/**
	 * 加增内力上限 t_bowmodel.f_addmaxmp
	 * 
	 * @param addmaxmp
	 *            the value for t_bowmodel.f_addmaxmp
	 * 
	 */
	public void setAddmaxmp(Integer addmaxmp) {
		this.addmaxmp = addmaxmp;
	}

	/**
	 * 增加体力上限 t_bowmodel.f_addmaxsp
	 * 
	 * @return the value of t_bowmodel.f_addmaxsp
	 * 
	 */
	public Integer getAddmaxsp() {
		return addmaxsp;
	}

	/**
	 * 增加体力上限 t_bowmodel.f_addmaxsp
	 * 
	 * @param addmaxsp
	 *            the value for t_bowmodel.f_addmaxsp
	 * 
	 */
	public void setAddmaxsp(Integer addmaxsp) {
		this.addmaxsp = addmaxsp;
	}

	/**
	 * 加增攻击速度 t_bowmodel.f_addattackspeed
	 * 
	 * @return the value of t_bowmodel.f_addattackspeed
	 * 
	 */
	public Integer getAddattackspeed() {
		return addattackspeed;
	}

	/**
	 * 加增攻击速度 t_bowmodel.f_addattackspeed
	 * 
	 * @param addattackspeed
	 *            the value for t_bowmodel.f_addattackspeed
	 * 
	 */
	public void setAddattackspeed(Integer addattackspeed) {
		this.addattackspeed = addattackspeed;
	}

	/**
	 * 增加移动速度 t_bowmodel.f_addmovespeed
	 * 
	 * @return the value of t_bowmodel.f_addmovespeed
	 * 
	 */
	public Integer getAddmovespeed() {
		return addmovespeed;
	}

	/**
	 * 增加移动速度 t_bowmodel.f_addmovespeed
	 * 
	 * @param addmovespeed
	 *            the value for t_bowmodel.f_addmovespeed
	 * 
	 */
	public void setAddmovespeed(Integer addmovespeed) {
		this.addmovespeed = addmovespeed;
	}

	/**
	 * 攻击增强比例 t_bowmodel.f_ratio_addattack
	 * 
	 * @return the value of t_bowmodel.f_ratio_addattack
	 * 
	 */
	public Integer getRatioAddattack() {
		return ratioAddattack;
	}

	/**
	 * 攻击增强比例 t_bowmodel.f_ratio_addattack
	 * 
	 * @param ratioAddattack
	 *            the value for t_bowmodel.f_ratio_addattack
	 * 
	 */
	public void setRatioAddattack(Integer ratioAddattack) {
		this.ratioAddattack = ratioAddattack;
	}

	/**
	 * 视忽防御比例 t_bowmodel.f_ratio_ignoredefence
	 * 
	 * @return the value of t_bowmodel.f_ratio_ignoredefence
	 * 
	 */
	public Integer getRatioIgnoredefence() {
		return ratioIgnoredefence;
	}

	/**
	 * 视忽防御比例 t_bowmodel.f_ratio_ignoredefence
	 * 
	 * @param ratioIgnoredefence
	 *            the value for t_bowmodel.f_ratio_ignoredefence
	 * 
	 */
	public void setRatioIgnoredefence(Integer ratioIgnoredefence) {
		this.ratioIgnoredefence = ratioIgnoredefence;
	}

	/**
	 * 定绑的特殊技能ID ；分隔 t_bowmodel.f_bindskill
	 * 
	 * @return the value of t_bowmodel.f_bindskill
	 * 
	 */
	public String getBindskill() {
		return bindskill;
	}

	/**
	 * 定绑的特殊技能ID ；分隔 t_bowmodel.f_bindskill
	 * 
	 * @param bindskill
	 *            the value for t_bowmodel.f_bindskill
	 * 
	 */
	public void setBindskill(String bindskill) {
		this.bindskill = bindskill;
	}

	/**
	 * 升阶消耗物品ID t_bowmodel.f_upconsume_goodid
	 * 
	 * @return the value of t_bowmodel.f_upconsume_goodid
	 * 
	 */
	public Integer getUpconsumeGoodid() {
		return upconsumeGoodid;
	}

	/**
	 * 升阶消耗物品ID t_bowmodel.f_upconsume_goodid
	 * 
	 * @param upconsumeGoodid
	 *            the value for t_bowmodel.f_upconsume_goodid
	 * 
	 */
	public void setUpconsumeGoodid(Integer upconsumeGoodid) {
		this.upconsumeGoodid = upconsumeGoodid;
	}

	/**
	 * 升阶消耗物品数量 t_bowmodel.f_upconsume_goodcount
	 * 
	 * @return the value of t_bowmodel.f_upconsume_goodcount
	 * 
	 */
	public Integer getUpconsumeGoodcount() {
		return upconsumeGoodcount;
	}

	/**
	 * 升阶消耗物品数量 t_bowmodel.f_upconsume_goodcount
	 * 
	 * @param upconsumeGoodcount
	 *            the value for t_bowmodel.f_upconsume_goodcount
	 * 
	 */
	public void setUpconsumeGoodcount(Integer upconsumeGoodcount) {
		this.upconsumeGoodcount = upconsumeGoodcount;
	}

	/**
	 * 升阶消耗铜币 t_bowmodel.f_upconsume_copper
	 * 
	 * @return the value of t_bowmodel.f_upconsume_copper
	 * 
	 */
	public Integer getUpconsumeCopper() {
		return upconsumeCopper;
	}

	/**
	 * 升阶消耗铜币 t_bowmodel.f_upconsume_copper
	 * 
	 * @param upconsumeCopper
	 *            the value for t_bowmodel.f_upconsume_copper
	 * 
	 */
	public void setUpconsumeCopper(Integer upconsumeCopper) {
		this.upconsumeCopper = upconsumeCopper;
	}

	/**
	 * 升阶成功几率 t_bowmodel.f_up_probability
	 * 
	 * @return the value of t_bowmodel.f_up_probability
	 * 
	 */
	public Integer getUpProbability() {
		return upProbability;
	}

	/**
	 * 升阶成功几率 t_bowmodel.f_up_probability
	 * 
	 * @param upProbability
	 *            the value for t_bowmodel.f_up_probability
	 * 
	 */
	public void setUpProbability(Integer upProbability) {
		this.upProbability = upProbability;
	}

	/**
	 * 显示成功几率 t_bowmodel.f_up_probability_show
	 * 
	 * @return the value of t_bowmodel.f_up_probability_show
	 * 
	 */
	public Integer getUpProbabilityShow() {
		return upProbabilityShow;
	}

	/**
	 * 显示成功几率 t_bowmodel.f_up_probability_show
	 * 
	 * @param upProbabilityShow
	 *            the value for t_bowmodel.f_up_probability_show
	 * 
	 */
	public void setUpProbabilityShow(Integer upProbabilityShow) {
		this.upProbabilityShow = upProbabilityShow;
	}

	/**
	 * 升阶合成需最小成功次数 t_bowmodel.f_up_needmincount
	 * 
	 * @return the value of t_bowmodel.f_up_needmincount
	 * 
	 */
	public Integer getUpNeedmincount() {
		return upNeedmincount;
	}

	/**
	 * 升阶合成需最小成功次数 t_bowmodel.f_up_needmincount
	 * 
	 * @param upNeedmincount
	 *            the value for t_bowmodel.f_up_needmincount
	 * 
	 */
	public void setUpNeedmincount(Integer upNeedmincount) {
		this.upNeedmincount = upNeedmincount;
	}

	/**
	 * 升阶合成需最大成功次数 t_bowmodel.f_up_needmaxcount
	 * 
	 * @return the value of t_bowmodel.f_up_needmaxcount
	 * 
	 */
	public Integer getUpNeedmaxcount() {
		return upNeedmaxcount;
	}

	/**
	 * 升阶合成需最大成功次数 t_bowmodel.f_up_needmaxcount
	 * 
	 * @param upNeedmaxcount
	 *            the value for t_bowmodel.f_up_needmaxcount
	 * 
	 */
	public void setUpNeedmaxcount(Integer upNeedmaxcount) {
		this.upNeedmaxcount = upNeedmaxcount;
	}

	/**
	 * 对应的物品模型 t_bowmodel.f_goodmodel_id
	 * 
	 * @return the value of t_bowmodel.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 对应的物品模型 t_bowmodel.f_goodmodel_id
	 * 
	 * @param goodmodelId
	 *            the value for t_bowmodel.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}
}
