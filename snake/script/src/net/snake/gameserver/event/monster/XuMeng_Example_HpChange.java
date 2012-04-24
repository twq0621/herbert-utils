package net.snake.gameserver.event.monster;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class XuMeng_Example_HpChange implements IEventListener {

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == 1040) {// 徐猛的怪物模型ID为1040
			if (monster.getNowHp() < 1000) {
				if (monster.getAttribute("firstevent") == null) {//
					monster.setAttribute("firstevent", "");// 做一下标记,只当这种事件产生一次时使用
					api.createMonsterAroundPoint(monster.getSceneRef(), monster.getX(), monster.getY(), 10, 1082, false, true, 160, 60 * 1000);
					// api.monsterAttackRole(monster2, 70002, monster.getEnmityManager().getHatesetRole());
				}

			}
		}
	}

}
