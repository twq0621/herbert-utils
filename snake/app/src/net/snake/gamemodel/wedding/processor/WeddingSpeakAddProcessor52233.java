/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.logic.CouplesSpeakController;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 耳语添加
 */

@MsgCodeAnn(msgcode = 52233)
public class WeddingSpeakAddProcessor52233 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		String content = request.getString();
		if (!character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			return;
		}
		CouplesSpeakController csc = character.getMyFriendManager().getRoleCouplesSpeakManager().getCouplesSpeakController();
		if (csc == null || csc.getCouplesSpeakSize() >= 30) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17520, 30 + ""));
			return;
		}
		if (content.length() > 30) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17522));
			return;
		}
		boolean b = BadWordsFilter.getInstance().hashBadWords(content);
		if (b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17521));
			return;
		}
		character.getMyFriendManager().getRoleCouplesSpeakManager().addCouplesSpeak(content);

	}

}
