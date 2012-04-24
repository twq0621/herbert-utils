package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.hero.bean.Breakthrough;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 瓶颈管理
 * 
 * @author serv_dev
 * 
 */

public class BreakthroughManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(BreakthroughManager.class);
	private Map<Integer, Breakthrough> breakthroughMap = new HashMap<Integer, Breakthrough>();
	private List<Breakthrough> breakthroughList = null;

	public Map<Integer, Breakthrough> getBreakthroughMap() {
		return breakthroughMap;
	}

	public List<Breakthrough> getBreakthroughList() {
		return breakthroughList;
	}

	private static BreakthroughDAO dao = new BreakthroughDAO(SystemFactory.getGamedataSqlMapClient());

	// 单例实现=====================================
	private static BreakthroughManager instance;

	private BreakthroughManager() {
	}

	public static BreakthroughManager getInstance() {
		if (instance == null) {
			instance = new BreakthroughManager();
		}
		return instance;
	}

	// 单例实现========================================
	@SuppressWarnings("unchecked")
	private Map<Integer, Breakthrough> getBreakthrough() {
		try {
			breakthroughList = dao.select();
			Map<Integer, Breakthrough> chMap = BeanTool.listToMap(breakthroughList, "type");
			return chMap;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void reload() {
		try {
			BeanTool.addOrUpdate(breakthroughMap, getBreakthrough(), "type");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "breakthrough";
	}

}
