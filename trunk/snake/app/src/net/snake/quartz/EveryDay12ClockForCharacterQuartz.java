package net.snake.quartz;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.serverenv.vline.CharacterRun;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class EveryDay12ClockForCharacterQuartz implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.setChongqiRecord(0);
				character.setDevilcnt(0);
				character.setTodayChengzhanShengwang(0);
			}
		});
		
		
		CharacterManager.getInstance().updateClock12Characters();
	}
}
