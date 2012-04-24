package net.snake.quartz;

import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.SceneBangqiManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 每小时更新
 * @author serv_dev
 */
public class TimerUpdateSceneBangqiToDbQuartz implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
              SceneBangqiManager.getInstance().update();
              FactionCityManager.getInstance().updateToDb();
	}   
}
