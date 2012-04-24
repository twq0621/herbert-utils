package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

/**
 * 夫子阵 副本
 * 
 */
public class FuZiZhen_SceneEnter extends SuperInstance implements IEventListener {
	private int npcId = 1738; // 夫子阵 副本NPCID.
	private String currTips; // 当前NPC提示语.

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneEnter;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene, SRole role
		// SInstance instance =(SInstance)args[0];
		SScene scene = (SScene) args[1];
		SRole role = (SRole) args[2];

		if (scene.getId() != 20175) {
			return;
		}

		api.updateNpcTip(role, npcId, GlobalLanguage.FuZiZhenNpcTip + currTips);

	}

}
