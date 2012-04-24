package net.snake.gameserver.event.monster;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class XuMeng_Example_Die implements IEventListener {

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == 1040) {// 徐猛的怪物模型ID为1040
			for (int i = 0; i < 30; i++) {// 生成30个霍都,攻击玩家
				api.createMonsterAroundPoint(monster.getSceneRef(), monster.getX(), monster.getY(), 10, 1000, false, true, 160, 60 * 1000);
			}
		}
	}

}
