package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.Property;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;
import net.snake.commons.script.SPropertyAdditionController;
import org.apache.log4j.Logger;

/**
 * 脱袍裂甲
 * 
 * @author serv_dev
 * 
 */
public class InfluenceDefend extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InfluenceDefend.class);

	public InfluenceDefend(EffectInfo effect) {
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
			if (attacker == null) {
				if (effect.getBufValue() > 0) {
					return true;
				} else {
					return false;
				}
			}

			CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
			if (characterSkill == null) {
//				logger.warn("enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
				return false;
			}

			if (AttackFormula.fengHujuTriggerlv(characterSkill.getRealGrade(), attacker, affecter)) {
				effect.setDuration(AppEventCtlor.getInstance().getEvtSkillFormula().fengHujuTime(characterSkill.getRealGrade(), attacker, affecter));
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
				if (affecter.getUnHujuGrade() > 0) {
					FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());
				}
				return false;
			}
		}
//		logger.info("enter(EffectController) - 改变属性前{}", affecter.toString()); //$NON-NLS-1$
		affecter.getPropertyAdditionController().addChangeListener(this);
//		logger.info("enter(EffectController) - 改变属性后{}", affecter.toString()); //$NON-NLS-1$

		return true;

	}

	@Override
	public boolean leave(EffectController controller) {
		effect.getAffecter().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity propertyEntity = new PropertyEntity();
		SPropertyAdditionController affecterPropertyController = effect.getAffecter().getPropertyAdditionController();
		int characterEquipDefend = 0;
		if (effect.getAffecter().getSceneObjType() == SceneObj.SceneObjType_Hero) {
			characterEquipDefend = ((CharacterPropertyAdditionControllerImp) affecterPropertyController).getZhuangbei().getDefend();
		}
		logger.info("eff" + effect.getBufValue());
		logger.info("characterEquipDefend=" + characterEquipDefend);
		propertyEntity.addExtraProperty(Property.defence, (-1) * characterEquipDefend);
		return propertyEntity;
	}

}
