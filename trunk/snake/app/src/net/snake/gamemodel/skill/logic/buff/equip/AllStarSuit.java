package net.snake.gamemodel.skill.logic.buff.equip;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
//import net.snake.script.ScriptEventCenter;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 满星套装加成：人物身上的装备都佩戴齐，共12件，每件均为满星装备
加成属性：您所穿戴的全部装备均为满星装备，获得额外攻击+5%，防御+5%的加成

 * @author serv_dev
 *
 */
public class AllStarSuit extends Buff {

	public AllStarSuit(EffectInfo effect) {
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
			pe.addExtraProperty(Property.attack, AppEventCtlor.getInstance().getEvtEquipmentFormula().allStarAttack());
			pe.addExtraProperty(Property.defence, AppEventCtlor.getInstance().getEvtEquipmentFormula().allStarDefence());
			return pe;
		}
		return null;
	}
	
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.taoZhuang;
	}

}
