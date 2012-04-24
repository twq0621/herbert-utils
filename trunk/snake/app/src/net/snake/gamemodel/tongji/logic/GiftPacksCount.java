package net.snake.gamemodel.tongji.logic;

import java.util.List;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveManger;
import net.snake.gamemodel.achieve.persistence.AchieveManager;

/**
 * 角色礼包相关统计
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksCount {
	MyCharacterAchieveCountManger myAchieveManger;

	public GiftPacksCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;

	}

	/**
	 * 礼包统计统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void clearGiftPacksCount(int countType, int count) {
		MyCharacterAchieveManger mcam = myAchieveManger.getCharacter().getMyCharacterAchieveManger();
		mcam.clearAchieveByChildKind(countType);
		characterAchieveMemoryCount(countType, count);
	}

	/**
	 * 礼包统计统计（增加时才会调用）
	 * 
	 * @param count
	 */
	public void giftPacksCount(int countType, int count) {
		characterAchieveMemoryCount(countType, count);
	}

	/**
	 * 不存数据库的临时内存存储的成就（便于统一处理）
	 */
	public void characterAchieveMemoryCount(int countType, int count) {
		myAchieveManger.addOrUpdateMemoryMapCount(countType, count);
		MyCharacterAchieveManger mcam = myAchieveManger.getCharacter().getMyCharacterAchieveManger();
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		for (int i = 0; i < list.size(); i++) {
			Achieve achieve = list.get(i);
			if (achieve.getAchieveCount() <= count) {
				boolean b = mcam.addMemoryMapAchieve(achieve);
				if (!b) {
					break;
				}
			}
		}
	}

	/**
	 * 初始化礼包数据
	 * 
	 * @param countType
	 * @param count
	 */
	public void initGiftAchieveMemoryCount(int countType, int count) {
		myAchieveManger.addOrUpdateMemoryMapCount(countType, count);
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		MyCharacterAchieveManger mcam = myAchieveManger.getCharacter().getMyCharacterAchieveManger();
		for (int i = 0; i < list.size(); i++) {
			Achieve achieve = list.get(i);
			if (achieve.getAchieveCount() <= count) {
				boolean b = mcam.initAddMemoryMapAchieve(achieve);
				if (!b) {
					break;
				}
			}
		}
	}
}
