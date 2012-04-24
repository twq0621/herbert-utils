package net.snake.gamemodel.skill.logic.buff.drug;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 龙气丹
 * @author serv_dev
 *
 */
public class LongQiDan extends Buff {

	public LongQiDan(EffectInfo effectInfo) {
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
	
	@Override
	public PropertyEntity getPropertyEntity() {
		VisibleObject vo = effect.getAffecter();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			PropertyEntity pe = new PropertyEntity();
			pe.addExtraProperty(Property.maxHp, effect.getPercent());
			return pe;
		}
		return null;
	}
	
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.specialYJ;
	}

}
