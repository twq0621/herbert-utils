package net.snake.gamemodel.equipment.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 装备强化表管理
 * 
 * @author benchild
 */

public class EquipmentPlayconfigManager implements CacheUpdateListener {

	private static final Logger logger = Logger.getLogger(EquipmentPlayconfigManager.class);
	private static EquipmentPlayconfigDAO dao = new EquipmentPlayconfigDAO(SystemFactory.getGamedataSqlMapClient());

	private static EquipmentPlayconfigManager instance;

	private Map<Integer, EquipmentPlayconfig> cacheEquipmentPlayconfigMap = new HashMap<Integer, EquipmentPlayconfig>();

	private EquipmentPlayconfigManager() {
	}

	public static EquipmentPlayconfigManager getInstance() {
		if (instance == null) {
			instance = new EquipmentPlayconfigManager();
		}
		return instance;
	}

	public EquipmentPlayconfig getEPlayconfigByGoodsId(int goodsId) {
		return cacheEquipmentPlayconfigMap.get(goodsId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			List<EquipmentPlayconfig> epclist = dao.select();
			BeanTool.addOrUpdate(cacheEquipmentPlayconfigMap, epclist, "goodmodelId");
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
		return "equipmentplayconfig";
	}
}
