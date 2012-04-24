package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.commons.GenerateProbability;
import org.apache.log4j.Logger;

/**
 * 该buff由门派绝学3带出 门派绝学3为群攻击能 群攻的目标将记录在effect中 根据每个目标进行判定 {五毒触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%}
 * 
 * @author serv_dev
 * 
 */
public class Du extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Du.class);

	public Du(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {

		VisibleObject attacker = effect.getAttacker();
		VisibleObject affecter = effect.getAffecter();
		// if (attacker == affecter) {
		// return false;
		// }
		if (affecter.isJumping()) {
			return false;
		}

		if (effect.getDuration() > 0) {
		} else {

			if (attacker == null) {
				if (effect.getBufValue() > 0) {
					return true;
				} else {
					return false;
				}
			}

			CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
			if (characterSkill == null) {
				logger.warn("enter(EffectController) - no CharacterSkill:"+ effect.getRelevanceSkillId()); //$NON-NLS-1$
				return false;
			}
			if (AttackFormula.poisoningTriggerlv(characterSkill.getRealGrade(), attacker, affecter)) {
//				if (logger.isDebugEnabled()) {
//					logger.debug("enter(EffectController){} - 中毒的被动技能被触发   中毒的怪物id:{}  controller:{}  effect:{}", new Object[] { this, affecter.getId(), controller, effect }); //$NON-NLS-1$
//				}
				int poisoningTime = AppEventCtlor.getInstance().getEvtSkillFormula().poisoningTime(characterSkill.getRealGrade(), attacker, affecter);
				effect.setDuration(poisoningTime);
				effect.setBufValue(AppEventCtlor.getInstance().getEvtSkillFormula().poisoningHp(attacker, affecter));
				FighterVO fighterVO = effect.getFighterVO();
				if (fighterVO != null) {
					VisibleObject sponsor = fighterVO.getSponsor();
					if (sponsor != null) {
						CharacterSkill _characterSkill = sponsor.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
						if (_characterSkill != null) {
							FightMsgSender.sendBeiDongSkillMsg(sponsor, _characterSkill.getSkill());
						}
					}
				}
				return true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("enter(EffectController) - 中毒的被动技能没有被触发"); //$NON-NLS-1$
				}

				if (affecter.getUnpoisoningGrade() > 0) {
					FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());
				}
				return false;
			}
		}
		// try {
		// controller.processEffectObject(effect.getType(),
		// effect.getBuffValue());//持续伤害的话，也进入本次的战斗伤害计算
		// } catch (Exception e) {
		// logger.error(e.getMessage(),e);
		// }

		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

	@Override
	public boolean leaveCondition(long now) {
		long beginTime = effect.getBufBeginTime();
		int count = effect.getCount();
		long gotime = count * effect.getPreDuration();
		if (now - beginTime > effect.getDuration()) {
//			if (logger.isDebugEnabled()) {
//				logger.debug("leaveCondition() - 中毒的buff{}离开物体{}", new Object[] { this, effect.getAffecter().getId() }); //$NON-NLS-1$
//			}

			return true;
		}

		if (now - (beginTime + gotime) > effect.getPreDuration()) {
			count++;
			effect.setCount(count);
			// effect.setBuffValue((-1) * effect.getBuffValue());//TODO 以后做成公式计算
			// 伤害为负
			zhongdu(effect);
		}

		return false;
	}

	private void zhongdu(EffectInfo effect) {
		VisibleObject vo = effect.getAffecter();
		int value = 0;
		if (effect.getBufValue() > 0) {
			value = effect.getBufValue();
		} else {
			double hurtvalue = vo.getPropertyAdditionController().getExtraMaxHp()
					* ((GenerateProbability.randomIntValue(effect.getHurtValueMin(), effect.getHurtValueMax())) * 0.0001);
			if (effect.getDuMaxHurt() > 0) {
				if (hurtvalue > effect.getDuMaxHurt()) {
					hurtvalue = effect.getDuMaxHurt();
				}
			}
			value = Math.round((float) ((effect.getHurtValue() + (vo.getPropertyAdditionController().getExtraMaxHp() * effect.getPercent()) * 0.0001) + hurtvalue));
			effect.setBufValue(value);
		}

		if (value > vo.getNowHp()) {
			value = vo.getNowHp();
		}
		CharacterSkill characterSkill = null;
		FighterVO fighterVO = null;
		if (effect.getAttacker() != null) {
			characterSkill = effect.getAttacker().getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
			if (characterSkill == null) {
				fighterVO = new FighterVO(effect.getAttacker(), effect.getAttacker(), effect.getAffecter(), SkillManager.getInstance().getSkillById(effect.getRelevanceSkillId()));
			} else {
				fighterVO = new FighterVO(effect.getAttacker(), effect.getAttacker(), effect.getAffecter(), characterSkill);
			}
		} else {
			fighterVO = new FighterVO(effect.getAttacker(), effect.getAttacker(), effect.getAffecter(), SkillManager.getInstance().getSkillById(effect.getRelevanceSkillId()));
		}
		fighterVO.setHurtValue(value);
		fighterVO.setBaoji(0);
		fighterVO.setDeBuff(true);
		vo.onBeAttack(effect.getAttacker(), fighterVO);
	}
}
