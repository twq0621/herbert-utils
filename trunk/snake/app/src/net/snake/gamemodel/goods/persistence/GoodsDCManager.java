package net.snake.gamemodel.goods.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.goods.bean.GoodsDC;
import net.snake.ibatis.SystemFactory;
import org.apache.log4j.Logger;

/**
 * 物品收集
 * 
 * @author serv_dev
 * 
 */

public class GoodsDCManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(GoodsDCManager.class);

	private GoodsDCDAO dao = new GoodsDCDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static GoodsDCManager instance;
	private final Map<Integer, GoodsDC> cacheGoodsDCMap = new HashMap<Integer, GoodsDC>();

	private GoodsDCManager() {
	}

	public static GoodsDCManager getInstance() {
		if (instance == null) {
			instance = new GoodsDCManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void initGoodsDCMap() {
		try {
			List<GoodsDC> goodsDCList = dao.select();
			BeanTool.addOrUpdate(cacheGoodsDCMap, goodsDCList, "id");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public GoodsDC getGoodsDCById(int id) {
		return cacheGoodsDCMap.get(id);
	}

	@Override
	public void reload() {
		initGoodsDCMap();
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "goodsdc";
	}
}
