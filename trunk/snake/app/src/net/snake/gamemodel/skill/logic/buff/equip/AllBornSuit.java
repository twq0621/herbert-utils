package net.snake.gamemodel.skill.logic.buff.equip;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 全身12件装备 天生属性进度都为100% 的玩家，
都将可以在本次活动中 免费激活
 天生套装属性：打怪经验增加10%、打怪铜币掉落增加10%、暴击增加5%、闪避增加5%.
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class AllBornSuit extends Buff {

	public AllBornSuit(EffectInfo effect) {
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
			pe.addExtraProperty(Property.crt, 500);
			pe.addExtraProperty(Property.dodge, 500);
			return pe;
		}
		return null;
	}
	
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.taoZhuang;
	}

}
