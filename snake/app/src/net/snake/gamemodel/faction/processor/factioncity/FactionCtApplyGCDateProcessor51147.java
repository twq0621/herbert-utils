/**
 * 
 */
package net.snake.gamemodel.faction.processor.factioncity;

import java.util.Date;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.response.factioncity.FactionCity51148;
import net.snake.gamemodel.fight.bean.GongchengDate;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 
 */

@MsgCodeAnn(msgcode = 51147)
public class FactionCtApplyGCDateProcessor51147 extends CharacterMsgProcessor {

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
		Date date = GongchengDate.applyDateToGongchengDate(new Date());
		character.sendMsg(new FactionCity51148(date.getTime()));

	}

}
