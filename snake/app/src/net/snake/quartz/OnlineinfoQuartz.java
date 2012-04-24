package net.snake.quartz;

import net.snake.GameServer;
import net.snake.gamemodel.gm.persistence.OnlineinfoManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OnlineinfoQuartz implements Job {
	private static Logger logger = Logger.getLogger(OnlineinfoQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		OnlineinfoManager.getInstance().init();
		// 内部日志输出
		logger.info("GameServer.vlineServerManager.getOnlineCount()="+GameServer.vlineServerManager.getOnlineCount());
	}
}
