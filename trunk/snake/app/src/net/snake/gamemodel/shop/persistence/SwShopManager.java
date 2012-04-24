package net.snake.gamemodel.shop.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.shop.bean.ShopShengwang;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class SwShopManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(SwShopManager.class);
	private final static SwShopManager instance = new SwShopManager();
	private SwShopEntryDAO dao = new SwShopEntryDAO(SystemFactory.getGamedataSqlMapClient());
	private HashMap<Integer, List<ShopShengwang>> map = new HashMap<Integer, List<ShopShengwang>>();
	private HashMap<Integer, ShopShengwang> idmap = new HashMap<Integer, ShopShengwang>();

	public static SwShopManager getInstance() {
		return instance;
	}

	private SwShopManager() {
	}

	public List<ShopShengwang> getShoItemByType(int type, int type2) {
		List<ShopShengwang> list = map.get(type);
		if (list == null) {
			return null;
		}
		List<ShopShengwang> list2 = new ArrayList<ShopShengwang>();
		for (ShopShengwang swShopEntry : list) {
			if (swShopEntry.getSaleType() == type2 || type2 == 3) {
				list2.add(swShopEntry);
			}
		}
		return list2;
	}

	@Override
	public void reload() {
		try {
			@SuppressWarnings("unchecked")
			List<ShopShengwang> swshoplist = dao.select();
			synchronized (map) {
				synchronized (idmap) {
					idmap.clear();
					map.clear();
					for (ShopShengwang swShopEntry : swshoplist) {
						if (swShopEntry.getIsEnable() == 1) {
							List<ShopShengwang> list = map.get(swShopEntry.getGoodsType());
							if (list == null) {
								list = new ArrayList<ShopShengwang>();
								map.put(swShopEntry.getGoodsType(), list);
							}
							list.add(swShopEntry);
							idmap.put(swShopEntry.getId(), swShopEntry);
						}
					}
				}
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
		return "swshop";
	}

	public ShopShengwang getShopItemById(int id) {
		return idmap.get(id);

	}

}
