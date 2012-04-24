package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.logic.CharacterGoodsDCController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 50905, accessLimit = 500)
public class GetAwardGoodsDCProcs50905 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int goodsDCId = request.getInt();
		CharacterGoodsDCController characterGoodsDCController = character.getCharacterGoodsDCController();
		characterGoodsDCController.getAward(goodsDCId);
	}
}
