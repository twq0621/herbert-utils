package net.snake.quartz;

import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ReSetCharacterTianXiaDiYiQuartz implements Job{
private static Logger logger = Logger.getLogger(ReSetCharacterTianXiaDiYiQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CharacterTianXiaDiYiManager.getInstance().resetCharacterTianXiaDiYiChest();
		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 重置天下第一领取............."); //$NON-NLS-1$
		}
		
	}
}
