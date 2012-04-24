package net.snake.gamemodel.skill.logic.buff.equip;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 等阶套装加成：人物身上的装备都佩戴齐，共12件，全部为同样的阶数
	等阶装备的程序判断方式：装备数据库里的装备阶数相同即为等阶装备
	加成属性：您所穿戴的全部装备均为等阶装备，获得额外体力+5%，闪避+5%的加成
 * @author serv_dev
 *
 */
public class EqualGradeSuit extends Buff {
	public EqualGradeSuit(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return false;
	}
	
	@Override
	public PropertyEntity getPropertyEntity() {
		VisibleObject vo = effect.getAffecter();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			PropertyEntity pe = new PropertyEntity();
			pe.addExtraProperty(Property.maxSp, AppEventCtlor.getInstance().getEvtEquipmentFormula().equalGradeSp());
			pe.addExtraProperty(Property.dodge, AppEventCtlor.getInstance().getEvtEquipmentFormula().equalGradeDodge());
			return pe;
		}
		return null;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.taoZhuang;
	}
}
