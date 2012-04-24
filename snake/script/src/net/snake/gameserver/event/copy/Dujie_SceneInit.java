package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

public class Dujie_SceneInit implements IEventListener {
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		// SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != 9) {
			return;
		}

		SRole role = instance.getInstanceAllCharacters().iterator().next();
		String txt = GlobalLanguage.exChangeParamToString(GlobalLanguage.DujieStart, "5");
		api.sendMsg(role, (byte) 7, txt);

	}
}
