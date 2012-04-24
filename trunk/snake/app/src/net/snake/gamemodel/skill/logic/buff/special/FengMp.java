package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.EffectType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

import org.apache.log4j.Logger;
/**
 * 化功大法
 * 
 * @author serv_dev
 * 
 */
public class FengMp extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FengMp.class);

	public FengMp(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject attacker = effect.getAttacker();
		VisibleObject affecter = effect.getAffecter();

		if (affecter.isJumping()) {
			return false;
		}

		if (effect.getDuration() > 0) {
		} else {
			if (attacker != null) {
				CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
				if (characterSkill == null) {
//					logger.warn("enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
					return false;
				}
				if (AttackFormula.fengMpTriggerlv(characterSkill.getRealGrade(), attacker, affecter)) {
					int hitvitalpointTime = AppEventCtlor.getInstance().getEvtSkillFormula().fengMpTime(characterSkill.getRealGrade(), attacker, affecter);
					effect.setDuration(hitvitalpointTime);
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
				} else {
					if (affecter.getUnFengMpGrade() > 0) {
						FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());
					}
					return false;
				}

				affecter.setNowMp(0);
				affecter.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.mp, affecter.getNowMp()));
			}
		}
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		if (logger.isDebugEnabled()) {
			logger.debug("leave(EffectController) - 封内力buff离开"); //$NON-NLS-1$
		}

		return true;
	}

}
