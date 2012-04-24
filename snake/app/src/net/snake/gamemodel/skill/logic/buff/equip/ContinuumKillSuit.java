package net.snake.gamemodel.skill.logic.buff.equip;

import net.snake.ai.fight.controller.EffectController;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 连斩系统buffer，打怪经验加成，对精英怪物和BOSS可以造成倍数伤害
 * @author serv_dev
 *
 */
public class ContinuumKillSuit extends Buff{

	public ContinuumKillSuit(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		controller.setContinuumKill(true);
		controller.setContinuumKillHate(effect.getHurtValue());
		float percent = effect.getPercent();
		controller.setContinuumExp(percent / 10000);
		effect.setBufValue(effect.getHurtValue());
		controller.setContinuumKillBuff(effect);
		return true;
	}
	
//	@Override
//	public boolean leaveCondition(long now) {
//		if ((now - effect.getBufBeginTime()) >= effect
//				.getDuration() + effect.getDuration2()) {// 时间已到
//			return true;
//		}
//		return false;
//	}

	@Override
	public boolean leave(EffectController controller) {
		if(leaveCondition(System.currentTimeMillis())){
			controller.setContinuumKill(false);	
			controller.setContinuumKillHate(1);
			controller.setContinuumExp(0f);
			controller.setContinuumKillBuff(null);
			return true;
		}
		return false;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.buff;
	}

}
