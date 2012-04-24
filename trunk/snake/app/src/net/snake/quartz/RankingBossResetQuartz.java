package net.snake.quartz;

import net.snake.gamemodel.hero.persistence.RankingManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class RankingBossResetQuartz implements Job{
private static Logger logger = Logger
		.getLogger(RankingBossResetQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		RankingManager.getInstance().resetBoss();
		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 排行榜Boos归零............."); //$NON-NLS-1$
		}
		
	}
}
