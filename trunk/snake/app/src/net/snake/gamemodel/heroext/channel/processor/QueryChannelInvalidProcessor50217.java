package net.snake.gamemodel.heroext.channel.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50218;
import net.snake.netio.message.RequestMsg;


/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 50217,accessLimit=1000)
public class QueryChannelInvalidProcessor50217 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		character.sendMsg(new ChannelResponse50218(character));
	}
}
