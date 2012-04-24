package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 帮会与玩家角色关系表管理
 * 
 */

public class FactionCharacterManager {
	private static final Logger logger = Logger.getLogger(FactionCharacterManager.class);
	private FactionCharacterDAO dao = new FactionCharacterDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static FactionCharacterManager instance;
	private Map<Integer, List<FactionCharacter>> map = new HashMap<Integer, List<FactionCharacter>>();
	private Map<Integer, FactionCharacter> fcMap = new ConcurrentHashMap<Integer, FactionCharacter>(); // 角色id
																										// 对应map

	private FactionCharacterManager() {
		initDate();
	}

	public static FactionCharacterManager getInstance() {
		if (instance == null) {
			instance = new FactionCharacterManager();
		}
		return instance;
	}

	public void deleteFactionCharacterByFactionID(int factionID) {
		try {
			dao.deleteByFactionID(factionID);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void initDate() {
		List<FactionCharacter> list = selectAllFactionCharacter();
		if (list == null || list.size() == 0) {
			return;
		}
		map.clear();
		fcMap.clear();
		for (FactionCharacter fc : list) {
			fcMap.put(fc.getCharacterId(), fc);

			String name = fc.getCce().getName();
			if (name != null && name.equals(fc.getName())) {
				fc.setName("");
			}
			addListMap(fc);
		}
	}

	public FactionCharacter getFc(int characterid) {
		return fcMap.get(characterid);
	}

	public FactionCharacter putFc(FactionCharacter fc) {
		return fcMap.put(fc.getCharacterId(), fc);
	}

	/**
	 * 根据帮会id获取帮会成员 （初始化加载后有效，游戏运行中无效）
	 * 
	 * @return
	 */
	public List<FactionCharacter> getFcListByFaction(int factionId) {
		return map.get(factionId);
	}

	private void addListMap(FactionCharacter fc) {
		List<FactionCharacter> list = map.get(fc.getFactionId());
		if (list == null) {
			list = new ArrayList<FactionCharacter>();
			map.put(fc.getFactionId(), list);
		}
		list.add(fc);
	}

	@SuppressWarnings("unchecked")
	public List<FactionCharacter> selectAllFactionCharacter() {
		try {
			List<FactionCharacter> list = dao.select();
			return list;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	// 单例实现========================================
	public int insert(FactionCharacter fc) {
		try {
			return dao.insert(fc);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}

	public void addFactionCharacter(FactionCharacter fc) {
		int suc = insert(fc);
		if (suc != -1) {
			fcMap.put(fc.getCharacterId(), fc);
		}
	}

	public int delete(FactionCharacter fc) {
		try {
			return dao.deleteById(fc.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}

	public void deleteFactionCharacter(FactionCharacter fc) {
		int suc = delete(fc);
		if (suc != -1) {
			fcMap.remove(fc.getCharacterId());
		}
	}

	public void update(FactionCharacter fc) {
		try {
			dao.updateByPrimaryKeySelective(fc);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeMemoryFactionCharacter(int characterId) {
		fcMap.remove(characterId);
	}
}
