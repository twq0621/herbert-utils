package net.snake.gamemodel.trade.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.goods.logic.container.IGirdContainer;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.trade.response.StallDetailInfo13012;
import net.snake.gamemodel.trade.response.StallSalelog13018;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13003, accessLimit = 100)
public class BuyStallGoodsProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.getMyTradeController().cancelTrade();
		int stallOwnerid = request.getInt();// 摊位主人
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (stallOwnerid == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 948));
			return;
		}
		short postion = request.getShort();// 索引id
		Hero stallowner = GameServer.vlineServerManager.getCharacterById(stallOwnerid);
		if (stallowner == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 949));
			return;
		}
		// 为了安全,只从容器里取
		IGirdContainer stall = stallowner.getCharacterGoodController().getStallGoodsContainer();
		CharacterGoods goods = stall.getGoodsByPostion(postion);
		if (goods == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 950));
			// 刷新摊位详情
			character.sendMsg(new StallDetailInfo13012(stallowner));
			return;
		}
		int goodId = request.getInt();
		if (goodId != goods.getGoodmodelId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1153));
			character.sendMsg(new StallDetailInfo13012(stallowner));
			return;
		}
		int oldCopper = request.getInt();
		int oldYuanbao = request.getInt();
		if (oldCopper < goods.getStallCopper() || oldYuanbao < goods.getStallIngot()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1151));
			character.sendMsg(new StallDetailInfo13012(stallowner));
			return;
		}
		if (character.getCopper() < goods.getStallCopper() || character.getAccount().getYuanbao() < goods.getStallIngot()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 951));
			return;
		}
		if (stallowner.getCopper() + goods.getStallCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1371));
			return;
		}
		IBag mybag = character.getCharacterGoodController().getBagGoodsContiner();
		if (!mybag.isHasSpaceForAddGoods(goods)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 952));
			return;
		}
		// 开始交易
		stall.deleteGoodsByPostion(postion);
		mybag.addGoods(goods);
		// 购买进入包裹效果
		character.sendMsg(new GoodToBadEffectMsg11170((byte) 6, goods));
		int tradCopper = goods.getStallCopper();
		if (tradCopper > 0) {
			CharacterPropertyManager.changeCopper(stallowner, tradCopper, CopperAction.ADD_DEAL);
			CharacterPropertyManager.changeCopper(character, -tradCopper, CopperAction.CONSUME);
		}
		if (goods.getStallIngot() > 0) {
			stallowner.getAccount().getAccountMonneyManager().changeTradeAddYuanbao(stallowner, goods.getStallIngot());
			character.getAccount().getAccountMonneyManager().changeTradeReduceYuanbao(character, goods.getStallIngot());
			// character.getAccount().getAccountMonneyLogManager().logStallTradeGoodYuanbaoLog(character, goods, stallowner);
		}
		String[] salemsg = { goods.getGoodModel().getNameI18n(), goods.getStallCopper() + "", goods.getStallIngot() + "" };
		stallowner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT + "," + TipMsg.MSGPOSITION_RIGHT, 1040, salemsg));

		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT + "," + TipMsg.MSGPOSITION_RIGHT, 1041, goods.getGoodModel().getNameI18n().split("_")[0]));
		// 刷新摊位详情
		character.sendMsg(new StallDetailInfo13012(stallowner));
		stallowner.sendMsg(new StallSalelog13018(character, stallowner, goods));
	}

}
