package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.Iterator;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.commons.script.STeleport;
import net.snake.resource.GlobalLanguage;

import org.apache.log4j.Logger;

/**
 * 
 * @author jack
 * 
 */
public class NianLun_SceneInit extends SuperInstance implements IEventListener {
	private static Logger logger = Logger.getLogger(NianLun_SceneInit.class);

	public static final int instanceId = 23;
	public static final int startSceneId = 30070;
	public static final int endSceneId = 30081;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		instance.setAttribute("monsterDie", 0);
		SScene scene = (SScene) args[1];
		if (instance.getAttribute("layer") == null) {
			instance.setAttribute("layer", 1);
			instance.setAttribute("nowSceneId", startSceneId);
		} else {
			int layer = Integer.parseInt(instance.getAttribute("layer").toString());
			instance.setAttribute("layer", layer + 1);
		}
		Collection<SMonster> collection = scene.getSMonsterCollection();
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		Iterator<SRole> iterator = roleCollection.iterator();
		SRole role = iterator.next();
		// 动态修改传送点名称
		if (role.getSceneRef().getId() != endSceneId) {
			for (STeleport sTeleport : scene.getAllTeleports()) {
				api.updateTeleportName(role, scene, sTeleport, GlobalLanguage.Lunhui);
			}
		}

		int grade = role.getGrade();
		int cxqd = 15;
		if (instance.getAttribute("cxqd") != null) {
			cxqd = Integer.parseInt(instance.getAttribute("cxqd").toString());
		}

		int ptId = instance.getInstanceMonsterId(cxqd, grade);

		for (SMonster monster : collection) {
			SMonster sceneMonster = api.createMonsterToScene(ptId, monster.getX(), monster.getY(), 7, 1, 1, false, scene);
			api.changeInstanceSceneMonsterflushCount(sceneMonster, 1);
		}
		api.closeTeleport(scene);
		// logger.info("monster count=" + collection.size());
		instance.setAttribute("normal", Integer.valueOf(collection.size()));
	}
}
