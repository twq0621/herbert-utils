package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class Tianguan_SceneExit implements IEventListener {
	public final int instanceId = 2;
	public final int tianguanYiId = 20038;
	public final int yanchiTime = 60 * 1000;
	public final int[] meiguihuaid = new int[] { 1201, 1202, 1203, 1204, 1205, 1206 };
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%
	public final int endTianguanId = 20050;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneExChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance,SScene enterScene, SRole role, boolean isSure,STeleport teleport
		SInstance instance = (SInstance) args[0];
		SScene enterScene = (SScene) args[1];
		SRole role = (SRole) args[2];
		boolean isSure = (Boolean) args[3];
		// STeleport teleport =(STeleport)args[4];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		if (!isSure) {
			api.setInstanceEnterMsg(role, GlobalLanguage.instanceTianguanGoodStr, true);
			return;
		}
		int instanceGrade = role.getInstanceflushGrade();
		int sceneCount = enterScene.getId() - tianguanYiId;
		if (sceneCount > instanceGrade || enterScene.getId() == tianguanYiId) {
			int idmeigui = 0;
			for (int id : meiguihuaid) {
				if (api.getSRoleBadGoodCountByGoodId(role, id) >= 1) {
					idmeigui = id;
					break;
				}
			}
			if (idmeigui == 0) {
				api.setInstanceEnterMsg(role, GlobalLanguage.instanceTianguanGood1Str, true);
			} else {
				boolean b = api.deleteSRoleBadGoodCountByGoodId(role, idmeigui, 1);
				if (b) {
					api.setInstanceEnterMsg(role, null, true);
					api.setInstanceflushGrade(role, role.getInstanceflushGrade() + 1);
				} else {
					api.setInstanceEnterMsg(role, GlobalLanguage.instanceTianguanEnterStr, true);
				}
			}
		}
	}
}
