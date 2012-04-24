package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.faction.bean.FactionCityConfig;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品包裹表数据管理
 * 
 */

public class FactionCityConfigManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(FactionCityConfigManager.class);
	private FactionCityConfigDAO dao = new FactionCityConfigDAO(SystemFactory.getGamedataSqlMapClient());
	private static FactionCityConfigManager instance;
	public FactionCityConfig factionCtConfig;

	private FactionCityConfigManager() {
		reload();
	}

	public static FactionCityConfigManager getInstance() {
		if (instance == null) {
			instance = new FactionCityConfigManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			List<FactionCityConfig> list = dao.select();
			if (list == null || list.size() == 0) {
				return;
			}
			factionCtConfig = list.get(0);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public FactionCityConfig getFactionCityConfig() {
		return this.factionCtConfig;
	}

	@Override
	public void reload() {
		init();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "factionCityConfig";
	}

}
