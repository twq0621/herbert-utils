package net.snake.gamemodel.dujie.processor;

import net.snake.gamemodel.dujie.response.DujieRoushenValResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 查看当前的肉身
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class RoushenStatPro extends MsgProcessor implements IThreadProcessor{
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero=session.getCurrentCharacter(Hero.class);
		int roushen=hero.getLiantiController().getLiantiJingjieId();
		DujieRoushenValResp resp = new DujieRoushenValResp(roushen);
		session.sendMsg(resp);
		
	}
}
