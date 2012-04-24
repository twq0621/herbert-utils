package net.snake.gamemodel.skill.logic.buff.spouse;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 * 风雨同济buff
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class FengYuTongJi extends Buff {

	
	public FengYuTongJi(EffectInfo effectInfo) {
		super(effectInfo);
		save = false;
	}

	@Override
	public boolean enter(EffectController controller) {
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		return false;
	}

}
