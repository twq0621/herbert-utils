/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.response.WeddingSpeakMsg52304;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 请求耳语列表
 */

@MsgCodeAnn(msgcode = 52303)
public class WeddingSpeakListProcessor52303 extends CharacterMsgProcessor implements IThreadProcessor {

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
			return;
		}
		character.sendMsg(new WeddingSpeakMsg52304(character));
	}

}