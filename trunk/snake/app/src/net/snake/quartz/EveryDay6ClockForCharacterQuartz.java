package net.snake.quartz;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.dantian.persistence.DanTianController;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.serverenv.vline.CharacterRun;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 每天6点
 * @author serv_dev
 *
 */
public class EveryDay6ClockForCharacterQuartz implements Job {
	private static final Logger logger = Logger.getLogger(EveryDay6ClockForCharacterQuartz.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//清除丹田祝福值
		try{
			DanTianController.clearZhuFu();	
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		//清除悟道祝福值
		try{
			DGWDController.clearAllZhuFu();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getCharacterHiddenWeaponController().clearLuck();
			}
		});
		CharacterManager.getInstance().clearLuckValueForHiddenWeaponCharacter();
	}
}
