package net.snake.gamemodel.faction.bean;

import net.snake.ibatis.IbatisEntity;

public class FactionFlag  implements IbatisEntity{

	/**
	 * t_faction_flag.id
	 * 
	 */
	private Integer id;
	/**
	 * 帮会等级 t_faction_flag.f_grade
	 * 
	 */
	private Integer fGrade;
	/**
	 * 升级铜钱需求条件 t_faction_flag.f_copper_limite
	 * 
	 */
	private Integer fCopperLimite;
	/**
	 * 升级物品1需要个数 t_faction_flag.f_qinglong_count
	 * 
	 */
	private Integer fQinglongCount;
	/**
	 * 升级物品1需要个数 t_faction_flag.f_baihu_count
	 * 
	 */
	private Integer fBaihuCount;
	/**
	 * 升级物品1需要个数 t_faction_flag.f_zhuqu_count
	 * 
	 */
	private Integer fZhuquCount;
	/**
	 * 升级物品1需要个数 t_faction_flag.f_xuanwu_count
	 * 
	 */
	private Integer fXuanwuCount;
	/**
	 * 帮旗血量 t_faction_flag.f_bangqi_maxhp
	 * 
	 */
	private Integer fBangqiMaxhp;
	/**
	 * 攻击加成 单位1/10000 t_faction_flag.attack
	 * 
	 */
	private Integer attack;
	/**
	 * 防御 单位1/10000 t_faction_flag.defence
	 * 
	 */
	private Integer defence;
	/**
	 * 暴击 单位1/10000 t_faction_flag.crt
	 * 
	 */
	private Integer crt;
	/**
	 * 闪避 单位1/10000 t_faction_flag.dodge
	 * 
	 */
	private Integer dodge;
	/**
	 * 最大加成限制 生命值上限 单位1/10000 t_faction_flag.max_hp_limite
	 * 
	 */
	private Integer maxHpLimite;
	/**
	 * 最大加成限制 体力值上限 单位1/10000 t_faction_flag.max_sp_limite
	 * 
	 */
	private Integer maxSpLimite;
	/**
	 * 最大加成限制 内力上限 单位1/10000 t_faction_flag.max_mp_limite
	 * 
	 */
	private Integer maxMpLimite;
	/**
	 * 攻击速度 单位1/10000 t_faction_flag.attackspeed
	 * 
	 */
	private Integer attackspeed;
	/**
	 * 移动速度 单位1/10000 t_faction_flag.movespeed
	 * 
	 */
	private Integer movespeed;
	/**
	 * 关联bufferId 帮旗属性加成buffer t_faction_flag.buffer_id
	 * 
	 */
	private Integer bufferId;
	/**
	 * 打怪经验加成 t_faction_flag.f_jiacheng_exp
	 * 
	 */
	private Integer fJiachengExp;
	/**
	 * 打坐真气加成 t_faction_flag.f_jiacheng_zheqi
	 * 
	 */
	private Integer fJiachengZheqi;
	/**
	 * 攻击最大加成限制 单位1/10000 t_faction_flag.max_attack_limite
	 * 
	 */
	private Integer maxAttackLimite;
	/**
	 * 最大加成限制 防御 单位1/10000 t_faction_flag.max_defence_limite
	 * 
	 */
	private Integer maxDefenceLimite;
	/**
	 * 最大加成限制 暴击 单位1/10000 t_faction_flag.max_crt_limite
	 * 
	 */
	private Integer maxCrtLimite;
	/**
	 * 最大加成限制 闪避 单位1/10000 t_faction_flag.max_dodge_limite
	 * 
	 */
	private Integer maxDodgeLimite;
	/**
	 * 血量加成值  单位1/10000 t_faction_flag.hp
	 * 
	 */
	private Integer hp;
	/**
	 * 体力值上限 单位1/10000 t_faction_flag.sp
	 * 
	 */
	private Integer sp;
	/**
	 * 内力上限 单位1/10000 t_faction_flag.mp
	 * 
	 */
	private Integer mp;
	/**
	 * 最大加成限制 攻击速度 单位1/10000 t_faction_flag.max_attackspeed_limite
	 * 
	 */
	private Integer maxAttackspeedLimite;
	/**
	 * 最大加成限制 移动速度 单位1/10000 t_faction_flag.max_movespeed_limite
	 * 
	 */
	private Integer maxMovespeedLimite;
	/**
	 * 帮旗经验buffer t_faction_flag.exp_buffer_id
	 * 
	 */
	private Integer expBufferId;
	/**
	 * 血量恢复值  单位 每分钟 t_faction_flag.renew_hp
	 * 
	 */
	private Integer renewHp;

	/**
	 * t_faction_flag.id
	 * @return  the value of t_faction_flag.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_faction_flag.id
	 * @param id  the value for t_faction_flag.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 帮会等级 t_faction_flag.f_grade
	 * @return  the value of t_faction_flag.f_grade
	 * 
	 */
	public Integer getfGrade() {
		return fGrade;
	}

	/**
	 * 帮会等级 t_faction_flag.f_grade
	 * @param fGrade  the value for t_faction_flag.f_grade
	 * 
	 */
	public void setfGrade(Integer fGrade) {
		this.fGrade = fGrade;
	}

	/**
	 * 升级铜钱需求条件 t_faction_flag.f_copper_limite
	 * @return  the value of t_faction_flag.f_copper_limite
	 * 
	 */
	public Integer getfCopperLimite() {
		return fCopperLimite;
	}

	/**
	 * 升级铜钱需求条件 t_faction_flag.f_copper_limite
	 * @param fCopperLimite  the value for t_faction_flag.f_copper_limite
	 * 
	 */
	public void setfCopperLimite(Integer fCopperLimite) {
		this.fCopperLimite = fCopperLimite;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_qinglong_count
	 * @return  the value of t_faction_flag.f_qinglong_count
	 * 
	 */
	public Integer getfQinglongCount() {
		return fQinglongCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_qinglong_count
	 * @param fQinglongCount  the value for t_faction_flag.f_qinglong_count
	 * 
	 */
	public void setfQinglongCount(Integer fQinglongCount) {
		this.fQinglongCount = fQinglongCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_baihu_count
	 * @return  the value of t_faction_flag.f_baihu_count
	 * 
	 */
	public Integer getfBaihuCount() {
		return fBaihuCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_baihu_count
	 * @param fBaihuCount  the value for t_faction_flag.f_baihu_count
	 * 
	 */
	public void setfBaihuCount(Integer fBaihuCount) {
		this.fBaihuCount = fBaihuCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_zhuqu_count
	 * @return  the value of t_faction_flag.f_zhuqu_count
	 * 
	 */
	public Integer getfZhuquCount() {
		return fZhuquCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_zhuqu_count
	 * @param fZhuquCount  the value for t_faction_flag.f_zhuqu_count
	 * 
	 */
	public void setfZhuquCount(Integer fZhuquCount) {
		this.fZhuquCount = fZhuquCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_xuanwu_count
	 * @return  the value of t_faction_flag.f_xuanwu_count
	 * 
	 */
	public Integer getfXuanwuCount() {
		return fXuanwuCount;
	}

	/**
	 * 升级物品1需要个数 t_faction_flag.f_xuanwu_count
	 * @param fXuanwuCount  the value for t_faction_flag.f_xuanwu_count
	 * 
	 */
	public void setfXuanwuCount(Integer fXuanwuCount) {
		this.fXuanwuCount = fXuanwuCount;
	}

	/**
	 * 帮旗血量 t_faction_flag.f_bangqi_maxhp
	 * @return  the value of t_faction_flag.f_bangqi_maxhp
	 * 
	 */
	public Integer getfBangqiMaxhp() {
		return fBangqiMaxhp;
	}

	/**
	 * 帮旗血量 t_faction_flag.f_bangqi_maxhp
	 * @param fBangqiMaxhp  the value for t_faction_flag.f_bangqi_maxhp
	 * 
	 */
	public void setfBangqiMaxhp(Integer fBangqiMaxhp) {
		this.fBangqiMaxhp = fBangqiMaxhp;
	}

	/**
	 * 攻击加成 单位1/10000 t_faction_flag.attack
	 * @return  the value of t_faction_flag.attack
	 * 
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 攻击加成 单位1/10000 t_faction_flag.attack
	 * @param attack  the value for t_faction_flag.attack
	 * 
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 防御 单位1/10000 t_faction_flag.defence
	 * @return  the value of t_faction_flag.defence
	 * 
	 */
	public Integer getDefence() {
		return defence;
	}

	/**
	 * 防御 单位1/10000 t_faction_flag.defence
	 * @param defence  the value for t_faction_flag.defence
	 * 
	 */
	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	/**
	 * 暴击 单位1/10000 t_faction_flag.crt
	 * @return  the value of t_faction_flag.crt
	 * 
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 暴击 单位1/10000 t_faction_flag.crt
	 * @param crt  the value for t_faction_flag.crt
	 * 
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 闪避 单位1/10000 t_faction_flag.dodge
	 * @return  the value of t_faction_flag.dodge
	 * 
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 闪避 单位1/10000 t_faction_flag.dodge
	 * @param dodge  the value for t_faction_flag.dodge
	 * 
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	/**
	 * 最大加成限制 生命值上限 单位1/10000 t_faction_flag.max_hp_limite
	 * @return  the value of t_faction_flag.max_hp_limite
	 * 
	 */
	public Integer getMaxHpLimite() {
		return maxHpLimite;
	}

	/**
	 * 最大加成限制 生命值上限 单位1/10000 t_faction_flag.max_hp_limite
	 * @param maxHpLimite  the value for t_faction_flag.max_hp_limite
	 * 
	 */
	public void setMaxHpLimite(Integer maxHpLimite) {
		this.maxHpLimite = maxHpLimite;
	}

	/**
	 * 最大加成限制 体力值上限 单位1/10000 t_faction_flag.max_sp_limite
	 * @return  the value of t_faction_flag.max_sp_limite
	 * 
	 */
	public Integer getMaxSpLimite() {
		return maxSpLimite;
	}

	/**
	 * 最大加成限制 体力值上限 单位1/10000 t_faction_flag.max_sp_limite
	 * @param maxSpLimite  the value for t_faction_flag.max_sp_limite
	 * 
	 */
	public void setMaxSpLimite(Integer maxSpLimite) {
		this.maxSpLimite = maxSpLimite;
	}

	/**
	 * 最大加成限制 内力上限 单位1/10000 t_faction_flag.max_mp_limite
	 * @return  the value of t_faction_flag.max_mp_limite
	 * 
	 */
	public Integer getMaxMpLimite() {
		return maxMpLimite;
	}

	/**
	 * 最大加成限制 内力上限 单位1/10000 t_faction_flag.max_mp_limite
	 * @param maxMpLimite  the value for t_faction_flag.max_mp_limite
	 * 
	 */
	public void setMaxMpLimite(Integer maxMpLimite) {
		this.maxMpLimite = maxMpLimite;
	}

	/**
	 * 攻击速度 单位1/10000 t_faction_flag.attackspeed
	 * @return  the value of t_faction_flag.attackspeed
	 * 
	 */
	public Integer getAttackspeed() {
		return attackspeed;
	}

	/**
	 * 攻击速度 单位1/10000 t_faction_flag.attackspeed
	 * @param attackspeed  the value for t_faction_flag.attackspeed
	 * 
	 */
	public void setAttackspeed(Integer attackspeed) {
		this.attackspeed = attackspeed;
	}

	/**
	 * 移动速度 单位1/10000 t_faction_flag.movespeed
	 * @return  the value of t_faction_flag.movespeed
	 * 
	 */
	public Integer getMovespeed() {
		return movespeed;
	}

	/**
	 * 移动速度 单位1/10000 t_faction_flag.movespeed
	 * @param movespeed  the value for t_faction_flag.movespeed
	 * 
	 */
	public void setMovespeed(Integer movespeed) {
		this.movespeed = movespeed;
	}

	/**
	 * 关联bufferId 帮旗属性加成buffer t_faction_flag.buffer_id
	 * @return  the value of t_faction_flag.buffer_id
	 * 
	 */
	public Integer getBufferId() {
		return bufferId;
	}

	/**
	 * 关联bufferId 帮旗属性加成buffer t_faction_flag.buffer_id
	 * @param bufferId  the value for t_faction_flag.buffer_id
	 * 
	 */
	public void setBufferId(Integer bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * 打怪经验加成 t_faction_flag.f_jiacheng_exp
	 * @return  the value of t_faction_flag.f_jiacheng_exp
	 * 
	 */
	public Integer getfJiachengExp() {
		return fJiachengExp;
	}

	/**
	 * 打怪经验加成 t_faction_flag.f_jiacheng_exp
	 * @param fJiachengExp  the value for t_faction_flag.f_jiacheng_exp
	 * 
	 */
	public void setfJiachengExp(Integer fJiachengExp) {
		this.fJiachengExp = fJiachengExp;
	}

	/**
	 * 打坐真气加成 t_faction_flag.f_jiacheng_zheqi
	 * @return  the value of t_faction_flag.f_jiacheng_zheqi
	 * 
	 */
	public Integer getfJiachengZheqi() {
		return fJiachengZheqi;
	}

	/**
	 * 打坐真气加成 t_faction_flag.f_jiacheng_zheqi
	 * @param fJiachengZheqi  the value for t_faction_flag.f_jiacheng_zheqi
	 * 
	 */
	public void setfJiachengZheqi(Integer fJiachengZheqi) {
		this.fJiachengZheqi = fJiachengZheqi;
	}

	/**
	 * 攻击最大加成限制 单位1/10000 t_faction_flag.max_attack_limite
	 * @return  the value of t_faction_flag.max_attack_limite
	 * 
	 */
	public Integer getMaxAttackLimite() {
		return maxAttackLimite;
	}

	/**
	 * 攻击最大加成限制 单位1/10000 t_faction_flag.max_attack_limite
	 * @param maxAttackLimite  the value for t_faction_flag.max_attack_limite
	 * 
	 */
	public void setMaxAttackLimite(Integer maxAttackLimite) {
		this.maxAttackLimite = maxAttackLimite;
	}

	/**
	 * 最大加成限制 防御 单位1/10000 t_faction_flag.max_defence_limite
	 * @return  the value of t_faction_flag.max_defence_limite
	 * 
	 */
	public Integer getMaxDefenceLimite() {
		return maxDefenceLimite;
	}

	/**
	 * 最大加成限制 防御 单位1/10000 t_faction_flag.max_defence_limite
	 * @param maxDefenceLimite  the value for t_faction_flag.max_defence_limite
	 * 
	 */
	public void setMaxDefenceLimite(Integer maxDefenceLimite) {
		this.maxDefenceLimite = maxDefenceLimite;
	}

	/**
	 * 最大加成限制 暴击 单位1/10000 t_faction_flag.max_crt_limite
	 * @return  the value of t_faction_flag.max_crt_limite
	 * 
	 */
	public Integer getMaxCrtLimite() {
		return maxCrtLimite;
	}

	/**
	 * 最大加成限制 暴击 单位1/10000 t_faction_flag.max_crt_limite
	 * @param maxCrtLimite  the value for t_faction_flag.max_crt_limite
	 * 
	 */
	public void setMaxCrtLimite(Integer maxCrtLimite) {
		this.maxCrtLimite = maxCrtLimite;
	}

	/**
	 * 最大加成限制 闪避 单位1/10000 t_faction_flag.max_dodge_limite
	 * @return  the value of t_faction_flag.max_dodge_limite
	 * 
	 */
	public Integer getMaxDodgeLimite() {
		return maxDodgeLimite;
	}

	/**
	 * 最大加成限制 闪避 单位1/10000 t_faction_flag.max_dodge_limite
	 * @param maxDodgeLimite  the value for t_faction_flag.max_dodge_limite
	 * 
	 */
	public void setMaxDodgeLimite(Integer maxDodgeLimite) {
		this.maxDodgeLimite = maxDodgeLimite;
	}

	/**
	 * 血量加成值  单位1/10000 t_faction_flag.hp
	 * @return  the value of t_faction_flag.hp
	 * 
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * 血量加成值  单位1/10000 t_faction_flag.hp
	 * @param hp  the value for t_faction_flag.hp
	 * 
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 体力值上限 单位1/10000 t_faction_flag.sp
	 * @return  the value of t_faction_flag.sp
	 * 
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * 体力值上限 单位1/10000 t_faction_flag.sp
	 * @param sp  the value for t_faction_flag.sp
	 * 
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * 内力上限 单位1/10000 t_faction_flag.mp
	 * @return  the value of t_faction_flag.mp
	 * 
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * 内力上限 单位1/10000 t_faction_flag.mp
	 * @param mp  the value for t_faction_flag.mp
	 * 
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * 最大加成限制 攻击速度 单位1/10000 t_faction_flag.max_attackspeed_limite
	 * @return  the value of t_faction_flag.max_attackspeed_limite
	 * 
	 */
	public Integer getMaxAttackspeedLimite() {
		return maxAttackspeedLimite;
	}

	/**
	 * 最大加成限制 攻击速度 单位1/10000 t_faction_flag.max_attackspeed_limite
	 * @param maxAttackspeedLimite  the value for t_faction_flag.max_attackspeed_limite
	 * 
	 */
	public void setMaxAttackspeedLimite(Integer maxAttackspeedLimite) {
		this.maxAttackspeedLimite = maxAttackspeedLimite;
	}

	/**
	 * 最大加成限制 移动速度 单位1/10000 t_faction_flag.max_movespeed_limite
	 * @return  the value of t_faction_flag.max_movespeed_limite
	 * 
	 */
	public Integer getMaxMovespeedLimite() {
		return maxMovespeedLimite;
	}

	/**
	 * 最大加成限制 移动速度 单位1/10000 t_faction_flag.max_movespeed_limite
	 * @param maxMovespeedLimite  the value for t_faction_flag.max_movespeed_limite
	 * 
	 */
	public void setMaxMovespeedLimite(Integer maxMovespeedLimite) {
		this.maxMovespeedLimite = maxMovespeedLimite;
	}

	/**
	 * 帮旗经验buffer t_faction_flag.exp_buffer_id
	 * @return  the value of t_faction_flag.exp_buffer_id
	 * 
	 */
	public Integer getExpBufferId() {
		return expBufferId;
	}

	/**
	 * 帮旗经验buffer t_faction_flag.exp_buffer_id
	 * @param expBufferId  the value for t_faction_flag.exp_buffer_id
	 * 
	 */
	public void setExpBufferId(Integer expBufferId) {
		this.expBufferId = expBufferId;
	}

	/**
	 * 血量恢复值  单位 每分钟 t_faction_flag.renew_hp
	 * @return  the value of t_faction_flag.renew_hp
	 * 
	 */
	public Integer getRenewHp() {
		return renewHp;
	}

	/**
	 * 血量恢复值  单位 每分钟 t_faction_flag.renew_hp
	 * @param renewHp  the value for t_faction_flag.renew_hp
	 * 
	 */
	public void setRenewHp(Integer renewHp) {
		this.renewHp = renewHp;
	}
}
