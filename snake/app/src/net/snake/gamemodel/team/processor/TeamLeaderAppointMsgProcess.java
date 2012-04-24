package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 转发任命队长消息给被任命者 10201
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10201, accessLimit = 500)
public class TeamLeaderAppointMsgProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero character = session.getCurrentCharacter(Hero.class);
		int newId = request.getInt();
		character.getMyTeamManager().sendChangeLeaderMsg(newId);
	}

}
