package net.snake.gameserver.event.monster;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class JueqingguDizi_Zhuxian_Die implements IEventListener {
	private final int MonsterID = 1290;// 绝情谷弟子的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {
			for (int i = 0; i < 4; i++) {// 3. 死亡时召唤出8个草寇使用战天伏魔技能（技能等级=40）
				api.createMonsterAroundPoint(monster.getSceneRef(), monster.getX(), monster.getY(), 10, 1140, false, true, 20, 60 * 1000);
				// monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
				// api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
			}
		}
	}

}