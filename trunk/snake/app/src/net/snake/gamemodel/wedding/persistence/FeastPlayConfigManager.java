package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.wedding.bean.FeastPlayConfig;
import net.snake.ibatis.SystemFactory;

/**
 * @author serv_dev
 */
public class FeastPlayConfigManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(FeastPlayConfigManager.class);
	private static FeastPlayConfigManager instance;
	private HashMap<Integer, FeastPlayConfig> map = new HashMap<Integer, FeastPlayConfig>();
	private FeastPlayConfigDAO dao = new FeastPlayConfigDAO(SystemFactory.getGamedataSqlMapClient());

	private FeastPlayConfigManager() {
		reload();
	}

	public static FeastPlayConfigManager getInstance() {
		if (instance == null) {
			instance = new FeastPlayConfigManager();
		}
		return instance;
	}

	public FeastPlayConfig getConfig(int type) {
		return map.get(type);
	}

	/**
	 * 婚宴玩法配置列表 所有的婚宴类型
	 * 
	 * @return
	 */
	public Collection<FeastPlayConfig> getConfigList() {
		Set<Entry<Integer, FeastPlayConfig>> entrySet = map.entrySet();
		Collection<FeastPlayConfig> configs = new ArrayList<FeastPlayConfig>();
		for (Entry<Integer, FeastPlayConfig> entry : entrySet) {
			configs.add(entry.getValue());
		}
		return configs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			map.clear();
			List<FeastPlayConfig> selectByExample = dao.select();
			if (selectByExample != null)
				for (FeastPlayConfig feastPlayConfig : selectByExample) {
					Integer id = feastPlayConfig.getId();
					map.put(id, feastPlayConfig);
				}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "feastplayconfig";
	}
}
