package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

public class AttackDefenceBuff extends Buff {

	public AttackDefenceBuff(EffectInfo effectInfo) {
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
		int buffValue = effect.getBufValue();
		FighterVO fighterVO = effect.getFighterVO();
		if (fighterVO == null) {
			return null;
		}
		CharacterSkill skill = fighterVO.getCharacterSkill();
		if(skill==null){
			return null;
		}
		int grade = skill.getGrade();
		int val = buffValue * grade;
		propertyEntity.setDefend(val);
		propertyEntity.setAttack(val);
		return propertyEntity;
	}

}
