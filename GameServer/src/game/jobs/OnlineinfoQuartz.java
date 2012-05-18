package game.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OnlineinfoQuartz implements Job {
	private static Logger logger = Logger.getLogger("OnlineLog");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//OnlineinfoManager.getInstance().init();
		// 内部日志输出
		//logger.info(" " + GameServer.vlineServerManager.getOnlineCount());
	}
}
