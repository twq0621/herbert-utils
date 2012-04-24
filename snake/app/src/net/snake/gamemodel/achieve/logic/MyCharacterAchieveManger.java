package net.snake.gamemodel.achieve.logic;

import net.snake.GameServer;
import net.snake.gamemodel.achieve.bean.Achieve;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.achieve.persistence.CharacterAchieveManager;
import net.snake.gamemodel.achieve.response.AchieveFinshiBoardMsg51010;
import net.snake.gamemodel.achieve.response.AchieveFinshiMsg51008;
import net.snake.gamemodel.achieve.response.AchieveListMsg51002;
import net.snake.gamemodel.achieve.response.AchieveTitleListMsg51004;
import net.snake.gamemodel.achieve.response.TitleOpenMsg51012;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.CharacterAchieve;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 角色获得成就管理器
 * 
 * @author serv_dev
 * 
 */
public class MyCharacterAchieveManger {
	private static Logger logger = Logger.getLogger(MyCharacterAchieveManger.class);

	private Hero character;
	public int chengjiuPoint = 0; // 玩家获得的成就点数
	// private Map<Integer, CharacterAchieve> memoryMap = new
	// ConcurrentHashMap<Integer, CharacterAchieve>();
	public Map<Integer, Achieve> memoryMap = new ConcurrentHashMap<Integer, Achieve>(); // 内存级别存储（不写入数据库）
	private Map<Integer, CharacterAchieve> dbMap = new ConcurrentHashMap<Integer, CharacterAchieve>(); // 玩家获得成就
	public List<CharacterAchieve> titleList = new ArrayList<CharacterAchieve>(); // 玩家获得的成就称号

	public MyCharacterAchieveManger(Hero character) {
		this.character = character;
	}

	public void destory() {
		if (memoryMap != null) {
			memoryMap.clear();
			memoryMap = null;
		}
		if (dbMap != null) {
			dbMap.clear();
			dbMap = null;
		}
		if (titleList != null) {
			titleList.clear();
			titleList = null;
		}
	}

	/**
	 * 角色登入初始化玩家成就
	 */
	public void initData() {
		try {
			List<CharacterAchieve> list = CharacterAchieveManager.getInstance().selecteListByCharacterId(character.getId());
			for (CharacterAchieve ca : list) {
				String title = ca.getTitle();
				if (title != null && !title.equals("")) {
					titleList.add(ca);
				}
				dbMap.put(ca.getAchieveId(), ca);
			}
			for (CharacterAchieve ca : dbMap.values()) {
				if (ca.getPoint() != null) {
					chengjiuPoint = ca.getPoint() + chengjiuPoint;
				}
			}
			character.setChengjiuPoint(chengjiuPoint);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public CharacterAchieve getCharacterAchieveDbMapById(int achieveId) {
		return dbMap.get(achieveId);
	}

	/**
	 * 获取完成的成就
	 * 
	 * @param achieveId
	 * @return
	 */
	public Achieve getFinishAchieve(int achieveId) {
		CharacterAchieve ca = dbMap.get(achieveId);
		if (ca != null) {
			return ca.getAchieve();
		}
		return memoryMap.get(achieveId);
	}

	/**
	 * 插入添加完成的成就
	 * 
	 * @param achieve
	 */
	public boolean addCharacterAchieve(Achieve achieve) {
		if (Options.IsCrossServ) {
			return false;
		}
		CharacterAchieve ca = getCharacterAchieveDbMapById(achieve.getId());
		if (ca != null) {
			return false;
		}
		ca = new CharacterAchieve();
		ca.setCharacterId(character.getId());
		ca.setAchieveId(achieve.getId());
		ca.setPoint(achieve.getPoint());
		chengjiuPoint = ca.getPoint() + chengjiuPoint;
		character.setChengjiuPoint(chengjiuPoint);
		String title = ca.getTitle();
		if (title != null && !title.equals("")) {
			titleList.add(ca);
		}
		CharacterAchieveManager.getInstance().asynInsertCharacterAchieve(character, ca);
		dbMap.put(ca.getAchieveId(), ca);
		if (achieve.getIsnotice() == 1) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new AchieveFinshiBoardMsg51010(character, achieve));
		}
		achieveReward(achieve);
		if (ca.getPoint() > 0) {
			CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		}
		return true;
	}

	/**
	 * 玩家获得成就 物品奖励调用此方法
	 * 
	 * @param achieve
	 */
	public void achieveReward(Achieve achieve) {
		if (achieve.getPoint() != null && achieve.getPoint() > 0) {
			int point = achieve.getPoint();
			CharacterPropertyManager.changeLijin(character, point);
			CharacterPropertyManager.changeMaxHp(character, point);
		}
		CharacterGoods cg = achieve.getJianglIGoods();
		if (cg == null) {
			character.sendMsg(new AchieveFinshiMsg51008(achieve));
			return;
		} else {
			boolean b = character.getCharacterGoodController().addGoodsToBag(cg);
			if (b) {
				character.sendMsg(new AchieveFinshiMsg51008(achieve));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 649));
			}
		}
	}

	/**
	 * 是否存在玩家获取的内存级别的成就
	 * 
	 * @param achieve
	 * @return
	 */
	public boolean isHaveMemoryAchieve(Achieve achieve) {
		Achieve ca = memoryMap.get(achieve.getId());
		if (ca != null) {
			return true;
		}
		return false;
	}

	/**
	 * 是否存在角色获取的数据库存储的成就
	 * 
	 * @param achieve
	 * @return
	 */
	public boolean isHaveDbAchieve(Achieve achieve) {
		CharacterAchieve ca = getCharacterAchieveDbMapById(achieve.getId());
		if (ca != null) {
			return true;
		}
		return false;
	}

	/**
	 * 发送类别成就
	 */
	public void sendAchieveListByKind(int kind) {
		character.sendMsg(new AchieveListMsg51002(character, kind));
	}

	/**
	 * 发送称号列表
	 */
	public void sendAchieveTitleList() {
		character.sendMsg(new AchieveTitleListMsg51004(this));
	}

	public List<CharacterAchieve> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<CharacterAchieve> titleList) {
		this.titleList = titleList;
	}

	public int getChengjiuPoint() {
		return chengjiuPoint;
	}

	public void setChengjiuPoint(int chengjiuPoint) {
		this.chengjiuPoint = chengjiuPoint;
	}

	/**
	 * 玩家启用某成就的称号
	 * 
	 * @param achieveId
	 */
	public void openTitle(int achieveId) {
		if (achieveId < 1) {
			character.setNowAppellationid(0);
			character.getEyeShotManager().sendMsg(new TitleOpenMsg51012(character));
			return;
		}
		CharacterAchieve ca = dbMap.get(achieveId);
		if (ca == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 650));
			return;
		}
		Achieve achieve = ca.getAchieve();
		if (achieve == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 651));
		}
		int id = achieve.getId();
		if (id == character.getNowAppellationid()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 652));
			return;
		}
		character.setNowAppellationid(ca.getAchieveId());
		character.getEyeShotManager().sendMsg(new TitleOpenMsg51012(character));
	}

	/**
	 * true 存在
	 * 
	 * @param achieve
	 * @return
	 */
	public boolean initAddMemoryMapAchieve(Achieve achieve) {
		Achieve ac = memoryMap.get(achieve.getId());
		if (ac != null) {
			return false;
		}
		memoryMap.put(achieve.getId(), achieve);
		return true;
	}

	/**
	 * 添加内存级别的成就（目前礼包类的） true 存在
	 * 
	 * @param achieve
	 * @return
	 */
	public boolean addMemoryMapAchieve(Achieve achieve) {
		Achieve ac = memoryMap.get(achieve.getId());
		if (ac != null) {
			return false;
		}
		memoryMap.put(achieve.getId(), achieve);
		achieveReward(achieve);
		return true;
	}

	/**
	 * 清楚某类型的内存级别的成就（主要针对礼包之类的）
	 * 
	 * @param countType
	 */
	public void clearAchieveByChildKind(int countType) {
		List<Achieve> list = AchieveManager.getInstance().getAchieveListByChildKind(countType);
		for (int i = 0; i < list.size(); i++) {
			Achieve achieve = list.get(i);
			memoryMap.remove(achieve.getId());
		}
	}

}
