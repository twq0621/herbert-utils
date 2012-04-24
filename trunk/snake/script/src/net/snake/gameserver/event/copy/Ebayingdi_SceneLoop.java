package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;

public class Ebayingdi_SceneLoop implements IEventListener {
	
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		// SScene scene=(SScene)args[1];

		if (instance.getInstanceId() != 12&&instance.getInstanceId() != 19) {
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
