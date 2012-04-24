package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.program.IReload;
import net.snake.gamemodel.gift.bean.GrandPrixData;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * @author serv_dev
 */
public class GrandPrixDataManager implements IReload {
	private static Logger logger = Logger.getLogger(GrandPrixDataManager.class);
	private static GrandPrixDataManager instance;
	private GrandPrixDataDAO dao = new GrandPrixDataDAO(SystemFactory.getGamedataSqlMapClient());
	private List<GrandPrixData> allGrandPrix;
	private Map<Integer, GrandPrixData> map;

	public static GrandPrixDataManager getInstance() {
		if (instance == null) {
			instance = new GrandPrixDataManager();
		}
		return instance;
	}

	private GrandPrixDataManager() {
		reload();
	}

	public GrandPrixData getGrandPrixById(int id) {
		return map.get(id);
	}

	public List<GrandPrixData> getAllGrandPrix() {
		return allGrandPrix;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			allGrandPrix = dao.select();
			map = new HashMap<Integer, GrandPrixData>();
			if (allGrandPrix != null) {
				for (GrandPrixData grandPrix : allGrandPrix) {
					map.put(grandPrix.getId(), grandPrix);
				}
			}

		} catch (SQLException e) {
			logger.debug(e.getMessage(), e);
		}
	}
}
