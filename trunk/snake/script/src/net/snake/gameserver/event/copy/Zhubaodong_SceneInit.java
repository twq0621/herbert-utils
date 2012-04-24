package net.snake.gameserver.event.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SScene;

public class Zhubaodong_SceneInit implements IEventListener {
	public static final int instanceId = 13;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		// 这里是在进入场景处开始刷怪
		instance.setAttribute("monsterDie", 0);
		// 这里是在进入场景处开始刷怪
		Collection<SMonster> collection = scene.getSMonsterCollection();
		int count = 0;
		List<SMonster> bosss = new ArrayList<SMonster>();
		for (SMonster monster : collection) {
			if (monster.getType() == 9 || monster.getType() == 2) {
				api.createMonsterToScene(monster.getModel(), monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				count++;
				continue;
			}
			bosss.add(monster);
		}
		instance.setAttribute("bosss", bosss);
		instance.setAttribute("normal", count);
		instance.setAttribute("monsterTotal", collection.size());
	}
}
