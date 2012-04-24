package net.snake.gameserver.event.copy;

import java.util.Collection;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class Tianguan_MonsterDie implements IEventListener {
	public final int instanceId = 2;
	public final int tianguanYiId = 20038;
	public final int yanchiTime = 60 * 1000;
	public final int[] meiguihuaid = new int[] { 1201, 1202, 1203, 1204, 1205, 1206 };
	public final int dropJiacheng = 10000; // 掉落几率为正常的100%
	public final int shuxingJiacheng = 10000;// 属性加成为正常怪物的100%
	public final int endTianguanId = 20050;

	// private int monsterDie_counter;
	public Tianguan_MonsterDie() {
		// monsterDie_counter = 0;
	}

	public boolean isFinishiInstance(SScene scene) {
		if (scene.getId() != endTianguanId) {
			return false;
		}
		return true;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SMonster monster
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];

		if (instance.getInstanceId() != instanceId) {
			return;
		}
		boolean b = api.openHideTeleport(monster.getSceneRef());
		if (b) {
			Collection<SRole> roles = instance.getInstanceAllCharacters();
			if (roles == null || roles.size() == 0) {
				return;
			}
			for (SRole role : roles) {
				// "通往下一关的传送点已经开启"
				api.sendMsg(role, (byte) 7, GlobalLanguage.instanceTianguanEnterNextStr);
			}
		}
		// monsterDie_counter++;
		// Integer monsters=(Integer)monster.getSceneRef().getAttribute("monster_total");
		// if (monsterDie_counter != monsters) {
		// return ;
		// }
		// monsterDie_counter =0;
		// if(isFinishiInstance(monster.getSceneRef())){
		// api.finishiInstance(instance);
		// }
		// boolean b=api.openHideTeleport(monster.getSceneRef());
		// if(b){
		// Collection<SRole> roles=instance.getInstanceAllCharacters();
		// if(roles==null||roles.size()==0){
		// return;
		// }
		// for(SRole role:roles){
		// // "通往下一关的传送点已经开启"
		// api.sendMsg(role, (byte)7,GlobalLanguage.instanceTianguanEnterNextStr);
		// }
		// }
	}
}
