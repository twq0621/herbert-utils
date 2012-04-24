package net.snake.gamemodel.tongji.logic;


import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.task.bean.Task;

/**
 * 任务某块统计
 * 
 * @author serv_dev
 * 
 */
public class TaskCount {
	MyCharacterAchieveCountManger myAchieveManger;

	public final  static int TaskType = 28;// 任务计数
	public final  static int yabiaoTaskType = 16;// 第一次杀怪

	public TaskCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 击杀怪物行为统计
	 * 
	 * @param monster
	 */
	public void finishTaskCount(Task task) {
		finishYabiaoTask(task);
		finishTask(task);
	}

	/**
	 * 押镖任务
	 */
	public void finishYabiaoTask(Task task) {
		if (task.getType() != Task.Day_YaBiao_TASK) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(yabiaoTaskType, 1);
	}

	/**
	 * 任务计数
	 * 
	 * @param monster
	 */
	public void finishTask(Task task) {
		myAchieveManger.characterAchieveToDBCount(TaskType, 1);
	}

}
