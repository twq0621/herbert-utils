/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.gamemodel.wedding.response.WeddingRingBuyMsg52220;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 购买玉佩
 */

@MsgCodeAnn(msgcode = 52219)
public class WeddingRingGoodBuyProcessor52219 extends CharacterMsgProcessor {

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
		int ringId = request.getInt();
		WeddingRing wr = WeddingRingManager.getInstance().getWeddingRingById(ringId);
		if (wr == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17503));
			return;
		}
		if (character.getCopper() < wr.getSaleCopper()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17504));
			return;
		}
		CharacterGoods cg = CharacterGoods.createCharacterGoods(1, wr.getRingId(), 0);
		if (cg == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17503));
			return;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(cg)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17503));
			return;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(cg);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17503));
			return;
		}
		CharacterPropertyManager.changeCopper(character, -wr.getSaleCopper(), CopperAction.CONSUME);
		character.sendMsg(new WeddingRingBuyMsg52220());
	}

}
