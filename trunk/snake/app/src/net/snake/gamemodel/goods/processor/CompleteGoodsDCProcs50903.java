package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.logic.CharacterGoodsDCController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 完成收集时提交
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50903, accessLimit = 500)
public class CompleteGoodsDCProcs50903 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int goodsDCId = request.getInt();
		CharacterGoodsDCController characterGoodsDCController = character.getCharacterGoodsDCController();
		characterGoodsDCController.gcGoodsDC(goodsDCId);
	}
}
