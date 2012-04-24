package net.snake.quartz;

import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *@author serv_dev
 */
public class AcrossOnlineNumUpdate implements Job {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AcrossOnlineNumUpdate.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			//2分钟内随机请求
			int time = GenerateProbability.randomIntValue(0,2*60*1000);
			GameServer.executorService.schedule(new Runnable() {
				
				@Override
				public void run() {
					try {
						AcrossServerDateManager instance = AcrossServerDateManager.getInstance();
						if(instance.isAcrossTime()){
							AcrossServerDateManager.getInstance().checkAndUpdateBalance();
						}
					} catch (InterruptedException e) {
						logger.error(e.getMessage(),e);
					}
				}
			},time,TimeUnit.MILLISECONDS);
//			logger.info("定时更新跨服在线人数 ");
	}

}
