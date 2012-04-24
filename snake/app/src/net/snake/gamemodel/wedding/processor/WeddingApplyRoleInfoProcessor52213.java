package net.snake.gamemodel.wedding.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.response.WeddingMsg52214;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 请求 向求婚者发送求婚请求 52213
 */

@MsgCodeAnn(msgcode = 52213)
public class WeddingApplyRoleInfoProcessor52213 extends CharacterMsgProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake
	 * .bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int weddId = request.getInt();
		if (weddId == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17530));
			return;
		}
		Hero wedder = GameServer.vlineServerManager.getCharacterById(weddId);
		if (wedder == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17501));
			return;
		}
		int line = wedder.getVlineserver().getLineid();
		if (line != character.getVlineserver().getLineid()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17502, line + ""));
			return;
		}
		if (character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17515));
			return;
		}
		if (wedder.getMyFriendManager().getRoleWedingManager().isWedding()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17532));
			return;
		}
		if (wedder.isMale() == character.isMale()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17531));
			return;
		}
		character.sendMsg(new WeddingMsg52214(wedder));
	}

}
