package net.snake.gamemodel.guide.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 50653
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50653)
public class NewcomeLeaderCheckSavaProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte type = request.getByte();
		byte isfinish = request.getByte();
		byte giudeNum = request.getByte();
		byte count = request.getByte();
		character.getMyNewcomeManager().addOrUpdateNewGuide(type, giudeNum, isfinish, count);

	}
}
