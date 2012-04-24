package net.snake.gamemodel.shop.processor;

import java.util.Set;

import net.snake.GameServer;
import net.snake.ai.util.ArithmeticUtils;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.bean.TaskShoppingGoods;
import net.snake.gamemodel.shop.response.ShopGoodBuyMsg12104;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 12103 购买商城物品
 * 
 * @author serv_dev
 * 
 */

@MsgCodeAnn(msgcode = 12103, accessLimit = 500)
public class ShopGoodBuyProcessor extends CharacterMsgProcessor {
	public static int YUANBAO_TYPE = 1;

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int shopId = request.getInt();
		int goodNum = request.getShort();
		if (goodNum < 1) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 1120));
			return;
		}
		if (goodNum > 2000) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 16505, 2000 + ""));
			return;
		}
		Shop shop = character.getMyShopManger().getShopByShopId(shopId);
		if (shop == null) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 1121));
			return;
		}
		if (!shop.isTimeExpression()) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 16504));
			return;
		}
		Goodmodel gm = shop.getGoodmodel();
		CharacterGoods cg = CharacterGoods.createCharacterGoods(goodNum, gm, 0, 0);
		if (shop.getIsBind() == 1) {
			cg.setBind(CommonUseNumber.byte1);
		}
		if (shop.getLastDate() != null && shop.getLastDate().length() > 0) {
			cg.setLastDate(ArithmeticUtils.stringToDate(shop.getLastDate()));
		}
		Account account = character.getAccount();
		int reduceValue = shop.getGoodsPrice() * goodNum;
		if (reduceValue < 1) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 16506));
			return;
		}
		if (shop.getSaleType() == ShopGoodBuyProcessor.YUANBAO_TYPE) {
			if (account.getYuanbao() < reduceValue) {
				character.sendMsg(new ShopGoodBuyMsg12104(shopId, 507));
				return;
			}
		} else {
			if (character.getJiaozi() < reduceValue) {
				character.sendMsg(new ShopGoodBuyMsg12104(shopId, 1122));
				return;
			}
			cg.setBind(CommonUseNumber.byte1);
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(cg)) {
			character.sendMsg(new ShopGoodBuyMsg12104(shopId, 1123));
			return;
		}

		if (shop.getSaleType() == ShopGoodBuyProcessor.YUANBAO_TYPE) {
			if (shop.getLimiteCount() > 0) {
				if (shop.getGoodmodelId() == 7313 && !character.getMyCharacterVipManger().isVipYueka()) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19030));
					return;
				}
				int count = character.getCount(shop.getGoodmodelId());
				if (shop.getLimiteCount() <= count) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19021, shop.getLimiteCount() + "", count + "", 0 + ""));
					return;
				}
				if (shop.getLimiteCount() < goodNum + count) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19029, (shop.getLimiteCount() - count) + ""));
					return;
				}
			}
			// int oldYuanbao = account.getYuanbao();
			boolean b = account.getAccountMonneyManager().consumYuanbao(character, reduceValue);
			if (b) {
				cg.setBuyType(CommonUseNumber.byte1);
				cg.setBuyPrice(shop.getGoodsPrice());
				character.getCharacterGoodController().addGoodsToBag(cg);
				// account.getAccountMonneyLogManager().logConSumYuanbaoShopGoodLog(character, shop, oldYuanbao, goodNum, reduceValue);// TODO 是否要去掉
				if (shop.getLimiteCount() > 0) {
					character.getCharacterCountController().count(shop.getGoodmodelId(), goodNum);
				}
				character.getMyCharacterAchieveCountManger().getCharacterChongzhiCount().characterConsumCount(reduceValue);
				Set<Integer> needShopGoodSet = TaskManager.getInstance().getNeedShopGoods();
				if (needShopGoodSet.contains(cg.getGoodmodelId())) {
					TaskShoppingGoods taskShoppingGoods = character.getTaskController().getTaskShoppingGoods(cg.getGoodmodelId());
					if (taskShoppingGoods == null) {
						taskShoppingGoods = TaskShoppingGoods.createTaskShoppingGoods(character.getId(), cg.getGoodmodelId(), goodNum);
					} else {
						taskShoppingGoods.setNum(taskShoppingGoods.getNum() + goodNum);
					}
					character.getTaskController().addTaskShoppingGoods(taskShoppingGoods);
				}
				// 购买进入包裹效果
				character.sendMsg(new GoodToBadEffectMsg11170((byte) 7, cg));
				// 发送日志
				GameServer.dataLogManager.logRMBBuyItem("1", cg.getGoodmodelId(), shop.getGoodsPrice(), goodNum, character.getLogUid(), character.getHeroInfo());
			} else {
				character.sendMsg(new ShopGoodBuyMsg12104(shopId, 507));
			}
		} else {
			character.getCharacterGoodController().addGoodsToBag(cg);
			CharacterPropertyManager.changeLijin(character, -shop.getGoodsPrice() * goodNum);
			// 购买进入包裹效果
			character.sendMsg(new GoodToBadEffectMsg11170((byte) 7, cg));
			// 发送日志
			// GameServer.dataLogManager.logRMBBuyItem("1", cg.getGoodmodelId(), shop.getGoodsPrice(), goodNum, character.getId(), character.getHeroInfo());
		}
		character.sendMsg(new ShopGoodBuyMsg12104(shopId));
	}
}
