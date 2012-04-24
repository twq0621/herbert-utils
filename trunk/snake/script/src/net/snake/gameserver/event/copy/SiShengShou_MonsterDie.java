package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;


/**
 *  四圣兽副本
 * 
 */
public class SiShengShou_MonsterDie extends SuperInstance implements IEventListener {

	public final int instanceId = 7;
	private int monsterDie_counter;
	public SiShengShou_MonsterDie() {
		monsterDie_counter=0;
	}


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		 //SInstance instance, SMonster monster
		SInstance instance=(SInstance)args[0];
		SMonster monster=(SMonster)args[1];
		
		if (instance.getInstanceId() != instanceId) {
			return;
		}

		monsterDie_counter++;
		Integer monsters=(Integer)monster.getSceneRef().getAttribute("monster_total");
		if (monsterDie_counter != monsters) {
			return ;
		}
		monsterDie_counter =0;
		instance.missionComplete();
	}
}
