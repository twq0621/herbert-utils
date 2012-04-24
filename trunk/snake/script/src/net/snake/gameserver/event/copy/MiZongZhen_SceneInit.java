package net.snake.gameserver.event.copy;

import java.util.Collection;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;


/**
 * 迷踪阵副本
 */
public class MiZongZhen_SceneInit extends SuperInstance implements IEventListener{

	public final int instanceId = 6;

	private int[] teleportIdAs = { 1233, 1236, 1239, 1242, 1245, 1248, 1251, 1254, 1257, 1260, 1263, 1266, 1269, 1272, 1275, 0 };
	private int[] teleportIdBs = { 1234, 1237, 1240, 1243, 1246, 1249, 1252, 1255, 1258, 1261, 1264, 1267, 1270, 1273, 1276, 0 };

	private static int mysteriousSceneId1 = 20192;// 迷踪阵神秘1层


	private int[] initOutDoors() {
		int[] outDoors = new int[16];
		for (int i = 0; i < outDoors.length; i++) {
			outDoors[i] = (int) (Math.random() * 2) == 0 ? this.teleportIdAs[i] : this.teleportIdBs[i];
		}

		return outDoors;
	}
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		//SInstance instance, SScene scene
		SInstance instance = (SInstance)args[0];
		SScene scene =(SScene)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		api.openHideTeleport(scene);
		api.initInstanceSceneMonster(scene);
		if (scene.getId() == mysteriousSceneId1) {
			int rolesGrade = roleCollection.iterator().next().getMaxTeamerGrade();
			for (SMonster sMonster : scene.getAllCurrentSceneMonster()) {
				sMonster.changeGrade((short) rolesGrade); // 怪物等级 = 人物等级
			}
		}
		if (instance.getAttribute("isInstanceSceneInit") != null) {
			return;
		}

		instance.setAttribute("isInstanceSceneInit", true);

		instance.setAttribute("outDoors", initOutDoors());
		instance.setAttribute("isTriggerErrors", new boolean[16]);
		// 进入副本后立即刷出第1波怪物，第一波怪物刷新时在屏幕中央弹出红字公告

		sendMsgs(api, GlobalLanguage.MiZongEnterTip, this.getTeamAllRole(roleCollection));
	}
}
