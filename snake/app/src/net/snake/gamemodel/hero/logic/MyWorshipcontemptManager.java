package net.snake.gamemodel.hero.logic;

import org.apache.log4j.Logger;

import net.snake.commons.Timer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Worshipcontempt;
import net.snake.gamemodel.hero.persistence.WorshipcontemptManager;



public class MyWorshipcontemptManager {
	private static Logger logger = Logger.getLogger(MyWorshipcontemptManager.class);
	private Hero character;
	private Worshipcontempt worshipcontempt;

	public Worshipcontempt getWorshipcontempt() {
		return worshipcontempt;
	}

	public void setWorshipcontempt(Worshipcontempt worshipcontempt) {
		this.worshipcontempt = worshipcontempt;
	}

	public MyWorshipcontemptManager(Hero character) {
		this.character = character;
	}

	public void destory() {
		worshipcontempt = null;
	}

	public void initData() {
		try {
			WorshipcontemptManager wm = WorshipcontemptManager.getInstance();
			worshipcontempt = wm.getWorshipcontemptById(character.getId());

			if (null != worshipcontempt) {
				String newtimeString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");
				String oldtimeString = worshipcontempt.getTime();
				if (null != oldtimeString) {
					int count = Integer.valueOf(Timer.getTwoDay(newtimeString, oldtimeString));
					if (count != 0) {
						wm.deleteWorshipcontempt(worshipcontempt.getCharacterId());
						worshipcontempt = null;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateWorshipcontempt(final Worshipcontempt worshipcontempt) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				WorshipcontemptManager.getInstance().updateWorshipcontempt(worshipcontempt);

			}
		});
	}

	public void addWorshipcontempt(final Worshipcontempt worshipcontempt) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				WorshipcontemptManager.getInstance().addWorshipcontempt(worshipcontempt);

			}
		});
	}

}
