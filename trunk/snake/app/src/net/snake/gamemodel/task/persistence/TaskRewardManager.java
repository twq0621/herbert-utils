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
import net.snake.consts.LoopTaskType;
import net.snake.gamemodel.task.bean.TaskReward;
import net.snake.ibatis.SystemFactory;



public class TaskRewardManager implements CacheUpdateListener{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskRewardManager.class);

	private static TaskRewardDAO dao = new TaskRewardDAO(SystemFactory.getGamedataSqlMapClient());
	
	//单例实现=====================================
	private static TaskRewardManager instance;	
	private Map<Integer,TaskReward> cacheTaskRewardMap = new ConcurrentHashMap<Integer, TaskReward>();
	public static TaskRewardManager getInstance() {
		if (instance == null) {
			instance=new TaskRewardManager();
		}
		return instance;
	}
	public void reload(){
		try {
			getTaskRewardMap();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private Map<Integer,TaskReward> getTaskRewardMap(){
		try {
			List<TaskReward> tasklist=dao.select();
			BeanTool.addOrUpdate(cacheTaskRewardMap, tasklist, "rewardId");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public TaskReward getTaskRewardById(int id){
		return cacheTaskRewardMap.get(id);
	}
	
	private TaskRewardManager() {	
	}
	
	/**
	 * 
	 * @param characterGrade 人物等级
	 * @param difGrade 难度系数
	 * @param type 环任务类型
	 * @param loopNum 环数
	 * @return
	 */
	public List<TaskReward> getTaskRewardByCharacterGradeAndGrade(int characterGrade,int difGrade,int type,int loopNum){
		
		List<TaskReward> taskRewards = new ArrayList<TaskReward>();
		for (Iterator<TaskReward> iterator = cacheTaskRewardMap.values().iterator(); iterator.hasNext();) {
			TaskReward taskReward = iterator.next();
			if (taskReward.getType() == type && difGrade == taskReward.getRewardGrade()) {
				if (type == LoopTaskType.week.getType()) {
					if (taskReward.getGradeMin() <= characterGrade && characterGrade <= taskReward.getGradeMax() && loopNum == taskReward.getLoopNum()) {
						taskRewards.add(taskReward);
					}
				} else {
					if (taskReward.getGradeMin() <= characterGrade && characterGrade <= taskReward.getGradeMax()) {
						taskRewards.add(taskReward);
					}
				}
				
			}
		}
		return taskRewards;
	}
	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}
	@Override
	public String getCachename() {
		return "taskReward";
	}
}
