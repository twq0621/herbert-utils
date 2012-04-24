package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.JituiResponse10144;
import net.snake.ai.formula.AttackFormula;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

import org.apache.log4j.Logger;

/**
 * 击退buff
 * 坐骑技能使用是配置默认的击退距离
 * 人物使用时默认击退技能为0
 * @author serv_dev
 *
 */
public class JiTui extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JiTui.class);

	public JiTui(EffectInfo effect) {
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
		
		if (affecter.isJiTui()) {
			return false;
		}
		
		int distance = effect.getJituiDistance();
		CharacterSkill characterSkill = attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
		if (distance > 0) {
			
		} 
		else
		{
			
			if (characterSkill  == null) {
//				logger.warn("enter(EffectController) - 不存在的CharacterSkill:{}", effect.getRelevanceSkillId()); //$NON-NLS-1$
				return false;
			}
			if (AttackFormula.knockbackTriggerlv(characterSkill.getRealGrade(), attacker, affecter)) {//成功触发击飞
				 distance = AppEventCtlor.getInstance().getEvtSkillFormula().knockbackDistance(characterSkill.getRealGrade(), attacker, affecter);
			} else {
				if (affecter.getUnKnockbackGrade() > 0) {
					FightMsgSender.sendBeiDongSkillMsg(affecter, characterSkill.getSkill().getKanxingSkill());	
				}
				return false;
			}
		}
		
		if (distance < 3) {//3为最小，8为最大
			distance = 3;
		} else if (distance > 8) {
			distance = 8;
		}
		
		if (tribJitui(attacker, affecter, distance,distance/4,1)) {
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
		}
		
		return false;
	}

	private boolean tribJitui(VisibleObject attacker, VisibleObject affecter,
			int distance,int distance2,int censhu) {
		short jituiX = AttackFormula.jituiPoint(attacker.getX(), affecter.getX(), distance);
		short jituiY = AttackFormula.jituiPoint(attacker.getY(), affecter.getY(), distance);
		Scene scene = attacker.getSceneRef();
		if (scene.isPermitted(jituiX, jituiY) && scene.findWay(affecter.getX(), affecter.getY(), jituiX, jituiY) != null) {
			affecter.setX(jituiX);
			affecter.setY(jituiY);
			affecter.getEyeShotManager().sendMsg(new JituiResponse10144(affecter));
			affecter.onBeJiTui(distance);
			affecter.getEyeShotManager().onMove();

//			if (logger.isDebugEnabled()) {
//				logger.debug("enter(EffectController) - 角色{}被击退",affecter.getId()); //$NON-NLS-1$
//			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("没有击退点"); //$NON-NLS-1$
			}
			censhu ++;//层数
			if (censhu > 5) {
				return false;
			}
			return tribJitui(attacker,affecter,(distance - distance2),distance2,censhu);
		}
	}

	@Override
	public boolean leave(EffectController controller) {
		return false;
	}
	

}
