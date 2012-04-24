package net.snake.gamemodel.equipment.logic;

import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.hero.bean.Hero;

public abstract class EquipmentPlay {

	protected boolean commonCondition(Hero hero, int copper, int zhenqi) {
		if (hero.getZhenqi() < zhenqi) {
			// 真气不足
			hero.sendMsg(new CombineFailMsg50150(3));
			return false;
		}
		if (hero.getCopper() < copper) {
			// 铜币不足
			hero.sendMsg(new CombineFailMsg50150(14));
			return false;
		}
		return true;
	}
}
