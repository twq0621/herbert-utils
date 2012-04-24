package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SScene;



public class Babaililianying_SceneInit implements IEventListener {
	public final int instanceId = 3;
	public final int endSceneId=20119;

	public boolean isFinishiInstance(SScene scene){
		if(scene.getId()!=endSceneId){
			return false;
		}
		return true;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		//SInstance instance, SScene scene
		SInstance instance =(SInstance)args[0];
		SScene scene=(SScene)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		api.initInstanceSceneMonster(scene);
		scene.setAttribute("monster_total", scene.getAllCurrentSceneMonster().size());
	}
}
