package net.snake.gamemodel.skill.logic.buff;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;


public class BuffNull2 extends Buff {
	
	

	public BuffNull2(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		if (effect == null) return false;
		if (effect.getDuration() <= 0) return false;
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}
}
