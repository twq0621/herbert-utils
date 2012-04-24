package net.snake.gamemodel.guide.processor;

import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 跳过新手导航 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class SkipNoviceNavigationProcessor50175 extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		String novicenavigation = request.getString();
		character.setSkipNoviceNavigation(novicenavigation);
		// character.sendMsg(new SkipNoviceNavigationMsg50176(character.getSkipNoviceNavigation() == null ?"" : character.getSkipNoviceNavigation()));
	}
}
