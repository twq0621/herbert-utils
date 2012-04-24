package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;

/**
 * 角色充值消费相关统计（暂时不用）
 * 
 * @author serv_dev
 * 
 */
public class CharacterChongzhiCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public static final int chongzhiType = 4;// 元宝充值类别

	public CharacterChongzhiCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 充值钱数统计
	 */
	public void characterConsumCount(int count) {
		myAchieveManger.characterAchieveToDBCount(chongzhiType, count);
	}
}
