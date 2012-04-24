package net.snake.gamemodel.skill.bow.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 */
@MsgCodeAnn(msgcode = 53037, accessLimit = 100)
public class ArrowPut53037 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		short position = request.getShort();
		CharacterGoods arrow = character.getCharacterGoodController().getGoodsByPositon(position);
		character.getBowController().addToArrow(arrow, type);
	}
}
