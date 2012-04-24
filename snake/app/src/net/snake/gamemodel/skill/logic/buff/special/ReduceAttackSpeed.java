package net.snake.gamemodel.skill.logic.buff.special;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.EffectType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 错骨分筋
 * @author serv_dev
 *
 */
public class ReduceAttackSpeed extends Buff {

	private static final Logger logger = Logger.getLogger(ReduceAttackSpeed.class);
	
	public ReduceAttackSpeed(EffectInfo effectInfo) {
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
				if (AttackFormula.bangpaiSkillLv(characterSkill.getRealGrade(), affecter.getUnDeAttackSpeedGrade())) {
					int durationTime = AppEventCtlor.getInstance().getEvtSkillFormula().bangpaiSkillDuration(characterSkill.getRealGrade(),affecter.getUnDeAttackSpeedGrade());
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
		
		controller.setReduceAttackSpeedBuff(true);
		int changeAttackSpeed = AppEventCtlor.getInstance().getEvtSkillFormula().getReduceAttackSpeed_CuoGuFenJin();
		int nowAttackSpeed = affecter.getPropertyAdditionController().getExtraAttackSpeed();
		if (nowAttackSpeed <= changeAttackSpeed) {
			changeAttackSpeed = nowAttackSpeed / 2;
		}
		
		affecter.getPropertyAdditionController().getTotalValue().setAttackSpeed(changeAttackSpeed);
		if (affecter.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			affecter.sendMsg(new CharacterOneAttributeMsg49990(affecter,
					EffectType.attackspeed, affecter.getPropertyAdditionController().getExtraAttackSpeed()));
		}
//		affecter.getPropertyAdditionController().recompute();
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setReduceAttackSpeedBuff(false);
		VisibleObject affecter = effect.getAffecter();
		if (affecter == null) {
			//bug如果您发现代码执行到这里，请告诉我              
			affecter = controller.getVo();
			logger.warn(
					"id:"+controller.getVo().getId()+",name:"+((SceneMonster) controller.getVo())
									.getMonsterModel().getName()+",sceneid:"+controller.getVo().getScene()+",attackerid:"+(effect.getAttacker() != null ? effect
									.getAttacker().getId() : 0) );
		}
		affecter.getPropertyAdditionController().recompute();
		return true;
	}

}
