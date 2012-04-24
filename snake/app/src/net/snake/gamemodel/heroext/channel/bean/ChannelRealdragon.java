package net.snake.gamemodel.heroext.channel.bean;

import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.ibatis.IbatisEntity;


public class ChannelRealdragon implements PropertyChangeListener,IbatisEntity{

	/**
	 * 经脉编号_真龙 t_channel_realdragon.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 经脉名称_真龙 t_channel_realdragon.f_name
	 * 
	 */
	private String name;
	/**
	 * 血_真龙 t_channel_realdragon.f_hp_add
	 * 
	 */
	private Integer hpAdd;
	/**
	 * 内力_真龙 t_channel_realdragon.f_mp_add
	 * 
	 */
	private Integer mpAdd;
	/**
	 * 体力_真龙 t_channel_realdragon.f_sp_add
	 * 
	 */
	private Integer spAdd;
	/**
	 * 攻_真龙 t_channel_realdragon.f_attack_add
	 * 
	 */
	private Integer attackAdd;
	/**
	 * 防_真龙 t_channel_realdragon.f_defence_add
	 * 
	 */
	private Integer defenceAdd;
	/**
	 * 闪避_真龙 t_channel_realdragon.f_dodge_add
	 * 
	 */
	private Integer dodgeAdd;
	/**
	 * 暴击_真龙 t_channel_realdragon.f_crt_add
	 * 
	 */
	private Integer crtAdd;
	/**
	 * 需要真气量_真龙 t_channel_realdragon.f_need_zhenqi
	 * 
	 */
	private Integer needZhenqi;
	/**
	 * 冲击几率万分之几_真龙 t_channel_realdragon.f_odds
	 * 
	 */
	private Integer odds;
	/**
	 * 武功id t_channel_realdragon.f_skill_id
	 * 
	 */
	private Integer skillId;
	/**
	 * 加成数量 t_channel_realdragon.f_skill_addcount
	 * 
	 */
	private Integer skillAddcount;
	/**
	 * 最小 t_channel_realdragon.f_zhenlong_min
	 * 
	 */
	private Integer zhenlongMin;
	/**
	 * 最大 t_channel_realdragon.f_zhenlong_max
	 * 
	 */
	private Integer zhenlongMax;
	/**
	 * 物品id t_channel_realdragon.f_goodmodel_id
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * 物品使用数量 t_channel_realdragon.f_goodmodel_count
	 * 
	 */
	private Integer goodmodelCount;
	/**
	 * 真龙脉名称国际化 t_channel_realdragon.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 令经脉失效的debuffId t_channel_realdragon.f_debuff_id
	 * 
	 */
	private Integer debuffId;

	/**
	 * 经脉编号_真龙 t_channel_realdragon.f_id
	 * @return  the value of t_channel_realdragon.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 经脉编号_真龙 t_channel_realdragon.f_id
	 * @param id  the value for t_channel_realdragon.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 经脉名称_真龙 t_channel_realdragon.f_name
	 * @return  the value of t_channel_realdragon.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 经脉名称_真龙 t_channel_realdragon.f_name
	 * @param name  the value for t_channel_realdragon.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 血_真龙 t_channel_realdragon.f_hp_add
	 * @return  the value of t_channel_realdragon.f_hp_add
	 * 
	 */
	public Integer getHpAdd() {
		return hpAdd;
	}

	/**
	 * 血_真龙 t_channel_realdragon.f_hp_add
	 * @param hpAdd  the value for t_channel_realdragon.f_hp_add
	 * 
	 */
	public void setHpAdd(Integer hpAdd) {
		this.hpAdd = hpAdd;
	}

	/**
	 * 内力_真龙 t_channel_realdragon.f_mp_add
	 * @return  the value of t_channel_realdragon.f_mp_add
	 * 
	 */
	public Integer getMpAdd() {
		return mpAdd;
	}

	/**
	 * 内力_真龙 t_channel_realdragon.f_mp_add
	 * @param mpAdd  the value for t_channel_realdragon.f_mp_add
	 * 
	 */
	public void setMpAdd(Integer mpAdd) {
		this.mpAdd = mpAdd;
	}

	/**
	 * 体力_真龙 t_channel_realdragon.f_sp_add
	 * @return  the value of t_channel_realdragon.f_sp_add
	 * 
	 */
	public Integer getSpAdd() {
		return spAdd;
	}

	/**
	 * 体力_真龙 t_channel_realdragon.f_sp_add
	 * @param spAdd  the value for t_channel_realdragon.f_sp_add
	 * 
	 */
	public void setSpAdd(Integer spAdd) {
		this.spAdd = spAdd;
	}

	/**
	 * 攻_真龙 t_channel_realdragon.f_attack_add
	 * @return  the value of t_channel_realdragon.f_attack_add
	 * 
	 */
	public Integer getAttackAdd() {
		return attackAdd;
	}

	/**
	 * 攻_真龙 t_channel_realdragon.f_attack_add
	 * @param attackAdd  the value for t_channel_realdragon.f_attack_add
	 * 
	 */
	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	/**
	 * 防_真龙 t_channel_realdragon.f_defence_add
	 * @return  the value of t_channel_realdragon.f_defence_add
	 * 
	 */
	public Integer getDefenceAdd() {
		return defenceAdd;
	}

	/**
	 * 防_真龙 t_channel_realdragon.f_defence_add
	 * @param defenceAdd  the value for t_channel_realdragon.f_defence_add
	 * 
	 */
	public void setDefenceAdd(Integer defenceAdd) {
		this.defenceAdd = defenceAdd;
	}

	/**
	 * 闪避_真龙 t_channel_realdragon.f_dodge_add
	 * @return  the value of t_channel_realdragon.f_dodge_add
	 * 
	 */
	public Integer getDodgeAdd() {
		return dodgeAdd;
	}

	/**
	 * 闪避_真龙 t_channel_realdragon.f_dodge_add
	 * @param dodgeAdd  the value for t_channel_realdragon.f_dodge_add
	 * 
	 */
	public void setDodgeAdd(Integer dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
	}

	/**
	 * 暴击_真龙 t_channel_realdragon.f_crt_add
	 * @return  the value of t_channel_realdragon.f_crt_add
	 * 
	 */
	public Integer getCrtAdd() {
		return crtAdd;
	}

	/**
	 * 暴击_真龙 t_channel_realdragon.f_crt_add
	 * @param crtAdd  the value for t_channel_realdragon.f_crt_add
	 * 
	 */
	public void setCrtAdd(Integer crtAdd) {
		this.crtAdd = crtAdd;
	}

	/**
	 * 需要真气量_真龙 t_channel_realdragon.f_need_zhenqi
	 * @return  the value of t_channel_realdragon.f_need_zhenqi
	 * 
	 */
	public Integer getNeedZhenqi() {
		return needZhenqi;
	}

	/**
	 * 需要真气量_真龙 t_channel_realdragon.f_need_zhenqi
	 * @param needZhenqi  the value for t_channel_realdragon.f_need_zhenqi
	 * 
	 */
	public void setNeedZhenqi(Integer needZhenqi) {
		this.needZhenqi = needZhenqi;
	}

	/**
	 * 冲击几率万分之几_真龙 t_channel_realdragon.f_odds
	 * @return  the value of t_channel_realdragon.f_odds
	 * 
	 */
	public Integer getOdds() {
		return odds;
	}

	/**
	 * 冲击几率万分之几_真龙 t_channel_realdragon.f_odds
	 * @param odds  the value for t_channel_realdragon.f_odds
	 * 
	 */
	public void setOdds(Integer odds) {
		this.odds = odds;
	}

	/**
	 * 武功id t_channel_realdragon.f_skill_id
	 * @return  the value of t_channel_realdragon.f_skill_id
	 * 
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * 武功id t_channel_realdragon.f_skill_id
	 * @param skillId  the value for t_channel_realdragon.f_skill_id
	 * 
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 加成数量 t_channel_realdragon.f_skill_addcount
	 * @return  the value of t_channel_realdragon.f_skill_addcount
	 * 
	 */
	public Integer getSkillAddcount() {
		return skillAddcount;
	}

	/**
	 * 加成数量 t_channel_realdragon.f_skill_addcount
	 * @param skillAddcount  the value for t_channel_realdragon.f_skill_addcount
	 * 
	 */
	public void setSkillAddcount(Integer skillAddcount) {
		this.skillAddcount = skillAddcount;
	}

	/**
	 * 最小 t_channel_realdragon.f_zhenlong_min
	 * @return  the value of t_channel_realdragon.f_zhenlong_min
	 * 
	 */
	public Integer getZhenlongMin() {
		return zhenlongMin;
	}

	/**
	 * 最小 t_channel_realdragon.f_zhenlong_min
	 * @param zhenlongMin  the value for t_channel_realdragon.f_zhenlong_min
	 * 
	 */
	public void setZhenlongMin(Integer zhenlongMin) {
		this.zhenlongMin = zhenlongMin;
	}

	/**
	 * 最大 t_channel_realdragon.f_zhenlong_max
	 * @return  the value of t_channel_realdragon.f_zhenlong_max
	 * 
	 */
	public Integer getZhenlongMax() {
		return zhenlongMax;
	}

	/**
	 * 最大 t_channel_realdragon.f_zhenlong_max
	 * @param zhenlongMax  the value for t_channel_realdragon.f_zhenlong_max
	 * 
	 */
	public void setZhenlongMax(Integer zhenlongMax) {
		this.zhenlongMax = zhenlongMax;
	}

	/**
	 * 物品id t_channel_realdragon.f_goodmodel_id
	 * @return  the value of t_channel_realdragon.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 物品id t_channel_realdragon.f_goodmodel_id
	 * @param goodmodelId  the value for t_channel_realdragon.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 物品使用数量 t_channel_realdragon.f_goodmodel_count
	 * @return  the value of t_channel_realdragon.f_goodmodel_count
	 * 
	 */
	public Integer getGoodmodelCount() {
		return goodmodelCount;
	}

	/**
	 * 物品使用数量 t_channel_realdragon.f_goodmodel_count
	 * @param goodmodelCount  the value for t_channel_realdragon.f_goodmodel_count
	 * 
	 */
	public void setGoodmodelCount(Integer goodmodelCount) {
		this.goodmodelCount = goodmodelCount;
	}

	/**
	 * 真龙脉名称国际化 t_channel_realdragon.f_name_i18n
	 * @return  the value of t_channel_realdragon.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 真龙脉名称国际化 t_channel_realdragon.f_name_i18n
	 * @param nameI18n  the value for t_channel_realdragon.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 令经脉失效的debuffId t_channel_realdragon.f_debuff_id
	 * @return  the value of t_channel_realdragon.f_debuff_id
	 * 
	 */
	public Integer getDebuffId() {
		return debuffId;
	}

	/**
	 * 令经脉失效的debuffId t_channel_realdragon.f_debuff_id
	 * @param debuffId  the value for t_channel_realdragon.f_debuff_id
	 * 
	 */
	public void setDebuffId(Integer debuffId) {
		this.debuffId = debuffId;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.jingmai;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe=new PropertyEntity();
		pe.addExtraProperty(Property.attack, getAttackAdd());
		pe.addExtraProperty(Property.maxMp, getMpAdd());
		pe.addExtraProperty(Property.maxHp, getHpAdd());
		pe.addExtraProperty(Property.maxSp, getSpAdd());
		pe.addExtraProperty(Property.defence, getDefenceAdd());
		pe.addExtraProperty(Property.dodge, getDodgeAdd());
		pe.addExtraProperty(Property.crt, getCrtAdd());
		return pe;
	}
}
