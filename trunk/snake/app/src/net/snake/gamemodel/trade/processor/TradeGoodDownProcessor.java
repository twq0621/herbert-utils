package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeGoodMsg10842;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10847 A|B-S: “取出交易物品“ 取出物包裹位置（short），取出物品交易位置（short）
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10847, accessLimit = 200)
public class TradeGoodDownProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		short position = request.getShort();
		short tradeIndex = request.getShort();
		IMyTradeController mtc = character.getMyTradeController();
		if (!mtc.isGoodOperate()) {
			return;
		}
		CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(position);
		if (cg == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 931));
			return;
		}
		if (cg.getTradeIndex() < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 932));
			return;
		}
		if (tradeIndex != cg.getTradeIndex()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 933));
			return;
		}
		mtc.removeGoodsInTradeMap(cg.getTradeIndex());
		cg.setTradeIndex(-1);
		TradeGoodMsg10842 goodmsg = new TradeGoodMsg10842(mtc);
		character.sendMsg(goodmsg);
		mtc.getTradeCharacter().sendMsg(goodmsg);
	}

}
