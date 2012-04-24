package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

public class Guiyu_MonsterDie implements IEventListener {
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];

		int num = instance.getInstanceId();
		if (num != 15) {
			return;
		}
		
		SMonster boss = (SMonster) instance.getAttribute("boss");
		
		Integer die = (Integer) instance.getAttribute("monsterDie");
		die++;
		instance.setAttribute("monsterDie", die);
		Integer normal =(Integer)instance.getAttribute("normal");
		if (die == normal.intValue()) {
			if (boss == null) {
				SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
				String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
				api.sendMsg(heroRole, (byte) 7, tips);
				long now = System.currentTimeMillis();
				instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
				api.sendCountDownMsg(heroRole, 60000, false);
				return;
			}
			
			SRole role = instance.getInstanceAllCharacters().iterator().next();
			api.sendMsg(role, (byte)7, GlobalLanguage.BossAlert);
			SMonster sceneMonster = api.createMonsterToScene(boss.getModel(), boss.getX(), boss.getY(), 7, 1, 1, false, monster.getSceneRef());
			api.changeInstanceSceneMonsterflushCount(sceneMonster, 1);
			
		}else if (monster.getModel() == boss.getModel()) {
			SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
			api.sendMsg(heroRole, (byte) 7, tips);
			long now = System.currentTimeMillis();
			instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
			api.sendCountDownMsg(heroRole, 60000, false);
		}
		
	}
}
