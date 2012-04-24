/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.logic.CouplesSpeakController;
import net.snake.gamemodel.wedding.response.WeddingSpeakMsg52232;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 检查是否可以新增耳语
 */

@MsgCodeAnn(msgcode = 52231)
public class WeddingSpeakCheckProcessor52231 extends CharacterMsgProcessor implements IThreadProcessor {

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
		if (!character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17556));
			return;
		}
		CouplesSpeakController csc = character.getMyFriendManager().getRoleCouplesSpeakManager().getCouplesSpeakController();
		if (csc == null || csc.getCouplesSpeakSize() >= 30) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17520, 30 + ""));
			return;
		}
		character.sendMsg(new WeddingSpeakMsg52232());

	}

}
