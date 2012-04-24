package net.snake.gameserver.event.copy;

import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

public class Zhubaodong_MonsterDie implements IEventListener {
	// private static Logger logger = Logger.getLogger(YaoTa_MonsterDie.class);

	public Zhubaodong_MonsterDie() {
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];
		if (instance.getInstanceId() != Zhubaodong_SceneInit.instanceId && instance.getInstanceId() != 22) {
			return;
		}
		Integer needNormal = (Integer) instance.getAttribute("normal");
		Object counterObject = instance.getAttribute("monsterDie");
		Integer counter = (Integer) counterObject;
		counter++;
		instance.setAttribute("monsterDie", counter);
		if (monster.getModel() == 71000383) {
			SRole heroRole = instance.getInstanceAllCharacters().iterator().next();
			long now = System.currentTimeMillis();
			instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
			String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
			api.sendMsg(heroRole, (byte) 7, tips);
			api.sendCountDownMsg(heroRole, 60000, false);
		} else {
			if (counter == needNormal) {
				List<SMonster> bosss = (List<SMonster>) instance.getAttribute("bosss");
				SRole role = instance.getInstanceAllCharacters().iterator().next();
				for (SMonster boss : bosss) {
					api.createMonsterToScene(boss.getModel(), boss.getX(), boss.getY(), 7, 1, 1, false, role.getSceneRef());
					api.sendMsg(role, (byte) 7, GlobalLanguage.BossAlert);
				}
			}
		}
	}
}
