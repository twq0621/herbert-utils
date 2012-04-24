package net.snake.gameserver.event.copy;

import org.apache.log4j.Logger;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.commons.script.STeleport;
import net.snake.resource.GlobalLanguage;

/**
 * 迷踪阵副本
 */
public class MiZongZhen_SceneEnter extends SuperInstance implements IEventListener {

	private static Logger logger = Logger.getLogger(MiZongZhen_SceneEnter.class);
	public final int instanceId = 6;

	private static int mysteriousSceneId1 = 20192;// 迷踪阵神秘1层
	private static int mysteriousSceneId2 = 20193;// 迷踪阵神秘2层

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneEnter;
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
		if (instance.getAttribute("layerMiZong" + role.getId()) == null) {
			instance.setAttribute("layerMiZong" + role.getId(), 1);
			instance.setAttribute("layerOriginal" + role.getId(), 1);
		}
		// 通关
		if (Integer.parseInt(instance.getAttribute("layerMiZong" + role.getId()).toString()) == 16 && instance.getAttribute("finishiMiZong" + role.getId()) == null) {
			api.finishiInstance(instance, role);
			instance.setAttribute("finishiMiZong" + role.getId(), true);
			logger.info("领取通关奖励:" + role.getId());
			return;
		}

		// 动态修改传送点名称
		if (role.getSceneRef().getId() == mysteriousSceneId1 || role.getSceneRef().getId() == mysteriousSceneId2) {
			for (STeleport sTeleport : scene.getAllTeleports()) {
				api.updateTeleportName(role, scene, sTeleport,
						GlobalLanguage.exChangeParamToString(GlobalLanguage.MiZongEnterTip1, instance.getAttribute("layerOriginal" + role.getId()).toString()));
			}
		}
	}
}
