package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeHorseMsg10846;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10851 A|B-S: “取出交易的坐骑“ 取出马的id
 * 
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10851, accessLimit = 200)
public class TradeHorseDownProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int hId = request.getInt();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		IMyTradeController mtc = character.getMyTradeController();
		if (!mtc.isGoodOperate()) {
			return;
		}
		Horse horse = character.getCharacterHorseController().getBagHorseContainer().getHorseByID(hId);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 937));
			return;
		}
		Horse tradeHorse = mtc.getHorse();
		if (horse.getTradeIndex() < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 938));
			return;
		}

		if (tradeHorse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 939));
		}
		if (tradeHorse.getId() != hId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 938));
			return;
		}
		tradeHorse.setTradeIndex(-1);
		mtc.setHorse(null);
		TradeHorseMsg10846 horseMsg = new TradeHorseMsg10846(mtc);
		character.sendMsg(horseMsg);
		mtc.getTradeCharacter().sendMsg(horseMsg);
	}

}
