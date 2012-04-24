package net.snake.gameserver.event.monster;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class DuZhuWang_Zhuxian_Die implements IEventListener {
	private final int MonsterID = 1390;// 毒株王的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {// 4.
												// 死亡时出现15只蜘蛛使用怪物近战技能（技能等级=60）攻击玩家
			for (int i = 0; i < 2; i++) {
				api.createMonsterAroundPoint(monster.getSceneRef(), monster.getX(), monster.getY(), 10, 1400, false, true, 20, 60 * 1000);
			}
		}
	}

}
