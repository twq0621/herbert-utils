package net.snake.gamemodel.equipment.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanUtils;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.equipment.bean.EquipmentStrengthen;

/**
 * 装备强化属性表管理
 * 
 * @author benchild
 */

public class EquipmentStrengthenManager implements CacheUpdateListener {

	private static EquipmentStrengthenDao dao = new EquipmentStrengthenDao();

	private static EquipmentStrengthenManager instance;

	private Map<String, EquipmentStrengthen> cacheEquipmentStrengthenMap = new HashMap<String, EquipmentStrengthen>();

	private EquipmentStrengthenManager() {
	}

	public static EquipmentStrengthenManager getInstance() {
		if (instance == null) {
			instance = new EquipmentStrengthenManager();
		}
		return instance;
	}

	public EquipmentStrengthen getEuipmentStrengthen(int goodModelid, int grade) {
		EquipmentStrengthen euipmentStrengthen = cacheEquipmentStrengthenMap.get(goodModelid + "_" + grade);
		return euipmentStrengthen;
	}

	private List<EquipmentStrengthen> getEuipmentStrengthenList() {
		return dao.getEuipmentStrengthenList();
	}

	@Override
	public void reload() {
		List<EquipmentStrengthen> euipmentStrengthenList = getEuipmentStrengthenList();
		if (euipmentStrengthenList != null && !euipmentStrengthenList.isEmpty()) {
			for (Iterator<EquipmentStrengthen> iterator = euipmentStrengthenList.iterator(); iterator.hasNext();) {
				EquipmentStrengthen euipmentStrengthen = (EquipmentStrengthen) iterator.next();
				String key = euipmentStrengthen.getGoodmodelId()+ "_" + euipmentStrengthen.getGrade();
				EquipmentStrengthen _euipmentStrengthen = cacheEquipmentStrengthenMap.get(key);// 老数据
				if (_euipmentStrengthen == null) {
					cacheEquipmentStrengthenMap.put(key, euipmentStrengthen);
				} else {
					BeanUtils.copyProperties(euipmentStrengthen, _euipmentStrengthen);
				}
			}
		}
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "equipmentStrengthen";
	}
}
