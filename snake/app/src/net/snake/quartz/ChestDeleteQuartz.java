package net.snake.quartz;

import net.snake.gamemodel.chest.persistence.ChestManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**    
 *     
 *    
 * @author serv_dev     
 * @version 1.0    
 * @created 2011-4-27 下午06:03:44   
 */    
public class ChestDeleteQuartz implements Job {
private static Logger logger = Logger.getLogger(ChestDeleteQuartz.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		ChestManager.getInstance().deleteChest();

		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 宝箱删除............."); //$NON-NLS-1$
		}

	}
}
