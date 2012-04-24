package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


public class MaMu extends Buff {

	public MaMu(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		controller.getVo().getPropertyAdditionController().addChangeListener(this);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.getVo().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}
	
	public PropertyEntity getPropertyEntity(){
		PropertyEntity pe=new PropertyEntity();
			pe.addExtraProperty(Property.attackspeed,(int)(effect.getAffecter().getPropertyAdditionController().getExtraAttackSpeed() * GenerateProbability.randomIntValue(effect.getHurtValueMin(),effect.getHurtValueMax()) / 10000f));
		return pe;
	}

}
