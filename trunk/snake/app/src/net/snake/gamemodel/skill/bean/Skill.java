package net.snake.gamemodel.skill.bean;

import net.snake.consts.ArrowWay;
import net.snake.consts.WugongType;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.ibatis.IbatisEntity;

/**
 * 玩家技能
 * 
 * @author jack
 */

public class Skill implements IbatisEntity {

	// 数据库映射字段==================================
	private int id;// 技能id
	private String name;// 技能名称
	private String nameI18n;
	private String image;// 技能图标
	/** 冷却时间(ms) */
	private int coolingtime;//
	/** 技能是否触发公共冷却时间,是1,否0 */
	private short iscommonCd;//
	private short depletionParameter;// 技能消耗参数（HP-1、MP-2、SP-3）
	private int depletionValue;// 技能消耗参数值
	/*** 施放距离 */
	private int distance;//
	private short useway;// 技能使用方式(主动使用1、被动加成2）
	/**
	 * 技能目标（1自己、2自己所在的小队、3友好目标、4范围内敌对目标,6当前目标,7地图点做目标,8点击快捷键直接通过鼠标位置释放,9对自己的配偶释放
	 * ,10对正在与您的配偶发生战斗的目标使用,11传送
	 * **/
	private short target;//
	/** 关联的效果ids */
	private String effectIds;//
	/** 是否为普通攻击，0不是，1是 */
	private short isgeneral;//
	/** 人物学习等级限制 */
	private short charLevel;//
	/** 需要什么秘籍来学习 */
	private int learningBook;//
	private String desc;// 描述
	private String descI18n;
	/** 升级修正系数 */
	private int revise;//
	/** 层数上限 */
	private int gradeLimit;//
	/** 技能带来的伤害系数 */
	private float hurtRevise;//
	/** 1马的技能 2角色的技能 */
	private int skilltype;//
	/** 技能触发几率 **/
	private short triggerProbability;// 技能触发几率
	private int effectResId0;// 技能展示效果0
	private int effectResId1;// 技能展示效果1
	private int effectResId2;// 技能展示效果2
	private int showOrder;// 显示顺序
	private int skillRelevance;// 关联技能
	private int playDelay;// 动作延时时间(毫秒)
	private int flySpeed;// 效果飞行速度(像素/秒)
	private String createLearn;// 0非创建角色时学习1创建角色时学习
	private int popsinger;// 门派(1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉)
	private int commonCoolType;// 公共技能冷却时间类型
	private int commonCoolTime;// 公共技能冷却时间(毫秒)
	private int enmityValue;// 技能带来的仇恨值
	/** 增益性技能(不可设为默认)1/伤害性技能2(可设为默认) */
	private int type;//
	private short hurtedTrib;// 0攻击目标时触发1被攻击时触发
	private short wugongType;// 1门派绝学2江湖绝学3其他
	private int kanxingSkill;// 对应的抗性技能id
	private Skill _kanxingSkill;
	private int beidongBaseValue;// 被动技能带来的基础值(目前使用在坐骑技能上)
	private short isZuhe;// (0不是1是)坐骑组合技能
	private int bangGong;// 学习技能消耗帮贡
	private int arrow_way;// 1面攻击(群攻)2线攻击(群攻)3点攻击(单攻)4点攻击(单攻+群攻)
	private int angle;// 面攻击角度(0~360)
	private int arrow_num;// 面攻击群发箭支数量
	private int arrow_point_radius;// 点攻击溅射范围(格子数)
	private int arrow_point_radius_decay_coefficient;// 点攻击溅射范围衰减系数(1~100)百分比
	private int dantian_grade;// 丹田技能限定等级
	// =============================================================
	// private final List<SkillEffect> effects = new ArrayList<SkillEffect>();
	private SkillEffect effect;

	public int getDantian_grade() {
		return dantian_grade;
	}

	public void setDantian_grade(int dantian_grade) {
		this.dantian_grade = dantian_grade;
	}

	/**
	 * 1面攻击(群攻)2线攻击(群攻)3点攻击(单攻)4点攻击(单攻+群攻)
	 * @return
	 */
	public int getArrow_way() {
		return arrow_way;
	}

	public void setArrow_way(int arrow_way) {
		this.arrow_way = arrow_way;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getArrow_num() {
		return arrow_num;
	}

	public void setArrow_num(int arrow_num) {
		this.arrow_num = arrow_num;
	}

	public int getArrow_point_radius() {
		return arrow_point_radius;
	}

	public void setArrow_point_radius(int arrow_point_radius) {
		this.arrow_point_radius = arrow_point_radius;
	}

	public int getArrow_point_radius_decay_coefficient() {
		return arrow_point_radius_decay_coefficient;
	}

	public void setArrow_point_radius_decay_coefficient(int arrow_point_radius_decay_coefficient) {
		this.arrow_point_radius_decay_coefficient = arrow_point_radius_decay_coefficient;
	}

	public int getBangGong() {
		return bangGong;
	}

	public WugongType getWugongTypeConsts() {
		return WugongType.getSkillType(getWugongType());
	}

	public void setBangGong(int bangGong) {
		this.bangGong = bangGong;
	}

	public boolean isZuhe() {
		return isZuhe == 1;
	}

	public Skill getKanxingSkill() {

		if (kanxingSkill == 0) {
			return null;
		}

		if (_kanxingSkill != null) {
			return _kanxingSkill;
		}
		_kanxingSkill = SkillManager.getInstance().getCacheSkillMap().get(kanxingSkill);
		return _kanxingSkill;
	}

	public int getBeidongBaseValue() {
		return beidongBaseValue;
	}

	public int getCommonCoolType() {
		return commonCoolType;
	}

	public int getCommonCoolTime() {
		return commonCoolTime;
	}

	public String getCreateLearn() {
		return createLearn;
	}

	public void setCreateLearn(String createLearn) {
		this.createLearn = createLearn;
	}

	public int getEnmityValue() {
		return enmityValue;
	}

	public int getType() {
		return type;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public int getPlayDelay() {
		return playDelay;
	}

	public void setPlayDelay(int playDelay) {
		this.playDelay = playDelay;
	}

	public int getFlySpeed() {
		return flySpeed;
	}

	public void setFlySpeed(int flySpeed) {
		this.flySpeed = flySpeed;
	}

	/** 1马的技能 2角色的技能 3怪物的技能  4灵宠的神技*/
	public int getSkilltype() {
		return skilltype;
	}

	public void setSkilltype(int skilltype) {
		this.skilltype = skilltype;
	}

	public short getTriggerProbability() {
		return triggerProbability;
	}

	public void setTriggerProbability(short triggerProbability) {
		this.triggerProbability = triggerProbability;
	}

	public float getHurtRevise() {
		return hurtRevise;
	}

	public void setHurtRevise(float hurtRevise) {
		this.hurtRevise = hurtRevise;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRevise() {
		return revise;
	}

	public void setRevise(int revise) {
		this.revise = revise;
	}

	public int getGradeLimit() {
		return gradeLimit;
	}

	public void setGradeLimit(int gradeLimit) {
		this.gradeLimit = gradeLimit;
	}

	public Integer getLearningBook() {
		return learningBook;
	}

	public void setLearningBook(Integer learningBook) {
		this.learningBook = learningBook;
	}

	public int getPopsinger() {
		return popsinger;
	}

	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	public short getCharLevel() {
		return charLevel;
	}

	public void setCharLevel(short charLevel) {
		this.charLevel = charLevel;
	}

	private String getEffectIds() {
		return effectIds;
	}

	public SkillEffect getEffect() {

		if (effect == null) {
			String effectIdStr = getEffectIds();
			String[] effectids = effectIdStr.split(",");
			if (effectids.length > 0) {
				effect = SkillEffectManager.getInstance().getCacheSkillEffect().get(Integer.parseInt(effectids[0]));
				return effect;
			}
		}
		return effect;
	}

	public short getUseway() {
		return useway;
	}

	public void setUseway(short useway) {
		this.useway = useway;
	}

	public short getIsgeneral() {
		return isgeneral;
	}

	public void setIsgeneral(short isgeneral) {
		this.isgeneral = isgeneral;
	}

	public short getWugongType() {
		return wugongType;
	}

	public void setWugongType(short wugongType) {
		this.wugongType = wugongType;
	}

	public short getIsZuhe() {
		return isZuhe;
	}

	public void setIsZuhe(short isZuhe) {
		this.isZuhe = isZuhe;
	}

	public void setCommonCoolType(int commonCoolType) {
		this.commonCoolType = commonCoolType;
	}

	public void setCommonCoolTime(int commonCoolTime) {
		this.commonCoolTime = commonCoolTime;
	}

	public void setEnmityValue(int enmityValue) {
		this.enmityValue = enmityValue;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setHurtedTrib(short hurtedTrib) {
		this.hurtedTrib = hurtedTrib;
	}

	public void setKanxingSkill(int kanxingSkill) {
		this.kanxingSkill = kanxingSkill;
	}

	public void setBeidongBaseValue(int beidongBaseValue) {
		this.beidongBaseValue = beidongBaseValue;
	}

	public void setEffectIds(String effectIds) {
		this.effectIds = effectIds;
	}

	/**
	 * 技能id，命名规则：前缀+技能类别+等级
	 * 
	 * @param String
	 *            id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 技能id，命名规则：前缀+技能类别+等级
	 * 
	 * @return String
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 技能名称
	 * 
	 * @param String
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 技能名称
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 技能图标
	 * 
	 * @param String
	 *            image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 技能图标
	 * 
	 * @return String
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * 冷却时间(ms)
	 * 
	 * @param Integer
	 *            coolingtime
	 */
	public void setCoolingtime(int coolingtime) {
		this.coolingtime = coolingtime;
	}

	/**
	 * 冷却时间(ms)
	 * 
	 * @return Integer
	 */
	public int getCoolingtime() {
		return coolingtime;
	}

	/**
	 * 技能是否触发公共冷却时间,是1,否0
	 * 
	 * @param Short
	 *            iscommonCd
	 */
	public void setIscommonCd(short iscommonCd) {
		this.iscommonCd = iscommonCd;
	}

	/**
	 * 技能是否触发公共冷却时间,是1,否0
	 * 
	 * @return Short
	 */
	public short getIscommonCd() {
		return this.iscommonCd;
	}

	/**
	 * 技能消耗参数（HP-1、MP-2、物品-3,怒气-4）
	 * 
	 * @param Short
	 *            depletionParameter
	 */
	public void setDepletionParameter(short depletionParameter) {
		this.depletionParameter = depletionParameter;
	}

	/**
	 * 技能消耗参数（HP-1、MP-2、物品-3,怒气-4）
	 * 
	 * @return Short
	 */
	public short getDepletionParameter() {
		return this.depletionParameter;
	}

	/**
	 * 技能消耗参数值
	 * 
	 * @param Integer
	 *            depletionValue
	 */
	public void setDepletionValue(int depletionValue) {
		this.depletionValue = depletionValue;
	}

	/**
	 * 技能消耗参数值
	 * 
	 * @return Integer
	 */
	public int getDepletionValue() {
		return this.depletionValue;
	}

	/**
	 * 拖放距离
	 * 
	 * @param Integer
	 *            distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * 拖放距离
	 * 
	 * @return Integer
	 */
	public int getDistance() {
		return this.distance;
	}

	/**
	 * 技能目标（1自己、2自己所在的小队、3友好目标、4范围内敌对目标,6当前目标,7地图点做目标,8点击快捷键直接通过鼠标位置释放,9对自己的配偶释放,10对正在与您的配偶发生战斗的目标使用,11传送
	 * 
	 * @param Short
	 *            target
	 */
	public void setTarget(short target) {
		this.target = target;
	}

	/**
	 * 技能目标（1自己、2自己所在的小队、3友好目标、4范围内敌对目标,6当前目标,7地图点做目标,8点击快捷键直接通过鼠标位置释放,9对自己的配偶释放,10对正在与您的配偶发生战斗的目标使用,11传送
	 * 
	 * @return Short
	 */
	public short getTarget() {
		return this.target;
	}

	public int getEffectResId0() {
		return effectResId0;
	}

	public void setEffectResId0(int effectResId0) {
		this.effectResId0 = effectResId0;
	}

	public int getEffectResId1() {
		return effectResId1;
	}

	public void setEffectResId1(int effectResId1) {
		this.effectResId1 = effectResId1;
	}

	public int getEffectResId2() {
		return effectResId2;
	}

	public void setEffectResId2(int effectResId2) {
		this.effectResId2 = effectResId2;
	}

	public int getSkillRelevance() {
		return skillRelevance;
	}

	public void setSkillRelevance(int skillRelevance) {
		this.skillRelevance = skillRelevance;
	}

	public void setLearningBook(int learningBook) {
		this.learningBook = learningBook;
	}

	public short getHurtedTrib() {
		return hurtedTrib;
	}
	/**
	 * 被动
	 * @return
	 */
	public boolean isPassive() {
		return useway == 2;
	}
	/**
	 * 主动
	 * @return
	 */
	public boolean isZhudong() {
		return useway == 1;
	}

	public boolean isGeneral() {
		return isgeneral == 1;
	}

	/**
	 * 是否是坐标点目标的效果
	 * 
	 * @return
	 */
	public boolean isPointEffect() {
		return getTarget() == 7 || getTarget() == 8;
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

	public ArrowWay getArrowWay() {
		return ArrowWay.getSkillType(getArrow_way());
	}
}
