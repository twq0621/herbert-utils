package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.response.TeamInfoMsg10210;
import net.snake.netio.message.RequestMsg;

/**
 * 10209 根据队伍id 获取组队信息
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10209)
public class TeamInfoByTeamIdProcess extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int teamId = request.getInt();
		Team t = character.getVlineserver().getTeamManager().getTeam(teamId);
		character.sendMsg(new TeamInfoMsg10210(t));
	}

}
