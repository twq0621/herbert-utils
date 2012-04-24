package net.snake.gameserver.event.copy;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

public class JianlaoDazhaishinei_OutTeam implements IEventListener {
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_OutOfTeam;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SRole hero = (SRole)args[0];
		SScene scene = hero.getSceneRef();
		SInstance instance = scene.getInstance();
		if (instance==null) {
			return ;
		}
		
		if (instance.getInstanceId() !=11 &&instance.getInstanceId()!=18) {
			return ;
		}
		
		String tips = GlobalLanguage.exChangeParamToString(GlobalLanguage.OutTeam, "5");
		api.sendMsg(hero, (byte)7, tips);
		
		long timestamp =System.currentTimeMillis()+5000;
		instance.setAttribute("outTeam_hero", hero);
		instance.setAttribute("outTeam_time", Long.valueOf(timestamp));
	}
}
