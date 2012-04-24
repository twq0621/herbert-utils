package net.snake.gamemodel.skill.logic.buff.lianti;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;



/**
 * 免费提升突破肉身脱胎换骨
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class Free_roushentuotaihuangu extends Buff {

	public Free_roushentuotaihuangu(EffectInfo effect) {
		super(effect);
	}
	
	@Override
	public boolean enter(EffectController controller) {
		
//		if (!(controller.getVo() instanceof Character)){
//			return false;
//		}
//		
//		Character character = (Character)(controller.getVo());
		
		return true;
	}
	
	@Override
	public boolean leave(EffectController controller) {
//		Character character = (Character)(controller.getVo());
//		character.getLiantiController().setPuticardUseCount(0);
		return true;
	}
	
	
	public static Buff getInstance(EffectInfo effect) {
			return new Free_roushentuotaihuangu(effect);
	}
}
