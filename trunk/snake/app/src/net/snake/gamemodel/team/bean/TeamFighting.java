package net.snake.gamemodel.team.bean;

import net.snake.ibatis.IbatisEntity;

public class TeamFighting implements Comparable<TeamFighting>, IbatisEntity {

	/**
	 * t_teamfighting.id
	 */
	private Integer id;
	/**
	 * 显示顺序（组队阵法列表排序） t_teamfighting.position
	 */
	private Integer position;
	/**
	 * 阵法名字 t_teamfighting.name
	 */
	private String name;
	/**
	 * 阵法描述 t_teamfighting.decs
	 */
	private String decs;
	/**
	 * 攻击加成 单位1/10000 t_teamfighting.attack
	 */
	private Integer attack;
	/**
	 * 防御 单位1/10000 t_teamfighting.defence
	 */
	private Integer defence;
	/**
	 * 暴击 单位1/10000 t_teamfighting.crt
	 */
	private Integer crt;
	/**
	 * 闪避 单位1/10000 t_teamfighting.dodge
	 */
	private Integer dodge;
	/**
	 * 生命值上限 单位1/10000 t_teamfighting.max_hp
	 */
	private Integer maxHp;
	/**
	 * 体力值上限 单位1/10000 t_teamfighting.max_sp
	 */
	private Integer maxSp;
	/**
	 * 学习秘籍id t_teamfighting.learn_id
	 */
	private Integer learnId;
	/**
	 * 内力上限 单位1/10000 t_teamfighting.max_mp
	 */
	private Integer maxMp;
	/**
	 * 攻击速度 单位1/10000 t_teamfighting.attackspeed
	 */
	private Integer attackspeed;
	/**
	 * 移动速度 单位1/10000 t_teamfighting.movespeed
	 */
	private Integer movespeed;
	/**
	 * 关联bufferId t_teamfighting.buffer_id
	 */
	private Integer bufferId;
	/**
	 * 阵法名字国际化 t_teamfighting.name_i18n
	 */
	private String nameI18n;

	/**
	 * t_teamfighting.id
	 * 
	 * @return the value of t_teamfighting.id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_teamfighting.id
	 * 
	 * @param id
	 *            the value for t_teamfighting.id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 显示顺序（组队阵法列表排序） t_teamfighting.position
	 * 
	 * @return the value of t_teamfighting.position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * 显示顺序（组队阵法列表排序） t_teamfighting.position
	 * 
	 * @param position
	 *            the value for t_teamfighting.position
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * 阵法名字 t_teamfighting.name
	 * 
	 * @return the value of t_teamfighting.name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 阵法名字 t_teamfighting.name
	 * 
	 * @param name
	 *            the value for t_teamfighting.name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 阵法描述 t_teamfighting.decs
	 * 
	 * @return the value of t_teamfighting.decs
	 */
	public String getDecs() {
		return decs;
	}

	/**
	 * 阵法描述 t_teamfighting.decs
	 * 
	 * @param decs
	 *            the value for t_teamfighting.decs
	 */
	public void setDecs(String decs) {
		this.decs = decs;
	}

	/**
	 * 攻击加成 单位1/10000 t_teamfighting.attack
	 * 
	 * @return the value of t_teamfighting.attack
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 攻击加成 单位1/10000 t_teamfighting.attack
	 * 
	 * @param attack
	 *            the value for t_teamfighting.attack
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 防御 单位1/10000 t_teamfighting.defence
	 * 
	 * @return the value of t_teamfighting.defence
	 */
	public Integer getDefence() {
		return defence;
	}

	/**
	 * 防御 单位1/10000 t_teamfighting.defence
	 * 
	 * @param defence
	 *            the value for t_teamfighting.defence
	 */
	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	/**
	 * 暴击 单位1/10000 t_teamfighting.crt
	 * 
	 * @return the value of t_teamfighting.crt
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 暴击 单位1/10000 t_teamfighting.crt
	 * 
	 * @param crt
	 *            the value for t_teamfighting.crt
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 闪避 单位1/10000 t_teamfighting.dodge
	 * 
	 * @return the value of t_teamfighting.dodge
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 闪避 单位1/10000 t_teamfighting.dodge
	 * 
	 * @param dodge
	 *            the value for t_teamfighting.dodge
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	/**
	 * 生命值上限 单位1/10000 t_teamfighting.max_hp
	 * 
	 * @return the value of t_teamfighting.max_hp
	 */
	public Integer getMaxHp() {
		return maxHp;
	}

	/**
	 * 生命值上限 单位1/10000 t_teamfighting.max_hp
	 * 
	 * @param maxHp
	 *            the value for t_teamfighting.max_hp
	 */
	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * 体力值上限 单位1/10000 t_teamfighting.max_sp
	 * 
	 * @return the value of t_teamfighting.max_sp
	 */
	public Integer getMaxSp() {
		return maxSp;
	}

	/**
	 * 体力值上限 单位1/10000 t_teamfighting.max_sp
	 * 
	 * @param maxSp
	 *            the value for t_teamfighting.max_sp
	 */
	public void setMaxSp(Integer maxSp) {
		this.maxSp = maxSp;
	}

	/**
	 * 学习秘籍id t_teamfighting.learn_id
	 * 
	 * @return the value of t_teamfighting.learn_id
	 */
	public Integer getLearnId() {
		return learnId;
	}

	/**
	 * 学习秘籍id t_teamfighting.learn_id
	 * 
	 * @param learnId
	 *            the value for t_teamfighting.learn_id
	 */
	public void setLearnId(Integer learnId) {
		this.learnId = learnId;
	}

	/**
	 * 内力上限 单位1/10000 t_teamfighting.max_mp
	 * 
	 * @return the value of t_teamfighting.max_mp
	 */
	public Integer getMaxMp() {
		return maxMp;
	}

	/**
	 * 内力上限 单位1/10000 t_teamfighting.max_mp
	 * 
	 * @param maxMp
	 *            the value for t_teamfighting.max_mp
	 */
	public void setMaxMp(Integer maxMp) {
		this.maxMp = maxMp;
	}

	/**
	 * 攻击速度 单位1/10000 t_teamfighting.attackspeed
	 * 
	 * @return the value of t_teamfighting.attackspeed
	 */
	public Integer getAttackspeed() {
		return attackspeed;
	}

	/**
	 * 攻击速度 单位1/10000 t_teamfighting.attackspeed
	 * 
	 * @param attackspeed
	 *            the value for t_teamfighting.attackspeed
	 */
	public void setAttackspeed(Integer attackspeed) {
		this.attackspeed = attackspeed;
	}

	/**
	 * 移动速度 单位1/10000 t_teamfighting.movespeed
	 * 
	 * @return the value of t_teamfighting.movespeed
	 */
	public Integer getMovespeed() {
		return movespeed;
	}

	/**
	 * 移动速度 单位1/10000 t_teamfighting.movespeed
	 * 
	 * @param movespeed
	 *            the value for t_teamfighting.movespeed
	 */
	public void setMovespeed(Integer movespeed) {
		this.movespeed = movespeed;
	}

	/**
	 * 关联bufferId t_teamfighting.buffer_id
	 * 
	 * @return the value of t_teamfighting.buffer_id
	 */
	public Integer getBufferId() {
		return bufferId;
	}

	/**
	 * 关联bufferId t_teamfighting.buffer_id
	 * 
	 * @param bufferId
	 *            the value for t_teamfighting.buffer_id
	 */
	public void setBufferId(Integer bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * 阵法名字国际化 t_teamfighting.name_i18n
	 * 
	 * @return the value of t_teamfighting.name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 阵法名字国际化 t_teamfighting.name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_teamfighting.name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	@Override
	public int compareTo(TeamFighting o) {
		if (this.getPosition() > o.getPosition()) {
			return 1;
		}
		if (this.getPosition() < o.getPosition()) {
			return -1;
		}
		return 0;
	}
}
