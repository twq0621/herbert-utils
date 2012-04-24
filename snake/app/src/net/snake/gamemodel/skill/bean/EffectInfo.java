package net.snake.gamemodel.skill.bean;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.logic.buff.Buff;
import net.snake.gamemodel.skill.logic.buff.BuffFactory;

/**
 * 效果对象
 * 
 * @author serv_dev
 * 
 */
public class EffectInfo {
	// SkillEffect是技能效果的静态数据 EffectInfo为SkillEffect的一个实例，用于效果计算
	private SkillEffect effect;
	private Buff buff;// 一定有值的
	private VisibleObject affecter;// 效果对象
	private VisibleObject attacker;// 施加者 举例，坐骑打怪（怪是效果对像，人是施加者,坐骑是发起者)
	private FighterVO fighterVO;
	private int duration;// 持续时间
	private long bufBeginTime;// buff开始时间
	private int bufValue;// buff值
	private int relevanceSkillId; // 效果所关联的技能
	private int count;// buff时间数数
	private int delayRecoverTime;// 延迟（随机1-10秒）补满
	private int remainPoint;// 例：续命丹剩余点的血量
	private int duration2;// 持续时间2(吃药获得)
	private int effectInfoType;// 类型
	private String jingmaiId = "";// 可影响到的筋脉
	private int passNum;// 被传递的次数

	public EffectInfo(SkillEffect skillEffect) {
		if (skillEffect != null) {
			effect = skillEffect;
			setDuration(skillEffect.getDuration());
			setBufValue(skillEffect.getHurtValue());
		}
	}

	public void destroy() {
		affecter = null;
		attacker = null;
		if (fighterVO != null) {
			fighterVO.destroy();
			fighterVO = null;
		}
	}

	/**
	 * 被传递的次数
	 * 
	 * @return
	 */
	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	public String getJingmaiId() {
		return jingmaiId;
	}

	public void setJingmaiId(String jingmaiId) {
		this.jingmaiId = jingmaiId;
	}

	public boolean isAttackerDieClean() {
		return effect.getAttackIsDieClean() == 1 ? true : false;
	}

	public int getDuMaxHurt() {
		return effect.getDuMaxHurt();
	}

	public int getEffectInfoType() {
		return effectInfoType;
	}

	public int getHurtValueMin() {
		return effect.getHurtValueMin();
	}

	public int getHurtValueMax() {
		return effect.getHurtValueMax();
	}

	public void setEffectInfoType(int effectInfoType) {
		this.effectInfoType = effectInfoType;
	}

	public FighterVO getFighterVO() {
		return fighterVO;
	}

	public void setFighterVO(FighterVO fighterVO) {
		this.fighterVO = fighterVO;
	}

	public int getDuration2() {
		return duration2;
	}

	public void setDuration2(int duration2) {
		this.duration2 = duration2;
	}

	public void randomDelayRecoverTime() {
		delayRecoverTime = effect.getPreDuration();
	}

	public int getDelayRecoverTime() {
		return delayRecoverTime;
	}

	public void setDelayRecoverTime(int delayRecoverTime) {
		this.delayRecoverTime = delayRecoverTime;
	}

	public int getRemainPoint() {
		return remainPoint;
	}

	public void setRemainPoint(int remainPoint) {
		if (remainPoint < 0)
			remainPoint = 0;
		this.remainPoint = remainPoint;
	}

	public short getIsDieClean() {
		return effect == null ? 0 : effect.getIsDieClean();
	}

	public SkillEffect getEffect() {
		return effect;
	}

	public VisibleObject getAffecter() {
		return affecter;
	}

	public void setAffecter(VisibleObject affecter) {
		this.affecter = affecter;
	}

	/**
	 * 施加者 举例，坐骑打怪（怪是效果对像，人是施加者,坐骑是发起者)
	 * 
	 * @return
	 */
	public VisibleObject getAttacker() {
		return attacker;
	}

	public void setAttacker(VisibleObject attacker) {
		this.attacker = attacker;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration < 0)
			duration = 0;
		this.duration = duration;
	}

	public int getBufValue() {
		return bufValue;
	}

	public void setBufValue(int bufValue) {
		this.bufValue = bufValue;
	}

	public int getRelevanceSkillId() {
		return relevanceSkillId;
	}

	public void setRelevanceSkillId(int relevanceSkillId) {
		this.relevanceSkillId = relevanceSkillId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getBufBeginTime() {
		return bufBeginTime;
	}

	public void setBufBeginTime(long bufBeginTime) {
		this.bufBeginTime = bufBeginTime;
	}

	/**
	 * 作用人数上限
	 * 
	 * @return Integer
	 */
	public Integer getUseLimit() {
		return effect == null ? 0 : effect.getUseLimit();
	}

	/**
	 * 效果叠加次数计数
	 * 
	 * @return
	 */
	public int getEffectCount() {
		return effect == null ? 0 : effect.getEffectCount();
	}

	/**
	 * 效果重复选项：效果叠加1、效果替换2、重复无效3
	 * 
	 * @return Short
	 */
	public Short getEffectRepeatOption() {
		return effect == null ? 0 : effect.getEffectRepeatOption();
	}

	/**
	 * 叠加上限次数，只有选择效果叠加时有用
	 * 
	 * @return Integer
	 */
	public Integer getEffectRepeatCount() {
		return effect == null ? 0 : effect.getEffectRepeatCount();
	}

	/**
	 * 作用范围（单体、群体）
	 * 
	 * @return Short
	 */
	public short getUserScope() {
		return effect == null ? 0 : effect.getUserScope();
	}

	/**
	 * 范围半径设定
	 * 
	 * @return Integer
	 */
	public int getScopeRadius() {
		return effect == null ? 0 : effect.getScopeRadius();
	}

	/**
	 * AOE种类(自身为核心1、目标为核心2）
	 * 
	 * @return Short
	 */
	public short getAoeType() {
		return effect == null ? 0 : effect.getAoeType();
	}

	/**
	 * 伤害值计算方式（单一目标1，全部目标2）
	 * 
	 * @return Short
	 */
	public short getHurtConsiderationWay() {
		return effect == null ? 0 : effect.getHurtConsiderationWay();
	}

	public int getHurtValue() {
		return effect == null ? 0 : effect.getHurtValue();
	}

	public int getIconId() {
		return effect == null ? 0 : effect.getIconId();
	}

	public int getEffectId() {
		return effect == null ? 0 : effect.getEffectId();
	}

	public int getGoodmodelId() {
		return effect == null ? 0 : effect.getGoodmodelId();
	}

	public int getHurtAdds() {
		return effect == null ? 0 : effect.getHurtAdds();
	}

	/**
	 * 单次作用时间
	 * 
	 * @return Integer
	 */
	public int getPreDuration() {
		return effect == null ? 0 : effect.getPreDuration();
	}

	/**
	 * 特殊效果变化（施放1、解除2、免疫3）
	 * 
	 * @return Short
	 */
	public short getSpecialEffectChange() {
		return effect == null ? 0 : effect.getSpecialEffectChange();
	}

	/**
	 * 生命值1、内力值2、怒气值3,、击退4、吸血5、内力值上限8、生命值上限9、会心11、闪避12、命中13、攻击力14、防御力15、封内力16、
	 * 封体力17、点穴18、中毒19、打落武器20、打落防具21
	 * 
	 * @return Short
	 */
	public short getType() {
		return effect == null ? 0 : effect.getType();
	}

	/**
	 * 技能伤害方式（直接伤害1、持续伤害2）
	 * 
	 * @return Short
	 */
	public short getHurtWay() {
		return effect == null ? 0 : effect.getHurtWay();
	}

	/**
	 * 
	 * @return String
	 */
	public int getId() {
		return effect == null ? 0 : effect.getId();
	}

	/**
	 * 效果名称
	 * 
	 * @return String
	 */
	public String getName() {
		return effect == null ? "" : effect.getNameI18n();
	}

	public boolean isImmb() {
		return effect == null ? false : effect.isImmb();
	}

	public boolean leaveCondition(long now) {
		return getBuff() == null ? false : getBuff().leaveCondition(now);
	}

	public Integer getJituiDistance() {
		return effect == null ? 0 : effect.getJituiDistance();
	}

	public int getPercent() {
		return effect == null ? 0 : effect.getPercent();
	}

	/** 是否触发伤害(0触发,1不触发) */
	public int getTypePn() {
		return effect == null ? 0 : effect.getTypePn();
	}

	public String getDesc() {
		return effect == null ? "" : effect.getDescI18n();
	}

	public int getContinueCount() {
		return effect == null ? 0 : effect.getContinueCount();
	}

	public void setBuff(Buff buff) {
		this.buff = buff;
	}

	/**
	 * 正面还是负面buff
	 * 
	 * @return 正面为true
	 */
	public boolean isFacade() {
		return effect == null ? false : effect.isFacade();
	}

	/**
	 * 0为正面buff,1为负面buff
	 * 
	 * @return
	 */
	public short getBuffFlag() {
		return effect == null ? 0 : effect.getBuffFlag();
	}

	public boolean init(EffectController controller) {
		Buff buff = BuffFactory.getDef(this);
		if (buff == null) {
			return false;
		}
		setBuff(buff);
		return buff.init(controller);
	}

	public boolean enter(EffectController controller) {
		Buff buff = getBuff();
		return buff.enter(controller);
	}

	public boolean leave(EffectController controller) {
		Buff buff = getBuff();
		if (buff == null) {
			return false;
		}
		return buff.leave(controller);
	}

	public Buff getBuff() {
		return buff;
	}

}
