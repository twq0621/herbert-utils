package net.snake.gameserver.event.copy;

import java.util.Collection;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class Babaililianying_MonsterDie implements IEventListener {
	public final int instanceId = 3;
	public final int endSceneId=20119;
	private int monsterDie_counter;
	public Babaililianying_MonsterDie(){
		monsterDie_counter =0;
	}
	public boolean isFinishiInstance(SScene scene){
		if(scene.getId()!=endSceneId){
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
		//SInstance instance,		SMonster monster
		SInstance instance = (SInstance)args[0];
		SMonster monster =(SMonster)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}
		monsterDie_counter++;
		Integer monsters=(Integer)monster.getSceneRef().getAttribute("monster_total");
		if (monsterDie_counter != monsters) {
			return ;
		}
		monsterDie_counter =0;
		if(isFinishiInstance(monster.getSceneRef())){
			instance.missionComplete();
		}
		boolean b = api.openHideTeleport(monster.getSceneRef());
		if (b) {
			Collection<SRole> roles = instance.getInstanceAllCharacters();
			if (roles == null || roles.size() == 0) {
				return;
			}
			for (SRole role : roles) {
				api.sendMsg(role, (byte) 7, GlobalLanguage.instanceBangbailiOpenStr);
			}
		}
	}
}
