package net.snake.gamemodel.pet.bean;

import net.snake.ibatis.IbatisEntity;

public class HorseModel implements IbatisEntity {

	/**
	 * t_horse_model.f_id
	 */
	private Integer id;

	private Integer quality;
	/**
	 * 名称 t_horse_model.f_name
	 */
	private String name;
	/**
	 * 头像ID t_horse_model.f_headicon_id
	 */
	private Integer headiconId;
	/**
	 * 坐骑的换装id t_horse_model.f_avatar_id
	 */
	private Integer avatarId;
	/**
	 * 可进阶成的模型ID t_horse_model.f_jinjie
	 */
	private Integer jinjie;
	/**
	 * 进阶需要的物品ID t_horse_model.f_jinjie_goods_id
	 */
	private Integer jinjieGoodsId;
	/**
	 * 进阶需要消耗的物品个数 t_horse_model.f_jinjie_goods_count
	 */
	private Integer jinjieGoodsCount;
	/**
	 * 进阶需要的铜币 t_horse_model.f_jinjie_copper
	 */
	private Integer jinjieCopper;
	/**
	 * 进阶成功率 t_horse_model.f_jinjie_success_probability
	 */
	private Integer jinjieSuccessProbability;

	/**
	 * 最大成长等级 t_horse_model.f_max_level
	 */
	private Integer maxLevel;
	/**
	 * 增加主人hp t_horse_model.f_add_owner_hp
	 */
	private float addOwnerHp;
	/**
	 * 增加主人mp t_horse_model.f_add_owner_mp
	 */
	private float addOwnerMp;
	/**
	 * 增加主人sp t_horse_model.f_add_owner_sp
	 */
	private float addOwnerSp;
	/**
	 * 增加主人攻击 t_horse_model.f_add_owner_attack
	 */
	private float addOwnerAttack;
	/**
	 * 增加主人防御 t_horse_model.f_add_owner_defence
	 */
	private float addOwnerDefence;
	/**
	 * 增加主人暴击 t_horse_model.f_add_owner_crt
	 */
	private float addOwnerCrt;
	/**
	 * 增加闪避 t_horse_model.f_add_owner_dodge
	 */
	private float addOwnerDodge;

	private float addOwnerHit;
	/**
	 * 增加攻击速度 t_horse_model.f_add_owner_atkspeed
	 */
	private float addOwnerAtkspeed;
	/**
	 * 增加移动速度 t_horse_model.f_add_owner_movespeed
	 */
	private float addOwnerMovespeed;
	/**
	 * 初始化等级 t_horse_model.f_init_level
	 */
	private Integer initLevel;

	/**
	 * 活力最小 t_horse_model.f_livingness_min
	 */
	private Integer livingnessMin;
	/**
	 * 活力最大 t_horse_model.f_livingness_max
	 */
	private Integer livingnessMax;

	private Integer maxPermitSkillCount;
	/**
	 * 蹄子光效资源id t_horse_model.f_foot_effect_id
	 */
	private Integer footEffectId;
	/**
	 * 展示坐骑的攻击时间间隔:单位毫秒 t_horse_model.f_attack_speed
	 */
	private Integer attackSpeed;

	/**
	 * 坐骑展示时自身的移动速度 t_horse_model.f_horse_movespeed
	 */
	private Integer horseMovespeed;
	/**
	 * 捕获坐骑,对人物等级的限制 t_horse_model.f_catch_me_grade_limit
	 */
	private Integer catchMeGradeLimit;
	/**
	 * 使用坐骑时,对角色的等级限制 t_horse_model.f_use_me_grade_limit
	 */
	private Integer useMeGradeLimit;
	/**
	 * 攻击时声音编号 t_horse_model.f_attack_audio
	 */
	private Integer attackAudio;
	/**
	 * 被攻击的声音 t_horse_model.f_hurt_audio
	 */
	private Integer hurtAudio;
	/**
	 * 死亡时的声音编号 t_horse_model.f_die_audio
	 */
	private Integer dieAudio;
	/**
	 * 是否绑定 0不是/1是 t_horse_model.f_bind
	 */
	private Integer bind;
	/**
	 * 进阶最小次数 t_horse_model.f_jinjie_min_count
	 */
	private Integer jinjieMinCount;
	/**
	 * 进阶最大次数 t_horse_model.f_jinjie_max_count
	 */
	private Integer jinjieMaxCount;
	/**
	 * 马的类别 t_horse_model.f_kind
	 */
	private Integer kind;
	/**
	 * 进阶显示成功几率 t_horse_model.f_jinjie_view_probability
	 */
	private Integer jinjieZhenqi;

	/**
	 * 名称国际化 t_horse_model.f_name_i18n
	 */
	private String nameI18n;

	private long neidanCdtime;

	private int changeModelId;
	private byte isUseShenji;

	private float attackQzxs; // 攻击潜质系数 0 0
	private float defenceQzxs; // 防御潜质系数 0 0
	private float hpQzxs;// hp潜质系数 0 0
	private float mpQzxs;// mp潜质系数 0 0
	private float hitQzxs; // 命中潜质系数 0 0
	private float dodgeQzxs; // 闪避潜质系数 0 0
	private float crtQzxs; // 暴击潜质系数 0 0
	private int resetSkillCopper; // 灵宠重置技能消耗铜币值
	private int resetSkillZhenqi;// 灵宠重置技能消耗真气值

	private int baseId;// 灵宠基础模型id，一般为一品的灵物id

	/**
	 * t_horse_model.f_id
	 * 
	 * @return the value of t_horse_model.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_horse_model.f_id
	 * 
	 * @param id
	 *            the value for t_horse_model.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 名称 t_horse_model.f_name
	 * 
	 * @return the value of t_horse_model.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称 t_horse_model.f_name
	 * 
	 * @param name
	 *            the value for t_horse_model.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 头像ID t_horse_model.f_headicon_id
	 * 
	 * @return the value of t_horse_model.f_headicon_id
	 */
	public Integer getHeadiconId() {
		return headiconId;
	}

	/**
	 * 头像ID t_horse_model.f_headicon_id
	 * 
	 * @param headiconId
	 *            the value for t_horse_model.f_headicon_id
	 */
	public void setHeadiconId(Integer headiconId) {
		this.headiconId = headiconId;
	}

	/**
	 * 坐骑的换装id t_horse_model.f_avatar_id
	 * 
	 * @return the value of t_horse_model.f_avatar_id
	 */
	public Integer getAvatarId() {
		return avatarId;
	}

	/**
	 * 坐骑的换装id t_horse_model.f_avatar_id
	 * 
	 * @param avatarId
	 *            the value for t_horse_model.f_avatar_id
	 */
	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	/**
	 * 可进阶成的模型ID t_horse_model.f_jinjie
	 * 
	 * @return the value of t_horse_model.f_jinjie
	 */
	public Integer getJinjie() {
		return jinjie;
	}

	/**
	 * 可进阶成的模型ID t_horse_model.f_jinjie
	 * 
	 * @param jinjie
	 *            the value for t_horse_model.f_jinjie
	 */
	public void setJinjie(Integer jinjie) {
		this.jinjie = jinjie;
	}

	/**
	 * 进阶需要的物品ID t_horse_model.f_jinjie_goods_id
	 * 
	 * @return the value of t_horse_model.f_jinjie_goods_id
	 */
	public Integer getJinjieGoodsId() {
		return jinjieGoodsId;
	}

	/**
	 * 进阶需要的物品ID t_horse_model.f_jinjie_goods_id
	 * 
	 * @param jinjieGoodsId
	 *            the value for t_horse_model.f_jinjie_goods_id
	 */
	public void setJinjieGoodsId(Integer jinjieGoodsId) {
		this.jinjieGoodsId = jinjieGoodsId;
	}

	/**
	 * 进阶需要消耗的物品个数 t_horse_model.f_jinjie_goods_count
	 * 
	 * @return the value of t_horse_model.f_jinjie_goods_count
	 */
	public Integer getJinjieGoodsCount() {
		return jinjieGoodsCount;
	}

	/**
	 * 进阶需要消耗的物品个数 t_horse_model.f_jinjie_goods_count
	 * 
	 * @param jinjieGoodsCount
	 *            the value for t_horse_model.f_jinjie_goods_count
	 */
	public void setJinjieGoodsCount(Integer jinjieGoodsCount) {
		this.jinjieGoodsCount = jinjieGoodsCount;
	}

	/**
	 * 进阶需要的铜币 t_horse_model.f_jinjie_copper
	 * 
	 * @return the value of t_horse_model.f_jinjie_copper
	 */
	public Integer getJinjieCopper() {
		return jinjieCopper;
	}

	/**
	 * 进阶需要的铜币 t_horse_model.f_jinjie_copper
	 * 
	 * @param jinjieCopper
	 *            the value for t_horse_model.f_jinjie_copper
	 */
	public void setJinjieCopper(Integer jinjieCopper) {
		this.jinjieCopper = jinjieCopper;
	}

	/**
	 * 进阶成功率 t_horse_model.f_jinjie_success_probability
	 * 
	 * @return the value of t_horse_model.f_jinjie_success_probability
	 */
	public Integer getJinjieSuccessProbability() {
		return jinjieSuccessProbability;
	}

	/**
	 * 进阶成功率 t_horse_model.f_jinjie_success_probability
	 * 
	 * @param jinjieSuccessProbability
	 *            the value for t_horse_model.f_jinjie_success_probability
	 */
	public void setJinjieSuccessProbability(Integer jinjieSuccessProbability) {
		this.jinjieSuccessProbability = jinjieSuccessProbability;
	}

	/**
	 * 最大成长等级 t_horse_model.f_max_level
	 * 
	 * @return the value of t_horse_model.f_max_level
	 */
	public Integer getMaxLevel() {
		return maxLevel;
	}

	/**
	 * 最大成长等级 t_horse_model.f_max_level
	 * 
	 * @param maxLevel
	 *            the value for t_horse_model.f_max_level
	 */
	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	/**
	 * 增加主人hp t_horse_model.f_add_owner_hp
	 * 
	 * @return the value of t_horse_model.f_add_owner_hp
	 */
	public float getAddOwnerHp() {
		return addOwnerHp;
	}

	/**
	 * 增加主人hp t_horse_model.f_add_owner_hp
	 * 
	 * @param addOwnerHp
	 *            the value for t_horse_model.f_add_owner_hp
	 */
	public void setAddOwnerHp(float addOwnerHp) {
		this.addOwnerHp = addOwnerHp;
	}

	/**
	 * 增加主人mp t_horse_model.f_add_owner_mp
	 * 
	 * @return the value of t_horse_model.f_add_owner_mp
	 */
	public float getAddOwnerMp() {
		return addOwnerMp;
	}

	/**
	 * 增加主人mp t_horse_model.f_add_owner_mp
	 * 
	 * @param addOwnerMp
	 *            the value for t_horse_model.f_add_owner_mp
	 */
	public void setAddOwnerMp(float addOwnerMp) {
		this.addOwnerMp = addOwnerMp;
	}

	/**
	 * 增加主人sp t_horse_model.f_add_owner_sp
	 * 
	 * @return the value of t_horse_model.f_add_owner_sp
	 */
	public float getAddOwnerSp() {
		return addOwnerSp;
	}

	/**
	 * 增加主人sp t_horse_model.f_add_owner_sp
	 * 
	 * @param addOwnerSp
	 *            the value for t_horse_model.f_add_owner_sp
	 */
	public void setAddOwnerSp(float addOwnerSp) {
		this.addOwnerSp = addOwnerSp;
	}

	/**
	 * 增加主人攻击 t_horse_model.f_add_owner_attack
	 * 
	 * @return the value of t_horse_model.f_add_owner_attack
	 */
	public float getAddOwnerAttack() {
		return addOwnerAttack;
	}

	/**
	 * 增加主人攻击 t_horse_model.f_add_owner_attack
	 * 
	 * @param addOwnerAttack
	 *            the value for t_horse_model.f_add_owner_attack
	 */
	public void setAddOwnerAttack(float addOwnerAttack) {
		this.addOwnerAttack = addOwnerAttack;
	}

	/**
	 * 增加主人防御 t_horse_model.f_add_owner_defence
	 * 
	 * @return the value of t_horse_model.f_add_owner_defence
	 */
	public float getAddOwnerDefence() {
		return addOwnerDefence;
	}

	/**
	 * 增加主人防御 t_horse_model.f_add_owner_defence
	 * 
	 * @param addOwnerDefence
	 *            the value for t_horse_model.f_add_owner_defence
	 */
	public void setAddOwnerDefence(float addOwnerDefence) {
		this.addOwnerDefence = addOwnerDefence;
	}

	/**
	 * 增加主人暴击 t_horse_model.f_add_owner_crt
	 * 
	 * @return the value of t_horse_model.f_add_owner_crt
	 */
	public float getAddOwnerCrt() {
		return addOwnerCrt;
	}

	/**
	 * 增加主人暴击 t_horse_model.f_add_owner_crt
	 * 
	 * @param addOwnerCrt
	 *            the value for t_horse_model.f_add_owner_crt
	 */
	public void setAddOwnerCrt(float addOwnerCrt) {
		this.addOwnerCrt = addOwnerCrt;
	}

	/**
	 * 增加闪避 t_horse_model.f_add_owner_dodge
	 * 
	 * @return the value of t_horse_model.f_add_owner_dodge
	 */
	public float getAddOwnerDodge() {
		return addOwnerDodge;
	}

	/**
	 * 增加闪避 t_horse_model.f_add_owner_dodge
	 * 
	 * @param addOwnerDodge
	 *            the value for t_horse_model.f_add_owner_dodge
	 */
	public void setAddOwnerDodge(float addOwnerDodge) {
		this.addOwnerDodge = addOwnerDodge;
	}

	/**
	 * 增加攻击速度 t_horse_model.f_add_owner_atkspeed
	 * 
	 * @return the value of t_horse_model.f_add_owner_atkspeed
	 */
	public float getAddOwnerAtkspeed() {
		return addOwnerAtkspeed;
	}

	/**
	 * 增加攻击速度 t_horse_model.f_add_owner_atkspeed
	 * 
	 * @param addOwnerAtkspeed
	 *            the value for t_horse_model.f_add_owner_atkspeed
	 */
	public void setAddOwnerAtkspeed(float addOwnerAtkspeed) {
		this.addOwnerAtkspeed = addOwnerAtkspeed;
	}

	/**
	 * 增加移动速度 t_horse_model.f_add_owner_movespeed
	 * 
	 * @return the value of t_horse_model.f_add_owner_movespeed
	 */
	public float getAddOwnerMovespeed() {
		return addOwnerMovespeed;
	}

	/**
	 * 增加移动速度 t_horse_model.f_add_owner_movespeed
	 * 
	 * @param addOwnerMovespeed
	 *            the value for t_horse_model.f_add_owner_movespeed
	 */
	public void setAddOwnerMovespeed(float addOwnerMovespeed) {
		this.addOwnerMovespeed = addOwnerMovespeed;
	}

	/**
	 * 初始化等级 t_horse_model.f_init_level
	 * 
	 * @return the value of t_horse_model.f_init_level
	 */
	public Integer getInitLevel() {
		return initLevel;
	}

	/**
	 * 初始化等级 t_horse_model.f_init_level
	 * 
	 * @param initLevel
	 *            the value for t_horse_model.f_init_level
	 */
	public void setInitLevel(Integer initLevel) {
		this.initLevel = initLevel;
	}

	/**
	 * 活力最小 t_horse_model.f_livingness_min
	 * 
	 * @return the value of t_horse_model.f_livingness_min
	 */
	public Integer getLivingnessMin() {
		return livingnessMin;
	}

	/**
	 * 活力最小 t_horse_model.f_livingness_min
	 * 
	 * @param livingnessMin
	 *            the value for t_horse_model.f_livingness_min
	 */
	public void setLivingnessMin(Integer livingnessMin) {
		this.livingnessMin = livingnessMin;
	}

	/**
	 * 活力最大 t_horse_model.f_livingness_max
	 * 
	 * @return the value of t_horse_model.f_livingness_max
	 */
	public Integer getLivingnessMax() {
		return livingnessMax;
	}

	/**
	 * 活力最大 t_horse_model.f_livingness_max
	 * 
	 * @param livingnessMax
	 *            the value for t_horse_model.f_livingness_max
	 */
	public void setLivingnessMax(Integer livingnessMax) {
		this.livingnessMax = livingnessMax;
	}

	/**
	 * 最大允许的技能计数 t_horse_model.f_max_permit_skill_count
	 * 
	 * @return the value of t_horse_model.f_max_permit_skill_count
	 */
	public Integer getMaxPermitSkillCount() {
		return maxPermitSkillCount;
	}

	/**
	 * 最大允许的技能计数 t_horse_model.f_max_permit_skill_count
	 * 
	 * @param maxPermitSkillCount
	 *            the value for t_horse_model.f_max_permit_skill_count
	 */
	public void setMaxPermitSkillCount(Integer maxPermitSkillCount) {
		this.maxPermitSkillCount = maxPermitSkillCount;
	}

	/**
	 * 蹄子光效资源id t_horse_model.f_foot_effect_id
	 * 
	 * @return the value of t_horse_model.f_foot_effect_id
	 */
	public Integer getFootEffectId() {
		return footEffectId;
	}

	/**
	 * 蹄子光效资源id t_horse_model.f_foot_effect_id
	 * 
	 * @param footEffectId
	 *            the value for t_horse_model.f_foot_effect_id
	 */
	public void setFootEffectId(Integer footEffectId) {
		this.footEffectId = footEffectId;
	}

	/**
	 * 展示坐骑的攻击时间间隔:单位毫秒 t_horse_model.f_attack_speed
	 * 
	 * @return the value of t_horse_model.f_attack_speed
	 */
	public Integer getAttackSpeed() {
		return attackSpeed;
	}

	/**
	 * 展示坐骑的攻击时间间隔:单位毫秒 t_horse_model.f_attack_speed
	 * 
	 * @param attackSpeed
	 *            the value for t_horse_model.f_attack_speed
	 */
	public void setAttackSpeed(Integer attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	/**
	 * 坐骑展示时自身的移动速度 t_horse_model.f_horse_movespeed
	 * 
	 * @return the value of t_horse_model.f_horse_movespeed
	 */
	public Integer getHorseMovespeed() {
		return horseMovespeed;
	}

	/**
	 * 坐骑展示时自身的移动速度 t_horse_model.f_horse_movespeed
	 * 
	 * @param horseMovespeed
	 *            the value for t_horse_model.f_horse_movespeed
	 */
	public void setHorseMovespeed(Integer horseMovespeed) {
		this.horseMovespeed = horseMovespeed;
	}

	/**
	 * 捕获坐骑,对人物等级的限制 t_horse_model.f_catch_me_grade_limit
	 * 
	 * @return the value of t_horse_model.f_catch_me_grade_limit
	 */
	public Integer getCatchMeGradeLimit() {
		return catchMeGradeLimit;
	}

	/**
	 * 捕获坐骑,对人物等级的限制 t_horse_model.f_catch_me_grade_limit
	 * 
	 * @param catchMeGradeLimit
	 *            the value for t_horse_model.f_catch_me_grade_limit
	 */
	public void setCatchMeGradeLimit(Integer catchMeGradeLimit) {
		this.catchMeGradeLimit = catchMeGradeLimit;
	}

	/**
	 * 使用坐骑时,对角色的等级限制 t_horse_model.f_use_me_grade_limit
	 * 
	 * @return the value of t_horse_model.f_use_me_grade_limit
	 */
	public Integer getUseMeGradeLimit() {
		return useMeGradeLimit;
	}

	/**
	 * 使用坐骑时,对角色的等级限制 t_horse_model.f_use_me_grade_limit
	 * 
	 * @param useMeGradeLimit
	 *            the value for t_horse_model.f_use_me_grade_limit
	 */
	public void setUseMeGradeLimit(Integer useMeGradeLimit) {
		this.useMeGradeLimit = useMeGradeLimit;
	}

	/**
	 * 攻击时声音编号 t_horse_model.f_attack_audio
	 * 
	 * @return the value of t_horse_model.f_attack_audio
	 */
	public Integer getAttackAudio() {
		return attackAudio;
	}

	/**
	 * 攻击时声音编号 t_horse_model.f_attack_audio
	 * 
	 * @param attackAudio
	 *            the value for t_horse_model.f_attack_audio
	 */
	public void setAttackAudio(Integer attackAudio) {
		this.attackAudio = attackAudio;
	}

	/**
	 * 被攻击的声音 t_horse_model.f_hurt_audio
	 * 
	 * @return the value of t_horse_model.f_hurt_audio
	 */
	public Integer getHurtAudio() {
		return hurtAudio;
	}

	/**
	 * 被攻击的声音 t_horse_model.f_hurt_audio
	 * 
	 * @param hurtAudio
	 *            the value for t_horse_model.f_hurt_audio
	 */
	public void setHurtAudio(Integer hurtAudio) {
		this.hurtAudio = hurtAudio;
	}

	/**
	 * 死亡时的声音编号 t_horse_model.f_die_audio
	 * 
	 * @return the value of t_horse_model.f_die_audio
	 */
	public Integer getDieAudio() {
		return dieAudio;
	}

	/**
	 * 死亡时的声音编号 t_horse_model.f_die_audio
	 * 
	 * @param dieAudio
	 *            the value for t_horse_model.f_die_audio
	 */
	public void setDieAudio(Integer dieAudio) {
		this.dieAudio = dieAudio;
	}

	/**
	 * 是否绑定 0不是/1是 t_horse_model.f_bind
	 * 
	 * @return the value of t_horse_model.f_bind
	 */
	public Integer getBind() {
		return bind;
	}

	/**
	 * 是否绑定 0不是/1是 t_horse_model.f_bind
	 * 
	 * @param bind
	 *            the value for t_horse_model.f_bind
	 */
	public void setBind(Integer bind) {
		this.bind = bind;
	}

	/**
	 * 进阶最小次数 t_horse_model.f_jinjie_min_count
	 * 
	 * @return the value of t_horse_model.f_jinjie_min_count
	 */
	public Integer getJinjieMinCount() {
		return jinjieMinCount;
	}

	/**
	 * 进阶最小次数 t_horse_model.f_jinjie_min_count
	 * 
	 * @param jinjieMinCount
	 *            the value for t_horse_model.f_jinjie_min_count
	 */
	public void setJinjieMinCount(Integer jinjieMinCount) {
		this.jinjieMinCount = jinjieMinCount;
	}

	/**
	 * 进阶最大次数 t_horse_model.f_jinjie_max_count
	 * 
	 * @return the value of t_horse_model.f_jinjie_max_count
	 */
	public Integer getJinjieMaxCount() {
		return jinjieMaxCount;
	}

	/**
	 * 进阶最大次数 t_horse_model.f_jinjie_max_count
	 * 
	 * @param jinjieMaxCount
	 *            the value for t_horse_model.f_jinjie_max_count
	 */
	public void setJinjieMaxCount(Integer jinjieMaxCount) {
		this.jinjieMaxCount = jinjieMaxCount;
	}

	/**
	 * 马的类别 t_horse_model.f_kind
	 * 
	 * @return the value of t_horse_model.f_kind
	 */
	public Integer getKind() {
		return kind;
	}

	/**
	 * 马的类别 t_horse_model.f_kind
	 * 
	 * @param kind
	 *            the value for t_horse_model.f_kind
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}

	/**
	 * 进阶显示成功几率 t_horse_model.f_jinjie_view_probability
	 * 
	 * @return the value of t_horse_model.f_jinjie_view_probability
	 */
	public Integer getJinjieZhenqi() {
		return jinjieZhenqi;
	}

	/**
	 * 进阶显示成功几率 t_horse_model.f_jinjie_view_probability
	 * 
	 * @param jinjieZhenqi
	 *            the value for t_horse_model.f_jinjie_view_probability
	 */
	public void setJinjieZhenqi(Integer jinjieZhenqi) {
		this.jinjieZhenqi = jinjieZhenqi;
	}

	/**
	 * 名称国际化 t_horse_model.f_name_i18n
	 * 
	 * @return the value of t_horse_model.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 名称国际化 t_horse_model.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_horse_model.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	public float getAddOwnerHit() {
		return addOwnerHit;
	}

	public void setAddOwnerHit(float addOwnerHit) {
		this.addOwnerHit = addOwnerHit;
	}

	/**
	 * 内丹cd，以秒级单位
	 * 
	 * @return
	 */
	public long getNeidanCdtime() {
		return neidanCdtime;
	}

	public void setNeidanCdtime(long neidanCdtime) {
		this.neidanCdtime = neidanCdtime;
	}

	/**
	 * 灵宠产生内丹道具的id
	 * 
	 * @return
	 */
	public int getChangeModelId() {
		return changeModelId;
	}

	public void setChangeModelId(int changeModelId) {
		this.changeModelId = changeModelId;
	}

	/**
	 * 该灵宠是否可以学习神技 1可以，0不可以
	 * 
	 * @return
	 */
	public byte getIsUseShenji() {
		return isUseShenji;
	}

	public void setIsUseShenji(byte isUseShenji) {
		this.isUseShenji = isUseShenji;
	}

	public float getAttackQzxs() {
		return attackQzxs;
	}

	public void setAttackQzxs(float attackQzxs) {
		this.attackQzxs = attackQzxs;
	}

	public float getDefenceQzxs() {
		return defenceQzxs;
	}

	public void setDefenceQzxs(float defenceQzxs) {
		this.defenceQzxs = defenceQzxs;
	}

	public float getHpQzxs() {
		return hpQzxs;
	}

	public void setHpQzxs(float hpQzxs) {
		this.hpQzxs = hpQzxs;
	}

	public float getMpQzxs() {
		return mpQzxs;
	}

	public void setMpQzxs(float mpQzxs) {
		this.mpQzxs = mpQzxs;
	}

	public float getHitQzxs() {
		return hitQzxs;
	}

	public void setHitQzxs(float hitQzxs) {
		this.hitQzxs = hitQzxs;
	}

	public float getDodgeQzxs() {
		return dodgeQzxs;
	}

	public void setDodgeQzxs(float dodgeQzxs) {
		this.dodgeQzxs = dodgeQzxs;
	}

	public float getCrtQzxs() {
		return crtQzxs;
	}

	public void setCrtQzxs(float crtQzxs) {
		this.crtQzxs = crtQzxs;
	}

	/**
	 * 品质 白1 蓝2 绿3 紫4
	 * 
	 * @return
	 */
	public Integer getQuality() {
		return quality;
	}

	/**
	 * 品质 白1 蓝2 绿3 紫4
	 * 
	 * @param quality
	 */
	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public int getResetSkillCopper() {
		return resetSkillCopper;
	}

	public void setResetSkillCopper(int resetSkillCopper) {
		this.resetSkillCopper = resetSkillCopper;
	}

	public int getResetSkillZhenqi() {
		return resetSkillZhenqi;
	}

	public void setResetSkillZhenqi(int resetSkillZhenqi) {
		this.resetSkillZhenqi = resetSkillZhenqi;
	}

	/**
	 * 灵宠基础模型id，一般为一品的灵物id
	 * 
	 * @return
	 */
	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}

}
