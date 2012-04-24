package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

public class UnAttackDefenceBuff extends Buff {

	public UnAttackDefenceBuff(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		VisibleObject affecter = effect.getAffecter();
		affecter.getPropertyAdditionController().addChangeListener(this);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		effect.getAffecter().getPropertyAdditionController().removeChangeListener(this);
		return true;
	}

	public PropertyEntity getPropertyEntity() {
		PropertyEntity propertyEntity = new PropertyEntity();
		int val = effect.getBufValue() * -1*effect.getFighterVO().getCharacterSkill().getGrade();
		propertyEntity.setDefend(val);
		propertyEntity.setAttack(val);
		return propertyEntity;
	}
}
