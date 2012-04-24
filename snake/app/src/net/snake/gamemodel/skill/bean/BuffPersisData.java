package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

/**
 * 
 * @author serv_dev
 * 
 */
public class BuffPersisData implements IbatisEntity {

	private int characterid;// 角色id
	private int duration;// buff总的持续时间
	private int buffvalue;// 产生的buff值
	private int relevanceSkillid;// 关联技能id
	private int gotime;// 已经失去的时间（毫秒）
	private int effectId;// 效果id
	private int duration2;// buff额外持续时间
	private int remainpoint;// 续命丹剩余点
	private int delayrecoverTime;// 延迟（随机1-10秒）补满
	private int effectInfoType;// 类型
	private String jingmaiId;// 经脉id

	public String getJingmaiId() {
		return jingmaiId;
	}

	public void setJingmaiId(String jingmaiId) {
		this.jingmaiId = jingmaiId;
	}

	public int getEffectInfoType() {
		return effectInfoType;
	}

	public void setEffectInfoType(int effectInfoType) {
		this.effectInfoType = effectInfoType;
	}

	public int getDuration2() {
		return duration2;
	}

	public void setDuration2(int duration2) {
		this.duration2 = duration2;
	}

	public int getRemainpoint() {
		return remainpoint;
	}

	public void setRemainpoint(int remainpoint) {
		this.remainpoint = remainpoint;
	}

	public int getDelayrecoverTime() {
		return delayrecoverTime;
	}

	public void setDelayrecoverTime(int delayrecoverTime) {
		this.delayrecoverTime = delayrecoverTime;
	}

	public int getCharacterid() {
		return characterid;
	}

	public void setCharacterid(int characterid) {
		this.characterid = characterid;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBuffvalue() {
		return buffvalue;
	}

	public void setBuffvalue(int buffvalue) {
		this.buffvalue = buffvalue;
	}

	public int getRelevanceSkillid() {
		return relevanceSkillid;
	}

	public void setRelevanceSkillid(int relevanceSkillid) {
		this.relevanceSkillid = relevanceSkillid;
	}

	public int getGotime() {
		return gotime;
	}

	public void setGotime(int gotime) {
		this.gotime = gotime;
	}

	public int getEffectId() {
		return effectId;
	}

	public void setEffectId(int effectId) {
		this.effectId = effectId;
	}

}
