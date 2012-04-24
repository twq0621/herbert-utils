package net.snake.gamemodel.pet.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterHorse implements Cloneable,IbatisEntity{

	/**
	 * 马实例的ID t_character_horse.f_id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * 
	 * 
	 */
	private Integer horseModelId;

	/**
	 * 当前活力 t_character_horse.f_livingness
	 * 
	 * 
	 */
	private Integer livingness;

	/**
	 * 当前级别 t_character_horse.f_grade
	 * 
	 * 
	 */
	private Integer grade;
	/**
	 * 0休息 1坐骑 2展示 3孵化中t_character_horse.f_status
	 * 
	 * 
	 */
	private Integer status;
	/**
	 * 马在人物身上(1-100) 马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * 
	 * 
	 */
	private Integer location;
	
	private Integer pin;//品
	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * 
	 * 
	 */
	private Integer jinjieCount;
	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * 
	 * 
	 */
	private Integer livingnessMax;
	/**
	 * 马的经验 t_character_horse.f_experience
	 * 
	 * 
	 */
	private Integer experience;
	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * 
	 * 
	 */
	private Integer stallCopper;
	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * 
	 * 
	 */
	private Integer stallIngot;
	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * 
	 * 
	 */
	private Date createDate;
	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * 
	 * 
	 */
	private Integer horsePrice;

	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * 
	 * 
	 */
	private Integer defaultSkillId;

	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * 
	 * 
	 */
	private Integer extraAttack;

	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * 
	 * 
	 */
	private Integer extraDefence;
	private Integer extraCrt;
	private Integer extraDodge;
	private Integer extraHit;
	private Integer extraHp;
	private Integer extraMp;

	private Integer attack;
	private Integer defence;
	private Integer crt;
	private Integer dodge;
	private Integer hit;
	private Integer hp;
	private Integer mp;

	private String name;
	private long neidanCdtime;
	private long neidanUsetime;
	private long neidanStarttime;

	/**
	 * 马实例的ID t_character_horse.f_id
	 * 
	 * @return the value of t_character_horse.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 马实例的ID t_character_horse.f_id
	 * 
	 * @param id
	 *            the value for t_character_horse.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * 
	 * @return the value of t_character_horse.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_horse.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * 
	 * @return the value of t_character_horse.f_horse_model_id
	 * 
	 */
	public Integer getHorseModelId() {
		return horseModelId;
	}

	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * 
	 * @param horseModelId
	 *            the value for t_character_horse.f_horse_model_id
	 * 
	 */
	public void setHorseModelId(Integer horseModelId) {
		this.horseModelId = horseModelId;
	}

	/**
	 * 当前活力 t_character_horse.f_livingness 1) 活力值120 -
	 * 100之间：灵宠处于亢奋阶段，属性加成、技能效果和持续时间额外增加20% 2)
	 * 活力值为30-99之间：灵宠处于正常战斗状态，属性加成、技能效果和持续时间为正常值； 3) 活力值为1-29时：
	 * 灵宠处于疲劳状态，属性加成、技能效果和持续时间减半直到活力值恢复； 4) 活力值为0时：灵宠强制进入休息状态，不可参战；
	 * 
	 * @return the value of t_character_horse.f_livingness
	 * 
	 */
	public Integer getLivingness() {
		return livingness;
	}

	/**
	 * 当前活力 t_character_horse.f_livingness
	 * 
	 * @param livingness
	 *            the value for t_character_horse.f_livingness
	 * 
	 */
	public void setLivingness(Integer livingness) {
		this.livingness = livingness;
	}

	/**
	 * 当前级别 t_character_horse.f_grade
	 * 
	 * @return the value of t_character_horse.f_grade
	 * 
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 当前级别 t_character_horse.f_grade
	 * 
	 * @param grade
	 *            the value for t_character_horse.f_grade
	 * 
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 0孵化中,1展示,2收起,3放生t_character_horse.f_status
	 * 
	 * @return the value of t_character_horse.f_status
	 * 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 0孵化中,1展示,2收起,3放生t_character_horse.f_status
	 * 
	 * @param status
	 *            the value for t_character_horse.f_status
	 * 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 马在人物身上(1-100) 马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * 
	 * @return the value of t_character_horse.f_location
	 * 
	 */
	public Integer getLocation() {
		return location;
	}

	/**
	 * 马在人物身上(1-100) 马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * 
	 * @param location
	 *            the value for t_character_horse.f_location
	 * 
	 */
	public void setLocation(Integer location) {
		this.location = location;
	}

	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * 
	 * @return the value of t_character_horse.f_jinjie_count
	 * 
	 */
	public Integer getJinjieCount() {
		return jinjieCount;
	}

	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * 
	 * @param jinjieCount
	 *            the value for t_character_horse.f_jinjie_count
	 * 
	 */
	public void setJinjieCount(Integer jinjieCount) {
		this.jinjieCount = jinjieCount;
	}

	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * 
	 * @return the value of t_character_horse.f_livingness_max
	 * 
	 */
	public Integer getLivingnessMax() {
		return livingnessMax;
	}

	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * 
	 * @param livingnessMax
	 *            the value for t_character_horse.f_livingness_max
	 * 
	 */
	public void setLivingnessMax(Integer livingnessMax) {
		this.livingnessMax = livingnessMax;
	}

	/**
	 * 马的经验 t_character_horse.f_experience
	 * 
	 * @return the value of t_character_horse.f_experience
	 * 
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * 马的经验 t_character_horse.f_experience
	 * 
	 * @param experience
	 *            the value for t_character_horse.f_experience
	 * 
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * 
	 * @return the value of t_character_horse.f_stall_copper
	 * 
	 */
	public Integer getStallCopper() {
		return stallCopper;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * 
	 * @param stallCopper
	 *            the value for t_character_horse.f_stall_copper
	 * 
	 */
	public void setStallCopper(Integer stallCopper) {
		this.stallCopper = stallCopper;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * 
	 * @return the value of t_character_horse.f_stall_ingot
	 * 
	 */
	public Integer getStallIngot() {
		return stallIngot;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * 
	 * @param stallIngot
	 *            the value for t_character_horse.f_stall_ingot
	 * 
	 */
	public void setStallIngot(Integer stallIngot) {
		this.stallIngot = stallIngot;
	}

	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * 
	 * @return the value of t_character_horse.f_create_date
	 * 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * 
	 * @param createDate
	 *            the value for t_character_horse.f_create_date
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * 
	 * @return the value of t_character_horse.f_horse_price
	 * 
	 */
	public Integer getHorsePrice() {
		return horsePrice;
	}

	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * 
	 * @param horsePrice
	 *            the value for t_character_horse.f_horse_price
	 * 
	 */
	public void setHorsePrice(Integer horsePrice) {
		this.horsePrice = horsePrice;
	}

	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * 
	 * @return the value of t_character_horse.f_default_skill_id
	 * 
	 */
	public Integer getDefaultSkillId() {
		return defaultSkillId;
	}

	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * 
	 * @param defaultSkillId
	 *            the value for t_character_horse.f_default_skill_id
	 * 
	 */
	public void setDefaultSkillId(Integer defaultSkillId) {
		this.defaultSkillId = defaultSkillId;
	}

	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * 
	 * @return the value of t_character_horse.f_extra_attack
	 * 
	 */
	public Integer getExtraAttack() {
		return extraAttack;
	}

	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * 
	 * @param extraAttack
	 *            the value for t_character_horse.f_extra_attack
	 * 
	 */
	public void setExtraAttack(Integer extraAttack) {
		this.extraAttack = extraAttack;
	}

	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * 
	 * @return the value of t_character_horse.f_extra_defence
	 * 
	 */
	public Integer getExtraDefence() {
		return extraDefence;
	}

	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * 
	 * @param extraDefence
	 *            the value for t_character_horse.f_extra_defence
	 * 
	 */
	public void setExtraDefence(Integer extraDefence) {
		this.extraDefence = extraDefence;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNeidanCdtime() {
		return neidanCdtime;
	}

	public void setNeidanCdtime(long neidanCdtime) {
		this.neidanCdtime = neidanCdtime;
	}

	public long getNeidanStarttime() {
		return neidanStarttime;
	}

	public void setNeidanStarttime(long neidanStarttime) {
		this.neidanStarttime = neidanStarttime;
	}

	public long getNeidanUsetime() {
		return neidanUsetime;
	}

	public void setNeidanUsetime(long neidanUsetime) {
		this.neidanUsetime = neidanUsetime;
	}

	public Integer getExtraCrt() {
		return extraCrt;
	}

	public void setExtraCrt(Integer extraCrt) {
		this.extraCrt = extraCrt;
	}

	public Integer getExtraDodge() {
		return extraDodge;
	}

	public void setExtraDodge(Integer extraDodge) {
		this.extraDodge = extraDodge;
	}

	public Integer getExtraHit() {
		return extraHit;
	}

	public void setExtraHit(Integer extraHit) {
		this.extraHit = extraHit;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDefence() {
		return defence;
	}

	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	public Integer getCrt() {
		return crt;
	}

	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	public Integer getDodge() {
		return dodge;
	}

	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getExtraHp() {
		return extraHp;
	}

	public void setExtraHp(Integer extraHp) {
		this.extraHp = extraHp;
	}

	public Integer getExtraMp() {
		return extraMp;
	}

	public void setExtraMp(Integer extraMp) {
		this.extraMp = extraMp;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getMp() {
		return mp;
	}

	public void setMp(Integer mp) {
		this.mp = mp;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

}
