package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

/**
 * 室内npc的死亡
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public class Dazhaishinei_MonsterDie implements IEventListener {
	
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance)args[0];
		SMonster monster = (SMonster)args[1];
		
		int num=instance.getInstanceId();
		if (num!=14&&num!=20) {
			return ;
		}
		SMonster npc = (SMonster)instance.getAttribute("protected");
		if(npc.getModel()!=monster.getModel()){
			npc.removeArroundWithMeInFightMonsterPositions(monster);
		}
		if (npc.getId().intValue() == monster.getId().intValue()) {
			SRole role = instance.getInstanceAllCharacters().iterator().next();
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoFail, "60");
			api.sendMsg(role, (byte)7, tips);
			instance.setAttribute("endInstance", Long.valueOf(System.currentTimeMillis()+60000));
			api.sendCountDownMsg(role, 60000, false);
			
			return ;
		}else if(71000116 == monster.getModel()|| (71000362 == monster.getModel() && num == 20)){
			SRole role = instance.getInstanceAllCharacters().iterator().next();
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
			api.sendMsg(role, (byte)7, tips);
			instance.setAttribute("endInstance", Long.valueOf(System.currentTimeMillis()+60000));
			
			api.sendCountDownMsg(role, 60000, false);
			return ;
		}
		
		
	}
}