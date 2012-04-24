package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

public class ChiHuan extends Buff {

	public ChiHuan(EffectInfo effectInfo) {
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

	public PropertyEntity getPropertyEntity() {
		PropertyEntity pe = new PropertyEntity();
		int speed = effect.getAffecter().getPropertyAdditionController().getExtraMoveSpeed();
		float random = GenerateProbability.randomIntValue(effect.getHurtValueMin(), effect.getHurtValueMax()) / 10000f;
		int movespeed = (int) (speed * random);
		SkillEffect skef = effect.getEffect();
		VisibleObject vo = effect.getAttacker();
		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero charvo = (Hero) vo;
			CharacterSkill charskill = charvo.getCharacterSkillManager().getCharacterSkillById(skef.getId());
			if (charskill != null) {
				movespeed = charskill.getAppendSkillValue(movespeed);
			}
		}
		// movespeed = 100;
		pe.addExtraProperty(Property.movespeed, -1 * movespeed);
		return pe;
	}
}
