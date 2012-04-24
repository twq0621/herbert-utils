package net.snake.gamemodel.skill.logic.buff.arrow;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 箭支tip
 * 
 */
public class Arrow_Tip extends Buff {


	public Arrow_Tip(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		return true;
	}
	
	@Override
	public boolean leave(EffectController controller) {
		return true;
	}
	
	@Override
	public boolean leaveCondition(long now) {
		return false;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new Arrow_Tip(effect);
	}
}
