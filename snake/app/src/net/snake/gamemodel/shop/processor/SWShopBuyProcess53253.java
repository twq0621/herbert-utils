package net.snake.gamemodel.shop.processor;

import java.util.Date;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.shop.bean.ShopShengwang;
import net.snake.gamemodel.shop.persistence.SwShopManager;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 53253)
public class SWShopBuyProcess53253 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 购买商城id(int),购买数量（short）;
		int id = request.getInt();
		short num = request.getShort();
		if (num < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1120));
			// character.sendMsg(new
			// SWShopBuyResult53254(shopEntry.getId(),false));
			return;
		}
		if (num > 2000) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 16505, 2000 + ""));
			// character.sendMsg(new
			// SWShopBuyResult53254(shopEntry.getId(),false));
			return;
		}
		ShopShengwang shopEntry = SwShopManager.getInstance().getShopItemById(id);
		if (shopEntry == null || shopEntry.getIsEnable() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1121));
			character.sendMsg(new SWShopBuyResult53254(shopEntry.getId(), false));
			return;
		}
		if (shopEntry != null && num > 0) {
			if (shopEntry.getSaleType() == 1 && !checkCZSW(character, shopEntry.getGoodsPrice() * num)) {
				// 城战声望不够
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 917));
				character.sendMsg(new SWShopBuyResult53254(shopEntry.getId(), false));
				return;
			}
			if (shopEntry.getSaleType() == 2 && !checkLJSW(character, shopEntry.getGoodsPrice() * num)) {
				// 论剑声望不够
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 917));
				character.sendMsg(new SWShopBuyResult53254(shopEntry.getId(), false));
				return;
			}
			CharacterGoods goods = CharacterGoods.createCharacterGood(shopEntry.getGoodmodelId(), num, shopEntry.getIsBind().byteValue());
			Integer lastDate = shopEntry.getLastDate() == null ? 0 : shopEntry.getLastDate().intValue();
			if (lastDate != 0) {
				goods.setLastDate(new Date(System.currentTimeMillis() + lastDate * 1000));
			}
			// goods.setStrengthenDesc(shopEntry.getStrengthenDesc());
			goods.setAdditionDesc(shopEntry.getAdditionDesc());
			if (character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(goods)) {
				// character.getPropertyAdditionController()
				if (shopEntry.getSaleType() == 1) {
					CharacterPropertyManager.changeGongChengShengWang(character, -shopEntry.getGoodsPrice() * num, false);
				}
				if (shopEntry.getSaleType() == 2) {
					CharacterPropertyManager.changeLunJianShengWang(character, -shopEntry.getGoodsPrice() * num);
				}
				// 扣除声望
				// 添加物品
				character.getCharacterGoodController().addGoodsToBag(goods);
				character.sendMsg(new SWShopBuyResult53254(shopEntry.getId(), true));
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20027));
				character.sendMsg(new SWShopBuyResult53254(shopEntry.getId(), false));
			}
		}
	}

	private boolean checkLJSW(Hero character, Integer goodsPrice) {
		return character.getLunjianShengWang() >= goodsPrice;
	}

	private boolean checkCZSW(Hero character, Integer goodsPrice) {
		return character.getChengzhanShengWang() >= goodsPrice;

	}
}

class SWShopBuyResult53254 extends ServerResponse {
	public SWShopBuyResult53254(int id, boolean tag) {
	}
}
