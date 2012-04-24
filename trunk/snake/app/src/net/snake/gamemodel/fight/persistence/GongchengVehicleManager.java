package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.ibatis.SystemFactory;

/**
 * 物品包裹表数据管理
 * 
 */

public class GongchengVehicleManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(GongchengVehicleManager.class);
	private GongchengVehicleDAO dao = new GongchengVehicleDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, GongchengVehicle> vehicleMap = new HashMap<Integer, GongchengVehicle>();
	private static GongchengVehicleManager instance;

	private GongchengVehicleManager() {
		init();
	}
	@SuppressWarnings("unchecked")
	public void init() {
		List<GongchengVehicle> list;
		try {
			list = dao.select();
			BeanTool.addOrUpdate(vehicleMap, list, "id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static GongchengVehicleManager getInstance() {
		if (instance == null) {
			instance = new GongchengVehicleManager();
		}
		return instance;
	}

	public GongchengVehicle getGongchengVehicleByVehicleId(int vehicleId) {
		return vehicleMap.get(vehicleId);
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
		return "vehicle";
	}

}
