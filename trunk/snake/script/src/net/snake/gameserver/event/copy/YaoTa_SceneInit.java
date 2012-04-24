package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.Iterator;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

import org.apache.log4j.Logger;

public class YaoTa_SceneInit implements IEventListener {
	private static Logger logger = Logger.getLogger(YaoTa_SceneInit.class);
	public static final int instanceId = 24;
	public static final int tianguanYiId = 30026;
	public static final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public static final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%
	public static final int endTianguanId = 30035;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		instance.setAttribute("monsterDie", 0);
		// 这里是在进入场景处开始刷怪
		// api.initInstanceSceneMonster(scene, dropJiacheng, shuxingJiacheng);
		// scene.setAttribute("monster_total", scene.getAllCurrentSceneMonster().size());
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		Iterator<SRole> iterator = roleCollection.iterator();
		SRole role = iterator.next();
		int grade = role.getGrade();
		Collection<SMonster> collection = scene.getSMonsterCollection();
		if (instance.getAttribute("monsterGrade") == null) {
			instance.setAttribute("monsterGrade", 3);
			grade = grade + 3;
		} else {
			grade = grade + Integer.parseInt(instance.getAttribute("monsterGrade").toString());
		}
		int ptId = instance.getInstanceMonsterId(9, grade);
		for (SMonster monster : collection) {
			if (monster.getType() == 9) {
				api.createMonsterToScene(ptId, monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				continue;
			}
			short x = monster.getX();
			short y = monster.getY();
			instance.setAttribute("bossPos", new short[] { x, y });
			instance.setAttribute("bossId", instance.getInstanceMonsterId(10, grade));
		}
		instance.setAttribute("normal", collection.size() - 1);
	}
}
