package net.snake.gamemodel.dujie.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 领取护法收益
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode=60333)
public class DujieRcvIncome extends MsgProcessor implements IThreadProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		
		if (hero.getDujieCtrlor().canReceive()) {
			hero.getDujieCtrlor().receiveGuardIncome();
		}

	}
}
