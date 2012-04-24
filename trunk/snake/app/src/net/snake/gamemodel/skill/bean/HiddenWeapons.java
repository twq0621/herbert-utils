package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

public class HiddenWeapons implements IbatisEntity {

	/**
	 * t_hidden_weapons.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 暗器名称 t_hidden_weapons.f_name
	 * 
	 */
	private String name;
	/**
	 * 暗器品阶 t_hidden_weapons.f_grade
	 * 
	 */
	private Integer grade;
	/**
	 * 修炼等级 t_hidden_weapons.f_xiu_grade
	 * 
	 */
	private Integer xiuGrade;
	/**
	 * 升级所需熟练度 t_hidden_weapons.f_mastery
	 * 
	 */
	private Integer mastery;
	/**
	 * 攻击能力（人物攻击力万分比）（单位10000） t_hidden_weapons.f_hidden_weaponscol
	 * 
	 */
	private Integer hiddenWeaponscol;
	/**
	 * 攻击距离 t_hidden_weapons.f_attack_dis
	 * 
	 */
	private Integer attackDis;
	/**
	 * 攻击目标数量 t_hidden_weapons.f_attack_targets
	 * 
	 */
	private Integer attackTargets;
	/**
	 * 触发几率显示值（单位10000） t_hidden_weapons.f_trib_lv
	 * 
	 */
	private Integer tribLv;
	/**
	 * 触发几率真实值（单位10000） t_hidden_weapons.f_trib_real_value
	 * 
	 */
	private Integer tribRealValue;
	/**
	 * 特殊能力种类（buffid;*）（多个用逗号隔开） t_hidden_weapons.f_spcial_type
	 * 
	 */
	private String spcialType;
	/**
	 * 特殊能力文字描述 t_hidden_weapons.f_spcial_desc
	 * 
	 */
	private String spcialDesc;
	/**
	 * 突破瓶颈消耗铜币数 t_hidden_weapons.f_upgrade_need_copper
	 * 
	 */
	private Integer upgradeNeedCopper;
	/**
	 * 突破消耗真气数 t_hidden_weapons.f_upgrade_need_zhenqi
	 * 
	 */
	private Integer upgradeNeedZhenqi;
	/**
	 * 突破消耗物品ID(格式:物品id,物品数量;)* (多个用分号分割) t_hidden_weapons.f_upgrade_need_goods
	 * 
	 */
	private String upgradeNeedGoods;
	/**
	 * 突破显示用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_lv
	 * 
	 */
	private Integer upgradeTribLv;
	/**
	 * 突破真实计算用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_real_lv
	 * 
	 */
	private Integer upgradeTribRealLv;
	/**
	 * 突破成功所需最小突破次数 t_hidden_weapons.f_upgrade_min_cnt
	 * 
	 */
	private Integer upgradeMinCnt;
	/**
	 * 突破必然成功所需最大成功次数 t_hidden_weapons.f_upgrade_max_cnt
	 * 
	 */
	private Integer upgradeMaxCnt;
	/**
	 * 获得方式 t_hidden_weapons.f_method
	 * 
	 */
	private String method;
	/**
	 * t_hidden_weapons.f_skill_id
	 * 
	 */
	private Integer skillId;
	/**
	 * 暗器名称国际化 t_hidden_weapons.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 特殊能力文字描述国际化 t_hidden_weapons.f_spcial_desc_i18n
	 * 
	 */
	private String spcialDescI18n;
	/**
	 * 获得方式 国际化 t_hidden_weapons.f_method_i18n
	 * 
	 */
	private String methodI18n;
	/**
	 * 附加攻击力 t_hidden_weapons.f_attack_add
	 * 
	 */
	private Integer attackAdd;
	/**
	 * 未活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_common_open_hidden_props
	 * 
	 */
	private String commonOpenHiddenProps;
	/**
	 * 活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_activity_open_hidden_props
	 * 
	 */
	private String activityOpenHiddenProps;

	/**
	 * t_hidden_weapons.f_id
	 * @return  the value of t_hidden_weapons.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_hidden_weapons.f_id
	 * @param id  the value for t_hidden_weapons.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 暗器名称 t_hidden_weapons.f_name
	 * @return  the value of t_hidden_weapons.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 暗器名称 t_hidden_weapons.f_name
	 * @param name  the value for t_hidden_weapons.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 暗器品阶 t_hidden_weapons.f_grade
	 * @return  the value of t_hidden_weapons.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 暗器品阶 t_hidden_weapons.f_grade
	 * @param grade  the value for t_hidden_weapons.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 修炼等级 t_hidden_weapons.f_xiu_grade
	 * @return  the value of t_hidden_weapons.f_xiu_grade
	 * 
	 */
	public Integer getXiuGrade() {
		return xiuGrade;
	}

	/**
	 * 修炼等级 t_hidden_weapons.f_xiu_grade
	 * @param xiuGrade  the value for t_hidden_weapons.f_xiu_grade
	 * 
	 */
	public void setXiuGrade(Integer xiuGrade) {
		this.xiuGrade = xiuGrade;
	}

	/**
	 * 升级所需熟练度 t_hidden_weapons.f_mastery
	 * @return  the value of t_hidden_weapons.f_mastery
	 * 
	 */
	public Integer getMastery() {
		return mastery;
	}

	/**
	 * 升级所需熟练度 t_hidden_weapons.f_mastery
	 * @param mastery  the value for t_hidden_weapons.f_mastery
	 * 
	 */
	public void setMastery(Integer mastery) {
		this.mastery = mastery;
	}

	/**
	 * 攻击能力（人物攻击力万分比）（单位10000） t_hidden_weapons.f_hidden_weaponscol
	 * @return  the value of t_hidden_weapons.f_hidden_weaponscol
	 * 
	 */
	public Integer getHiddenWeaponscol() {
		return hiddenWeaponscol;
	}

	/**
	 * 攻击能力（人物攻击力万分比）（单位10000） t_hidden_weapons.f_hidden_weaponscol
	 * @param hiddenWeaponscol  the value for t_hidden_weapons.f_hidden_weaponscol
	 * 
	 */
	public void setHiddenWeaponscol(Integer hiddenWeaponscol) {
		this.hiddenWeaponscol = hiddenWeaponscol;
	}

	/**
	 * 攻击距离 t_hidden_weapons.f_attack_dis
	 * @return  the value of t_hidden_weapons.f_attack_dis
	 * 
	 */
	public Integer getAttackDis() {
		return attackDis;
	}

	/**
	 * 攻击距离 t_hidden_weapons.f_attack_dis
	 * @param attackDis  the value for t_hidden_weapons.f_attack_dis
	 * 
	 */
	public void setAttackDis(Integer attackDis) {
		this.attackDis = attackDis;
	}

	/**
	 * 攻击目标数量 t_hidden_weapons.f_attack_targets
	 * @return  the value of t_hidden_weapons.f_attack_targets
	 * 
	 */
	public Integer getAttackTargets() {
		return attackTargets;
	}

	/**
	 * 攻击目标数量 t_hidden_weapons.f_attack_targets
	 * @param attackTargets  the value for t_hidden_weapons.f_attack_targets
	 * 
	 */
	public void setAttackTargets(Integer attackTargets) {
		this.attackTargets = attackTargets;
	}

	/**
	 * 触发几率显示值（单位10000） t_hidden_weapons.f_trib_lv
	 * @return  the value of t_hidden_weapons.f_trib_lv
	 * 
	 */
	public Integer getTribLv() {
		return tribLv;
	}

	/**
	 * 触发几率显示值（单位10000） t_hidden_weapons.f_trib_lv
	 * @param tribLv  the value for t_hidden_weapons.f_trib_lv
	 * 
	 */
	public void setTribLv(Integer tribLv) {
		this.tribLv = tribLv;
	}

	/**
	 * 触发几率真实值（单位10000） t_hidden_weapons.f_trib_real_value
	 * @return  the value of t_hidden_weapons.f_trib_real_value
	 * 
	 */
	public Integer getTribRealValue() {
		return tribRealValue;
	}

	/**
	 * 触发几率真实值（单位10000） t_hidden_weapons.f_trib_real_value
	 * @param tribRealValue  the value for t_hidden_weapons.f_trib_real_value
	 * 
	 */
	public void setTribRealValue(Integer tribRealValue) {
		this.tribRealValue = tribRealValue;
	}

	/**
	 * 特殊能力种类（buffid;*）（多个用逗号隔开） t_hidden_weapons.f_spcial_type
	 * @return  the value of t_hidden_weapons.f_spcial_type
	 * 
	 */
	public String getSpcialType() {
		return spcialType;
	}

	/**
	 * 特殊能力种类（buffid;*）（多个用逗号隔开） t_hidden_weapons.f_spcial_type
	 * @param spcialType  the value for t_hidden_weapons.f_spcial_type
	 * 
	 */
	public void setSpcialType(String spcialType) {
		this.spcialType = spcialType;
	}

	/**
	 * 特殊能力文字描述 t_hidden_weapons.f_spcial_desc
	 * @return  the value of t_hidden_weapons.f_spcial_desc
	 * 
	 */
	public String getSpcialDesc() {
		return spcialDesc;
	}

	/**
	 * 特殊能力文字描述 t_hidden_weapons.f_spcial_desc
	 * @param spcialDesc  the value for t_hidden_weapons.f_spcial_desc
	 * 
	 */
	public void setSpcialDesc(String spcialDesc) {
		this.spcialDesc = spcialDesc;
	}

	/**
	 * 突破瓶颈消耗铜币数 t_hidden_weapons.f_upgrade_need_copper
	 * @return  the value of t_hidden_weapons.f_upgrade_need_copper
	 * 
	 */
	public Integer getUpgradeNeedCopper() {
		return upgradeNeedCopper;
	}

	/**
	 * 突破瓶颈消耗铜币数 t_hidden_weapons.f_upgrade_need_copper
	 * @param upgradeNeedCopper  the value for t_hidden_weapons.f_upgrade_need_copper
	 * 
	 */
	public void setUpgradeNeedCopper(Integer upgradeNeedCopper) {
		this.upgradeNeedCopper = upgradeNeedCopper;
	}

	/**
	 * 突破消耗真气数 t_hidden_weapons.f_upgrade_need_zhenqi
	 * @return  the value of t_hidden_weapons.f_upgrade_need_zhenqi
	 * 
	 */
	public Integer getUpgradeNeedZhenqi() {
		return upgradeNeedZhenqi;
	}

	/**
	 * 突破消耗真气数 t_hidden_weapons.f_upgrade_need_zhenqi
	 * @param upgradeNeedZhenqi  the value for t_hidden_weapons.f_upgrade_need_zhenqi
	 * 
	 */
	public void setUpgradeNeedZhenqi(Integer upgradeNeedZhenqi) {
		this.upgradeNeedZhenqi = upgradeNeedZhenqi;
	}

	/**
	 * 突破消耗物品ID(格式:物品id,物品数量;)* (多个用分号分割) t_hidden_weapons.f_upgrade_need_goods
	 * @return  the value of t_hidden_weapons.f_upgrade_need_goods
	 * 
	 */
	public String getUpgradeNeedGoods() {
		return upgradeNeedGoods;
	}

	/**
	 * 突破消耗物品ID(格式:物品id,物品数量;)* (多个用分号分割) t_hidden_weapons.f_upgrade_need_goods
	 * @param upgradeNeedGoods  the value for t_hidden_weapons.f_upgrade_need_goods
	 * 
	 */
	public void setUpgradeNeedGoods(String upgradeNeedGoods) {
		this.upgradeNeedGoods = upgradeNeedGoods;
	}

	/**
	 * 突破显示用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_lv
	 * @return  the value of t_hidden_weapons.f_upgrade_trib_lv
	 * 
	 */
	public Integer getUpgradeTribLv() {
		return upgradeTribLv;
	}

	/**
	 * 突破显示用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_lv
	 * @param upgradeTribLv  the value for t_hidden_weapons.f_upgrade_trib_lv
	 * 
	 */
	public void setUpgradeTribLv(Integer upgradeTribLv) {
		this.upgradeTribLv = upgradeTribLv;
	}

	/**
	 * 突破真实计算用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_real_lv
	 * @return  the value of t_hidden_weapons.f_upgrade_trib_real_lv
	 * 
	 */
	public Integer getUpgradeTribRealLv() {
		return upgradeTribRealLv;
	}

	/**
	 * 突破真实计算用成功几率（单位10000） t_hidden_weapons.f_upgrade_trib_real_lv
	 * @param upgradeTribRealLv  the value for t_hidden_weapons.f_upgrade_trib_real_lv
	 * 
	 */
	public void setUpgradeTribRealLv(Integer upgradeTribRealLv) {
		this.upgradeTribRealLv = upgradeTribRealLv;
	}

	/**
	 * 突破成功所需最小突破次数 t_hidden_weapons.f_upgrade_min_cnt
	 * @return  the value of t_hidden_weapons.f_upgrade_min_cnt
	 * 
	 */
	public Integer getUpgradeMinCnt() {
		return upgradeMinCnt;
	}

	/**
	 * 突破成功所需最小突破次数 t_hidden_weapons.f_upgrade_min_cnt
	 * @param upgradeMinCnt  the value for t_hidden_weapons.f_upgrade_min_cnt
	 * 
	 */
	public void setUpgradeMinCnt(Integer upgradeMinCnt) {
		this.upgradeMinCnt = upgradeMinCnt;
	}

	/**
	 * 突破必然成功所需最大成功次数 t_hidden_weapons.f_upgrade_max_cnt
	 * @return  the value of t_hidden_weapons.f_upgrade_max_cnt
	 * 
	 */
	public Integer getUpgradeMaxCnt() {
		return upgradeMaxCnt;
	}

	/**
	 * 突破必然成功所需最大成功次数 t_hidden_weapons.f_upgrade_max_cnt
	 * @param upgradeMaxCnt  the value for t_hidden_weapons.f_upgrade_max_cnt
	 * 
	 */
	public void setUpgradeMaxCnt(Integer upgradeMaxCnt) {
		this.upgradeMaxCnt = upgradeMaxCnt;
	}

	/**
	 * 获得方式 t_hidden_weapons.f_method
	 * @return  the value of t_hidden_weapons.f_method
	 * 
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 获得方式 t_hidden_weapons.f_method
	 * @param method  the value for t_hidden_weapons.f_method
	 * 
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * t_hidden_weapons.f_skill_id
	 * @return  the value of t_hidden_weapons.f_skill_id
	 * 
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * t_hidden_weapons.f_skill_id
	 * @param skillId  the value for t_hidden_weapons.f_skill_id
	 * 
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 暗器名称国际化 t_hidden_weapons.f_name_i18n
	 * @return  the value of t_hidden_weapons.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 暗器名称国际化 t_hidden_weapons.f_name_i18n
	 * @param nameI18n  the value for t_hidden_weapons.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 特殊能力文字描述国际化 t_hidden_weapons.f_spcial_desc_i18n
	 * @return  the value of t_hidden_weapons.f_spcial_desc_i18n
	 * 
	 */
	public String getSpcialDescI18n() {
		return spcialDescI18n;
	}

	/**
	 * 特殊能力文字描述国际化 t_hidden_weapons.f_spcial_desc_i18n
	 * @param spcialDescI18n  the value for t_hidden_weapons.f_spcial_desc_i18n
	 * 
	 */
	public void setSpcialDescI18n(String spcialDescI18n) {
		this.spcialDescI18n = spcialDescI18n;
	}

	/**
	 * 获得方式 国际化 t_hidden_weapons.f_method_i18n
	 * @return  the value of t_hidden_weapons.f_method_i18n
	 * 
	 */
	public String getMethodI18n() {
		return methodI18n;
	}

	/**
	 * 获得方式 国际化 t_hidden_weapons.f_method_i18n
	 * @param methodI18n  the value for t_hidden_weapons.f_method_i18n
	 * 
	 */
	public void setMethodI18n(String methodI18n) {
		this.methodI18n = methodI18n;
	}

	/**
	 * 附加攻击力 t_hidden_weapons.f_attack_add
	 * @return  the value of t_hidden_weapons.f_attack_add
	 * 
	 */
	public Integer getAttackAdd() {
		return attackAdd;
	}

	/**
	 * 附加攻击力 t_hidden_weapons.f_attack_add
	 * @param attackAdd  the value for t_hidden_weapons.f_attack_add
	 * 
	 */
	public void setAttackAdd(Integer attackAdd) {
		this.attackAdd = attackAdd;
	}

	/**
	 * 未活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_common_open_hidden_props
	 * @return  the value of t_hidden_weapons.f_common_open_hidden_props
	 * 
	 */
	public String getCommonOpenHiddenProps() {
		return commonOpenHiddenProps;
	}

	/**
	 * 未活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_common_open_hidden_props
	 * @param commonOpenHiddenProps  the value for t_hidden_weapons.f_common_open_hidden_props
	 * 
	 */
	public void setCommonOpenHiddenProps(String commonOpenHiddenProps) {
		this.commonOpenHiddenProps = commonOpenHiddenProps;
	}

	/**
	 * 活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_activity_open_hidden_props
	 * @return  the value of t_hidden_weapons.f_activity_open_hidden_props
	 * 
	 */
	public String getActivityOpenHiddenProps() {
		return activityOpenHiddenProps;
	}

	/**
	 * 活动期间打开暗器隐藏属性{需要的物品id*数量;}* t_hidden_weapons.f_activity_open_hidden_props
	 * @param activityOpenHiddenProps  the value for t_hidden_weapons.f_activity_open_hidden_props
	 * 
	 */
	public void setActivityOpenHiddenProps(String activityOpenHiddenProps) {
		this.activityOpenHiddenProps = activityOpenHiddenProps;
	}
}
