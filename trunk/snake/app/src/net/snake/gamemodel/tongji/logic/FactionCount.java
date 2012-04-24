package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;

/**
 * 帮会模块统计
 * 
 * @author serv_dev
 * 
 */
public class FactionCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public final static int factionType = 10;
	public final static int contributionType = 26;
	public final static int junlintianxiaType = 60;
	public final static int tianxiaDiyiType = 58;
	public final static int bangqiShouhuType = 59;
	public final static int chengzhanShengwangType = 66;
	public final static int lunjianshengwangType = 67;

	public FactionCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 加入帮会
	 * 
	 * @param size
	 */
	public void factionAddCount(int size) {
		myAchieveManger.characterAchieveMemoryCount(factionType, size);
	}

	/**
	 * 帮会贡献
	 * 
	 * @param size
	 */
	public void factionContributionCount(int size) {
		myAchieveManger.characterAchieveMemoryCount(contributionType, size);
	}

	/**
	 * 获取襄阳城主
	 */
	public void xiangyangchengzhuCount() {
		myAchieveManger.characterAchieveToDBCount(junlintianxiaType, 1);
	}

	/**
	 * 帮旗守护次数
	 */
	public void bangqiShouhuCount(int addCount) {
		myAchieveManger.characterAchieveToDBCount(bangqiShouhuType, addCount);
	}

	public void chengzhangShengwangCount(int chengzhanShengwang) {
		myAchieveManger.characterAchieveMemoryCount(chengzhanShengwangType, chengzhanShengwang);
	}

	public void lunjianShengwangCount(int lunjianShengwang) {
		myAchieveManger.characterAchieveMemoryCount(lunjianshengwangType, lunjianShengwang);
	}

	public void tianxiaDiyiCount() {
		myAchieveManger.characterAchieveToDBCount(tianxiaDiyiType, 1);
	}
}
