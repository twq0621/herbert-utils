package net.snake.gameserver.event.copy;

import java.util.Collection;

import org.apache.log4j.Logger;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SScene;

/**
 * 敌军大营的副本场景初始化
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Dijundaying_SceneInit implements IEventListener {
	private static final Logger logger = Logger.getLogger(Dijundaying_SceneInit.class);
	public final int instanceId = 10;
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%

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

		Collection<SMonster> collection = scene.getSMonsterCollection();
		int sum = 0;
		for (SMonster monster : collection) {
			if (monster.getType() == 9) {
				api.createMonsterToScene(monster.getModel(), monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				sum++;
				continue;
			}
//			short x = monster.getX();
//			short y = monster.getY();
			instance.setAttribute("bossPos", monster);
		}
		instance.setAttribute("normal", Integer.valueOf(sum));
		// logger.info("need kill monster number is " + sum);
	}
}
