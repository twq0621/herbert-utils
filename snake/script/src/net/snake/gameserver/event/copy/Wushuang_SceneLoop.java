package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

/**
 * 无双场景刷怪
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Wushuang_SceneLoop implements IEventListener {
	private static final int[] pos = { 43, 97, 10, 48, 81, 23, 150, 48, 119, 98 };
	private static final int[] monsterId = { 70900155, 70900156, 70900157, 70900158, 70900159, 70900160, 70900161, 70900162, 70900163, 70900164, 70900165, 70900166, 70900167,
			70900168, 70900169, 70900170, 70900171, 70900172, 70900173, 70900174, 70900175, 70900176, 70900177, 70900178, 70900179, 70900180, 70900181, 70900182, 70900183,
			70900184, 70900185, 70900186, 70900187, 70900188, 70900189, 70900190, 70900191, 70900192, 70900193, 70900194, 70900195, 70900196, 70900197, 70900198, 70900199,
			70900200 };

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != 1) {
			return;
		}

		Object timeObject = instance.getAttribute("timestamp");
		if (timeObject == null) {
			init(instance);
			return;
		}

		long timestamp = (Long) timeObject;
		if (timestamp == 0) {
			return;
		}

		long now = System.currentTimeMillis();
		if (now >= timestamp) {
			SRole heRole = instance.getInstanceAllCharacters().iterator().next();
			int monsterIndex = (Integer) instance.getAttribute("monsterIndex");

			for (int i = 0; i < pos.length; i = i + 2) {
				SMonster sceneMonster = api.createMonsterToScene(monsterId[monsterIndex], pos[i], pos[i + 1], 7, 1, 1, false, scene);
				sceneMonster.nextTarget(heRole);
				SMonster sceneMonster2 = api.createMonsterToScene(monsterId[monsterIndex], pos[i], pos[i + 1], 7, 1, 1, false, scene);
				sceneMonster2.nextTarget(heRole);
			}
			flushMonsterCounter(instance, timestamp);

		}
	}

	private void init(SInstance instance) {
		instance.setAttribute("timestamp", Long.valueOf(System.currentTimeMillis() + 1000));
		SRole hero = instance.getInstanceAllCharacters().iterator().next();
		int grade = hero.getGrade();
		int diff = grade * 2 - 20;
		if (diff < 0) {
			diff = 0;
		} else if (diff >= monsterId.length) {
			diff = monsterId.length - 1;
		}
		instance.setAttribute("monsterIndex", diff);
		instance.setAttribute("unit", Integer.valueOf(1));
		instance.setAttribute("wave", Integer.valueOf(1));
		instance.setAttribute("monsterDie", Integer.valueOf(0));
		return;
	}

	private void flushMonsterCounter(SInstance instance, long currTimestamp) {
		int unit = (Integer) instance.getAttribute("unit");
		if (unit == 10) {

			instance.setAttribute("unit", Integer.valueOf(1));

			int wave = (Integer) instance.getAttribute("wave");
			wave++;
			switch (wave) {
			case 3:
			case 5:
			case 7:
			case 9:
				int index = (Integer) instance.getAttribute("monsterIndex");
				index++;
				if (index == monsterId.length) {
					index = monsterId.length - 1;
				}
				instance.setAttribute("monsterIndex", index);
				break;
			}

			instance.setAttribute("wave", wave);
			if (wave == 11) {
				instance.setAttribute("timestamp", 0);
			} else {
				instance.setAttribute("timestamp", currTimestamp + 30000);
			}
		} else {

			unit++;
			instance.setAttribute("unit", unit);
			instance.setAttribute("timestamp", currTimestamp + 1000);
		}

	}
}
