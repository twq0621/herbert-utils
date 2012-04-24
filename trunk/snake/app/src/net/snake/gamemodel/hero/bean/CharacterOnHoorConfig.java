package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterOnHoorConfig implements IbatisEntity {

	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 */
	private Boolean autoRevertHp;
	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 */
	private Integer revertHp;
	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 */
	private Integer revertMp;
	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 */
	private Integer revertSp;
	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 */
	private Integer revertHpMethod;
	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 */
	private Integer revertMpMethod;
	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 */
	private Integer revertSpMethod;
	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 */
	private Boolean autoPickupEquip;
	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色） t_character_onhoor_config.f_quality_equip
	 * 
	 */
	private Byte qualityEquip;
	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花） t_character_onhoor_config.f_limit_popsinger
	 * 
	 */
	private Byte limitPopsinger;
	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 */
	private Integer gradeEquip;
	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 */
	private Boolean isTaskGoods;
	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 */
	private Boolean isYaopin;
	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 */
	private Boolean isCailiao;
	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 */
	private Boolean otherGoods;

	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_scope
	 * 
	 */
	private Integer attackScope;
	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_time
	 * 
	 */
	private Integer attackTime;
	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 */
	private Boolean backSit;
	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 */
	private Boolean avoidMonster;
	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 */
	private Boolean backUseRose;
	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 */
	private Boolean zhenqidan;
	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 */
	private Boolean expdan;
	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 */
	private Boolean horseExpdan;
	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 */
	private Boolean autoRevertMp;
	/**
	 * 自动恢复体力(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_sp
	 * 
	 */
	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 */
	private Boolean isMoney;
	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 */
	private Boolean huolishendan;
	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 */
	private Boolean moshixiuli;

	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 */
	private Boolean shouhu;
	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 * @return the value of t_character_onhoor_config.f_character_id
	 * 
	 */

	private boolean autoHpBuff; // 九转还魂丹--自动回血buff
	private boolean autoMpBuff;// 琼浆玉液露--自动回蓝buff
	private int skillOne;
	private int skillTwo;
	private int skillThree;
	private boolean horseAutoLivingness;
	private int horseLivingness;
	private byte type;//挂机配置类型，1常规，2效率，3自定义

	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_onhoor_config.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_onhoor_config.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_revert_hp
	 * 
	 */
	public Boolean getAutoRevertHp() {
		return autoRevertHp;
	}

	/**
	 * 自动恢复血量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_hp
	 * 
	 * @param autoRevertHp
	 *            the value for t_character_onhoor_config.f_auto_revert_hp
	 * 
	 */
	public void setAutoRevertHp(Boolean autoRevertHp) {
		this.autoRevertHp = autoRevertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_hp
	 * 
	 */
	public Integer getRevertHp() {
		return revertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_hp
	 * 
	 * @param revertHp
	 *            the value for t_character_onhoor_config.f_revert_hp
	 * 
	 */
	public void setRevertHp(Integer revertHp) {
		this.revertHp = revertHp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_mp
	 * 
	 */
	public Integer getRevertMp() {
		return revertMp;
	}

	/**
	 * 当生命低于 %时使用 t_character_onhoor_config.f_revert_mp
	 * 
	 * @param revertMp
	 *            the value for t_character_onhoor_config.f_revert_mp
	 * 
	 */
	public void setRevertMp(Integer revertMp) {
		this.revertMp = revertMp;
	}

	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_sp
	 * 
	 */
	public Integer getRevertSp() {
		return revertSp;
	}

	/**
	 * 当内力低于 %时使用 t_character_onhoor_config.f_revert_sp
	 * 
	 * @param revertSp
	 *            the value for t_character_onhoor_config.f_revert_sp
	 * 
	 */
	public void setRevertSp(Integer revertSp) {
		this.revertSp = revertSp;
	}

	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_hp_method
	 * 
	 */
	public Integer getRevertHpMethod() {
		return revertHpMethod;
	}

	/**
	 * 恢复生命类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_hp_method
	 * 
	 * @param revertHpMethod
	 *            the value for t_character_onhoor_config.f_revert_hp_method
	 * 
	 */
	public void setRevertHpMethod(Integer revertHpMethod) {
		this.revertHpMethod = revertHpMethod;
	}

	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_mp_method
	 * 
	 */
	public Integer getRevertMpMethod() {
		return revertMpMethod;
	}

	/**
	 * 恢复内力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_mp_method
	 * 
	 * @param revertMpMethod
	 *            the value for t_character_onhoor_config.f_revert_mp_method
	 * 
	 */
	public void setRevertMpMethod(Integer revertMpMethod) {
		this.revertMpMethod = revertMpMethod;
	}

	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 * @return the value of t_character_onhoor_config.f_revert_sp_method
	 * 
	 */
	public Integer getRevertSpMethod() {
		return revertSpMethod;
	}

	/**
	 * 恢复体力类药品方式(从小到大1从大到小2) t_character_onhoor_config.f_revert_sp_method
	 * 
	 * @param revertSpMethod
	 *            the value for t_character_onhoor_config.f_revert_sp_method
	 * 
	 */
	public void setRevertSpMethod(Integer revertSpMethod) {
		this.revertSpMethod = revertSpMethod;
	}

	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 */
	public Boolean getAutoPickupEquip() {
		return autoPickupEquip;
	}

	/**
	 * 是否自动拾取装备物品 t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 * @param autoPickupEquip
	 *            the value for t_character_onhoor_config.f_auto_pickup_equip
	 * 
	 */
	public void setAutoPickupEquip(Boolean autoPickupEquip) {
		this.autoPickupEquip = autoPickupEquip;
	}

	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色） t_character_onhoor_config.f_quality_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_quality_equip
	 * 
	 */
	public Byte getQualityEquip() {
		return qualityEquip;
	}

	/**
	 * 部分拾取时，装备品质的筛选（0.没有1.白色2.蓝色3.绿色4.紫色） t_character_onhoor_config.f_quality_equip
	 * 
	 * @param qualityEquip
	 *            the value for t_character_onhoor_config.f_quality_equip
	 * 
	 */
	public void setQualityEquip(Byte qualityEquip) {
		this.qualityEquip = qualityEquip;
	}

	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花） t_character_onhoor_config.f_limit_popsinger
	 * 
	 * @return the value of t_character_onhoor_config.f_limit_popsinger
	 * 
	 */
	public Byte getLimitPopsinger() {
		return limitPopsinger;
	}

	/**
	 * 部分拾取时，装备门派的限定装备（0-全门派1-少林2-全真3-古墓4-桃花） t_character_onhoor_config.f_limit_popsinger
	 * 
	 * @param limitPopsinger
	 *            the value for t_character_onhoor_config.f_limit_popsinger
	 * 
	 */
	public void setLimitPopsinger(Byte limitPopsinger) {
		this.limitPopsinger = limitPopsinger;
	}

	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 * @return the value of t_character_onhoor_config.f_grade_equip
	 * 
	 */
	public Integer getGradeEquip() {
		return gradeEquip;
	}

	/**
	 * 部分拾取时，等级装备的限定（0全部拾取） t_character_onhoor_config.f_grade_equip
	 * 
	 * @param gradeEquip
	 *            the value for t_character_onhoor_config.f_grade_equip
	 * 
	 */
	public void setGradeEquip(Integer gradeEquip) {
		this.gradeEquip = gradeEquip;
	}

	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 * @return the value of t_character_onhoor_config.f_is_task_goods
	 * 
	 */
	public Boolean getIsTaskGoods() {
		return isTaskGoods;
	}

	/**
	 * 部分物品拾取时，任务物品的选择(0为不选1选择) t_character_onhoor_config.f_is_task_goods
	 * 
	 * @param isTaskGoods
	 *            the value for t_character_onhoor_config.f_is_task_goods
	 * 
	 */
	public void setIsTaskGoods(Boolean isTaskGoods) {
		this.isTaskGoods = isTaskGoods;
	}

	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 * @return the value of t_character_onhoor_config.f_is_yaopin
	 * 
	 */
	public Boolean getIsYaopin() {
		return isYaopin;
	}

	/**
	 * 部分物品拾取时，药品选择(0为不选1选择) t_character_onhoor_config.f_is_yaopin
	 * 
	 * @param isYaopin
	 *            the value for t_character_onhoor_config.f_is_yaopin
	 * 
	 */
	public void setIsYaopin(Boolean isYaopin) {
		this.isYaopin = isYaopin;
	}

	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 * @return the value of t_character_onhoor_config.f_is_cailiao
	 * 
	 */
	public Boolean getIsCailiao() {
		return isCailiao;
	}

	/**
	 * 部分物品拾取时，材料的选择(0为不选1选择) t_character_onhoor_config.f_is_cailiao
	 * 
	 * @param isCailiao
	 *            the value for t_character_onhoor_config.f_is_cailiao
	 * 
	 */
	public void setIsCailiao(Boolean isCailiao) {
		this.isCailiao = isCailiao;
	}

	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 * @return the value of t_character_onhoor_config.f_other_goods
	 * 
	 */
	public Boolean getOtherGoods() {
		return otherGoods;
	}

	/**
	 * 部分物品拾取时，其他选择(0为不选1选择) t_character_onhoor_config.f_other_goods
	 * 
	 * @param otherGoods
	 *            the value for t_character_onhoor_config.f_other_goods
	 * 
	 */
	public void setOtherGoods(Boolean otherGoods) {
		this.otherGoods = otherGoods;
	}

	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_scope
	 * 
	 * @return the value of t_character_onhoor_config.f_attack_scope
	 * 
	 */
	public Integer getAttackScope() {
		return attackScope;
	}

	/**
	 * 自动战斗范围周围XX格：默认填写为20（填写范围为1-99， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_scope
	 * 
	 * @param attackScope
	 *            the value for t_character_onhoor_config.f_attack_scope
	 * 
	 */
	public void setAttackScope(Integer attackScope) {
		this.attackScope = attackScope;
	}

	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_time
	 * 
	 * @return the value of t_character_onhoor_config.f_attack_time
	 * 
	 */
	public Integer getAttackTime() {
		return attackTime;
	}

	/**
	 * 自动战斗时间XX分钟：默认填写为30（填写范围为1-500， 若输入0则自动变为1） 毫秒为单位 t_character_onhoor_config.f_attack_time
	 * 
	 * @param attackTime
	 *            the value for t_character_onhoor_config.f_attack_time
	 * 
	 */
	public void setAttackTime(Integer attackTime) {
		this.attackTime = attackTime;
	}

	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 * @return the value of t_character_onhoor_config.f_back_sit
	 * 
	 */
	public Boolean getBackSit() {
		return backSit;
	}

	/**
	 * 自动战斗结束后，回城打坐：默认为打√状态1,0非√ t_character_onhoor_config.f_back_sit
	 * 
	 * @param backSit
	 *            the value for t_character_onhoor_config.f_back_sit
	 * 
	 */
	public void setBackSit(Boolean backSit) {
		this.backSit = backSit;
	}

	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 * @return the value of t_character_onhoor_config.f_avoid_monster
	 * 
	 */
	public Boolean getAvoidMonster() {
		return avoidMonster;
	}

	/**
	 * 自动战斗时，自动避开变异怪：默认为打√状态 t_character_onhoor_config.f_avoid_monster
	 * 
	 * @param avoidMonster
	 *            the value for t_character_onhoor_config.f_avoid_monster
	 * 
	 */
	public void setAvoidMonster(Boolean avoidMonster) {
		this.avoidMonster = avoidMonster;
	}

	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 * @return the value of t_character_onhoor_config.f_back_use_rose
	 * 
	 */
	public Boolean getBackUseRose() {
		return backUseRose;
	}

	/**
	 * 死亡后，自动使用玫瑰原地复活：默认为打√状态 t_character_onhoor_config.f_back_use_rose
	 * 
	 * @param backUseRose
	 *            the value for t_character_onhoor_config.f_back_use_rose
	 * 
	 */
	public void setBackUseRose(Boolean backUseRose) {
		this.backUseRose = backUseRose;
	}

	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 * @return the value of t_character_onhoor_config.f_zhenqidan
	 * 
	 */
	public Boolean getZhenqidan() {
		return zhenqidan;
	}

	/**
	 * 是否自动续用双倍真气丹（0不选中1选中） t_character_onhoor_config.f_zhenqidan
	 * 
	 * @param zhenqidan
	 *            the value for t_character_onhoor_config.f_zhenqidan
	 * 
	 */
	public void setZhenqidan(Boolean zhenqidan) {
		this.zhenqidan = zhenqidan;
	}

	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 * @return the value of t_character_onhoor_config.f_expdan
	 * 
	 */
	public Boolean getExpdan() {
		return expdan;
	}

	/**
	 * 是否自动续用双倍经验丹（0不选中1选中） t_character_onhoor_config.f_expdan
	 * 
	 * @param expdan
	 *            the value for t_character_onhoor_config.f_expdan
	 * 
	 */
	public void setExpdan(Boolean expdan) {
		this.expdan = expdan;
	}

	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 * @return the value of t_character_onhoor_config.f_horse_expdan
	 * 
	 */
	public Boolean getHorseExpdan() {
		return horseExpdan;
	}

	/**
	 * 是否使用坐骑双倍经验丹（0不选中1选中） t_character_onhoor_config.f_horse_expdan
	 * 
	 * @param horseExpdan
	 *            the value for t_character_onhoor_config.f_horse_expdan
	 * 
	 */
	public void setHorseExpdan(Boolean horseExpdan) {
		this.horseExpdan = horseExpdan;
	}

	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 * @return the value of t_character_onhoor_config.f_auto_revert_mp
	 * 
	 */
	public Boolean getAutoRevertMp() {
		return autoRevertMp;
	}

	/**
	 * 自动恢复魔法量(0标示不自动恢复 1标示自动恢复) t_character_onhoor_config.f_auto_revert_mp
	 * 
	 * @param autoRevertMp
	 *            the value for t_character_onhoor_config.f_auto_revert_mp
	 * 
	 */
	public void setAutoRevertMp(Boolean autoRevertMp) {
		this.autoRevertMp = autoRevertMp;
	}

	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 * @return the value of t_character_onhoor_config.f_is_money
	 * 
	 */
	public Boolean getIsMoney() {
		return isMoney;
	}

	/**
	 * 部分物品拾取时，铜钱的选择(0为不选1选择) t_character_onhoor_config.f_is_money
	 * 
	 * @param isMoney
	 *            the value for t_character_onhoor_config.f_is_money
	 * 
	 */
	public void setIsMoney(Boolean isMoney) {
		this.isMoney = isMoney;
	}

	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 * @return the value of t_character_onhoor_config.f_huolishendan
	 * 
	 */
	public Boolean getHuolishendan() {
		return huolishendan;
	}

	/**
	 * 活力过低且草药不足时自动使用活力神丹 t_character_onhoor_config.f_huolishendan
	 * 
	 * @param huolishendan
	 *            the value for t_character_onhoor_config.f_huolishendan
	 * 
	 */
	public void setHuolishendan(Boolean huolishendan) {
		this.huolishendan = huolishendan;
	}

	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 * @return the value of t_character_onhoor_config.f_moshixiuli
	 * 
	 */
	public Boolean getMoshixiuli() {
		return moshixiuli;
	}

	/**
	 * 装备耐久为零时自动使用打磨石修理 t_character_onhoor_config.f_moshixiuli
	 * 
	 * @param moshixiuli
	 *            the value for t_character_onhoor_config.f_moshixiuli
	 * 
	 */
	public void setMoshixiuli(Boolean moshixiuli) {
		this.moshixiuli = moshixiuli;
	}

	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 * @return the value of t_character_onhoor_config.f_shouhu
	 * 
	 */
	public Boolean getShouhu() {
		return shouhu;
	}

	/**
	 * 是否自动使用夫妻守护 t_character_onhoor_config.f_shouhu
	 * 
	 * @param shouhu
	 *            the value for t_character_onhoor_config.f_shouhu
	 * 
	 */
	public void setShouhu(Boolean shouhu) {
		this.shouhu = shouhu;
	}

	/**
	 * 是否自动拾取
	 * 装备|材料|任务道具|药品|金钱|其他
	 * @return
	 */
	public boolean getAutoPickup() {
		 boolean pick=getAutoPickupEquip() || getOtherGoods() || getIsCailiao() || getIsTaskGoods() || getIsYaopin() || getIsMoney();
		 return pick;
	}

	public boolean getAutoHpBuff() {
		return autoHpBuff;
	}

	public void setAutoHpBuff(boolean autoHpBuff) {
		this.autoHpBuff = autoHpBuff;
	}

	public boolean getAutoMpBuff() {
		return autoMpBuff;
	}

	public void setAutoMpBuff(boolean autoMpBuff) {
		this.autoMpBuff = autoMpBuff;
	}

	public int getSkillOne() {
		return skillOne;
	}

	public void setSkillOne(int skillOne) {
		this.skillOne = skillOne;
	}

	public int getSkillTwo() {
		return skillTwo;
	}

	public void setSkillTwo(int skillTwo) {
		this.skillTwo = skillTwo;
	}

	public int getSkillThree() {
		return skillThree;
	}

	public void setSkillThree(int skillThree) {
		this.skillThree = skillThree;
	}

	public boolean getHorseAutoLivingness() {
		return horseAutoLivingness;
	}

	public void setHorseAutoLivingness(boolean horseAutoLivingness) {
		this.horseAutoLivingness = horseAutoLivingness;
	}

	public int getHorseLivingness() {
		return horseLivingness;
	}

	public void setHorseLivingness(int horseLivingness) {
		this.horseLivingness = horseLivingness;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
	
}
