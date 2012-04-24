package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 队长踢掉玩家 消息：10217
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10217, accessLimit = 500)
public class TakeOfferCharacterTeamProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero character = session.getCurrentCharacter(Hero.class);
		character.getMyTeamManager().kick(request.getInt());
	}

}
