package net.snake.gamemodel.task.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.task.bean.TaskCondition;
import net.snake.ibatis.SystemFactory;



public class TaskConditionManager  implements CacheUpdateListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskConditionManager.class);

private static TaskConditionDAO dao = new TaskConditionDAO(SystemFactory.getGamedataSqlMapClient());
	

	//单例实现=====================================
	private static TaskConditionManager instance;	
	private Map<Integer,TaskCondition> cacheTaskConditionMap = new ConcurrentHashMap<Integer, TaskCondition>();
	public static TaskConditionManager getInstance() {
		if (instance == null) {
			instance=new TaskConditionManager();
		}
		return instance;
	}
	
	public void reload(){
		try {
			getTaskConditionMap();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private Map<Integer,TaskCondition> getTaskConditionMap(){
		try {
			List<TaskCondition> tasklist=dao.select();
			BeanTool.addOrUpdate(cacheTaskConditionMap, tasklist, "conditionId");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public TaskCondition getTaskConditionById(int id){
		return cacheTaskConditionMap.get(id);
	}
	
	private TaskConditionManager() {
	}
	//单例实现========================================
	
	/**
	 * 
	 * @param characterGrade 任务等级
	 * @param difGrade 任务难度
	 * @param type 任务类型 0日环任务1周环任务2.升级环任务
	 * @return
	 */
	public List<TaskCondition> getTaskConditionByCharacterGradeAndGrade(int characterGrade,int difGrade,int type){
		List<TaskCondition> taskConditions = new ArrayList<TaskCondition>();
		for (Iterator<TaskCondition> iterator = cacheTaskConditionMap.values().iterator(); iterator.hasNext();) {
			TaskCondition taskCondition =  iterator.next();
			int _type = taskCondition.getType();
			int _diffc=taskCondition.getDifficultyDegree();
			if (type ==  _type && difGrade == _diffc) {
				if (taskCondition.getGradeMin() <= characterGrade && characterGrade <= taskCondition.getGradeMax()) {
					taskConditions.add(taskCondition);
				}
			}
		}
		return taskConditions;
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "taskCondition";
	}
}
