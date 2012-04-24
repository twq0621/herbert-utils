package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.message.RequestMsg;

/**
 * 组队玩家申请加入队伍处理 消息号 10187
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10187, accessLimit = 500)
public class ApllyInTeamProcess extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		Integer teamId = request.getInt();//
		Team leaderTeam = character.getVlineserver().getTeamManager().getTeam(teamId);
		character.getMyTeamManager().applyTeam(leaderTeam);
	}

}
