package net.snake.gamemodel.skill.logic.buff.arrow;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 失去防御
 * 
 */
public class Nodefence extends Buff {

	public Nodefence(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		
		controller.setNodefence(true);
		controller.getVo().getPropertyAdditionController().recompute();
		return true;
	}
	
	@Override
	public boolean leave(EffectController controller) {
		controller.setNodefence(false);
		controller.getVo().getPropertyAdditionController().recompute();
		return true;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new Nodefence(effect);
	}
}
