package net.snake.gamemodel.skill.logic.buff.spouse;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 * 合并双休
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class HeBingShuangXiu extends Buff {

	public HeBingShuangXiu(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}
	

}
