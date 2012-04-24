package net.snake.gamemodel.map.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import net.snake.gamemodel.map.bean.TransportDate;
import net.snake.ibatis.SystemFactory;

/**
 * 传送点表管理
 * 
 */
public class TransportDateManager {
	private static final Logger logger = Logger.getLogger(TransportDateManager.class);
	private static TransportDateDAO dao = new TransportDateDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static TransportDateManager instance;

	private Map<Integer, Set<TransportDate>> cacheTransportDateMap;

	private TransportDateManager() {
		initTransportDate();
	}

	public void Map() {

	}

	public static TransportDateManager getInstance() {
		if (instance == null) {
			instance = new TransportDateManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<TransportDate> selectAllTransportDate() {
		try {
			return dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void addCacheTransportDateMap(TransportDate td) {
		Set<TransportDate> setTd = cacheTransportDateMap.get(td.getSceneId());
		if (setTd == null) {
			setTd = new HashSet<TransportDate>();
			cacheTransportDateMap.put(td.getSceneId(), setTd);
		}
		setTd.add(td);
	}

	public Set<TransportDate> getTransportDateSet(int mapId) {
		return cacheTransportDateMap.get(mapId);
	}

	public void initTransportDate() {
		cacheTransportDateMap = new HashMap<Integer, Set<TransportDate>>();
		List<TransportDate> list = selectAllTransportDate();
		for (TransportDate td : list) {
			addCacheTransportDateMap(td);
		}
	}
}
