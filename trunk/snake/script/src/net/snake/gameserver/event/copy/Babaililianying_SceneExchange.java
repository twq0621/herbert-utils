package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

public class Babaililianying_SceneExchange implements IEventListener {
	public final int instanceId = 3;
	public final int endSceneId = 20119;

	public boolean isFinishiInstance(SScene scene) {
		if (scene.getId() != endSceneId) {
			return false;
		}
		return true;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneExChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance,SScene enterScene, SRole role, boolean isSure,STeleport teleport
		SInstance instance = (SInstance) args[0];
		//SScene enterScene = (SScene) args[1];
		SRole role = (SRole) args[2];
		//boolean isSure = (Boolean) args[3];
		//STeleport teleport = (STeleport) args[4];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		api.setInstanceEnterMsg(role, null, true);
	}
}
