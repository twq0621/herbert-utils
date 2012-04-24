package net.snake.gamemodel.common.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 消息号 为1
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 40301, accessLimit = 5000)
public class HeartListenerProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (character != null) {
			character.lastHeatBeat = System.currentTimeMillis();
		}

	}
}
