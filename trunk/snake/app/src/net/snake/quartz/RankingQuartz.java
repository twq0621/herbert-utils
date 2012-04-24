package net.snake.quartz;

import net.snake.gamemodel.hero.persistence.RankingManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class RankingQuartz implements Job{
private static Logger logger = org.apache.log4j.Logger.getLogger(RankingQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		RankingManager.getInstance().init();
		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 排行榜更新............."); //$NON-NLS-1$
		}
		
	}
}
