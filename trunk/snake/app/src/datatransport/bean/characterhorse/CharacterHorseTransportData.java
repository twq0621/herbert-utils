package datatransport.bean.characterhorse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class CharacterHorseTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.location == null ? Integer.MIN_VALUE : this.location);
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeInt(this.status == null ? Integer.MIN_VALUE : this.status);
		out.writeObject(this.createDate);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.grade == null ? Integer.MIN_VALUE : this.grade);
		out.writeInt(this.stallCopper == null ? Integer.MIN_VALUE : this.stallCopper);
		out.writeInt(this.stallIngot == null ? Integer.MIN_VALUE : this.stallIngot);
		out.writeInt(this.horseModelId == null ? Integer.MIN_VALUE : this.horseModelId);
		out.writeInt(this.savvy == null ? Integer.MIN_VALUE : this.savvy);
		out.writeInt(this.livingness == null ? Integer.MIN_VALUE : this.livingness);
		out.writeInt(this.potentialAttack == null ? Integer.MIN_VALUE : this.potentialAttack);
		out.writeInt(this.potentialDefence == null ? Integer.MIN_VALUE : this.potentialDefence);
		out.writeInt(this.potentialLight == null ? Integer.MIN_VALUE : this.potentialLight);
		out.writeInt(this.potentialStrong == null ? Integer.MIN_VALUE : this.potentialStrong);
		out.writeUTF(this.savvySkills == null ? "NaN" : this.savvySkills);
		out.writeInt(this.jinjieCount == null ? Integer.MIN_VALUE : this.jinjieCount);
		out.writeInt(this.livingnessMax == null ? Integer.MIN_VALUE : this.livingnessMax);
		out.writeInt(this.experience == null ? Integer.MIN_VALUE : this.experience);
		out.writeInt(this.horsePrice == null ? Integer.MIN_VALUE : this.horsePrice);
		out.writeUTF(this.boreSkill == null ? "NaN" : this.boreSkill);
		out.writeUTF(this.mijiSkill == null ? "NaN" : this.mijiSkill);
		out.writeUTF(this.skillCds == null ? "NaN" : this.skillCds);
		out.writeInt(this.defaultSkillId == null ? Integer.MIN_VALUE : this.defaultSkillId);
		out.writeInt(this.jinjieLeijiprobability == null ? Integer.MIN_VALUE : this.jinjieLeijiprobability);
		out.writeUTF(this.zuheSkillId == null ? "NaN" : this.zuheSkillId);
		out.writeInt(this.extraAttack == null ? Integer.MIN_VALUE : this.extraAttack);
		out.writeInt(this.extraDefence == null ? Integer.MIN_VALUE : this.extraDefence);
		out.writeInt(this.extraLight == null ? Integer.MIN_VALUE : this.extraLight);
		out.writeInt(this.extralStrong == null ? Integer.MIN_VALUE : this.extralStrong);
		out.writeInt(this.zhufuReward == null ? Integer.MIN_VALUE : this.zhufuReward);
		out.writeInt(this.qualityCount == null ? Integer.MIN_VALUE : this.qualityCount);
		out.writeInt(this.priJinjieZhufu == null ? Integer.MIN_VALUE : this.priJinjieZhufu);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.location = in.readInt();
		this.location = this.location == Integer.MIN_VALUE ? null : this.location;
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.status = in.readInt();
		this.status = this.status == Integer.MIN_VALUE ? null : this.status;
		this.createDate = (Date)in.readObject();
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.grade = in.readInt();
		this.grade = this.grade == Integer.MIN_VALUE ? null : this.grade;
		this.stallCopper = in.readInt();
		this.stallCopper = this.stallCopper == Integer.MIN_VALUE ? null : this.stallCopper;
		this.stallIngot = in.readInt();
		this.stallIngot = this.stallIngot == Integer.MIN_VALUE ? null : this.stallIngot;
		this.horseModelId = in.readInt();
		this.horseModelId = this.horseModelId == Integer.MIN_VALUE ? null : this.horseModelId;
		this.savvy = in.readInt();
		this.savvy = this.savvy == Integer.MIN_VALUE ? null : this.savvy;
		this.livingness = in.readInt();
		this.livingness = this.livingness == Integer.MIN_VALUE ? null : this.livingness;
		this.potentialAttack = in.readInt();
		this.potentialAttack = this.potentialAttack == Integer.MIN_VALUE ? null : this.potentialAttack;
		this.potentialDefence = in.readInt();
		this.potentialDefence = this.potentialDefence == Integer.MIN_VALUE ? null : this.potentialDefence;
		this.potentialLight = in.readInt();
		this.potentialLight = this.potentialLight == Integer.MIN_VALUE ? null : this.potentialLight;
		this.potentialStrong = in.readInt();
		this.potentialStrong = this.potentialStrong == Integer.MIN_VALUE ? null : this.potentialStrong;
		this.savvySkills = in.readUTF();
		this.savvySkills = this.savvySkills.equals("NaN") ? null : this.savvySkills;
		this.jinjieCount = in.readInt();
		this.jinjieCount = this.jinjieCount == Integer.MIN_VALUE ? null : this.jinjieCount;
		this.livingnessMax = in.readInt();
		this.livingnessMax = this.livingnessMax == Integer.MIN_VALUE ? null : this.livingnessMax;
		this.experience = in.readInt();
		this.experience = this.experience == Integer.MIN_VALUE ? null : this.experience;
		this.horsePrice = in.readInt();
		this.horsePrice = this.horsePrice == Integer.MIN_VALUE ? null : this.horsePrice;
		this.boreSkill = in.readUTF();
		this.boreSkill = this.boreSkill.equals("NaN") ? null : this.boreSkill;
		this.mijiSkill = in.readUTF();
		this.mijiSkill = this.mijiSkill.equals("NaN") ? null : this.mijiSkill;
		this.skillCds = in.readUTF();
		this.skillCds = this.skillCds.equals("NaN") ? null : this.skillCds;
		this.defaultSkillId = in.readInt();
		this.defaultSkillId = this.defaultSkillId == Integer.MIN_VALUE ? null : this.defaultSkillId;
		this.jinjieLeijiprobability = in.readInt();
		this.jinjieLeijiprobability = this.jinjieLeijiprobability == Integer.MIN_VALUE ? null : this.jinjieLeijiprobability;
		this.zuheSkillId = in.readUTF();
		this.zuheSkillId = this.zuheSkillId.equals("NaN") ? null : this.zuheSkillId;
		this.extraAttack = in.readInt();
		this.extraAttack = this.extraAttack == Integer.MIN_VALUE ? null : this.extraAttack;
		this.extraDefence = in.readInt();
		this.extraDefence = this.extraDefence == Integer.MIN_VALUE ? null : this.extraDefence;
		this.extraLight = in.readInt();
		this.extraLight = this.extraLight == Integer.MIN_VALUE ? null : this.extraLight;
		this.extralStrong = in.readInt();
		this.extralStrong = this.extralStrong == Integer.MIN_VALUE ? null : this.extralStrong;
		this.zhufuReward = in.readInt();
		this.zhufuReward = this.zhufuReward == Integer.MIN_VALUE ? null : this.zhufuReward;
		this.qualityCount = in.readInt();
		this.qualityCount = this.qualityCount == Integer.MIN_VALUE ? null : this.qualityCount;
		this.priJinjieZhufu = in.readInt();
		this.priJinjieZhufu = this.priJinjieZhufu == Integer.MIN_VALUE ? null : this.priJinjieZhufu;
	}
	/**
	 * 马实例的ID t_character_horse.f_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	private Integer id;
	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	private Integer characterId;
	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	private Integer horseModelId;
	/**
	 * 悟性 t_character_horse.f_savvy
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	private Integer savvy;
	/**
	 * 当前活力 t_character_horse.f_livingness
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	private Integer livingness;
	/**
	 * 攻击潜能 t_character_horse.f_potential_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer potentialAttack;
	/**
	 * 防御潜能 t_character_horse.f_potential_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer potentialDefence;
	/**
	 * 轻身潜能 t_character_horse.f_potential_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer potentialLight;
	/**
	 * 健体潜能 t_character_horse.f_potential_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer potentialStrong;
	/**
	 * 通过领悟已经学会的技能 t_character_horse.f_savvy_skills
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private String savvySkills;
	/**
	 * 当前级别 t_character_horse.f_grade
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer grade;
	/**
	 * 0休息 1坐骑 2展示 t_character_horse.f_status
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer status;
	/**
	 * 马在人物身上(1-100)  马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer location;
	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer jinjieCount;
	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer livingnessMax;
	/**
	 * 马的经验 t_character_horse.f_experience
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer experience;
	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer stallCopper;
	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer stallIngot;
	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Date createDate;
	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer horsePrice;
	/**
	 * 天生学会的技能 t_character_horse.f_bore_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private String boreSkill;
	/**
	 * 通过秘籍书学会的技能 t_character_horse.f_miji_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private String mijiSkill;
	/**
	 * 坐骑当前技能的冷却时间skillid,技能开始时间;skillid,技能开始时间 t_character_horse.f_skill_cds
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private String skillCds;
	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer defaultSkillId;
	/**
	 * 坐骑当前累计祝福值（正常不保存数据库） t_character_horse.f_jinjie_leijiprobability
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer jinjieLeijiprobability;
	/**
	 * 坐骑的组合技能ids(格式:{组合技能id;}*) t_character_horse.f_zuhe_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private String zuheSkillId;
	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer extraAttack;
	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer extraDefence;
	/**
	 * 轻身潜力额外添加 t_character_horse.f_extra_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer extraLight;
	/**
	 * 强身潜力额外添加 t_character_horse.f_extral_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer extralStrong;
	/**
	 * t_character_horse.f_zhufu_reward
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer zhufuReward;
	/**
	 * （品级失败次数） t_character_horse.f_quality_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer qualityCount;
	/**
	 * 上一次进阶祝福值 t_character_horse.f_pri_jinjie_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	private Integer priJinjieZhufu;

	/**
	 * 马实例的ID t_character_horse.f_id
	 * @return  the value of t_character_horse.f_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 马实例的ID t_character_horse.f_id
	 * @param id  the value for t_character_horse.f_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * @return  the value of t_character_horse.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 马的拥有者 t_character_horse.f_character_id
	 * @param characterId  the value for t_character_horse.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * @return  the value of t_character_horse.f_horse_model_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public Integer getHorseModelId() {
		return horseModelId;
	}

	/**
	 * 马的模型ID t_character_horse.f_horse_model_id
	 * @param horseModelId  the value for t_character_horse.f_horse_model_id
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public void setHorseModelId(Integer horseModelId) {
		this.horseModelId = horseModelId;
	}

	/**
	 * 悟性 t_character_horse.f_savvy
	 * @return  the value of t_character_horse.f_savvy
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public Integer getSavvy() {
		return savvy;
	}

	/**
	 * 悟性 t_character_horse.f_savvy
	 * @param savvy  the value for t_character_horse.f_savvy
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public void setSavvy(Integer savvy) {
		this.savvy = savvy;
	}

	/**
	 * 当前活力 t_character_horse.f_livingness
	 * @return  the value of t_character_horse.f_livingness
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public Integer getLivingness() {
		return livingness;
	}

	/**
	 * 当前活力 t_character_horse.f_livingness
	 * @param livingness  the value for t_character_horse.f_livingness
	 * @ibatorgenerated  Fri Jun 24 09:16:09 CST 2011
	 */
	public void setLivingness(Integer livingness) {
		this.livingness = livingness;
	}

	/**
	 * 攻击潜能 t_character_horse.f_potential_attack
	 * @return  the value of t_character_horse.f_potential_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getPotentialAttack() {
		return potentialAttack;
	}

	/**
	 * 攻击潜能 t_character_horse.f_potential_attack
	 * @param potentialAttack  the value for t_character_horse.f_potential_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setPotentialAttack(Integer potentialAttack) {
		this.potentialAttack = potentialAttack;
	}

	/**
	 * 防御潜能 t_character_horse.f_potential_defence
	 * @return  the value of t_character_horse.f_potential_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getPotentialDefence() {
		return potentialDefence;
	}

	/**
	 * 防御潜能 t_character_horse.f_potential_defence
	 * @param potentialDefence  the value for t_character_horse.f_potential_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setPotentialDefence(Integer potentialDefence) {
		this.potentialDefence = potentialDefence;
	}

	/**
	 * 轻身潜能 t_character_horse.f_potential_light
	 * @return  the value of t_character_horse.f_potential_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getPotentialLight() {
		return potentialLight;
	}

	/**
	 * 轻身潜能 t_character_horse.f_potential_light
	 * @param potentialLight  the value for t_character_horse.f_potential_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setPotentialLight(Integer potentialLight) {
		this.potentialLight = potentialLight;
	}

	/**
	 * 健体潜能 t_character_horse.f_potential_strong
	 * @return  the value of t_character_horse.f_potential_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getPotentialStrong() {
		return potentialStrong;
	}

	/**
	 * 健体潜能 t_character_horse.f_potential_strong
	 * @param potentialStrong  the value for t_character_horse.f_potential_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setPotentialStrong(Integer potentialStrong) {
		this.potentialStrong = potentialStrong;
	}

	/**
	 * 通过领悟已经学会的技能 t_character_horse.f_savvy_skills
	 * @return  the value of t_character_horse.f_savvy_skills
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public String getSavvySkills() {
		return savvySkills;
	}

	/**
	 * 通过领悟已经学会的技能 t_character_horse.f_savvy_skills
	 * @param savvySkills  the value for t_character_horse.f_savvy_skills
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setSavvySkills(String savvySkills) {
		this.savvySkills = savvySkills;
	}

	/**
	 * 当前级别 t_character_horse.f_grade
	 * @return  the value of t_character_horse.f_grade
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 当前级别 t_character_horse.f_grade
	 * @param grade  the value for t_character_horse.f_grade
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 0休息 1坐骑 2展示 t_character_horse.f_status
	 * @return  the value of t_character_horse.f_status
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 0休息 1坐骑 2展示 t_character_horse.f_status
	 * @param status  the value for t_character_horse.f_status
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 马在人物身上(1-100)  马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * @return  the value of t_character_horse.f_location
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getLocation() {
		return location;
	}

	/**
	 * 马在人物身上(1-100)  马在仓库里(101-200) ,马在摊位上(201-300) t_character_horse.f_location
	 * @param location  the value for t_character_horse.f_location
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setLocation(Integer location) {
		this.location = location;
	}

	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * @return  the value of t_character_horse.f_jinjie_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getJinjieCount() {
		return jinjieCount;
	}

	/**
	 * 累积进阶次数 锻造次数 t_character_horse.f_jinjie_count
	 * @param jinjieCount  the value for t_character_horse.f_jinjie_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setJinjieCount(Integer jinjieCount) {
		this.jinjieCount = jinjieCount;
	}

	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * @return  the value of t_character_horse.f_livingness_max
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getLivingnessMax() {
		return livingnessMax;
	}

	/**
	 * 最大活力值 t_character_horse.f_livingness_max
	 * @param livingnessMax  the value for t_character_horse.f_livingness_max
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setLivingnessMax(Integer livingnessMax) {
		this.livingnessMax = livingnessMax;
	}

	/**
	 * 马的经验 t_character_horse.f_experience
	 * @return  the value of t_character_horse.f_experience
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * 马的经验 t_character_horse.f_experience
	 * @param experience  the value for t_character_horse.f_experience
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * @return  the value of t_character_horse.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getStallCopper() {
		return stallCopper;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_horse.f_stall_copper
	 * @param stallCopper  the value for t_character_horse.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setStallCopper(Integer stallCopper) {
		this.stallCopper = stallCopper;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * @return  the value of t_character_horse.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getStallIngot() {
		return stallIngot;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_horse.f_stall_ingot
	 * @param stallIngot  the value for t_character_horse.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setStallIngot(Integer stallIngot) {
		this.stallIngot = stallIngot;
	}

	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * @return  the value of t_character_horse.f_create_date
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 马的创建时间 t_character_horse.f_create_date
	 * @param createDate  the value for t_character_horse.f_create_date
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * @return  the value of t_character_horse.f_horse_price
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getHorsePrice() {
		return horsePrice;
	}

	/**
	 * 马的价值 t_character_horse.f_horse_price
	 * @param horsePrice  the value for t_character_horse.f_horse_price
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setHorsePrice(Integer horsePrice) {
		this.horsePrice = horsePrice;
	}

	/**
	 * 天生学会的技能 t_character_horse.f_bore_skill
	 * @return  the value of t_character_horse.f_bore_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public String getBoreSkill() {
		return boreSkill;
	}

	/**
	 * 天生学会的技能 t_character_horse.f_bore_skill
	 * @param boreSkill  the value for t_character_horse.f_bore_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setBoreSkill(String boreSkill) {
		this.boreSkill = boreSkill;
	}

	/**
	 * 通过秘籍书学会的技能 t_character_horse.f_miji_skill
	 * @return  the value of t_character_horse.f_miji_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public String getMijiSkill() {
		return mijiSkill;
	}

	/**
	 * 通过秘籍书学会的技能 t_character_horse.f_miji_skill
	 * @param mijiSkill  the value for t_character_horse.f_miji_skill
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setMijiSkill(String mijiSkill) {
		this.mijiSkill = mijiSkill;
	}

	/**
	 * 坐骑当前技能的冷却时间skillid,技能开始时间;skillid,技能开始时间 t_character_horse.f_skill_cds
	 * @return  the value of t_character_horse.f_skill_cds
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public String getSkillCds() {
		return skillCds;
	}

	/**
	 * 坐骑当前技能的冷却时间skillid,技能开始时间;skillid,技能开始时间 t_character_horse.f_skill_cds
	 * @param skillCds  the value for t_character_horse.f_skill_cds
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setSkillCds(String skillCds) {
		this.skillCds = skillCds;
	}

	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * @return  the value of t_character_horse.f_default_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getDefaultSkillId() {
		return defaultSkillId;
	}

	/**
	 * 坐骑默认技能 t_character_horse.f_default_skill_id
	 * @param defaultSkillId  the value for t_character_horse.f_default_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setDefaultSkillId(Integer defaultSkillId) {
		this.defaultSkillId = defaultSkillId;
	}

	/**
	 * 坐骑当前累计祝福值（正常不保存数据库） t_character_horse.f_jinjie_leijiprobability
	 * @return  the value of t_character_horse.f_jinjie_leijiprobability
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getJinjieLeijiprobability() {
		return jinjieLeijiprobability;
	}

	/**
	 * 坐骑当前累计祝福值（正常不保存数据库） t_character_horse.f_jinjie_leijiprobability
	 * @param jinjieLeijiprobability  the value for t_character_horse.f_jinjie_leijiprobability
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setJinjieLeijiprobability(Integer jinjieLeijiprobability) {
		this.jinjieLeijiprobability = jinjieLeijiprobability;
	}

	/**
	 * 坐骑的组合技能ids(格式:{组合技能id;}*) t_character_horse.f_zuhe_skill_id
	 * @return  the value of t_character_horse.f_zuhe_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public String getZuheSkillId() {
		return zuheSkillId;
	}

	/**
	 * 坐骑的组合技能ids(格式:{组合技能id;}*) t_character_horse.f_zuhe_skill_id
	 * @param zuheSkillId  the value for t_character_horse.f_zuhe_skill_id
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setZuheSkillId(String zuheSkillId) {
		this.zuheSkillId = zuheSkillId;
	}

	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * @return  the value of t_character_horse.f_extra_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getExtraAttack() {
		return extraAttack;
	}

	/**
	 * 攻击潜力额外添加 t_character_horse.f_extra_attack
	 * @param extraAttack  the value for t_character_horse.f_extra_attack
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setExtraAttack(Integer extraAttack) {
		this.extraAttack = extraAttack;
	}

	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * @return  the value of t_character_horse.f_extra_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getExtraDefence() {
		return extraDefence;
	}

	/**
	 * 防御潜能额外添加 t_character_horse.f_extra_defence
	 * @param extraDefence  the value for t_character_horse.f_extra_defence
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setExtraDefence(Integer extraDefence) {
		this.extraDefence = extraDefence;
	}

	/**
	 * 轻身潜力额外添加 t_character_horse.f_extra_light
	 * @return  the value of t_character_horse.f_extra_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getExtraLight() {
		return extraLight;
	}

	/**
	 * 轻身潜力额外添加 t_character_horse.f_extra_light
	 * @param extraLight  the value for t_character_horse.f_extra_light
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setExtraLight(Integer extraLight) {
		this.extraLight = extraLight;
	}

	/**
	 * 强身潜力额外添加 t_character_horse.f_extral_strong
	 * @return  the value of t_character_horse.f_extral_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getExtralStrong() {
		return extralStrong;
	}

	/**
	 * 强身潜力额外添加 t_character_horse.f_extral_strong
	 * @param extralStrong  the value for t_character_horse.f_extral_strong
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setExtralStrong(Integer extralStrong) {
		this.extralStrong = extralStrong;
	}

	/**
	 * t_character_horse.f_zhufu_reward
	 * @return  the value of t_character_horse.f_zhufu_reward
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getZhufuReward() {
		return zhufuReward;
	}

	/**
	 * t_character_horse.f_zhufu_reward
	 * @param zhufuReward  the value for t_character_horse.f_zhufu_reward
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setZhufuReward(Integer zhufuReward) {
		this.zhufuReward = zhufuReward;
	}

	/**
	 * （品级失败次数） t_character_horse.f_quality_count
	 * @return  the value of t_character_horse.f_quality_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getQualityCount() {
		return qualityCount;
	}

	/**
	 * （品级失败次数） t_character_horse.f_quality_count
	 * @param qualityCount  the value for t_character_horse.f_quality_count
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setQualityCount(Integer qualityCount) {
		this.qualityCount = qualityCount;
	}

	/**
	 * 上一次进阶祝福值 t_character_horse.f_pri_jinjie_zhufu
	 * @return  the value of t_character_horse.f_pri_jinjie_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public Integer getPriJinjieZhufu() {
		return priJinjieZhufu;
	}

	/**
	 * 上一次进阶祝福值 t_character_horse.f_pri_jinjie_zhufu
	 * @param priJinjieZhufu  the value for t_character_horse.f_pri_jinjie_zhufu
	 * @ibatorgenerated  Fri Jun 24 09:16:10 CST 2011
	 */
	public void setPriJinjieZhufu(Integer priJinjieZhufu) {
		this.priJinjieZhufu = priJinjieZhufu;
	}
}
