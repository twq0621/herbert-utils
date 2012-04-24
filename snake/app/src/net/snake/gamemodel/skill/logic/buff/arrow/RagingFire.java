package net.snake.gamemodel.skill.logic.buff.arrow;

import java.util.Iterator;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.formula.DistanceFormula;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 烈火
 * 
 */
public class RagingFire extends Buff {

	private static final Logger logger = Logger.getLogger(RagingFire.class);

	public RagingFire(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		FighterVO fighterVo = effect.getFighterVO();
		VisibleObject vo = fighterVo.getRecipient();
		Skill skill = fighterVo.getSkill();
		if (skill != null) {
			if (fighterVo.getVisibleObjects() != null) {
				for (Iterator<VisibleObject> iterator = fighterVo.getVisibleObjects().iterator(); iterator.hasNext();) {
					VisibleObject target =  iterator.next();
					int gezi = (int)DistanceFormula.getDistance2(target.getX(), target.getY(), vo.getX(), target.getY());
					float coefficient = -(gezi * skill.getArrow_point_radius_decay_coefficient()/100f);
					coefficient = coefficient < -1 ? -1 : coefficient;
					if (coefficient > 0) coefficient = 0;
					FighterVO fighterVo2 = new FighterVO(fighterVo.getFighter(), fighterVo.getFighter(), target, fighterVo.getCharacterSkill());
					fighterVo2.setHurt_decay_coefficient(coefficient);
					try {
						
						fighterVo.getFighter().getFightController().getEffectHandler().attackHurtValue(fighterVo2, target);
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean leave(EffectController controller) {
		return false;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new RagingFire(effect);
	}
}
