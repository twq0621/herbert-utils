package net.snake.gamemodel.task.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.task.bean.CharacterTask;
import net.snake.gamemodel.task.bean.TaskDataEntry;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.logic.action.TaskActionFactory;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * InnoDB free: 11264 kB管理
 * 
 */

public class CharacterTaskManager {

	private static TaskDataEntryDAO dao = new TaskDataEntryDAO(SystemFactory.getCharacterSqlMapClient());
	private static final Logger logger = Logger.getLogger(CharacterTaskManager.class);
	// 单例实现=====================================
	private static CharacterTaskManager instance;

	private CharacterTaskManager() {
	}

	public static CharacterTaskManager getInstance() {
		if (instance == null) {
			instance = new CharacterTaskManager();
		}
		return instance;
	}

	// 单例实现========================================
	protected TaskDataEntryDAO getEntityDao() {
		return dao;
	}

	/**
	 * 获取 当前没有完成的任务
	 * 
	 * @param characterId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void initCurrentCharacterTaskMap(Map<Integer, CharacterTask> characterTaskMap, CharacterTaskController taskController) throws SQLException {
		List<TaskDataEntry> list = dao.selectByEndtimeIsNull(taskController.getCharacter().getId());
		for (TaskDataEntry taskdataentry : list) {
			if (TaskManager.getInstance().get(taskdataentry.getTask()) == null) {
				continue;
			}
			CharacterTask characterTask = new CharacterTask(taskdataentry);
			characterTaskMap.put(taskdataentry.getTask(), characterTask);
			characterTask.setTaskVO(TaskActionFactory.getInstance().createAction(characterTask.getOrgTask(), taskController));
		}
	}

	/**
	 * 获取已经完成的任务 根据接任务时间排序
	 * 
	 * @param characterId
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void initTerminativeCharacterTaskMap(Map<Integer, CharacterTask> characterTaskMap, CharacterTaskController taskController) throws SQLException {
		List<TaskDataEntry> list = dao.selectByEndtimeIsNotNull(taskController.getCharacter().getId());
		for (TaskDataEntry taskdataentry : list) {
			if (TaskManager.getInstance().get(taskdataentry.getTask()) == null) {
				continue;
			}
			CharacterTask characterTask = new CharacterTask(taskdataentry);
			characterTaskMap.put(taskdataentry.getTask(), characterTask);
			characterTask.setTaskVO(TaskActionFactory.getInstance().createAction(characterTask.getOrgTask(), taskController));
		}
	}

	public void insertCharacterTask(Hero character, final CharacterTask characterTask) {
		if (Options.IsCrossServ)
			return;
		try {
			dao.insertSelective(characterTask);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updataCharacterTask(Hero character, final CharacterTask characterTask) {
		if (Options.IsCrossServ)
			return;
		try {
			dao.updateByPrimaryKey(characterTask);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteCharacterTaskByCharacterId(int id) {
		if (Options.IsCrossServ)
			return;
		try {
			dao.deleteByCharacterId(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteCharacterTask(Hero character, final String id) {
		if (Options.IsCrossServ)
			return;
		try {
			dao.deleteByPrimaryKey(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
