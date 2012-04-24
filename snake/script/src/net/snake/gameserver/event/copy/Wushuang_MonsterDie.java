package net.snake.gameserver.event.copy;

import net.snake.commons.GenerateProbability;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;

/**
 * 无双副本出boss
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Wushuang_MonsterDie implements IEventListener {
	private static final int[] BossGrade = { 25, 30, 35, 40, 45, 50, 55, 60 };
	private static final int[] BossId = { 71022201, 71000202, 70978203, 70956204, 70934205, 70912206, 70890207, 70868208 };
	private static final int[] pos = { 43, 97, 10, 48, 81, 23, 150, 48, 119, 98 };

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];
		if (instance.getInstanceId() != 1) {
			return;
		}

		Object bossId = instance.getAttribute("BoosId");
		if (bossId != null) {
			if (((Integer) bossId).intValue() == monster.getModel()) {
				instance.missionComplete();
			}
			return;
		}

		Object monsterDie = instance.getAttribute("monsterDie");
		Integer counter = (Integer) monsterDie;
		counter++;
		if (counter == 1000) {

			SRole heRole = instance.getInstanceAllCharacters().iterator().next();
			int index = GenerateProbability.rdmValue(0, 5);
			index = index * 2;

			int id = getBossId(heRole);
			SMonster sceneMonster = api.createMonsterToScene(id, pos[0], pos[1], 7, 1, 1, false, heRole.getSceneRef());
			sceneMonster.nextTarget(heRole);
			instance.setAttribute("BoosId", Integer.valueOf(id));
			return;
		}
		instance.setAttribute("monsterDie", counter);

	}

	private int getBossId(SRole role) {
		int lvl = role.getGrade();
		for (int i = 0; i < BossGrade.length; i++) {
			if (lvl < BossGrade[i]) {
				return BossId[i];
			}
		}
		return BossId[BossId.length - 1];
	}
}
