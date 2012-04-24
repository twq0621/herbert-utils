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



/**
 * 千幻掌
 * @author serv_dev
 *
 */
public class ReduceDodge extends Buff {

//	private static final Logger logger = Logger.getLogger(ReduceDodge.class);
	
	public ReduceDodge(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject attacker = effect.getAttacker();
		VisibleObject affecter = effect.getAffecter();
//		if (attacker == affecter) {
//			return false;
//		}
		
		if (affecter.isJumping()) {
			return false;
		}
		
		if (effect.getDuration() > 0) {
		}
		else 
		{	if (attacker != null ) {
				CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
				if (characterSkill  == null) {
//					logger.warn("enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
					return false;
				}
				if (AttackFormula.bangpaiSkillLv(characterSkill.getRealGrade(), affecter.getUnDodgeGrade())) {
					int durationTime = AppEventCtlor.getInstance().getEvtSkillFormula().bangpaiSkillDuration(characterSkill.getRealGrade(),affecter.getUnDodgeGrade());
					effect.setDuration(durationTime);
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
				}else {
					if (affecter.getUnDeMoveSpeedGrade() > 0) {
						FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());	
					}
					return false;
				}
				
			} 
		}
		
		
		controller.setReduceDodgeBuff(true);
		affecter.getPropertyAdditionController().recompute();
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setReduceDodgeBuff(false);
		VisibleObject affecter = effect.getAffecter();
		affecter.getPropertyAdditionController().recompute();
		return true;
	}
	

}
