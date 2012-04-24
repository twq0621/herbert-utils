package net.snake.gamemodel.skill.bow.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.skill.bow.bean.BowModel;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 弓箭模型缓存
 * 
 * @author serv_dev
 */
public class BowModelCacheManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(BowModelCacheManager.class);
	private HashMap<Integer, BowModel> modelCache = new HashMap<Integer, BowModel>();
	private HashMap<Integer, BowModel> modelLevelCache = new HashMap<Integer, BowModel>();
	private BowModelDAO dao = new BowModelDAO(SystemFactory.getGamedataSqlMapClient());
	private static BowModelCacheManager instance = new BowModelCacheManager();

	public static BowModelCacheManager getInstance() {
		return instance;
	}

	private BowModelCacheManager() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void reload() {
		try {
			List selectByExample = dao.select();
			BeanTool.addOrUpdate(modelCache, selectByExample, "id");
			BeanTool.addOrUpdate(modelLevelCache, selectByExample, "level");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public BowModel getBowModelById(int id) {
		return modelCache.get(id);
	}

	public BowModel getBowModelByLevel(int level) {
		return modelLevelCache.get(level);
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "bowmodel";
	}

}
