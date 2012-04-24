package net.snake.quartz;

import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiGoodsManager;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 天下第一排行榜,每届跨服论剑活动结束时，产生新一轮的天下第一榜
 *               由原服定时每周二、每周五22:01分前往各个跨服战场去同步数据到本服保存。
 * 
 * @author serv_dev.
 * @version: 1.0
 * @Create at: 2011-6-11 上午02:04:49
 */
public class AcrossWorldFirstQuartz implements Job {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AcrossWorldFirstQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		GameServer.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				CharacterTianXiaDiYiManager.getInstance().updateData();
				CharacterTianXiaDiYiGoodsManager.getInstance().updateData();
			}
		}, 1, TimeUnit.SECONDS);
		logger.info("获取天下第一数据.");
	}

}
