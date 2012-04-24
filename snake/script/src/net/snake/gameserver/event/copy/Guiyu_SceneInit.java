package net.snake.gameserver.event.copy;

import java.util.Collection;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SScene;

public class Guiyu_SceneInit implements IEventListener{
	public final int instanceId =15;
	public final int dropJiacheng=10000; //掉落几率为正常的100%
	public final int shuxingJiacheng=10000;//属性加成为正常怪物的100%


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		 //SInstance instance, SScene scene
		SInstance instance=(SInstance)args[0];
		SScene scene=(SScene)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		//这里是在进入场景处开始刷怪
//		api.initInstanceSceneMonster(scene,dropJiacheng,shuxingJiacheng);
//		scene.setAttribute("monster_total", scene.getAllCurrentSceneMonster().size());
		
		
		
		Collection<SMonster> collection = scene.getSMonsterCollection();
		int normalSum =0;
		instance.setAttribute("monsterDie",0);
		for (SMonster monster : collection) {
			if (monster.getType() == 9) {
				SMonster sceneMonster = api.createMonsterToScene(monster.getModel(), monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				api.changeInstanceSceneMonsterflushCount(sceneMonster, 1);
				normalSum++;
				continue;
			}
			instance.setAttribute("boss", monster);
		}
		instance.setAttribute("normal", Integer.valueOf(normalSum));
	}
}
