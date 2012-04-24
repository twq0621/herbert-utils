package net.snake.gameserver.event.copy;

import org.apache.log4j.Logger;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

/**
 * 迷踪阵副本
 */
public class MiZongZhen_SceneExit extends SuperInstance implements IEventListener {

	private static Logger logger = Logger.getLogger(MiZongZhen_SceneExit.class);
	public final int instanceId = 6;
	private static int mysteriousSceneId1 = 20192;// 迷踪阵神秘1层
	private static int mysteriousSceneId2 = 20193;// 迷踪阵神秘2层

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneExit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene, SRole role
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];
		SRole role = (SRole) args[2];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		if (scene.getCharacterCount() == 1 && scene.getAllCurrentSceneMonster().size() > 0 && scene.getId() != mysteriousSceneId1 && scene.getId() != mysteriousSceneId2) {
			logger.info("清除" + instance.getAttribute("layerOriginal" + role.getId()) + "层的怪物数据");
			for (SMonster monster : scene.getAllCurrentSceneMonster()) {
				monster.destoryDispose();
			}
			scene.getAllCurrentSceneMonster().clear();
			scene.clearRefreshMonsterController();
		}
	}

}
