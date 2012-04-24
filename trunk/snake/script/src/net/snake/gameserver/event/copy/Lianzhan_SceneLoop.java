package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;

public class Lianzhan_SceneLoop implements IEventListener {
	
	public int getInterestEvent() {
		return Evt_InstanceSceneLoop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SInstance instance=(SInstance)args[0];
		
		if (instance.getInstanceId()!=1) {
			return ;
		}
		
		Object end=instance.getAttribute("endInstance");
		if (end !=null) {
			Long endTimeLong = (Long)end;
			if ( System.currentTimeMillis() >= endTimeLong) {
				instance.missionFailed();
			}
			return ;
		}
	}
}

