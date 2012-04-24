package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.monster.bean.BossLostGoodDate;
import net.snake.gamemodel.monster.logic.lostgoods.BossLostGood;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品包裹表数据管理
 * 
 */

public class BossLostGoodDateManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(BossLostGoodDateManager.class);

	private BossLostGoodDateDAO dao = new BossLostGoodDateDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, BossLostGood> bossGoodMap;

	// 单例实现=====================================
	private static BossLostGoodDateManager instance;

	private BossLostGoodDateManager() {
	}

	public static BossLostGoodDateManager getInstance() {
		if (instance == null) {
			instance = new BossLostGoodDateManager();
		}
		return instance;
	}

	public BossLostGood getBossLostGoodByNameInMap(int bossId) {
		return this.bossGoodMap.get(bossId);
	}

	public void initBossGoodMap() {
		this.bossGoodMap = new HashMap<Integer, BossLostGood>();
		List<BossLostGoodDate> list = getAllGoodspackgeDateList();
		if (list == null || list.size() == 0) {
			bossGoodMap = new HashMap<Integer, BossLostGood>();
			return;
		}
		for (BossLostGoodDate blgd : list) {
			bossGoodMap.put(blgd.getBossId(), new BossLostGood(blgd));
		}
	}

	@SuppressWarnings("unchecked")
	public List<BossLostGoodDate> getAllGoodspackgeDateList() {
		try {
			return this.dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void reload() {

	}

	@Override
	public String getAppname() {
		return "bossgood";
	}

	@Override
	public String getCachename() {
		return "bossgood";
	}
}
