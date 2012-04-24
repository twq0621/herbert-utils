package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

/**
 * 监牢刷怪
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Jianlao_SceneInit implements IEventListener {
	private final int Npc_x = 106, Npc_y = 21;

	public final int instanceId = 11;
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != instanceId&&instance.getInstanceId() != 18) {
			return;
		}
		SMonster sceneMonster = api.createMonsterToScene(70100152, Npc_x, Npc_y, 7, 1, 1, false, scene);
		instance.setAttribute("protected", sceneMonster);
		
		SRole role = instance.getInstanceAllCharacters().iterator().next();
		api.sendMsg(role, (byte)7, GlobalLanguage.JianlaoStart);

	}
}
