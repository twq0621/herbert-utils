package net.snake.quartz;

import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ChannelBeidongExpQuartz implements Job {
	private static Logger logger = Logger.getLogger(ChannelBeidongExpQuartz.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CharacterManager.getInstance().addCharacter_ChannelBeiDongExp();
		ChannelManager.getInstance().addOnlineCharacter_ChannelBeiDongExp();
		if (logger.isDebugEnabled()) {
			logger.debug("execute(JobExecutionContext) - 人物表添加被动经脉经验次数............."); //$NON-NLS-1$
		}

	}
}
