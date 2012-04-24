package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class Jianlao_SceneLoop implements IEventListener {
	private static int[] pos1 = { 21, 17 };
	private static int[] pos2 = { 30, 67 };
	private static int[] pos3 = { 115, 67 };

	private static int[] monsterIds = { 70900063, 70900063, 70900063, 70900063, 71000062, 70900064, 70900064, 70900064, 70900064 };

	public int getInterestEvent() {
		return Evt_InstanceSceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != 11) {
			return;
		}

		Object outTeamObject = instance.getAttribute("outTeam_hero");
		if (outTeamObject != null) {
			long timestamp = (Long) instance.getAttribute("outTeam_time");
			if (System.currentTimeMillis() >= timestamp) {
				SRole hero = (SRole) outTeamObject;
				api.backWorld(hero);

				instance.removeAttribute("outTeam_hero");
				instance.removeAttribute("outTeam_time");
				return;
			}
		}

		Object end = instance.getAttribute("endInstance");
		if (end != null) {
			Long endTimeLong = (Long) end;
			if (System.currentTimeMillis() >= endTimeLong) {
				instance.missionFailed();
			}
			return;
		}

		Long timestamp = (Long) instance.getAttribute("timestamp");
		if (timestamp == null) {
			init(instance);
			return;
		}
		if (timestamp == -1) {
			return;
		}
		if (timestamp == 0) {
			timestamp = System.currentTimeMillis() + 10000;
			instance.setAttribute("timestamp", timestamp);
			return;
		}

		long now = System.currentTimeMillis();
		Integer counter = (Integer) instance.getAttribute("counter");
		if (now >= timestamp) {
			SMonster npc = (SMonster) instance.getAttribute("protected");
			if (counter == 4) {
				int size = scene.getAllCurrentSceneMonster().size();
				if (size == 1) {
					SMonster sceneMonster = api.createMonsterToScene(71000062, pos3[0], pos3[1], 7, 1, 1, false, scene);
					sceneMonster.nextTarget(npc);
					counter++;
					instance.setAttribute("counter", counter);
					timestamp = timestamp + 60000;
					instance.setAttribute("timestamp", timestamp);
				}
				return;
			}
			if (counter == 9) {
				int size = scene.getAllCurrentSceneMonster().size();
				if (size == 1) {
					SRole role = instance.getInstanceAllCharacters().iterator().next();
					api.sendMsg(role, (byte) 7, GlobalLanguage.BossAlert);

					SMonster sceneMonster = api.createMonsterToScene(71000065, pos1[0], pos1[1], 7, 1, 1, false, scene);
					sceneMonster.nextTarget(npc);
					timestamp = (long) -1;
					instance.setAttribute("timestamp", timestamp);
					counter++;
					instance.setAttribute("counter", counter);
				}
				return;
			}
			Integer unitCnt = (Integer) instance.getAttribute("unitCnt");

			SMonster sceneMonster1 = api.createMonsterToScene(monsterIds[counter], pos1[0], pos1[1], 7, 1, 1, false, scene);
			SMonster sceneMonster2 = api.createMonsterToScene(monsterIds[counter], pos1[0], pos1[1], 7, 1, 1, false, scene);

			SMonster sceneMonster3 = api.createMonsterToScene(monsterIds[counter], pos2[0], pos2[1], 7, 1, 1, false, scene);
			SMonster sceneMonster4 = api.createMonsterToScene(monsterIds[counter], pos2[0], pos2[1], 7, 1, 1, false, scene);

			SMonster sceneMonster5 = api.createMonsterToScene(monsterIds[counter], pos3[0], pos3[1], 7, 1, 1, false, scene);
			SMonster sceneMonster6 = api.createMonsterToScene(monsterIds[counter], pos3[0], pos3[1], 7, 1, 1, false, scene);

			sceneMonster1.nextTarget(npc);
			sceneMonster2.nextTarget(npc);
			sceneMonster3.nextTarget(npc);
			sceneMonster4.nextTarget(npc);
			sceneMonster5.nextTarget(npc);
			sceneMonster6.nextTarget(npc);

			unitCnt = unitCnt + 2;

			if (unitCnt == 10) {
				timestamp = timestamp + 60000;
				instance.setAttribute("timestamp", timestamp);
				counter++;
				instance.setAttribute("counter", counter);
				unitCnt = 0;
			} else {
				timestamp = timestamp + 2000;
				instance.setAttribute("timestamp", timestamp);
			}
			instance.setAttribute("unitCnt", unitCnt);
		}

	}

	private void init(SInstance instance) {
		instance.setAttribute("timestamp", Long.valueOf(0));
		instance.setAttribute("unitCnt", Integer.valueOf(0));
		instance.setAttribute("counter", Integer.valueOf(0));

		instance.setAttribute("attack", Long.valueOf(System.currentTimeMillis()));
	}
}
