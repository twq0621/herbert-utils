package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.monster.bean.MonsterLostgoodsDate;
import net.snake.gamemodel.monster.logic.lostgoods.MonsterLostgoods;
import net.snake.ibatis.SystemFactory;

/**
 * 怪物掉落物品数据定义管理
 * 
 */

public class MonsterLostgoodsDateManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(MonsterLostgoodsDateManager.class);
	private MonsterLostgoodsDateDAO dao = new MonsterLostgoodsDateDAO(SystemFactory.getGamedataSqlMapClient());
	private static MonsterLostgoodsDateManager instance;
	private Map<Integer, MonsterLostgoods> monsterLostgoodsMap;

	private MonsterLostgoodsDateManager() {
	}

	public static MonsterLostgoodsDateManager getInstance() {
		if (instance == null) {
			instance = new MonsterLostgoodsDateManager();
		}
		return instance;
	}

	public MonsterLostgoods getMonsterLostgoodsByMonsterId(int monsterId) {
		return monsterLostgoodsMap.get(monsterId);
	}

	/**
	 * 初始化怪物掉落数据 以及更新怪物模型掉落物品数据
	 */
	public void initMonsterLostgoods() {
		List<MonsterLostgoodsDate> list = getAllMonsterLostgoodsDateList();
		if (list == null || list.size() == 0) {
			monsterLostgoodsMap = new HashMap<Integer, MonsterLostgoods>();
			return;
		}
		Map<Integer, MonsterLostgoods> map = new HashMap<Integer, MonsterLostgoods>();
		for (MonsterLostgoodsDate mlgd : list) {
			MonsterLostgoods mlg = new MonsterLostgoods(mlgd);
			map.put(mlgd.getMonstermodelId(), mlg);
		}
		monsterLostgoodsMap = map;
	}
	@SuppressWarnings("unchecked")
	public List<MonsterLostgoodsDate> getAllMonsterLostgoodsDateList() {
		try {
			return this.dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void reload() {
		initMonsterLostgoods();
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "monsterlostgoods";
	}

}
