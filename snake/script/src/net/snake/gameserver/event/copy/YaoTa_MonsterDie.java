package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

public class YaoTa_MonsterDie implements IEventListener {
	// private static Logger logger = Logger.getLogger(YaoTa_MonsterDie.class);

	public YaoTa_MonsterDie() {
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];
		if (instance.getInstanceId() != YaoTa_SceneInit.instanceId) {
			return;
		}
		int modelId = monster.getModel();
		int boosId = Integer.parseInt(instance.getAttribute("bossId").toString());
		if (modelId == boosId) {
			SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
			if (YaoTa_SceneInit.endTianguanId == monster.getSceneRef().getId()) {
				long now = System.currentTimeMillis();
				instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
				String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
				api.sendMsg(heroRole, (byte) 7, tips);
				api.sendCountDownMsg(heroRole, 60000, false);
				return;
			}
			instance.setAttribute("monsterGrade", Integer.parseInt(instance.getAttribute("monsterGrade").toString()) + 1);
			boolean b = api.openHideTeleport(monster.getSceneRef());
			if (b) {
				api.sendMsg(heroRole, (byte) 7, GlobalLanguage.instanceTianguanEnterNextStr);
			}
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
			// logger.info("need monster count=" + needNormal);
			// logger.info("now have monster count=" + counter);
			if (counter == needNormal.intValue()) {
				short[] pos = (short[]) instance.getAttribute("bossPos");
				SRole role = instance.getInstanceAllCharacters().iterator().next();
				api.sendMsg(role, (byte) 7, GlobalLanguage.BossAlert);
				api.createMonsterToScene(boosId, pos[0], pos[1], 7, 1, 1, false, role.getSceneRef());
			}
		}

	}
}
