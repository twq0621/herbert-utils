package net.snake.quartz;

import net.snake.gamemodel.hero.persistence.RankingManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



public class ReSetChengZhanLunJianQuartz implements Job {
private static Logger logger = Logger
		.getLogger(ReSetChengZhanLunJianQuartz.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		RankingManager.getInstance().resetChengJianLunJian();
		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 重置城站和论剑排行和跨服战场声望............."); //$NON-NLS-1$
		}
		
	}

}
