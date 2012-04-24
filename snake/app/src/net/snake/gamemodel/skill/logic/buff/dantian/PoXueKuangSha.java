package net.snake.gamemodel.skill.logic.buff.dantian;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 *破血狂杀
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class PoXueKuangSha extends Buff {

	public PoXueKuangSha(EffectInfo effect) {
		super(effect);
	}
	
	private int attack;
	private int defence;
	
	@Override
	public boolean enter(EffectController controller) {
		VisibleObject vo = controller.getVo();
		float $n = (vo.getGrade() / 30f);
		float attack = 0;
		if ($n != 0) {
			attack = 10000f / $n;
		}
		int maxAttack =	vo.getPropertyAdditionController().getExtraAttack();
		if (attack > maxAttack) {
			attack = maxAttack;
		}
		float defence = vo.getGrade() * vo.getGrade() / 3f;
		int maxDefence = vo.getPropertyAdditionController().getExtraDefend() / 2;
		if (defence > maxDefence) {
			defence = maxDefence;
		}
		this.attack = (int)attack;
		this.defence = (int)defence;
		controller.getVo().getPropertyAdditionController().addChangeListener(this);
		return true;
	}
	
	@Override
	public PropertyEntity getPropertyEntity() {
		//增加攻击力计算公式：增量 = 10000 / (等级/30 )（最大增加=当前攻击力）
		//减少防御值计算公式：减量 = 等级*等级/3 （最大减少当前防御/2）
		
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.attack, attack);
		pe.addExtraProperty(Property.defence, -defence);
		return pe;
	}
	
	
	@Override
	public boolean leave(EffectController controller) {
		controller.getVo().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new PoXueKuangSha(effect);
	}
}
