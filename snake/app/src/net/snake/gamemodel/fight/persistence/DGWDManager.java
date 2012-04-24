package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.heroext.wudao.bean.DgwdModel;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 悟道
 * @author jack
 *
 */
public class DGWDManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(DGWDManager.class);
	private static DGWDManager manager = new DGWDManager();
	private Map<Integer, DgwdModel> modelmap = new HashMap<Integer, DgwdModel>();
	private DgwdModelDAO dao = new DgwdModelDAO(SystemFactory.getGamedataSqlMapClient());

	public static DGWDManager getInstance() {
		return manager;
	}

	private DGWDManager() {
	}

	public DgwdModel getModel(int id) {
		return modelmap.get(id);
	}

	public Collection<DgwdModel> getAllModel() {
		return modelmap.values();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void reload() {
		List selectByExample;
		try {
			selectByExample = dao.select();
			BeanTool.addOrUpdate(modelmap, selectByExample, "id");
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
		return "dgwd";
	}

}
