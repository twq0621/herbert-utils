package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.response.TeamSysSetUpMsg10194;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 组队参数设置处理器 消息号10193
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10193, accessLimit = 500)
public class TeamSysSetupProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		byte type = request.getByte();
		byte value = request.getByte();
		if (value > 1) {
			value = 1;
		}
		if (value < 0) {
			value = 0;
		}
		Hero character = session.getCurrentCharacter(Hero.class);
		if (type == 1) {
			character.getMyTeamManager().setAccessTeam(value);
			session.sendMsg(new TeamSysSetUpMsg10194(type, value));
			return;
		}
		if (type == 2) {
			character.getMyTeamManager().setAccessApplyTeam(value);
			session.sendMsg(new TeamSysSetUpMsg10194(type, value));
			return;
		}
		if (type == 3) {
			character.getMyTeamManager().setTeamUI(value);
			session.sendMsg(new TeamSysSetUpMsg10194(type, value));
			return;
		}
		session.sendMsg(new TeamSysSetUpMsg10194(1110));
	}

}
