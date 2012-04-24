package net.snake.gamemodel.skill.bean;

import net.snake.ibatis.IbatisEntity;

/**
 * 技能效果
 * 
 * @author serv_dev
 */

public class SkillEffect implements IbatisEntity {

	/** id */
	protected int id;//
	protected String name;// 效果名称
	protected String nameI18n;
	protected short userScope;// 作用范围（单体1、群体2）
	// || （1.特定门派施放 2.对仇恨列表中特定位置的目标施放）
	protected short type;// 效果类型（1,增加攻击力2,增加防御力3,增加暴击4,增加闪避5,
	// 增加攻击速度6,增加移动速度7,增加生命上限值8,增加内力上限值9,增加体力上限值10,
	// 增加潜能点个数11,增加等级12,增加经验13,增加坐骑经验14,增加真气储量15,
	// 增加战场声望16,恢复生命值17,恢复内力值18,恢复体力值19,恢复坐骑活力20,
	// 解除全部负面状态21,双倍经验获得22,双倍真气储量获得23,吸血24,封内力25,
	// 封体力26,点穴27,中毒28,打落武器29,打落防具30,击退31,马奶32,连斩）
	protected short hurtWay;// 技能伤害方式（直接伤害1、持续伤害2）
							// 直接效果为给自己+,给目标- 直接伤害、持续伤害
	protected int scopeRadius;// 范围半径设定(人物对象中写个函数，参数包括半径)

	protected short hurtConsiderationWay;// 伤害值计算方式（单一目标1，全部目标2）
	// 全部目标参考maxHurtValue
	protected short aoeType;// AOE种类(自身为核心1、目标为核心2）
	protected int hurtValue;// 伤害数值
	protected int duration;// 总的持续时间（ms）
	protected int preDuration;// 单次作用时间
	protected int continueCount;// 持续次数累计
	protected short specialEffectChange;// 特殊效果变化（施放1、解除2、免疫3）
	protected String desc;// 描述
	protected String descI18n;
	protected int percent;// 万分比
	protected short typePn;// 是否触发伤害(0触发,1不触发)
	protected int jituiDistance;// 击退距离(默认)
	protected int iconId;//
	protected int effectId;// 换装id
	protected int goodmodelId;// 生成特殊道具(马奶)
	protected short buffFlag;// 0为正面buff,1为负面buff
	protected int effectCount = 0;// 效果叠加次数计数
	// buff 持续使用
	protected short effectRepeatOption;// 效果重复选项：效果叠加1、效果替换2、重复无效3
	protected int effectRepeatCount;// 叠加上限次数，只有选择效果叠加时有用
	protected int useLimit;// 作用人数上限 //攻\防都有
	protected int hurtAdds;// 伤害附加
	protected short isDieClean;// 0死亡时不清除buff，1死亡时清除buff
	private int hurtValueMin;// 数值随机最小值
	private int hurtValueMax;// 数值随机最大值
	private int attackIsDieClean;// 0施加者死亡下线时不清除buff，1施加者死亡下线时清除buff
	private int duMaxHurt;// 中毒的上限血量

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserScope(short userScope) {
		this.userScope = userScope;
	}

	public void setType(short type) {
		this.type = type;
	}

	public void setHurtWay(short hurtWay) {
		this.hurtWay = hurtWay;
	}

	public void setScopeRadius(int scopeRadius) {
		this.scopeRadius = scopeRadius;
	}

	public void setHurtConsiderationWay(short hurtConsiderationWay) {
		this.hurtConsiderationWay = hurtConsiderationWay;
	}

	public void setAoeType(short aoeType) {
		this.aoeType = aoeType;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setPreDuration(int preDuration) {
		this.preDuration = preDuration;
	}

	public void setContinueCount(int continueCount) {
		this.continueCount = continueCount;
	}

	public void setSpecialEffectChange(short specialEffectChange) {
		this.specialEffectChange = specialEffectChange;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public void setTypePn(short typePn) {
		this.typePn = typePn;
	}

	public void setJituiDistance(int jituiDistance) {
		this.jituiDistance = jituiDistance;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public void setEffectId(int effectId) {
		this.effectId = effectId;
	}

	public void setGoodmodelId(int goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	public void setBuffFlag(short buffFlag) {
		this.buffFlag = buffFlag;
	}

	public void setEffectCount(int effectCount) {
		this.effectCount = effectCount;
	}

	public void setEffectRepeatOption(short effectRepeatOption) {
		this.effectRepeatOption = effectRepeatOption;
	}

	public void setEffectRepeatCount(int effectRepeatCount) {
		this.effectRepeatCount = effectRepeatCount;
	}

	public void setUseLimit(int useLimit) {
		this.useLimit = useLimit;
	}

	public void setHurtAdds(int hurtAdds) {
		this.hurtAdds = hurtAdds;
	}

	public void setIsDieClean(short isDieClean) {
		this.isDieClean = isDieClean;
	}

	public void setHurtValueMin(int hurtValueMin) {
		this.hurtValueMin = hurtValueMin;
	}

	public void setHurtValueMax(int hurtValueMax) {
		this.hurtValueMax = hurtValueMax;
	}

	public int getDuMaxHurt() {
		return duMaxHurt;
	}

	public void setDuMaxHurt(int duMaxHurt) {
		this.duMaxHurt = duMaxHurt;
	}

	public int getAttackIsDieClean() {
		return attackIsDieClean;
	}

	public void setAttackIsDieClean(int attackIsDieClean) {
		this.attackIsDieClean = attackIsDieClean;
	}

	public int getHurtValueMin() {
		return hurtValueMin;
	}

	public int getHurtValueMax() {
		return hurtValueMax;
	}

	public short getIsDieClean() {
		return isDieClean;
	}

	/**
	 * 是否触发伤害
	 * 
	 * @return true触发
	 */
	public boolean isHurt() {// 伤害型的效果
		return typePn == 0;
	}

	/**
	 * 正面还是负面buff
	 * 
	 * @return 正面为true
	 */
	public boolean isFacade() {
		return buffFlag == 0;
	}

	public short getBuffFlag() {
		return buffFlag;
	}

	public int getJituiDistance() {
		return jituiDistance;
	}

	public int getPercent() {
		return percent;
	}

	/** 是否触发伤害(0触发,1不触发) */
	public int getTypePn() {
		return typePn;
	}

	public String getDesc() {
		return desc;
	}

	public int getContinueCount() {
		return continueCount;
	}

	public boolean isImmb() {
		return type == 18;
	}

	/**
	 * 
	 * @return String
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 效果名称
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	public int getHurtValue() {
		return hurtValue;
	}

	/**
	 * 总的持续时间（ms）
	 * 
	 * @return Integer
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * 单次作用时间
	 * 
	 * @return Integer
	 */
	public int getPreDuration() {
		return this.preDuration;
	}

	/**
	 * 特殊效果变化（施放1、解除2、免疫3）
	 * 
	 * @return Short
	 */
	public short getSpecialEffectChange() {
		return this.specialEffectChange;
	}

	/**
	 * 生命值1、内力值2、怒气值3,、击退4、吸血5、内力值上限8、生命值上限9、会心11、闪避12、命中13、攻击力14、防御力15、封内力16、封体力17、点穴18、中毒19、打落武器20、打落防具21
	 * 
	 * @return Short
	 */
	public short getType() {
		return this.type;
	}

	/**
	 * 技能伤害方式（直接伤害1、持续伤害2）
	 * 
	 * @return Short
	 */
	public short getHurtWay() {
		return this.hurtWay;
	}

	/**
	 * 作用范围（单体、群体）
	 * 
	 * @return Short
	 */
	public short getUserScope() {
		return this.userScope;
	}

	/**
	 * 范围半径设定
	 * 
	 * @return Integer
	 */
	public int getScopeRadius() {
		return this.scopeRadius;
	}

	/**
	 * AOE种类(自身为核心1、目标为核心2）
	 * 
	 * @return Short
	 */
	public short getAoeType() {
		return this.aoeType;
	}

	/**
	 * 伤害值计算方式（单一目标1，全部目标2）
	 * 
	 * @return Short
	 */
	public short getHurtConsiderationWay() {
		return this.hurtConsiderationWay;
	}

	public int getIconId() {
		return iconId;
	}

	public int getEffectId() {
		return effectId;
	}

	public int getGoodmodelId() {
		return goodmodelId;
	}

	public int getHurtAdds() {
		return hurtAdds;
	}

	/**
	 * 作用人数上限
	 * 
	 * @return Integer
	 */
	public int getUseLimit() {
		return this.useLimit;
	}

	/**
	 * 效果叠加次数计数
	 * 
	 * @return
	 */
	public int getEffectCount() {
		return effectCount;
	}

	/**
	 * 效果重复选项：效果叠加1、效果替换2、重复无效3
	 * 
	 * @return Short
	 */
	public short getEffectRepeatOption() {
		return this.effectRepeatOption;
	}

	/**
	 * 叠加上限次数，只有选择效果叠加时有用
	 * 
	 * @return Integer
	 */
	public int getEffectRepeatCount() {
		return this.effectRepeatCount;
	}

	public String getNameI18n() {
		return nameI18n;
	}

	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	public String getDescI18n() {
		return descI18n;
	}

	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

}
