package net.snake.gamemodel.hero.logic;

import java.util.ArrayList;
//import java.util.LinkedHashMap;
import java.util.LinkedList;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.serverenv.CharacterTaskExcuter;

import org.apache.log4j.Logger;

public class AsynchronousTask {
	private static Logger logger = Logger.getLogger(AsynchronousTask.class);
	private Hero character;
	private LinkedList<Runnable> taskList = new LinkedList<Runnable>();

	public AsynchronousTask(Hero character) {
		this.character = character;
	}

	public void destory() {

	}

	/**
	 * 每隔一定时间批量执行的任务
	 * 
	 * @param run
	 */
	public void addToBatchUpdateTask(Runnable run) {

		synchronized (taskList) {
			taskList.addLast(run);
		}

		CharacterTaskExcuter.getInstance().addCharacter(character);
	}

	public void excuteTaskList() {
		ArrayList<Runnable> templist = null;
		synchronized (taskList) {
			templist = new ArrayList<Runnable>(taskList);
			taskList.clear();
		}
		for (Runnable run : templist) {
			try {
				run.run();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}

	}

}
