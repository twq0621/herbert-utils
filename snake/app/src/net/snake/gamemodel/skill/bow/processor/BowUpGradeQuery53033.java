package net.snake.gamemodel.skill.bow.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.bow.logic.BowController;
import net.snake.gamemodel.skill.bow.response.BowUpGradeQueryResult53034;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 */
@MsgCodeAnn(msgcode = 53033, accessLimit = 100)
public class BowUpGradeQuery53033 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		BowController bowController = character.getBowController();
		Bow bow = bowController.getBow();
		if (bow == null) {
			// character.sendMsg(new TipMsg("您没有弓可升阶"));
			return;
		}
		character.sendMsg(new BowUpGradeQueryResult53034(bow, character));
	}

}
