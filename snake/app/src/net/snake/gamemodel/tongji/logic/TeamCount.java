package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;

/**
 * 角色组队行文相关统计
 * @author serv_dev
 *
 */
public class TeamCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public final  static int teamType = 9;
	public final  static int zhenfaType = 25;
	public TeamCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}
	 /**
	  * 角色第一次组队
	  */
	public void teamCount() {
		myAchieveManger.characterAchieveMemoryCount(teamType, 1);
	}
	/**
	 * 角色阵法学习数量
	 * @param size
	 */
	public void learnZhenfaCount(int size) {
		myAchieveManger.characterAchieveMemoryCount(zhenfaType, size);
	}
}
