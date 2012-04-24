package net.snake.gamemodel.shop.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.response.ShopLinshiDazheInfoMsg12110;

/**
 * 角色商城物品管理 （主要针对特殊道具开启的临时打折物品） Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class MyShopManger {
	Hero character = null;
	public static int dazheType = 99;
	public static int dazheCount = 15;
	private Map<Integer, Shop> daojueMap = new HashMap<Integer, Shop>();
	private ShopDazheTimer shopDazhe;

	public void destroy() {
		if (daojueMap != null) {
			daojueMap.clear();
			shopDazhe = null;
		}
	}

	public boolean isUseDazheKa() {
		if (daojueMap.size() < 1) {
			return true;
		}
		return false;
	}

	public MyShopManger(Hero character) {
		this.character = character;
		shopDazhe = new ShopDazheTimer(character);
	}

	/**
	 * 根据商城物品id 获得商城物品
	 * 
	 * @param shopId
	 * @return
	 */
	public Shop getShopByShopId(int shopId) {
		if (daojueMap == null || daojueMap.size() == 0) {
			return ShopManager.getInstance().getShopInShopIdMapByShopId(shopId);
		}
		Shop shop = daojueMap.get(shopId);
		if (shop != null) {
			return shop;
		}
		return ShopManager.getInstance().getShopInShopIdMapByShopId(shopId);
	}

	public void clearDazheShopInfo() {
		daojueMap.clear();
	}

	public void breakDazheShop() {
		shopDazhe.shutdown();
		clearDazheShopInfo();
	}

	public void endLingshiDazhe() {
		clearDazheShopInfo();
		character.sendMsg(new ShopLinshiDazheInfoMsg12110());
	}

	public boolean useLinShiDazheKa(CharacterGoods cg) {
		if (!isUseDazheKa()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16501));
			return false;
		}
		List<Shop> list = ShopManager.getInstance().getListByShopType(dazheType);
		if (list.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16502));
			return false;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cg.getPosition(), 1);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16503));
			return false;
		}
		for (Shop shop : list) {
			daojueMap.put(shop.getId(), shop);
		}
		Integer time = cg.getGoodModel().getDrugValue();
		if (time == null) {
			time = 600;
		}
		shopDazhe.start(time);
		return true;
	}

	public List<Shop> getDazheList() {
		List<Shop> list = new ArrayList<Shop>();
		list.addAll(daojueMap.values());
		return list;
	}
}
