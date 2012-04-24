package net.snake.gamemodel.skill.logic.buff.special;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.EffectType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyAdditionController;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 狮吼功
 * 
 * @author serv_dev
 * 
 */
public class ReduceMoveSpeed extends Buff {

	private static final Logger logger = Logger
			.getLogger(ReduceMoveSpeed.class);

	public ReduceMoveSpeed(EffectInfo effectInfo) {
		super(effectInfo);
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
			if (attacker != null) {
				CharacterSkill characterSkill = attacker.getSkillManager()
						.getCharacterSkillById(effect.getRelevanceSkillId());
				if (characterSkill == null) {
//					logger.warn(
//							"enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
					return false;
				}
				if (AttackFormula.bangpaiSkillLv(characterSkill.getRealGrade(),
						affecter.getUnDeMoveSpeedGrade())) {
					int durationTime = AppEventCtlor
							.getInstance()
							.getEvtSkillFormula()
							.bangpaiSkillDuration(
									characterSkill.getRealGrade(),
									affecter.getUnDeMoveSpeedGrade());
					effect.setDuration(durationTime);
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
					if (affecter.getUnDeMoveSpeedGrade() > 0) {
						FightMsgSender.sendBeiDongSkillMsg(affecter,
								characterSkill.getSkill().getKanxingSkill());
					}
					return false;
				}

			}
		}
		controller.setReduceMoveSpeedBuff(true);

		int changeMoveSpeed = AppEventCtlor.getInstance()
				.getEvtSkillFormula().getReduceMoveSpeed_SiHouGong();
		int nowMoveSpeed = affecter.getPropertyAdditionController()
				.getExtraMoveSpeed();
		if (nowMoveSpeed <= changeMoveSpeed) {
			changeMoveSpeed = nowMoveSpeed / 2;
		}

		affecter.getPropertyAdditionController().getTotalValue()
				.setMoveSpeed(changeMoveSpeed);
		affecter.getEyeShotManager().sendMsg(
				new CharacterOneAttributeMsg49990(affecter,
						EffectType.movespeed, affecter
								.getPropertyAdditionController()
								.getExtraMoveSpeed()));
		affecter.getPropertyAdditionController().recompute();
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setReduceMoveSpeedBuff(false);
		VisibleObject affecter = effect.getAffecter();
		if (affecter == null) {
			//bug如果您发现代码执行到这里，请告诉我              
			affecter = controller.getVo();
			logger.warn(
					"id:"+controller.getVo().getId()+",name:"+((SceneMonster) controller.getVo())
									.getMonsterModel().getName()+",sceneid:"+controller.getVo().getScene()+",attackerid:"+(effect.getAttacker() != null ? effect
									.getAttacker().getId() : 0));
		}
		PropertyAdditionController padc = affecter
				.getPropertyAdditionController();
		padc.recompute();
		return true;
	}

}
