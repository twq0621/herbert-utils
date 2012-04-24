package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;

/**
 * 
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Dujie_MonsterDie implements IEventListener {
	
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance)args[0];
		SMonster monster = (SMonster)args[1];
		
		int num=instance.getInstanceId();
		if (num!=9) {
			return ;
		}
		
		int model = (Integer)instance.getAttribute("bossModel");
		if (model != monster.getModel()) {
			return ;
		}
		
		SRole role =instance.getInstanceAllCharacters().iterator().next();
		api.sendDujieEndMsg(role, true);
		
		instance.setAttribute("sucComp",Long.valueOf(0));
		instance.setAttribute("dujieComplete", Boolean.TRUE);
	}
}
