package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.logic.CharacterGoodsDCController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 点击NPC初始化收集数据
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50901, accessLimit = 200)
public class InitGoodsDCProcs50901 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		CharacterGoodsDCController characterGoodsDCController = character.getCharacterGoodsDCController();
		characterGoodsDCController.queryGoodsDC();
	}
}
