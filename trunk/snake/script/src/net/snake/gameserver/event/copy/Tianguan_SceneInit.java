package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SScene;

public class Tianguan_SceneInit implements IEventListener {
	public final int instanceId = 2;
	public final int tianguanYiId = 20038;
	public final int yanchiTime = 60 * 1000;
	public final int[] meiguihuaid = new int[] { 1201, 1202, 1203, 1204, 1205, 1206 };
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%
	public final int endTianguanId = 20050;

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
		api.initInstanceSceneMonster(scene, dropJiacheng, shuxingJiacheng);
		scene.setAttribute("monster_total", scene.getAllCurrentSceneMonster().size());
	}
}
