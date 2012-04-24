package net.snake.gamemodel.skill.logic.buff.special;

import java.util.Collection;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CancelImmbMsg10154;
import net.snake.ai.fight.response.ImmbMsg10162;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.NetTool;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;
/**
 * 定身
 * 
 * @author serv_dev
 * 
 */
public class Immb extends Buff {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(Immb.class);

	public Immb(EffectInfo effect) {
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
				CharacterSkill characterSkill = attacker.getSkillManager()
						.getCharacterSkillById(effect.getRelevanceSkillId());
				if (characterSkill == null) {
//					logger.warn(
//							"enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
					return false;
				}

				if (AttackFormula.hitvitalpointTriggerlv(
						characterSkill.getRealGrade(), attacker, affecter)) {
					int hitvitalpointTime = AppEventCtlor
							.getInstance()
							.getEvtSkillFormula()
							.hitvitalpointTime(characterSkill.getRealGrade(),
									attacker, affecter);
					effect.setDuration(hitvitalpointTime);
					FighterVO fighterVO = effect.getFighterVO();
					if (fighterVO != null) {
						VisibleObject sponsor = fighterVO.getSponsor();
						if (sponsor != null) {
							CharacterSkill _characterSkill = sponsor
									.getSkillManager().getCharacterSkillById(
											effect.getRelevanceSkillId());
							if (_characterSkill != null) {
								FightMsgSender.sendBeiDongSkillMsg(sponsor,
										_characterSkill.getSkill());
							}
						}
					}
				} else {
					if (affecter.getUnhitvitalpointGrade() > 0) {
						FightMsgSender.sendBeiDongSkillMsg(affecter,
								characterSkill.getSkill().getKanxingSkill());
					}
					return false;
				}
			}
		}
		Collection<Hero> chars = affecter.getEyeShotManager()
				.getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new ImmbMsg10162(
				ImmbMsg10162.Effect_Dingshen, affecter));
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		VisibleObject vo = controller.getVo();
		Collection<Hero> chars = vo.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new CancelImmbMsg10154(
				ImmbMsg10162.Effect_Dingshen, vo));
		return true;
	}

	public static Buff getInstance(EffectInfo effect) {
		return new Immb(effect);
	}
}
