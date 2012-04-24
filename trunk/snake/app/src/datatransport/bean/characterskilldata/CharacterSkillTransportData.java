package datatransport.bean.characterskilldata;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CharacterSkillTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.grade == null ? Integer.MIN_VALUE : this.grade);
		out.writeShort(this.position);
		out.writeInt(this.quickbarindex == null ? Integer.MIN_VALUE : this.quickbarindex);
		out.writeInt(this.skillId == null ? Integer.MIN_VALUE : this.skillId);
		out.writeLong(this.startcd);
		out.writeInt(this.mastery == null ? Integer.MIN_VALUE : this.mastery);
		out.writeInt(this.maxMastery == null ? Integer.MIN_VALUE : this.maxMastery);
		out.writeByte(this.po == null ? 2 : this.po== true ? 1 : 0);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.grade = in.readInt();
		this.grade = this.grade == Integer.MIN_VALUE ? null : this.grade;
		this.position = in.readShort();
		this.quickbarindex = in.readInt();
		this.quickbarindex = this.quickbarindex == Integer.MIN_VALUE ? null : this.quickbarindex;
		this.skillId = in.readInt();
		this.skillId = this.skillId == Integer.MIN_VALUE ? null : this.skillId;
		this.startcd = in.readLong();
		this.mastery = in.readInt();
		this.mastery = this.mastery == Integer.MIN_VALUE ? null : this.mastery;
		this.maxMastery = in.readInt();
		this.maxMastery = this.maxMastery == Integer.MIN_VALUE ? null : this.maxMastery;
		this.po = cv(in.readByte());
	}
	private Boolean cv(byte b) {
		if(b == 0){
			return false;
		}else
		if(b == 1){
			return true;
		}
		return null;
	}
	/**
	 * t_character_skill.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer id;
	/**
	 * 人物id t_character_skill.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer characterId;
	/**
	 * 技能id t_character_skill.f_skill_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer skillId;
	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer grade;
	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Short position;
	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer quickbarindex;
	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Long startcd;
	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer mastery;
	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Integer maxMastery;
	/**
	 * 是否已突破瓶颈1突破 t_character_skill.f_po
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	private Boolean po;
	
	/** 1马的技能 2角色的技能 */
	private int skilltype;

	/**
	 * t_character_skill.f_id
	 * @return  the value of t_character_skill.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_skill.f_id
	 * @param id  the value for t_character_skill.f_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 人物id t_character_skill.f_character_id
	 * @return  the value of t_character_skill.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物id t_character_skill.f_character_id
	 * @param characterId  the value for t_character_skill.f_character_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 技能id t_character_skill.f_skill_id
	 * @return  the value of t_character_skill.f_skill_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getSkillId() {
		return skillId;
	}

	/**
	 * 技能id t_character_skill.f_skill_id
	 * @param skillId  the value for t_character_skill.f_skill_id
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * @return  the value of t_character_skill.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 技能等级提高 t_character_skill.f_grade
	 * @param grade  the value for t_character_skill.f_grade
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * @return  the value of t_character_skill.f_position
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * 技能的显示顺序(3000-3099) t_character_skill.f_position
	 * @param position  the value for t_character_skill.f_position
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * @return  the value of t_character_skill.f_quickbarindex
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getQuickbarindex() {
		return quickbarindex;
	}

	/**
	 * 技能快捷栏索引 t_character_skill.f_quickbarindex
	 * @param quickbarindex  the value for t_character_skill.f_quickbarindex
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setQuickbarindex(Integer quickbarindex) {
		this.quickbarindex = quickbarindex;
	}

	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * @return  the value of t_character_skill.f_startCD
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Long getStartcd() {
		return startcd;
	}

	/**
	 * 技能当时使用的时间点(单位毫秒) t_character_skill.f_startCD
	 * @param startcd  the value for t_character_skill.f_startCD
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setStartcd(Long startcd) {
		this.startcd = startcd;
	}

	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * @return  the value of t_character_skill.f_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getMastery() {
		return mastery;
	}

	/**
	 * 技能熟练度 t_character_skill.f_mastery
	 * @param mastery  the value for t_character_skill.f_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setMastery(Integer mastery) {
		this.mastery = mastery;
	}

	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * @return  the value of t_character_skill.f_max_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Integer getMaxMastery() {
		return maxMastery;
	}

	/**
	 * 最大熟练度值(真气) t_character_skill.f_max_mastery
	 * @param maxMastery  the value for t_character_skill.f_max_mastery
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setMaxMastery(Integer maxMastery) {
		this.maxMastery = maxMastery;
	}

	/**
	 * 是否已突破瓶颈1突破 t_character_skill.f_po
	 * @return  the value of t_character_skill.f_po
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public Boolean getPo() {
		return po;
	}

	/**
	 * 是否已突破瓶颈1突破 t_character_skill.f_po
	 * @param po  the value for t_character_skill.f_po
	 * @ibatorgenerated  Fri Jul 01 21:57:13 CST 2011
	 */
	public void setPo(Boolean po) {
		this.po = po;
	}
	
	/**
	 * 1马的技能 2角色的技能
	 * @return
	 */
	public int getSkilltype() {
		return skilltype;
	}

	/**
	 * 1马的技能 2角色的技能
	 * @param skilltype
	 */
	public void setSkilltype(int skilltype) {
		this.skilltype = skilltype;
	}
	
}
