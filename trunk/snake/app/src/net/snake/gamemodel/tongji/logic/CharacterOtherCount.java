package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.serverenv.cache.CharacterCacheManager;

/**
 * 角色其他行为统计
 * 
 * @author serv_dev
 * 
 */
public class CharacterOtherCount {
	private MyCharacterAchieveCountManger myAchieveManger;
	public static final int wangbaoType = 15; // 挖藏宝图
	public static final int prestigeType = 12; // 战场声望
	public static final int copperType = 27;
	public static final int gradeType = 30;
	public static final int wugongType = 31;
	public static final int jingmaiType = 32;
	public static final int lianzhanType = 57;
	public static final int jumpType = 6;
	public static final int lianxuejumpType = 20;
	public static final int tiaoshanType = 21;
	public static final int feibiaoKillCharacterType = 62; // 飞彪暗器系统
	public static final int feibiaoKillMonsterType = 61; // 飞彪暗器系统
	public static final int zhenlongJingmaiType=65;
	public CharacterOtherCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}

	/**
	 * 跳跃统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void jumpCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(jumpType, count);
		myAchieveManger.characterAchieveMemoryCount(lianxuejumpType, count);
	}

	/**
	 * 铜钱统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void copperCount(int count) {
		if (count < 0) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(copperType, count);
	}

	/**
	 * 普通静脉统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void jingmaiCount(int count) {
		if (count <= 0) {
			return;
		}
		//更新缓存数据
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(
				myAchieveManger.getCharacter());
		myAchieveManger.characterAchieveMemoryCount(jingmaiType, count);
	}
	/**
	 * 真龙经脉统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void zhenlongJingmaiCount(int count) {
		if (count <= 0) {
			return;
		}
		//更新缓存数据
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(
				myAchieveManger.getCharacter());
		myAchieveManger.characterAchieveMemoryCount(zhenlongJingmaiType, count);
	}
	/**
	 * 角色战场声望统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void prestigeCount(int count) {
		if (count <= 0) {
			return;
		}
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(
				myAchieveManger.getCharacter());
		myAchieveManger.characterAchieveMemoryCount(prestigeType, count);
	}

	/**
	 * 跳闪成功统计统计（增加时才会调用 更新数据库）
	 * 
	 * @param count
	 */
	public void tiaoshanCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(tiaoshanType, count);
	}

	/**
	 * 武功层数统计统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void wugongCount(int count) {
		if (count <= 0) {
			return;
		}
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(
				myAchieveManger.getCharacter());
		myAchieveManger.characterAchieveMemoryCount(wugongType, count);
	}

	/**
	 * 连斩最高计入统计统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void lianzhanCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger.characterAchieveMemoryCount(lianzhanType, count);
	}

	public void gradeCount(short grade) {
		myAchieveManger.characterAchieveMemoryCount(gradeType, grade);
	}

	/**
	 * 挖藏宝图
	 * 
	 * @param count
	 */
	public void wabaoCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(wangbaoType, count);
	}

	/**
	 * 暗器飞镖统计
	 * 
	 * @param count
	 */
	public void anqiFeiBiaoKillCharacterCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger.characterAchieveToDBCount(feibiaoKillCharacterType,
				count);
	}

	/**
	 * 暗器飞镖统计
	 * 
	 * @param count
	 */
	public void anqiFeiBiaoKillMonsterCount(int count) {
		if (count <= 0) {
			return;
		}
		myAchieveManger
				.characterAchieveToDBCount(feibiaoKillMonsterType, count);
	}
}
