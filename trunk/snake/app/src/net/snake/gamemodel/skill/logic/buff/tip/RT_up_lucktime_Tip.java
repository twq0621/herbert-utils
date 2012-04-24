package net.snake.gamemodel.skill.logic.buff.tip;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 箭支tip
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class RT_up_lucktime_Tip extends Buff {

	public RT_up_lucktime_Tip(EffectInfo effect) {
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
	
}
