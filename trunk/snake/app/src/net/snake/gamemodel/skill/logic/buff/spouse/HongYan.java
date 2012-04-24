package net.snake.gamemodel.skill.logic.buff.spouse;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.EffectType;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 
 * 红颜 
 * 
 * 释放后清空释放者当前的内力值，被命中者生命值减少对应数量 * 2（无视防御的伤害） 
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class HongYan extends Buff {

	public HongYan(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject attacker = effect.getAttacker();
		VisibleObject affecter = effect.getAffecter();
		FighterVO fighterVO = new FighterVO(attacker, attacker, affecter);
		int attackMp = attacker.getNowMp();
		int value = attacker.changeNowMp(-attackMp);
		if (value != 0) {
			attacker.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(attacker, EffectType.mp, attacker.getNowMp()));
		}
		fighterVO.setHurtValue(attackMp * 3);
		affecter.onBeAttack(effect.getAttacker(), fighterVO);
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

}
