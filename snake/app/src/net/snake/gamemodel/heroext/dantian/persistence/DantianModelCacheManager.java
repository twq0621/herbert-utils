package net.snake.gamemodel.heroext.dantian.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 丹田模型类
 * 
 * @author serv_dev
 */
public class DantianModelCacheManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(DantianModelCacheManager.class);
	private static final DantianModelCacheManager instance = new DantianModelCacheManager();
	DantianModelDAO dao = new DantianModelDAO(SystemFactory.getGamedataSqlMapClient());
	private final HashMap<Integer, DantianModel> map = new HashMap<Integer, DantianModel>();

	public static DantianModelCacheManager getInstance() {
		return instance;
	}

	private DantianModelCacheManager() {
	}

	public DantianModel getModelById(int id) {
		return map.get(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void reload() {
		try {
			List selectByExample = dao.select();
			BeanTool.addOrUpdate(map, selectByExample, "id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
		return "dantian";
	}

}
