package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

/**
 *  四圣兽副本
 * 
 */
public class SiShengShou_SceneInit extends SuperInstance implements IEventListener {

	public final int instanceId = 7;


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		//SInstance instance, SScene scene
		SInstance instance =(SInstance)args[0];
		SScene scene =(SScene)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		List<SRole> roles =this.getTeamAllRole(roleCollection);;

		api.initInstanceSceneMonster(scene);
		sendMsgs(api, GlobalLanguage.SiShengShouEnterTip, roles);
		scene.setAttribute("monster_total", scene.getAllCurrentSceneMonster().size());
	}
}
