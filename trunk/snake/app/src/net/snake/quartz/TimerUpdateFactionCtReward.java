package net.snake.quartz;

import net.snake.gamemodel.faction.persistence.FactionCityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TimerUpdateFactionCtReward implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		FactionCityManager.getInstance().update();
		
	}

}
