package net.snake.serverenv;

import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;

import org.apache.log4j.Logger;
import org.apache.mina.util.ConcurrentHashSet;

public class CharacterTaskExcuter {
	private static Logger logger = Logger.getLogger(CharacterTaskExcuter.class);
	private volatile ConcurrentHashSet<Hero> characterSet = new ConcurrentHashSet<Hero>();

	// 单例实现=====================================
	private static CharacterTaskExcuter instance;

	private CharacterTaskExcuter() {
		int delay = 10 * 1000;// 10秒
		GameServer.executorServiceForDB.scheduleWithFixedDelay(new updateTask(), delay, delay, TimeUnit.MILLISECONDS);
	}

	public static CharacterTaskExcuter getInstance() {
		if (instance == null) {
			instance = new CharacterTaskExcuter();
		}
		return instance;
	}

	// 单例实现========================================
	public void addCharacter(Hero character) {
		characterSet.add(character);
	}

	public void delAndExcuterTask(Hero character) {
		try {
			character.getAsynchronousTask().excuteTaskList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		characterSet.remove(character);
	}

	class updateTask implements Runnable {
		@Override
		public void run() {
			for (Hero character : characterSet) {
				try {
					character.getAsynchronousTask().excuteTaskList();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

	}
}
