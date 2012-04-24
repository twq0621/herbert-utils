package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;

/**
 * 好友某块统计
 * @author serv_dev
 *
 */
public class FriendCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public final  static int friendType = 8;
	public final  static int weddingFirstType = 63;
	public final  static int weddingFavorType = 64;
	public FriendCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}
	public void friendCount(int size) {
		myAchieveManger.characterAchieveMemoryCount(friendType, size);
	}
	/**
	 * 第一次结婚统计
	 * @param count
	 */
	public void weddingFirstCount(int count){
		myAchieveManger.catchAchieveToDBCount(weddingFirstType,count);
	}
	/**
	 *结婚夫妻间好感度统计
	 * @param count
	 */
	public void weddingFavorCount(int count){
		myAchieveManger.catchAchieveToDBCount(weddingFavorType,count);
	}
}
