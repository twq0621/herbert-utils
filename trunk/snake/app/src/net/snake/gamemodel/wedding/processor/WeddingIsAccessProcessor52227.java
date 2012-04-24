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
 * 是否接受对方求婚
 * 
 */

@MsgCodeAnn(msgcode = 52227)
public class WeddingIsAccessProcessor52227 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int weddId = request.getInt();
		int ringId = request.getInt();
		byte type = request.getByte();
		if (weddId == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17530));
			return;
		}
		if (!character.getMyFriendManager().getRoleWedingManager().isHaveApplyWedding(weddId)) {
			return;
		}
		character.getMyFriendManager().getRoleWedingManager().clearApplyWeddinglog(weddId);
		Hero wedder = GameServer.vlineServerManager.getCharacterById(weddId);
		if (type == 0) {
			if (wedder != null) {
				wedder.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17511, character.getViewName()));
				return;
			}
		} else {
			if (wedder == null) {
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
			int count = wedder.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(ringId);
			if (count < 1) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17517, wedder.getViewName()));
				return;
			}

			character.getMyFriendManager().getRoleWedingManager().finishiWedding(wedder, wr);
		}
	}

}
