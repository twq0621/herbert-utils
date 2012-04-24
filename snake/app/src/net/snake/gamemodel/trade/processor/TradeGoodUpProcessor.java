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
 * A|B-S: “添加交易物品“ 10841
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10841, accessLimit = 200)
public class TradeGoodUpProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short position = request.getShort();
		short tradeIndex = request.getShort();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		IMyTradeController mtc = character.getMyTradeController();
		if (!mtc.isGoodOperate()) {
			return;
		}
		if (tradeIndex == 0) {
			tradeIndex = (short) mtc.findTradIndex();
		}
		CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(position);
		if (cg == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 934));
			return;
		}
		if (cg.getBind() == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 935));
			return;
		}
		if (cg.getTradeIndex() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 936));
			return;
		}
		CharacterGoods tradeGoods = mtc.getCharacterGoodsByTradeIndex(tradeIndex);
		if (tradeGoods != null) {
			tradeGoods.setTradeIndex(-1);
		}

		mtc.putGoodsToTradeMap(tradeIndex, cg);
		TradeGoodMsg10842 goodmsg = new TradeGoodMsg10842(mtc);
		character.sendMsg(goodmsg);
		mtc.getTradeCharacter().sendMsg(goodmsg);
		return;

	}

}
