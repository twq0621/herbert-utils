/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.response.WeddingMsg52372;
import net.snake.netio.message.RequestMsg;

/**
 * 发生 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 52371)
public class WeddingDongyixiaProcessor52371 extends CharacterMsgProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake.
	 * bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int wedderId = character.getMyFriendManager().getRoleWedingManager().getWedderId();
		if (wedderId < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
		}
		Hero wedder = GameServer.vlineServerManager.getCharacterById(wedderId);
		if (wedder == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17575));
			return;
		}
		wedder.sendMsg(new WeddingMsg52372());
	}
}
