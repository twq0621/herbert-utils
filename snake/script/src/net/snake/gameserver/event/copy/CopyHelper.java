package net.snake.gameserver.event.copy;

import java.util.Collection;

import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class CopyHelper {

	public void initMonster(SApi api, SInstance instance, SScene scene) {
		// 这里是在进入场景处开始刷怪
		instance.setAttribute("monsterDie", 0);
		// 这里是在进入场景处开始刷怪
		Collection<SMonster> collection = scene.getSMonsterCollection();
		for (SMonster monster : collection) {
			if (monster.getType() == 9 || monster.getType() == 2) {
				api.createMonsterToScene(monster.getModel(), monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				continue;
			}
			short x = monster.getX();
			short y = monster.getY();
			instance.setAttribute("bossPos", new short[] { x, y });
			instance.setAttribute("bossId", monster.getModel());
		}
		instance.setAttribute("normal", collection.size() - 1);
	}

	public void initMonsterByGrade(SApi api, SInstance instance, SScene scene) {
		// 这里是在进入场景处开始刷怪
		instance.setAttribute("monsterDie", 0);
		// 这里是在进入场景处开始刷怪
		Collection<SMonster> collection = scene.getSMonsterCollection();
		for (SMonster monster : collection) {
			if (monster.getType() == 9 || monster.getType() == 2) {
				api.createMonsterToScene(monster.getModel(), monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				continue;
			}
			short x = monster.getX();
			short y = monster.getY();
			instance.setAttribute("bossPos", new short[] { x, y });
			instance.setAttribute("bossId", monster.getModel());
		}
		instance.setAttribute("normal", collection.size() - 1);
	}

	public void monsterDie(SApi api, SInstance instance, SMonster monster) {
		int modelId = monster.getModel();
		int boosId = Integer.parseInt(instance.getAttribute("bossId").toString());
		if (modelId == boosId) {
			SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
			long now = System.currentTimeMillis();
			instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
			api.sendMsg(heroRole, (byte) 7, tips);
			api.sendCountDownMsg(heroRole, 60000, false);
			return;
		} else {
			Object counterObject = instance.getAttribute("monsterDie");
			Integer counter = (Integer) counterObject;
			counter++;
			instance.setAttribute("monsterDie", counter);
			Integer needNormal = (Integer) instance.getAttribute("normal");
			if (counter == needNormal.intValue()) {
				short[] pos = (short[]) instance.getAttribute("bossPos");
				SRole role = instance.getInstanceAllCharacters().iterator().next();
				api.sendMsg(role, (byte) 7, GlobalLanguage.BossAlert);
				api.createMonsterToScene(boosId, pos[0], pos[1], 7, 1, 1, false, role.getSceneRef());
			}
		}
	}
}
