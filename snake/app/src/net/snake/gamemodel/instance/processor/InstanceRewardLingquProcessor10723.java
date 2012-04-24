package net.snake.gamemodel.instance.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 领取副本通关奖励
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 10723)
public class InstanceRewardLingquProcessor10723 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.getMyCharacterInstanceManager().lingquInstanceReward();
	}

}
