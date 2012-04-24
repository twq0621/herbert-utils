package net.snake.gamemodel.npc.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * npc 传送处理 20109
 * 
 * @author dev
 * 
 */
@MsgCodeAnn(msgcode = 20109, accessLimit = 500)
public class NpcTransPortProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// Short npcid = request.getShort();// npcid (以后验证使用)
		// int transportId = request.getInt();// 传送点id

	}

}
