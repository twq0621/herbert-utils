package net.snake.quartz;

import net.snake.gamemodel.chest.persistence.ChestCountManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.apache.log4j.Logger;

public class ChestCountResetExchangeCountQuartz implements Job {
private static Logger logger = Logger
		.getLogger(ChestCountResetExchangeCountQuartz.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		ChestCountManager.getInstance().reSetChestCountExchangeCount();
		ChestCountManager.getInstance().reSetChestCountExchangeCountSql();
		
		ChestCountManager.getInstance().reSetChestUseCount();

		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 金松果次数重置和松果友好丹充值............."); //$NON-NLS-1$
		}

	}
}
