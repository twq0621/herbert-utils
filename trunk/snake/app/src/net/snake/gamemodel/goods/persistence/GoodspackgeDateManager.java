package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodspackge;
import net.snake.gamemodel.goods.bean.GoodspackgeDate;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品包裹表数据管理
 * 
 */

public class GoodspackgeDateManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(GoodspackgeDateManager.class);

	private GoodspackgeDateDAO dao = new GoodspackgeDateDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<String, Goodspackge> packgeMap = new HashMap<String, Goodspackge>();
	private Map<Integer, Goodspackge> packgeIdMap = new HashMap<Integer, Goodspackge>();
	// 单例实现=====================================
	private static GoodspackgeDateManager instance;

	private GoodspackgeDateManager() {
		initPackgeMap();
	}

	public static GoodspackgeDateManager getInstance() {
		if (instance == null) {
			instance = new GoodspackgeDateManager();
		}
		return instance;
	}

	/**
	 * 任务物品掉落奖励
	 * 
	 * @param packsId包裹id
	 * @param count
	 *            物品数量
	 * @param loop
	 *            掉落属性循环
	 * @return
	 */
	public List<CharacterGoods> getTaskRewardGoods(int packsId, int count, int loop) {
		Goodspackge gp = packgeIdMap.get(packsId);
		if (gp == null) {
			return null;
		}
		return gp.dropTaskRewardGoods(count, loop);
	}

	public Map<Integer, GoodspackgeDate> getGoodsPackageMap() {
		return null;
	}

	public Goodspackge getGoodspackgeByNameInMap(String name) {
		return this.packgeMap.get(name);
	}

	public void initPackgeMap() {
		List<GoodspackgeDate> list = getAllGoodspackgeDateList();
		if (list == null || list.size() == 0) {
			return;
		}
		for (GoodspackgeDate gpgd : list) {
			addOrUpdateGoodPackge(gpgd);
		}
		for (Goodspackge gp : packgeMap.values()) {
			packgeIdMap.put(gp.getGpd().getId(), gp);
		}
	}

	private void addOrUpdateGoodPackge(GoodspackgeDate gpgd) {
		Goodspackge gp = getGoodspackgeByNameInMap(gpgd.getName());
		if (gp == null) {
			gp = new Goodspackge();
			packgeMap.put(gpgd.getName(), gp);
		}
		gp.initGoodPackge(gpgd);
	}

	@SuppressWarnings("unchecked")
	public List<GoodspackgeDate> getAllGoodspackgeDateList() {
		try {
			return this.dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void reload() {
		initPackgeMap();
	}

	@Override
	public String getAppname() {
		return "goodspackge";
	}

	@Override
	public String getCachename() {
		return "goodspackge";
	}

}
