package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SScene;

public class Guiyu_SceneLoop implements IEventListener {
	public final int instanceId = 15;
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_SceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SScene scene = (SScene) args[0];
		SInstance instance = scene.getInstance();
		if(instance==null){
			return;
		}
		if (instance.getInstanceId() != instanceId && instance.getInstanceId() != 21) {
			return;
		}

		Long endTimestamp = (Long) instance.getAttribute("endTimestamp");
		if (endTimestamp == null) {
			return;
		}
		long now = System.currentTimeMillis();
		if (now >= endTimestamp) {
			instance.missionFailed();
		}
	}
}
