package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.response.TeamMapInfoMsg10246;
import net.snake.gamemodel.team.response.TeamUIInfoMsg10212;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 10211 玩家请求我的队伍面板数据
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10211)
public class MyTeamListProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {

		Hero character = session.getCurrentCharacter(Hero.class);
		if (character.getMyTeamManager().isTeam()) {
			session.sendMsg(new TeamMapInfoMsg10246(character.getMyTeamManager().getMyTeam(), character));
		}
		session.sendMsg(new TeamUIInfoMsg10212(character.getMyTeamManager()));
	}

}
