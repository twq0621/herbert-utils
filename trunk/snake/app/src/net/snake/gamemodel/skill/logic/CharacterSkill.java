package net.snake.gamemodel.skill.logic;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.upgrade.response.SkillMasteryMsg10284;
import net.snake.ai.formula.Formula;
import net.snake.commons.BeanUtils;
import net.snake.commons.script.SCharacterSkill;
import net.snake.consts.ArrowWay;
import net.snake.consts.ClientConfig;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.consts.SkillId;
import net.snake.consts.SkillStatus;
import net.snake.consts.SkillTarget;
import net.snake.consts.WugongType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.quickbar.logic.QuickBarGoods;
import net.snake.gamemodel.skill.bean.CharacterSkillData;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.skill.persistence.SkillUpgradeExpManage;
import net.snake.shell.Options;

import org.apache.log4j.Logger;
/**
 * 人物技能关系
 * 
 * @author serv_dev
 */

public class CharacterSkill extends CharacterSkillData implements QuickBarGoods, PropertyChangeListener, SCharacterSkill {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CharacterSkill.class);
	private Skill skill;
	/** 发起者 */
	private VisibleObject sponsor;//
	/** 攻击者 */
	private VisibleObject fighter;//
	private int actionTime;// 动作播放的时间
	private int triggerProbability;// 触发几率 怪物使用
	private volatile int sendMsgTime = 0;
	private int usedCnt;// 可以使用了多少次
	private int maxUsedCnt;// 使用次数上限
	// private int appendType;
	// private int appendValue;
	private CharacterSkill appendCharacterSkill;

	public CharacterSkill(VisibleObject sponsor, VisibleObject fighter) {
		this.sponsor = sponsor;
		this.fighter = fighter;
	}

	public CharacterSkill(CharacterSkillData skilldata, VisibleObject sponsor, VisibleObject fighter) {
		BeanUtils.copyProperties(skilldata, this);
		this.sponsor = sponsor;
		this.fighter = fighter;
	}

	public void destroy() {
		fighter = null;
		sponsor = null;
		// skill = null;
	}

	public int getMaxUsedCnt() {
		return maxUsedCnt;
	}

	public void setMaxUsedCnt(int maxUsedCnt) {
		this.maxUsedCnt = maxUsedCnt;
	}

	public int getUsedCnt() {
		return usedCnt;
	}

	public void setUsedCnt(int usedCnt) {
		this.usedCnt = usedCnt;
	}

	@Override
	public int getRealGrade() {
		int grade = getGrade();
		if (sponsor != null && sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) (sponsor);
			grade = grade + character.getEquipmentController().getskillAddGrade(getSkillId());
			grade = grade + character.getMyChannelManager().getJinMaiZhongLongAddWuGong();
			DanTian danTian = character.getDanTianController().getDanTian();
			grade = grade + (danTian == null ? 0 : danTian.getWuXueGrade(getSkillId()));
			// CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(Position.POSTION_TESHU);
			// if (cg != null) {
			// grade = grade + (character.getEquipmentController().isShelongHushenFu() ? cg.getGoodModel().getWugongGrade() : 0);
			// }
		}
		return grade;
	}

	public int getCommonCoolTime() {

		return (int) (getSkill().getCommonCoolTime() / (1 + (getSponsor().getPropertyAdditionController().getExtraAttackSpeed() * 0.001)));
	}

	@Override
	public Long getStartcd() {
		Long started = super.getStartcd();
		return started == null ? 0 : started;
	}

	public int getTriggerProbability() {
		return triggerProbability;
	}

	public void setTriggerProbability(int triggerProbability) {
		this.triggerProbability = triggerProbability;
	}

	/**
	 * 
	 * 
	 * 根据目标的情况产生一个触发概率 （ 破箭式 降低您受到其他玩家弓箭箭术技能攻击几率30% 破气式 降低您受到其他玩家丹田武学的攻击几率30% ）
	 * 
	 * @param vo
	 *            目标
	 * @return 概率
	 */
	public int getTriggerProbabilityByTarget(VisibleObject vo) {
		int triblv = getSkill().getTriggerProbability();
		WugongType wType = getSkill().getWugongTypeConsts();
		int reduce_jv = 0;
		switch (wType) {
		case GONG:
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero affector = (Hero) vo;
				reduce_jv = ((CharacterPropertyAdditionControllerImp) affector.getPropertyAdditionController()).getDugujiujian().getReduce_gong();
			}
			break;
		case Dantian:
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero affector = (Hero) vo;
				reduce_jv = ((CharacterPropertyAdditionControllerImp) affector.getPropertyAdditionController()).getDugujiujian().getReduce_dantian();
			}
		default:
			break;
		}

		triblv = (int) (triblv * ((1 - reduce_jv / 10000f)));
		return triblv;
	}

	/**
	 * 动作播放的时间
	 * 
	 * @return
	 */
	public int getActionTime() {
		return actionTime;
	}

	public void setActionTime(int actionTime) {
		this.actionTime = actionTime;
	}

	public VisibleObject getSponsor() {
		return sponsor;
	}

	public VisibleObject getFighter() {
		return fighter;
	}

	public Skill getSkill() {
		if (skill == null) {
			int si = getSkillId();
			skill = SkillManager.getInstance().getSkillById(si);
		}
		return skill;
	}

	/**
	 * 升级需要的真气
	 * 
	 * @return
	 */
	public SkillUpgradeExp upgradeNeedZhenqi() {
		if (isPinKan()) {
			return null;
		}
		int grade = getGrade()+1;
		int skillId = getSkillId();
		return SkillUpgradeExpManage.getInstance().getSkillUpgradeExp(skillId, grade);
	}

	public PropertyEntity getPropertyEntity() {
		if (skill.isZhudong()) {
			return null;
		}
		// 被动技能
		SkillEffect effect = skill.getEffect();
		if (effect == null) {
			return null;
		}
		int type = effect.getType();
		// 技能属性加成
		if (skill.getSkillRelevance() != 0) {
			Hero _character = null;
			if (sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				_character = (Hero) sponsor;
			} else {
				return null;
			}
			CharacterSkill _charSkill = _character.getCharacterSkillManager().getCharacterSkillById(skill.getSkillRelevance());
			if (_charSkill != null) {
				_charSkill.appendCharacterSkill = this;
			}
			return null;
		}
		Property property = Property.getpPropertyByEffectType(type);
		PropertyEntity pe = null;
		// 人物属性加成
		// if (skill.getSkilltype() == 2) {
		// }
		pe = this.getCharacterPropertyEntity(property);
		return pe;
	}

	/**
	 * 人物被动技能的加成
	 * 
	 * @param property
	 * @return
	 */
	public PropertyEntity getCharacterPropertyEntity(Property property) {
		PropertyEntity pe = new PropertyEntity();
		// if (!(sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero)) {
		// return null;
		// }
		int value = 0;
		int peValue = 0;
		if (property == Property.all) {
			// 主要提升的属性是：攻击、防御、生命上限、法力上限，暴击、闪避
			peValue = sponsor.getValueByProperty(Property.attack);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.attack, value);

			peValue = sponsor.getValueByProperty(Property.defence);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.defence, value);

			peValue = sponsor.getValueByProperty(Property.maxHp);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.maxHp, value);

			peValue = sponsor.getValueByProperty(Property.maxMp);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.maxMp, value);

			peValue = sponsor.getValueByProperty(Property.crt);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.crt, value);

			peValue = sponsor.getValueByProperty(Property.dodge);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(Property.dodge, value);
		} else {
//			if(property==Property.attack){
//			}
			peValue = sponsor.getValueByProperty(property);
			value = this.getPassiveAppendValue(peValue) - peValue;
			pe.addExtraProperty(property, value);
		}
		return pe;
	}

	/**
	 * 计算被动给人物相关属性的加成
	 * 
	 * @param peValue
	 *            要加成的属性的值
	 * @return 返回加成后结果
	 */
	public int getPassiveAppendValue(int peValue) {
		if (skill.isZhudong()) {
			return peValue;
		}
		int skillgrade = getRealGrade();
		int value = 0;
		if (skill.getEffect().getHurtValue() == 0) {
			double wancent = skill.getEffect().getPercent() / Formula.Wan + (skillgrade - 1) * 0.03;
			int disval = 0;
			if (wancent > 0) {
				disval = 1;
			}
			value = (int) (peValue * (1 + wancent)) + disval;
		} else {
			value = peValue + skill.getEffect().getHurtValue();
		}
		return value;
	}

	/**
	 * 得到附加在该技能上的加强技能对该属性值的影响
	 * 
	 * @param peValue
	 * @return 如果没有加强技能，则返回该属性值本身
	 */
	public int getAppendSkillValue(int peValue) {
		CharacterSkill appendCharacterSkill = this.getAppendCharacterSkill();
		if (appendCharacterSkill != null) {
			peValue = appendCharacterSkill.getPassiveAppendValue(peValue);
		}
		return peValue;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		if (getSkill().getSkilltype() == 2) {
			return PropertyAdditionType.xinfa;
		} else {
			return PropertyAdditionType.zuoqiJineng;
		}
	}

	/**
	 * 是否处于冷却状态
	 * 
	 * @return
	 */
	public boolean cool() {
		long nowstart = System.currentTimeMillis();
		long time = nowstart - getStartcd();
		if (time < 0)
			setStartcd(nowstart);
		if (nowstart - getStartcd() < getCoolingtime()) {
			return true;
		} else {
			return false;
		}
	}

	/** 技能冷却时间/(1+攻击速度值/1000) */
	public int getCoolingtime() {
		int coolingTime = getSkill().getCoolingtime();
		if (getSkillId() == SkillId.hong_yan_skill && getSponsor().getSceneObjType() == SceneObj.SceneObjType_Hero) {
			int haoGanV = ((Hero) getSponsor()).getMyFriendManager().getRoleWedingManager().getPeiouFavor();

			// skill表中规定的冷却时间（毫秒） - 夫妻之间的友好度 若计算所得值低于10000毫秒，则以10000毫秒作为最小冷却时间
			coolingTime = coolingTime - haoGanV;
			if (coolingTime < 10000) {
				coolingTime = 10000;
			}
		}
		int returnV = (int) (coolingTime / (1 + (getSponsor().getPropertyAdditionController().getExtraAttackSpeed() * 0.001)));
		return returnV;
	}

	/**
	 * 是否可以使用该技能 验证冷却时间、公共冷却时间、消耗hp,mp,sp
	 * 
	 * @return true 可以使用
	 */
	public SkillStatus ableUse() {
		if (getSkill().getShowOrder() != 0) {
			if (Options.IsCrossServ && SkillTarget.getTarget(skill.getTarget()) == SkillTarget.ScopeHostilityTarge) {
				return SkillStatus.lunjianqungongunused;
			}
			// 是否处于公共冷却时间内
			if (sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero && ((Hero) sponsor).getFightController().isCommonCool(this)) {
				if (logger.isDebugEnabled()) {
					logger.debug("公共时间冷却中");
				}
				return SkillStatus.commonCooling;
			}
			SkillTarget st = SkillTarget.getTarget(getSkill().getTarget());
			// 是否处于冷却时间内
			if (cool()) {
				if (SkillTarget.SpouseFighting == st) {
					sponsor.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20115));
				}
				return SkillStatus.cooling;
			}
		}
		// 技能消耗魔法、道具等
		return consume();
	}

	/**
	 * 消耗的物质条件判定
	 * 
	 * @param vo
	 *            该对象可消耗的HP、MP、SP
	 * @return 0表示可以使用该技能
	 */
	private SkillStatus consume() {
		Skill _skill = getSkill();
		// 是否有需要消耗的物品（HP、MP、怒气）
		int _depletionValue = _skill.getDepletionValue();
		int _depletionParameter = _skill.getDepletionParameter();
		switch (_depletionParameter) {
		case 1:
			int _nowhp = fighter.getNowHp();
			if (_nowhp < _depletionValue) {
				return SkillStatus.no_enough_hp;
			}
			break;
		case 2:
			if (sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				if (!(_skill.getArrowWay() == ArrowWay.NULL)) {
					return SkillStatus.ok;
				}
			}
			int _nowmp = fighter.getNowMp();
			// 技能施法消耗MP=技能消耗参数值字段数值*对应技能等级/10（向上取整）
			_depletionValue = 10 + Math.round(_depletionValue * getGrade() * 0.1f);
			if (_nowmp < _depletionValue) {
				return SkillStatus.no_enough_mp;
			}
			break;
		case 3:
			int _nowsp = fighter.getNowSp();
			//_depletionValue = 10 + Math.round(_depletionValue * getGrade() * 0.1f);
			if (_nowsp < _depletionValue) {
				return SkillStatus.no_enough_sp;
			}
		default:
			break;
		}
		return SkillStatus.ok;
	}

	public void xiaohaoValue(Hero character) {
		this.xiaohaoValue(character, null);
	}

	/**
	 * 消耗的物质\更新冷却时间\触发公共冷却时间
	 * 
	 * @param vo
	 *            该对象可消耗的HP、MP、SP
	 * @return
	 */
	public void xiaohaoValue(Hero character, CharacterSkill charSkill) {

		Skill _skill = getSkill();
		// 是否有需要消耗的物品（HP、MP、怒气）
		int _depletionValue = _skill.getDepletionValue();
		int _depletionParameter = _skill.getDepletionParameter();
		switch (_depletionParameter) {
		case 1:
			int _nowhp = character.getNowHp();
			if (_nowhp >= _depletionValue) {
				CharacterPropertyManager.changeNowHp(character, _depletionValue * (-1));
			}
			break;
		case 2:
			int _nowmp = character.getNowMp();
			/**
			 * 技能施法消耗MP=10+技能消耗参数值字段数值*对应技能等级/10（向上取整）
			 * 
			 * 技能消耗MP公式增加了基础值 10
			 */
			// 技能施法消耗MP=技能消耗参数值字段数值*对应技能等级/10（向上取整）
			_depletionValue = 10 + Math.round(_depletionValue * getGrade() * 0.1f);
			if (charSkill != null) {
				_depletionValue = charSkill.getAppendSkillValue(_depletionValue);
			}
			if (_nowmp >= _depletionValue) {
				CharacterPropertyManager.changeNowMp(character, _depletionValue * (-1));
			}
			break;
		case 3:
			int _nowsp = character.getNowSp();
			//_depletionValue = 10 + Math.round(_depletionValue * getGrade() * 0.1f);
			if (_nowsp >= _depletionValue) {
				CharacterPropertyManager.changeNowSp(character, _depletionValue * (-1));
			}
		default:
			break;
		}
		triCooltime();
		// 技能加熟练度
		addMastery(character);
	}

	/**
	 * 加熟练度
	 * 
	 * @param character
	 */
	public void addMastery(Hero character) {
		if (Options.IsCrossServ) {
			return;
		}

		if (getSkill().getWugongTypeConsts() == WugongType.GONG) {
			return;
		}
		SkillUpgradeExp exp = upgradeNeedZhenqi();
		if (exp == null) {
			return;
		}
		if (getGrade() >= getSkill().getGradeLimit()) {
			return;
		}
		if (getMastery() < exp.getExpZhengqi()) {
			boolean pinjin = exp.getPinjin() == 1 ? true : false;
			if (pinjin && !getPo()) {
				if (sendMsgTime % 10 == 0) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 548));
				}
				sendMsgTime++;
			} else {
				setMastery(getMastery() + 1);
				character.sendMsg(new SkillMasteryMsg10284(getSkillId(), getMastery()));
			}
		} else {
			if (getGrade() < character.getGrade()) {
				Integer expZhengqi = exp.getExpZhengqi() - getMastery();
				if (character.getZhenqi() >= expZhengqi) {
					if (sendMsgTime % 10 == 0) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 549, getSkill().getNameI18n()));
					}
					sendMsgTime++;
				}
			}
		}
		((CharacterSkillManager) character.getSkillManager()).updateCharacterSkill(this);
	}

	public void triCooltime() {
		long nowstart = System.currentTimeMillis();
		setStartcd(nowstart);
	}

	/**
	 * 是否为普通技能
	 * 
	 * @return
	 */
	public boolean isPinKan() {
		return getSkill().isGeneral();
	}

	@Override
	public int getHurtAdds() {
		SkillEffect skillEffect = getSkill().getEffect();
		if (skillEffect == null) {
			return 0;
		}
		int hurtadds = skillEffect.getHurtAdds();
		hurtadds = this.getAppendSkillValue(hurtadds);
		return hurtadds;
	}

	@Override
	public float getHurtRevise() {
		Skill skill = getSkill();
		if (skill == null) {
			return 0;
		}
		return skill.getHurtRevise();
	}

	@Override
	public int getRevise() {
		Skill skill = getSkill();
		if (skill == null) {
			return 0;
		}
		return skill.getRevise();
	}

	/**
	 * 是否可以使用（是否可以使用该技能 验证冷却时间、公共冷却时间、消耗hp,mp,sp）
	 * 
	 * @param targetObject
	 *            目标
	 * @return true可以使用
	 */
	public boolean able2Use(VisibleObject targetObject) {

		SkillStatus skillStus = ableUse();
		if(skillStus==SkillStatus.no_enough_sp){
			sponsor.sendMsg(new  PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60060));
		}
		if (skillStus != SkillStatus.ok) {
			FightMsgSender.sendSkillReleaseFailMsg(sponsor, skillStus);
			return false;
		}

		SkillTarget st = SkillTarget.getTarget(getSkill().getTarget());
		if (SkillTarget.Transport == st) {
			if (sponsor.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero c = ((Hero) sponsor);
				RoleWedingManager roleWedingManager = c.getMyFriendManager().getRoleWedingManager();
				Hero wedder = GameServer.vlineServerManager.getCharacterById(roleWedingManager.getWedderId());
				if (wedder == null) {
					sponsor.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20116));
					return false;
				}
				long now = System.currentTimeMillis();
				if (c.inFighting(now)) {
					long shenyuTime = (ClientConfig.FightingTime - (now - c.getFightController().getAttackModelBeginTime())) / 1000;
					sponsor.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20125, shenyuTime == 0 ? 1 : shenyuTime));
					return false;
				}

				if (wedder.getSceneRef() == null) {
					return false;
				}

				if (wedder.getSceneRef().isInstanceScene()) {
					sponsor.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20117));
					return false;
				}
			}
		}

		return true;
	}

	public CharacterSkill getAppendCharacterSkill() {
		return appendCharacterSkill;
	}

	public void setAppendCharacterSkill(CharacterSkill appendCharacterSkill) {
		this.appendCharacterSkill = appendCharacterSkill;
	}

}
