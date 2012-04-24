package net.snake.gamemodel.skill.logic.buff.base;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

public class BaseAffectPropertyBuf extends Buff {

	public BaseAffectPropertyBuf(EffectInfo effect) {
		super(effect);
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

	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe = new PropertyEntity();
		FighterVO fvo = effect.getFighterVO();
		int jiabei = 1;
		if (fvo != null) {
			CharacterSkill cs = fvo.getCharacterSkill();
			if (cs != null) {
				jiabei = cs.getGrade();
				return null;
			}
		}
		int val = effect.getBufValue() * jiabei;
		pe.addExtraProperty(Property.getpPropertyByEffectType(effect.getType()), val);
		return pe;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		int hurtValue = effect.getHurtValue();
		int percent = effect.getPercent();
		return hurtValue == 0 && percent == 0 ? PropertyAdditionType.buff : PropertyAdditionType.chuzhanzuoqiJineng;
	}

}
