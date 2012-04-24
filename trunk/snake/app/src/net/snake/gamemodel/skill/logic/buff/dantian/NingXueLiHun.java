package net.snake.gamemodel.skill.logic.buff.dantian;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 * 凝血离魂
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class NingXueLiHun extends Buff {

	public NingXueLiHun(EffectInfo effect) {
		super(effect);
	}
	
	private int maxhp;
	private int dodge;
	
	@Override
	public boolean enter(EffectController controller) {
		VisibleObject vo = controller.getVo();
		//增加生命值上限计算公式：增量 = 30000 / (等级/30 ) （最大增加=当前血量上限）
		//减少闪避值计算公式：减量 = 等级*等级/1.5 （最大减少当前闪避/2）

		float $n = (vo.getGrade() / 30f);
		float maxhp = 0;
		if ($n != 0) {
			maxhp = 30000f / $n;
		}
		int extraMaxHp =	vo.getPropertyAdditionController().getExtraMaxHp();
		if (maxhp > extraMaxHp) {
			maxhp = extraMaxHp;
		}
		float dodge = vo.getGrade() * vo.getGrade() / 1.5f;
		int maxdodge = vo.getPropertyAdditionController().getExtraDodge() / 2;
		if (dodge > maxdodge) {
			dodge = maxdodge;
		}
		
		this.maxhp = (int)maxhp;
		this.dodge = (int)dodge;
		controller.getVo().getPropertyAdditionController().addChangeListener(this);
		return true;
	}
	
	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.maxHp, maxhp);
		pe.addExtraProperty(Property.dodge, -dodge);
		return pe;
	}
	
	
	@Override
	public boolean leave(EffectController controller) {
		controller.getVo().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}
	
	public static Buff getInstance(EffectInfo effect) {
			return new NingXueLiHun(effect);
	}
}
