package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 无敌buff
 * @author serv_dev
 *
 */
public class UnWithstand extends Buff {

	public UnWithstand(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		controller.setUnWithstand(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setUnWithstand(false);
		return true;
	}

}
