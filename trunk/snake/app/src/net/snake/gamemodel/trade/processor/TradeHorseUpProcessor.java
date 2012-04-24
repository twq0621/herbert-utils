package net.snake.gamemodel.trade.processor;

import java.util.Collection;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeHorseMsg10846;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10845 A|B-S: “马放入交易栏“ 放入马的模型id，放入马的id
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10845, accessLimit = 200)
public class TradeHorseUpProcessor extends CharacterMsgProcessor {

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
		if (horse.getHorseModel().getBind() == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 940));
			return;
		}
		if (horse.getCharacterHorse().getStatus() != HorseStateConsts.REST) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 941));
			return;
		}
		if (horse.getTradeIndex() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 942));
			return;
		}
		Collection<CharacterGoods> collection = horse.getGoodsContainer().getGoodsList();
		if (collection.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17001));
			return;
		}
		Horse tradeHorse = mtc.getHorse();
		if (tradeHorse != null) {
			tradeHorse.setTradeIndex(-1);
		}
		horse.setTradeIndex(1);
		mtc.setHorse(horse);
		TradeHorseMsg10846 horseMsg = new TradeHorseMsg10846(mtc);
		character.sendMsg(horseMsg);
		mtc.getTradeCharacter().sendMsg(horseMsg);
	}

}
