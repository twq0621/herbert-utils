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
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 求婚申请 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 52221)
public class WeddingApplyProcessor52221 extends CharacterMsgProcessor {

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
		int id = request.getInt();
		int ringId = request.getInt();
		if (id == 0) {
			return;
		}
		if (id == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17530));
			return;
		}
		if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(ringId) < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17506));
			return;
		}
		Hero wedder = GameServer.vlineServerManager.getCharacterById(id);
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
		WeddingRing wr = WeddingRingManager.getInstance().getWeddingRingById(ringId);
		if (wr == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17509));
			return;
		}
		if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(ringId) < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17533));
			return;
		}
		wedder.getMyFriendManager().getRoleWedingManager().onRequestWedding(character, wr);

	}

}
