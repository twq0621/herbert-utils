package net.snake.gamemodel.skill.logic.buff.special;

import java.util.Collection;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CancelImmbMsg10154;
import net.snake.ai.fight.response.ImmbMsg10162;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.NetTool;
import net.snake.consts.EffectType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 绵骨大法
 * 
 * @author serv_dev
 * 
 */
public class FengSp extends Buff {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(FengSp.class);

	public FengSp(EffectInfo effect) {
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
					int hitvitalpointTime = AppEventCtlor.getInstance().getEvtSkillFormula().fengSpTime(characterSkill.getRealGrade(), attacker, affecter);
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
					affecter.setNowSp(0);
					affecter.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.sp, affecter.getNowSp()));
				} else {

					if (affecter.getUnFengMpGrade() > 0) {
						FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());
					}
					return false;
				}

			}
		}
		Collection<Hero> chars = affecter.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new ImmbMsg10162(ImmbMsg10162.Effect_Miangu, affecter));
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		VisibleObject affecter = effect.getAffecter();
		Collection<Hero> chars = affecter.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		NetTool.sendToCharacters(chars, new CancelImmbMsg10154(ImmbMsg10162.Effect_Miangu, affecter));
		return true;
	}

	public static Buff getInstance(EffectInfo effect) {
		return new FengSp(effect);
	}

}
