package net.snake.gamemodel.shop.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 商城商品
 * 
 * @author benchild
 */

public class ShopManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(ShopManager.class);
	private ShopDAO dao = new ShopDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, List<Shop>> shopTypeMap = new HashMap<Integer, List<Shop>>(); // 商城分类存储
	private Map<String, Shop> shopGoodsidMap = new HashMap<String, Shop>(); // 商城
																			// 根据物品id获取商城物品
	private Map<Integer, Shop> shopIdMap = new HashMap<Integer, Shop>(); // 商城物品id获取商城物品
	private static ShopManager instance;

	private ShopManager() {
	}

	public static ShopManager getInstance() {
		if (instance == null) {
			instance = new ShopManager();
		}
		return instance;
	}

	public List<Shop> getListByShopType(int type) {
		List<Shop> list = shopTypeMap.get(type);
		if (list == null) {
			return null;
		}
		List<Shop> temp = new ArrayList<Shop>();
		for (Shop shop : list) {
			if (shop.isTimeExpression()) {
				temp.add(shop);
			}
		}
		return temp;
	}

	public Shop getShopByGoodId(int goodId, int saleType) {
		return shopGoodsidMap.get(goodId + "_" + saleType);
	}

	public void putShopTypeMap(Shop shop, Map<Integer, List<Shop>> shopTypeMap1) {
		List<Shop> list = shopTypeMap1.get(shop.getGoodsType());
		if (list == null) {
			list = new ArrayList<Shop>();
			shopTypeMap1.put(shop.getGoodsType(), list);
		}
		list.add(shop);
	}

	public void putshopGoodsidMap(Shop shop, Map<String, Shop> shopGoodsidMap1) {
		if (shop.getSaleType() == 2) {// 礼金类
			shopGoodsidMap1.put(shop.getGoodmodelId() + "_" + 2, shop);
		} else if (shop.getHotMark() == 2 || shop.getHotMark() == 3) {// 限购类
			shopGoodsidMap1.put(shop.getGoodmodelId() + "_" + 1, shop);
		} else {// 其他类
			shopGoodsidMap1.put(shop.getGoodmodelId() + "_" + 0, shop);
		}
	}

	public void putshopIdMap(Shop shop, Map<Integer, Shop> shopIdMap1) {
		shopIdMap1.put(shop.getId(), shop);
	}

	public Shop getShopInShopIdMapByShopId(int shopId) {
		return this.shopIdMap.get(shopId);
	}

	@SuppressWarnings("unchecked")
	public List<Shop> selectAllShopList() throws SQLException {
		List<Shop> list = dao.select();
		return list;
	}

	public void initShopDate() {
		try {
			List<Shop> list = selectAllShopList();
			if (list == null || list.size() == 0) {
				return;
			}
			Map<Integer, List<Shop>> shopTypeMap1 = new HashMap<Integer, List<Shop>>(); // 商城分类存储
			Map<String, Shop> shopGoodsidMap1 = new HashMap<String, Shop>(); // 商城
			Map<Integer, Shop> shopIdMap1 = new HashMap<Integer, Shop>(); // 商城物品id获取商城物品
			GoodmodelManager gmm = GoodmodelManager.getInstance();
			for (Shop shop : list) {
				if (shop != null) {
					if (shop.getGoodsOldPrice() == null) {
						shop.setGoodsOldPrice(shop.getGoodsPrice());
					}
					Goodmodel gm = gmm.get(shop.getGoodmodelId());
					if (gm == null) {
						continue;
					}
					if (shop.getIsdel() == 1) {
						continue;
					}
					shop.setGoodmodel(gm);
					putShopTypeMap(shop, shopTypeMap1);
					if (shop.getGoodsType() != 99) {
						putshopGoodsidMap(shop, shopGoodsidMap1);
						putshopIdMap(shop, shopIdMap1);
					}
				}
			}
			shopTypeMap = shopTypeMap1; // 商城分类存储
			shopGoodsidMap = shopGoodsidMap1; // 商城
			shopIdMap = shopIdMap1;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void reload() {
		initShopDate();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "shop";
	}
}
