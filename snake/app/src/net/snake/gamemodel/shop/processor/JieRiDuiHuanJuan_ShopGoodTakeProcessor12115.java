package net.snake.gamemodel.shop.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.response.JieRiDuiHuanJuan_ShopGoodTakeMsg12116;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 
 * 节日兑换券
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 12115, accessLimit = 100)
public class JieRiDuiHuanJuan_ShopGoodTakeProcessor12115 extends CharacterMsgProcessor {
	public static int YUANBAO_TYPE = 1;
	public static int duihuan_num = 10;

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int shopId = request.getInt();

		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(46)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1085));
			return;
		}

		Shop shop = character.getMyShopManger().getShopByShopId(shopId);

		if (shop == null) {
			character.sendMsg(new JieRiDuiHuanJuan_ShopGoodTakeMsg12116(shopId, 1121));
			return;
		}
		if (!shop.isTimeExpression()) {
			character.sendMsg(new JieRiDuiHuanJuan_ShopGoodTakeMsg12116(shopId, 20127));
			return;
		}

		if (!character.getCharacterGoodController().isEnoughGoods(GoodItemId.JieRiDiKouJuan, 1)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20128));
			return;
		}
		Goodmodel gm = shop.getGoodmodel();
		CharacterGoods cg = CharacterGoods.createCharacterGoods(duihuan_num, gm, 0, 0);
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(cg)) {
			character.sendMsg(new JieRiDuiHuanJuan_ShopGoodTakeMsg12116(shopId, 1123));
			return;
		}

		cg.setBind(CommonUseNumber.byte1);

		character.getCharacterGoodController().addGoodsToBag(cg);
		character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.JieRiDiKouJuan, 1);
		character.sendMsg(new JieRiDuiHuanJuan_ShopGoodTakeMsg12116(shopId));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20129, gm.getNameI18n()));
	}

}
