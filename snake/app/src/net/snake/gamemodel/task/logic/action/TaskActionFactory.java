package net.snake.gamemodel.task.logic.action;

import org.apache.log4j.Logger;

import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.CharacterTaskController;


public class TaskActionFactory {
	private static final Logger logger = Logger.getLogger(TaskActionFactory.class);
	private static TaskActionFactory factory;
	private static final TaskAction orgBaseTask = new BaseTaskAction();
	private static final TaskAction orgDayCTask = new DayCTaskAction();
	private static final TaskAction orgWeekCTask = new WeekCTaskAction();
	private static final TaskAction orgDayYaBiaoCTask = new DayYaBiaoTaskAction();
	private static final TaskAction orgUpgradeCTask = new UpgradeCTaskAction();
	private TaskActionFactory(){};
	
	public static TaskActionFactory getInstance(){
		if (factory == null){
			factory = new TaskActionFactory();
		}
		return factory;
	}
	
	public TaskAction createAction(Task task,CharacterTaskController taskController) {
		if (task == null) return null;
		TaskAction taskVO = null;
		switch (task.getType()) {
		case Task.MAIN_TASK:
		case Task.BRANCH_TASK:
			try {
				taskVO = (TaskAction)(orgBaseTask.clone());
				taskVO.setTask(task);
				taskVO.setTaskController(taskController);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(),e);
			}
			break;
		case Task.Day_TASK:
			try {
				taskVO = (TaskAction)(orgDayCTask.clone());
				taskVO.setTask(task);
				taskVO.setTaskController(taskController);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(),e);
			}
			break;
		case Task.Day_YaBiao_TASK:
			try {
				taskVO = (TaskAction)(orgDayYaBiaoCTask.clone());
				taskVO.setTask(task);
				taskVO.setTaskController(taskController);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(),e);
			}
			break;
		case Task.Week_TASK:
			try {
				taskVO = (TaskAction)(orgWeekCTask.clone());
				taskVO.setTask(task);
				taskVO.setTaskController(taskController);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(),e);
			}
			break;
		case Task.Upgrade_TASK:
			try {
				taskVO = (TaskAction)(orgUpgradeCTask.clone());
				taskVO.setTask(task);
				taskVO.setTaskController(taskController);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(),e);
			}
			break;
		default:
			break;
		}
		
		return taskVO;
	}
}
