package game.jobs;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
	private static final Logger logger = Logger.getLogger(QuartzUtil.class);

	/**
	 * 获得scheduler
	 * 
	 * @return Scheduler
	 * @throws
	 */
	public static Scheduler getDefaultScheduler() {

		StdSchedulerFactory schefactory = new StdSchedulerFactory();
		Scheduler schedul = null;
		try {
			schedul = schefactory.getScheduler();
			if (schedul.isShutdown()) {
				schedul.start(); // 若关闭,启动
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
		return schedul;
	}
}
