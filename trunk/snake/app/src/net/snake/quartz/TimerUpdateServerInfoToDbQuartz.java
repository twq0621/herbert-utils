package net.snake.quartz;

import net.snake.GameServer;
import net.snake.gamemodel.line.persistence.SeverinfoManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 每天凌晨0点更新
 * @author serv_dev
 */
public class TimerUpdateServerInfoToDbQuartz implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if (GameServer.vlineServerManager.getOnlineCount() < 50) {
			return;
		}
		SeverinfoManager.getInstance().updateServerInfo();
	}   
}
