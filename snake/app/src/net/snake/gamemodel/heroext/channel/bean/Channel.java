package net.snake.gamemodel.heroext.channel.bean;

import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.ibatis.IbatisEntity;


public class Channel implements PropertyChangeListener,IbatisEntity{

	/**
	 * 经脉编号 t_channel.f_id
	 */
	private Integer id;
	/**
	 * 经脉名称 t_channel.f_name
	 */
	private String name;
	/**
	 * 血 t_channel.f_hp_add
	 */
	private Integer hpAdd;
	/**
	 * 内力 t_channel.f_mp_add
	 */
	private Integer mpAdd;
	/**
	 * 体力 t_channel.f_sp_add
	 */
	private Integer spAdd;
	/**
	 * 攻 t_channel.f_attack_add
	 */
	private Integer attackAdd;
	/**
	 * 防 t_channel.f_defence_add
	 */
	private Integer defenceAdd;
	/**
	 * 闪避 t_channel.f_dodge_add
	 */
	private Integer dodgeAdd;
	/**
	 * 暴击 t_channel.f_crt_add
	 */
	private Integer crtAdd;
	/**
	 * 需要真气量 t_channel.f_need_zhenqi
	 */
	private Integer needZhenqi;
	/**
	 * 冲击几率万分之几 t_channel.f_odds
	 */
	private Integer odds;
	/**
	 * 经脉名称国际化 t_channel.f_name_i18n
	 */
	private String nameI18n;
	/**
	 * 令经脉失效的debuffId t_channel.f_debuff_id
	 */
	private Integer debuffId;
	/**
	 * 经脉编号 t_channel.f_id
	 * @return  the value of t_channel.f_id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 经脉编号 t_channel.f_id
	 * @param id  the value for t_channel.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 经脉名称 t_channel.f_name
	 * @return  the value of t_channel.f_name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 经脉名称 t_channel.f_name
	 * @param name  the value for t_channel.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 血 t_channel.f_hp_add
	 * @return  the value of t_channel.f_hp_add
	 */
	public Integer getHpAdd() {
		return hpAdd;
	}
	/**
	 * 血 t_channel.f_hp_add
	 * @param hpAdd  the value for t_channel.f_hp_add
	 */
	public void setHpAdd(Integer hpAdd) {
		this.hpAdd = hpAdd;
	}
	/**
	 * 内力 t_channel.f_mp_add
	 * @return  the value of t_channel.f_mp_add
	 */
	public Integer getMpAdd() {
		return mpAdd;
	}
	/**
	 * 内力 t_channel.f_mp_add
	 * @param mpAdd  the value for t_channel.f_mp_add
	 */
	public void setMpAdd(Integer mpAdd) {
		this.mpAdd = mpAdd;
	}
	/**
	 * 体力 t_channel.f_sp_add
	 * @return  the value of t_channel.f_sp_add
	 */
	public Integer getSpAdd() {
		return spAdd;
	}
	/**
	 * 体力 t_channel.f_sp_add
	 * @param spAdd  the value for t_channel.f_sp_add
	 */
	public void setSpAdd(Integer spAdd) {
		this.spAdd = spAdd;
	}
	/**
	 * 攻 t_channel.f_attack_add
	 * @return  the value of t_channel.f_attack_add
	 */
	public Integer getAttackAdd() {
		return attackAdd;
	}
	/**
	 * 攻 t_channel.f_attack_add
	 * @param attackAdd  the value for t_channel.f_attack_add
	 */
	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}
	/**
	 * 防 t_channel.f_defence_add
	 * @return  the value of t_channel.f_defence_add
	 */
	public Integer getDefenceAdd() {
		return defenceAdd;
	}
	/**
	 * 防 t_channel.f_defence_add
	 * @param defenceAdd  the value for t_channel.f_defence_add
	 */
	public void setDefenceAdd(Integer defenceAdd) {
		this.defenceAdd = defenceAdd;
	}
	/**
	 * 闪避 t_channel.f_dodge_add
	 * @return  the value of t_channel.f_dodge_add
	 */
	public Integer getDodgeAdd() {
		return dodgeAdd;
	}
	/**
	 * 闪避 t_channel.f_dodge_add
	 * @param dodgeAdd  the value for t_channel.f_dodge_add
	 */
	public void setDodgeAdd(Integer dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
	}
	/**
	 * 暴击 t_channel.f_crt_add
	 * @return  the value of t_channel.f_crt_add
	 */
	public Integer getCrtAdd() {
		return crtAdd;
	}
	/**
	 * 暴击 t_channel.f_crt_add
	 * @param crtAdd  the value for t_channel.f_crt_add
	 */
	public void setCrtAdd(Integer crtAdd) {
		this.crtAdd = crtAdd;
	}
	/**
	 * 需要真气量 t_channel.f_need_zhenqi
	 * @return  the value of t_channel.f_need_zhenqi
	 */
	public Integer getNeedZhenqi() {
		return needZhenqi;
	}
	/**
	 * 需要真气量 t_channel.f_need_zhenqi
	 * @param needZhenqi  the value for t_channel.f_need_zhenqi
	 */
	public void setNeedZhenqi(Integer needZhenqi) {
		this.needZhenqi = needZhenqi;
	}
	/**
	 * 冲击几率万分之几 t_channel.f_odds
	 * @return  the value of t_channel.f_odds
	 */
	public Integer getOdds() {
		return odds;
	}
	/**
	 * 冲击几率万分之几 t_channel.f_odds
	 * @param odds  the value for t_channel.f_odds
	 */
	public void setOdds(Integer odds) {
		this.odds = odds;
	}
	/**
	 * 经脉名称国际化 t_channel.f_name_i18n
	 * @return  the value of t_channel.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}
	/**
	 * 经脉名称国际化 t_channel.f_name_i18n
	 * @param nameI18n  the value for t_channel.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}
	/**
	 * 令经脉失效的debuffId t_channel.f_debuff_id
	 * @return  the value of t_channel.f_debuff_id
	 */
	public Integer getDebuffId() {
		return debuffId;
	}
	/**
	 * 令经脉失效的debuffId t_channel.f_debuff_id
	 * @param debuffId  the value for t_channel.f_debuff_id
	 */
	public void setDebuffId(Integer debuffId) {
		this.debuffId = debuffId;
	}
	//	@Override
//	public void change(VisibleObject vo) {
//		if (vo instanceof Character) {
//			Character character = (Character)vo;
//		PropertyController propertyController = character.getPropertyController();
//		propertyController.addExtraProperty(Property.attack, getAttackAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.maxMp, getMpAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.maxHp, getHpAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.maxSp, getSpAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.defence, getDefenceAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.dodge, getDodgeAdd(), PropertyControllerType.jingmai);
//		propertyController.addExtraProperty(Property.crt, getCrtAdd(), PropertyControllerType.jingmai);
//	}
//		
//	}
	public PropertyEntity getPropertyEntity(){
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
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.jingmai;
	}
}
