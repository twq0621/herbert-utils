package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.trade.response.TradeMsg10840;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10833 取消交易
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10833, accessLimit = 200)
public class TradeCancelProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (!character.getMyTradeController().isTrading()) {
			return;
		}
		Hero his = character.getMyTradeController().getTradeCharacter();
		character.getMyTradeController().cancelTrade();
		his.sendMsg(new TradeMsg10840(13512));

	}

}
