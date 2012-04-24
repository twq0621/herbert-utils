package net.snake.quartz;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public class QuartzApi {

	private static Logger logger = Logger.getLogger(QuartzApi.class);

	private Scheduler sched = null;
	private JobDetail job = null;
	private CronTrigger Ctrigger = null;

	private Map<String, CronTrigger> triggerMap = new HashMap<String, CronTrigger>();

	public QuartzApi() {
		sched = QuartzUtil.getDefaultScheduler();
	}

	public void start() {
		startRankingUpdate();
		startEveryDay12ClockForCharacter();
		startRankingBossResetUpdate();
		startEveryDay6ClockForCharacter();
		startTimerUpdateFactionCtReward();
		startTimerUpdateServerInfoToDbQuartz();
		startChestDeleteQuartz();
		startOnlineinfoQuartz();
		startAddChestCountResetExchangeCountQuartz();
		startClearDataQuartz();
	}

	private void startClearDataQuartz() {
		createJob("ClearDataQuartz", "0 0 3 * * ?", ClearDataQuartz.class);
	}

	private void startOnlineinfoQuartz() {
		createJob("OnlineinfoQuartz", "0 0/10 * * * ?", OnlineinfoQuartz.class);
	}

	private void startChestDeleteQuartz() {
		createJob("ChestDeleteQuartz", "0 0 6 * * ?", ChestDeleteQuartz.class);
	}

	private void startAddChestCountResetExchangeCountQuartz() {
		createJob("ChestCountResetExchangeCountQuartz", "0 5 0 * * ?", ChestCountResetExchangeCountQuartz.class);
	}

	public void startTimerUpdateServerInfoToDbQuartz() {
		createJob("TimerUpdateServerInfoToDbQuartz", "0 0 0 * * ?", TimerUpdateServerInfoToDbQuartz.class);
	}

	public void startTimerUpdateSceneBangqiToDb() {
		createJob("TimerUpdateSceneBangqiToDb", "0 0 0/1 * * ?", TimerUpdateSceneBangqiToDbQuartz.class);
	}

	public void startTimerUpdateFactionCtReward() {
		createJob("TimerUpdateFactionCtReward", "0 0 0 * * ?", TimerUpdateFactionCtReward.class);
	}

	private void startEveryDay12ClockForCharacter() {
		createJob("EveryDay12ClockForCharacter", "0 0 0 * * ?", EveryDay12ClockForCharacterQuartz.class);
	}

	private void startEveryDay6ClockForCharacter() {
		createJob("EveryDay6ClockForCharacter", "0 0 6 * * ?", EveryDay6ClockForCharacterQuartz.class);
	}

	private void startRankingBossResetUpdate() {
		createJob("RankingBoosRestUpdate", "0 0 0 ? * 1", RankingBossResetQuartz.class);
	}

	@SuppressWarnings("unused")
	private void startAcrossOnlineNumUpdate() {
		createJob("updateAcrossOnlineNum", "0 0/3 * * * ?", AcrossOnlineNumUpdate.class);
	}

	@SuppressWarnings("unused")
	private void startReSetCharacterTianXiaDiYiQuartz() {
		createJob("ReSetCharacterTianXiaDiYiQuartz", "0 3 0 * * ?", ReSetCharacterTianXiaDiYiQuartz.class);
	}

	@SuppressWarnings("unused")
	private void startReSetChengZhanLunJianQuartz() {
		// 每周早上7点
		createJob("ReSetChengZhanLunJianQuartz", "0 0 7 ? * 2", ReSetChengZhanLunJianQuartz.class);
	}

	@SuppressWarnings("unused")
	private void startAddCharacterChannelBeiDongExp() {
		createJob("AddCharacterChannelBeiDongExp", "0 5 0 * * ?", ChannelBeidongExpQuartz.class);
	}

	public void updateTriggerCronExpression(String jobName, String cronExpression) {
		CronTrigger trigger = triggerMap.get(jobName.trim());
		if (trigger != null) {
			try {

				trigger.setCronExpression(new CronExpression(cronExpression));
				sched.rescheduleJob(jobName + "Trigger", Scheduler.DEFAULT_GROUP, trigger);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void createJob(String jobName, String expression, Class classs) {
		// 任务名称，任务组名称，任务实现类
		job = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, classs);
		try {
			// 删除作业
			if (sched.getJobDetail(jobName, Scheduler.DEFAULT_GROUP) != null) {
				sched.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
			}
			Ctrigger = new CronTrigger(jobName + "Trigger", null);
			CronExpression cronExpression = null;

			cronExpression = new CronExpression(expression);
			Ctrigger.setCronExpression(cronExpression);
			triggerMap.put(jobName, Ctrigger);
			// 注册作业
			sched.scheduleJob(job, Ctrigger);
			if (!sched.isShutdown()) {
				sched.start();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * cron类型作业
	 */
	public void startRankingUpdate() {

		sched = QuartzUtil.getDefaultScheduler();

		String jobName = "RankingUpdate";
		String groupName = "DEFAULT";
		// 任务名称，任务组名称，任务实现类
		job = new JobDetail(jobName, groupName, RankingQuartz.class);
		try {
			// 删除作业
			if (sched.getJobDetail(jobName, groupName) != null) {
				sched.deleteJob(jobName, groupName);
			}
			Ctrigger = new CronTrigger("RankingTrigger", null);
			CronExpression cronExpression = null;
			// 夜里6点运行
			cronExpression = new CronExpression("0 0 6 * * ?");

			Ctrigger.setCronExpression(cronExpression);

			// 注册作业
			sched.scheduleJob(job, Ctrigger);

			if (!sched.isShutdown()) {
				sched.start();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void destroy() {
		sched = QuartzUtil.getDefaultScheduler();
		try {
			if (sched != null)
				sched.shutdown();
		} catch (Exception e) {
			logger.error("Quartz Scheduler failed to shutdown cleanly: " + e.toString());
			logger.error(e.getMessage(), e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Quartz Scheduler successful shutdown.");
		}

	}

	/**
	 * 添加Cron类型作业
	 * 
	 * @author cncsi juzhi
	 * @param cronExpress
	 * @param className
	 * @param mapArgus
	 * @return 添加成功返回作业名称 失败返回异常
	 */
	@SuppressWarnings("rawtypes")
	public void addCronJob(String jobName, Class className) {
		try {
			Scheduler scheduler = QuartzUtil.getDefaultScheduler();

			jobName = "simpleJob";
			String groupName = "DEFAULT";

			// 删除作业
			if (scheduler.getJobDetail(jobName, groupName) != null) {
				scheduler.deleteJob(jobName, groupName);
			}

			// 作业的详细信息
			// 任务名称，任务组名称，任务实现类
			JobDetail jobDetail = new JobDetail(jobName, groupName, className);

			// 创建简单触发器
			// SimpleTrigger simpleTrigger = new
			// SimpleTrigger("simpleTrigger",null);
			//
			// simpleTrigger.setRepeatCount(100); // 调用100次
			// simpleTrigger.setRepeatInterval(5*1000); //每5秒钟调用一次
			//
			// //注册作业
			// scheduler.scheduleJob(jobDetail, simpleTrigger);

			CronTrigger cronTrigger = new CronTrigger("cronTrigger", null);
			CronExpression cronExpression = null;
			try {
				cronExpression = new CronExpression("0 0 0 * * ?"); // 每天12点运行
				cronTrigger.setCronExpression(cronExpression);

				// 注册作业
				scheduler.scheduleJob(jobDetail, cronTrigger);

				if (!scheduler.isShutdown()) {
					scheduler.start();
				}

			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}

		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		new QuartzApi().startRankingUpdate();
		new QuartzApi().destroy();
	}

}
