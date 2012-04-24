package net.snake.gamemodel.achieve.logic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.achieve.persistence.CharacterAchieveCountManager;
import net.snake.gamemodel.fight.bean.CharacterAchieveCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.tongji.logic.CharacterChongzhiCount;
import net.snake.gamemodel.tongji.logic.CharacterDieCount;
import net.snake.gamemodel.tongji.logic.CharacterOtherCount;
import net.snake.gamemodel.tongji.logic.EquipmentCount;
import net.snake.gamemodel.tongji.logic.FactionCount;
import net.snake.gamemodel.tongji.logic.FriendCount;
import net.snake.gamemodel.tongji.logic.GiftPacksCount;
import net.snake.gamemodel.tongji.logic.HourseCount;
import net.snake.gamemodel.tongji.logic.InstanceCount;
import net.snake.gamemodel.tongji.logic.KillMonsterCount;
import net.snake.gamemodel.tongji.logic.TaskCount;
import net.snake.gamemodel.tongji.logic.TeamCount;
import net.snake.shell.Options;



/**
 * 成就统计管理器（主要针对游戏中玩家个中行文统计）
 * 
 * @author serv_dev
 * 
 */
public class MyCharacterAchieveCountManger {
	private static final Logger logger = Logger.getLogger(MyCharacterAchieveCountManger.class);
	private Hero character;
	public Map<Integer, CharacterAchieveCount> memoryMap = new ConcurrentHashMap<Integer, CharacterAchieveCount>(); // 内存级别存储（不写入数据库）
	public Map<Integer, CharacterAchieveCount> dbMap = new ConcurrentHashMap<Integer, CharacterAchieveCount>(); // 统计类型
	private final KillMonsterCount killMonster = new KillMonsterCount(this);
	private final TaskCount taskCount = new TaskCount(this);
	private final CharacterDieCount characterDieCount = new CharacterDieCount(this);
	private final HourseCount horseCount = new HourseCount(this);
	private final EquipmentCount equipmentCount = new EquipmentCount(this);
	private final FriendCount friendCount = new FriendCount(this);
	private final FactionCount factionCount = new FactionCount(this);
	private final TeamCount teamCount = new TeamCount(this);
	private final CharacterOtherCount characterOtherCount = new CharacterOtherCount(this);
	private final CharacterChongzhiCount characterChongzhiCount = new CharacterChongzhiCount(this);
	private final GiftPacksCount giftPacksCount = new GiftPacksCount(this);
	private final InstanceCount instanceCount = new InstanceCount(this);

	public void destroy() {
		if (memoryMap != null) {
			memoryMap.clear();
			memoryMap = null;
		}
		if (dbMap != null) {
			dbMap.clear();
			dbMap = null;
		}
	}

	public MyCharacterAchieveCountManger(Hero character) {
		this.character = character;
	}

	/**
	 * 初始化角色当前角色行为统计信息
	 */
	public void init() {
		if (Options.IsCrossServ) {
			return;
		}
		characterOtherCount.copperCount(character.getCopper());
		characterOtherCount.prestigeCount(character.getPrestige());
		characterOtherCount.lianzhanCount(character.getMaxContinueKillcount());
		characterOtherCount.gradeCount(character.getGrade());
		characterOtherCount.jumpCount(character.getMaxJumpCount());
		factionCount.chengzhangShengwangCount(character.getChengzhanShengWang());
		factionCount.lunjianShengwangCount(character.getLunjianShengWang());
		// factionCount.factionContributionCount(character.getContribution());
		List<CharacterAchieveCount> list = CharacterAchieveCountManager.getInstance().selecteListByCharacterId(character.getId());
		if (list == null || list.size() == 0) {
			return;
		}
		for (CharacterAchieveCount cac : list) {
			dbMap.put(cac.getChildKind(), cac);
		}
	}

	public GiftPacksCount getGiftPacksCount() {
		return giftPacksCount;
	}

	public FactionCount getFactionCount() {
		return factionCount;
	}

	public InstanceCount getInstanceCount() {
		return instanceCount;
	}

	public Hero getCharacter() {
		return character;
	}

	public void setCharacter(Hero character) {
		this.character = character;
	}

	/**
	 * 角色某些行文统计 数据库记入
	 * 
	 * @param childKind
	 * @param count
	 * @return
	 */
	public CharacterAchieveCount addOrUpdateDbMapAchieve(int childKind, int count) {
		CharacterAchieveCount cac = dbMap.get(childKind);
		if (cac == null) {
			cac = new CharacterAchieveCount();
			cac.setAchieveCount(count);
			cac.setCharacterId(character.getId());
			cac.setChildKind(childKind);
			CharacterAchieveCountManager.getInstance().asynInsertCharacterAchieveCount(character, cac);
			dbMap.put(cac.getChildKind(), cac);
			return cac;
		}
		cac.setAchieveCount(cac.getAchieveCount() + count);
		CharacterAchieveCountManager.getInstance().asynUpdateCharacterAchieveCount(character, cac);
		return cac;
	}

	/**
	 * 获取存取数据库的统计
	 * 
	 * @param childKind
	 * @return
	 */
	public CharacterAchieveCount getDBAchieveCountBychildKind(int childKind) {
		return dbMap.get(childKind);
	}

	/**
	 * 获取所有统计
	 * 
	 * @param childKind
	 * @return
	 */
	public CharacterAchieveCount getAchieveCountAllBychildKind(int childKind) {
		CharacterAchieveCount cac = dbMap.get(childKind);
		if (cac == null) {
			cac = memoryMap.get(childKind);
		}
		return cac;
	}

	/**
	 * 根据当前统计值 进行添加更新统计
	 * 
	 * @param countType
	 * @param count
	 */
	public void catchAchieveToDBCount(int countType, int count) {
		boolean isover = isOverCountDbByType(countType);
		if (isover) {
			return;
		}
		CharacterAchieveCount cac = getDBAchieveCountBychildKind(countType);
		if (cac == null) {
			characterAchieveToDBCount(countType, count);
		} else {
			count = count - cac.getAchieveCount();
			if (count > 0) {
				characterAchieveToDBCount(countType, count);
			}
		}
	}

	/**
	 * 玩家根据俄类别进行不同的行为统计
	 * 
	 * @param countType
	 * @param addCount
	 */
	public void characterAchieveToDBCount(int countType, int addCount) {
		boolean isOver = isOverCountDbByType(countType);
		if (isOver) {
			return;
		}
		CharacterAchieveCount cac = addOrUpdateDbMapAchieve(countType, addCount);
		int count = cac.getAchieveCount();
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		for (int i = 0; i < list.size(); i++) {
			Achieve achieve = list.get(i);
			if (achieve.getAchieveCount() <= count) {
				boolean b = character.getMyCharacterAchieveManger().addCharacterAchieve(achieve);
				if (!b) {
					break;
				}
			}
		}
	}

	/**
	 * 不存数据库的临时内存存储的成就（便于统一处理）
	 */
	public void characterAchieveMemoryCount(int countType, int count) {
		boolean isOver = isOverCountMemoryByType(countType);
		if (isOver) {
			return;
		}
		addOrUpdateMemoryMapCount(countType, count);
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		for (int i = 0; i < list.size(); i++) {
			Achieve achieve = list.get(i);
			if (achieve.getAchieveCount() <= count) {
				boolean b = character.getMyCharacterAchieveManger().addCharacterAchieve(achieve);
				if (!b) {
					break;
				}
			}
		}
	}

	/**
	 * 不存数据库的临时内存存储的成就（便于统一处理）
	 */
	public CharacterAchieveCount addOrUpdateMemoryMapCount(int childKind, int count) {
		CharacterAchieveCount cac = memoryMap.get(childKind);
		if (cac == null) {
			cac = new CharacterAchieveCount();
			cac.setAchieveCount(count);
			cac.setCharacterId(character.getId());
			cac.setChildKind(childKind);
			memoryMap.put(cac.getChildKind(), cac);
			return cac;
		}
		cac.setAchieveCount(count);
		return cac;
	}

	/**
	 * 内存中成就是否存在
	 * 
	 * @param countType
	 * @return
	 */
	public boolean isOverCountMemoryByType(int countType) {
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		if (list == null || list.size() == 0) {
			logger.error("data err about Achieve "+ countType);
			return true;
		}
		Achieve achieve = list.get(0);
		return character.getMyCharacterAchieveManger().isHaveMemoryAchieve(achieve);
	}

	/**
	 * 数据库存储成就是否存在
	 * 
	 * @param countType
	 * @return
	 */
	public boolean isOverCountDbByType(int countType) {
		if (Options.IsCrossServ) {
			return true;
		}
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		if (list == null || list.size() == 0) {
//			logger.error("成就数据数类别为：{} 的数据错误", countType);
			return true;
		}
		Achieve achieve = list.get(0);
		return character.getMyCharacterAchieveManger().isHaveDbAchieve(achieve);
	}

	public KillMonsterCount getKillMonster() {
		return killMonster;
	}

	public TaskCount getTaskCount() {
		return taskCount;
	}

	public CharacterDieCount getCharacterDieCount() {
		return characterDieCount;
	}

	public HourseCount getHorseCount() {
		return horseCount;
	}

	public EquipmentCount getEquipmentCount() {
		return equipmentCount;
	}

	public FriendCount getFriendCount() {
		return friendCount;
	}

	public TeamCount getTeamCount() {
		return teamCount;
	}

	public CharacterOtherCount getCharacterOtherCount() {
		return characterOtherCount;
	}

	public CharacterChongzhiCount getCharacterChongzhiCount() {
		return characterChongzhiCount;
	}

}
