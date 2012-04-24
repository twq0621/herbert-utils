package datatransport.bean.characterbuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CharacterBuffTransportData implements Serializable {
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.type == null ? Integer.MIN_VALUE : this.type);
		out.writeInt(this.characterid == null ? Integer.MIN_VALUE : this.characterid);
		out.writeInt(this.duration == null ? Integer.MIN_VALUE : this.duration);
		out.writeInt(this.buffvalue == null ? Integer.MIN_VALUE : this.buffvalue);
		out.writeInt(this.relevanceSkillid == null ? Integer.MIN_VALUE : this.relevanceSkillid);
		out.writeInt(this.gotime == null ? Integer.MIN_VALUE : this.gotime);
		out.writeInt(this.effectId == null ? Integer.MIN_VALUE : this.effectId);
		out.writeInt(this.duration2 == null ? Integer.MIN_VALUE : this.duration2);
		out.writeInt(this.remainpoint == null ? Integer.MIN_VALUE : this.remainpoint);
		out.writeInt(this.delayrecoverTime == null ? Integer.MIN_VALUE : this.delayrecoverTime);
		out.writeUTF(this.jingmaiId == null ? "NaN" : this.jingmaiId);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.type = in.readInt();
		this.type = this.type == Integer.MIN_VALUE ? null : this.type;
		this.characterid = in.readInt();
		this.characterid = this.characterid == Integer.MIN_VALUE ? null : this.characterid;
		this.duration = in.readInt();
		this.duration = this.duration == Integer.MIN_VALUE ? null : this.duration;
		this.buffvalue = in.readInt();
		this.buffvalue = this.buffvalue == Integer.MIN_VALUE ? null : this.buffvalue;
		this.relevanceSkillid = in.readInt();
		this.relevanceSkillid = this.relevanceSkillid == Integer.MIN_VALUE ? null : this.relevanceSkillid;
		this.gotime = in.readInt();
		this.gotime = this.gotime == Integer.MIN_VALUE ? null : this.gotime;
		this.effectId = in.readInt();
		this.effectId = this.effectId == Integer.MIN_VALUE ? null : this.effectId;
		this.duration2 = in.readInt();
		this.duration2 = this.duration2 == Integer.MIN_VALUE ? null : this.duration2;
		this.remainpoint = in.readInt();
		this.remainpoint = this.remainpoint == Integer.MIN_VALUE ? null : this.remainpoint;
		this.delayrecoverTime = in.readInt();
		this.delayrecoverTime = this.delayrecoverTime == Integer.MIN_VALUE ? null : this.delayrecoverTime;
		this.jingmaiId = in.readUTF();
		this.jingmaiId = this.jingmaiId.equals("NaN") ? null : this.jingmaiId;
	}

	/**
	 * 角色id t_character_buff.f_characterId
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer characterid;
	/**
	 * buff总的持续时间 t_character_buff.f_duration
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer duration;
	/**
	 * 产生的buff值 t_character_buff.f_buffValue
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer buffvalue;
	/**
	 * 关联技能id t_character_buff.f_relevance_skillId
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer relevanceSkillid;
	/**
	 * 已经失去的时间（毫秒） t_character_buff.f_gotime
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer gotime;
	/**
	 * 效果id t_character_buff.f_effect_id
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer effectId;
	/**
	 * t_character_buff.f_duration2
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer duration2;
	/**
	 * 血池 t_character_buff.f_remainpoint
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer remainpoint;
	/**
	 * 延时时间 t_character_buff.f_delayrecover_time
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer delayrecoverTime;
	/**
	 * buff类型 t_character_buff.f_type
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private Integer type;
	/**
	 * 经脉id t_character_buff.f_jingmai_id
	 * 
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	private String jingmaiId;

	/**
	 * 角色id t_character_buff.f_characterId
	 * 
	 * @return the value of t_character_buff.f_characterId
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getCharacterid() {
		return characterid;
	}

	/**
	 * 角色id t_character_buff.f_characterId
	 * 
	 * @param characterid
	 *            the value for t_character_buff.f_characterId
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setCharacterid(Integer characterid) {
		this.characterid = characterid;
	}

	/**
	 * buff总的持续时间 t_character_buff.f_duration
	 * 
	 * @return the value of t_character_buff.f_duration
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * buff总的持续时间 t_character_buff.f_duration
	 * 
	 * @param duration
	 *            the value for t_character_buff.f_duration
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * 产生的buff值 t_character_buff.f_buffValue
	 * 
	 * @return the value of t_character_buff.f_buffValue
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getBuffvalue() {
		return buffvalue;
	}

	/**
	 * 产生的buff值 t_character_buff.f_buffValue
	 * 
	 * @param buffvalue
	 *            the value for t_character_buff.f_buffValue
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setBuffvalue(Integer buffvalue) {
		this.buffvalue = buffvalue;
	}

	/**
	 * 关联技能id t_character_buff.f_relevance_skillId
	 * 
	 * @return the value of t_character_buff.f_relevance_skillId
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getRelevanceSkillid() {
		return relevanceSkillid;
	}

	/**
	 * 关联技能id t_character_buff.f_relevance_skillId
	 * 
	 * @param relevanceSkillid
	 *            the value for t_character_buff.f_relevance_skillId
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setRelevanceSkillid(Integer relevanceSkillid) {
		this.relevanceSkillid = relevanceSkillid;
	}

	/**
	 * 已经失去的时间（毫秒） t_character_buff.f_gotime
	 * 
	 * @return the value of t_character_buff.f_gotime
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getGotime() {
		return gotime;
	}

	/**
	 * 已经失去的时间（毫秒） t_character_buff.f_gotime
	 * 
	 * @param gotime
	 *            the value for t_character_buff.f_gotime
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setGotime(Integer gotime) {
		this.gotime = gotime;
	}

	/**
	 * 效果id t_character_buff.f_effect_id
	 * 
	 * @return the value of t_character_buff.f_effect_id
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getEffectId() {
		return effectId;
	}

	/**
	 * 效果id t_character_buff.f_effect_id
	 * 
	 * @param effectId
	 *            the value for t_character_buff.f_effect_id
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setEffectId(Integer effectId) {
		this.effectId = effectId;
	}

	/**
	 * t_character_buff.f_duration2
	 * 
	 * @return the value of t_character_buff.f_duration2
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getDuration2() {
		return duration2;
	}

	/**
	 * t_character_buff.f_duration2
	 * 
	 * @param duration2
	 *            the value for t_character_buff.f_duration2
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setDuration2(Integer duration2) {
		this.duration2 = duration2;
	}

	/**
	 * 血池 t_character_buff.f_remainpoint
	 * 
	 * @return the value of t_character_buff.f_remainpoint
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getRemainpoint() {
		return remainpoint;
	}

	/**
	 * 血池 t_character_buff.f_remainpoint
	 * 
	 * @param remainpoint
	 *            the value for t_character_buff.f_remainpoint
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setRemainpoint(Integer remainpoint) {
		this.remainpoint = remainpoint;
	}

	/**
	 * 延时时间 t_character_buff.f_delayrecover_time
	 * 
	 * @return the value of t_character_buff.f_delayrecover_time
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getDelayrecoverTime() {
		return delayrecoverTime;
	}

	/**
	 * 延时时间 t_character_buff.f_delayrecover_time
	 * 
	 * @param delayrecoverTime
	 *            the value for t_character_buff.f_delayrecover_time
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setDelayrecoverTime(Integer delayrecoverTime) {
		this.delayrecoverTime = delayrecoverTime;
	}

	/**
	 * buff类型 t_character_buff.f_type
	 * 
	 * @return the value of t_character_buff.f_type
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * buff类型 t_character_buff.f_type
	 * 
	 * @param type
	 *            the value for t_character_buff.f_type
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 经脉id t_character_buff.f_jingmai_id
	 * 
	 * @return the value of t_character_buff.f_jingmai_id
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public String getJingmaiId() {
		return jingmaiId;
	}

	/**
	 * 经脉id t_character_buff.f_jingmai_id
	 * 
	 * @param jingmaiId
	 *            the value for t_character_buff.f_jingmai_id
	 * @ibatorgenerated Fri Jun 24 09:14:34 CST 2011
	 */
	public void setJingmaiId(String jingmaiId) {
		this.jingmaiId = jingmaiId;
	}
}
