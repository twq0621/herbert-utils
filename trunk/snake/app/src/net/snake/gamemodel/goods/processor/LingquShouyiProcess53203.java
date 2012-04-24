/**
 * 
 */
package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 领取跨服收益
 */

@MsgCodeAnn(msgcode = 53203)
public class LingquShouyiProcess53203 extends CharacterMsgProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake.
	 * bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		AcrossServerDate date = AcrossServerDateManager.getInstance().getList().get(0);
		if (date != null && date.isTimeExpression()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1308));
			return;
		}
		if (!character.getMyCharacterAcrossIncomeManager().isInit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1301));
			return;
		}

		character.getMyCharacterAcrossIncomeManager().lingquShouyi();

	}

}
