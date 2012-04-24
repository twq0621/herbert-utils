package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.Iterator;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

import org.apache.log4j.Logger;

public class NianLun_MonsterDie extends SuperInstance implements IEventListener {
	private static Logger logger = Logger.getLogger(NianLun_MonsterDie.class);

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance = (SInstance) args[0];
		if (instance.getInstanceId() != NianLun_SceneInit.instanceId) {
			return;
		}

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
			SMonster monster = (SMonster) args[1];
			Collection<SRole> roles = instance.getInstanceAllCharacters();
			Iterator<SRole> iterator = roles.iterator();
			SRole role = iterator.next();
			if (monster.getSceneRef().getId() == NianLun_SceneInit.endSceneId) {
				long now = System.currentTimeMillis();
				// instance.setAttribute("instanceSucess", true);
				api.finishiInstance(instance);
				instance.setAttribute("endTimestamp", Long.valueOf(now + 60000));
				String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.JianlaoSuc, "60");
				api.sendMsg(role, (byte) 7,tips);
				api.sendCountDownMsg(role, 60000, false);
			}
			boolean tf = api.openHideTeleport(monster.getSceneRef());
			if (tf) {
				api.sendMsg(role, (byte) 7, GlobalLanguage.instanceTianguanEnterNextStr);
			}
		}
	}
}
