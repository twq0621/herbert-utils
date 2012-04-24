package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

import org.apache.log4j.Logger;

public class Dijundaying_MonsterDie implements IEventListener {

	private static final Logger logger = Logger.getLogger(Dijundaying_MonsterDie.class);

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];

		int num = instance.getInstanceId();
		if (num != 10 && num != 17) {
			return;
		}

		int modelId = monster.getModel();
		if (modelId == 71000033) {
			SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
			api.sendMsg(heroRole, (byte) 7, tips);
			long now = System.currentTimeMillis();
			instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
			api.sendCountDownMsg(heroRole, 60000, false);
		} else {
			Object counterObject = instance.getAttribute("monsterDie");
			if (counterObject == null) {
				instance.setAttribute("monsterDie", Integer.valueOf(1));
				return;
			}
			Integer counter = (Integer) counterObject;
			counter++;
			instance.setAttribute("monsterDie", counter);

			Integer needNormal = (Integer) instance.getAttribute("normal");
			// logger.info("kill monster number is "+counter);
			// logger.info("need kill monster number is "+needNormal);
			if (counter == needNormal.intValue()) {
				SMonster boss = (SMonster) instance.getAttribute("bossPos");
				SRole role = instance.getInstanceAllCharacters().iterator().next();
				api.sendMsg(role, (byte) 7, GlobalLanguage.BossAlert);
				int bossModelId = boss.getModel();
				if (num == 17) {
					int grade = instance.getInstanceAllCharacters().iterator().next().getGrade();
					bossModelId = instance.getInstanceMonsterId(10, grade);
				}
				api.createMonsterToScene(bossModelId, boss.getOriginalX(), boss.getOriginalY(), 7, 1, 1, false, role.getSceneRef());
			}
		}
	}
}
