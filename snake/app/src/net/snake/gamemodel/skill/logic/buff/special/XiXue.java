package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.CommonUseNumber;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 吸星大法
 * @author serv_dev
 *
 */
public class XiXue extends Buff {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(XiXue.class);

	public XiXue(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject attacker = effect.getAttacker();
		VisibleObject affecter = effect.getAffecter();
		if (attacker == affecter) {
			return false;
		}
		
		if (affecter.isJumping()) {
			return false;
		}
		
		CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
		if (characterSkill  == null) {
//			logger.warn("enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
			return false;
		}
		
		if (AttackFormula.xixueTriggerlv(characterSkill.getRealGrade(), attacker, affecter)) {
			FighterVO fighterVO = new FighterVO(attacker, attacker,affecter, characterSkill);
			int hurtValue = AppEventCtlor.getInstance().getEvtFightFormula().getHurtValue(attacker, affecter,fighterVO);
			if (attacker.isZeroHp()) return false;
			attacker.changeNowHp(Math.round((float)(hurtValue * AppEventCtlor.getInstance().getEvtSkillFormula().xixuePercent(characterSkill.getRealGrade()) * 0.01)));
			FightMsgSender.directHurtBroadCase(null, attacker,0, CommonUseNumber.byte0);
		} else {
			if (affecter.getUnXiXueGrade() > 0) {
				FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());	
			}
		}
		
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return false;
	}
	
	public static Buff getInstance(EffectInfo effect) {
		return new XiXue(effect);
	}

}
