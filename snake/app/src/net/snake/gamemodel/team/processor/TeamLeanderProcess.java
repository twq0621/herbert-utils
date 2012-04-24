package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.response.TeamLeaderApllyMsg10192;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 队长是否同意玩家加入队伍处理 消息号 10191
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10191, accessLimit = 500)
public class TeamLeanderProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		byte type = request.getByte();// 队长是否同意 1同意 0不同意
		int hisId = request.getInt();// 申请者
		Hero character = session.getCurrentCharacter(Hero.class);
		if (!character.getMyTeamManager().isTeamLeader()) {
			character.sendMsg(new TeamLeaderApllyMsg10192(1111));
			return;
		}
		Team myTeam = character.getMyTeamManager().getMyTeam();
		if (myTeam.getTeamPopulation() >= 7) {
			character.sendMsg(new TeamLeaderApllyMsg10192(1112));
			return;
		}
		Hero hisCharacter = character.getVlineserver().getOnlineManager().getByID(hisId);
		if (hisCharacter == null || hisCharacter.getPlayer() == null) {
			if (type != 0) {
				character.sendMsg(new TeamLeaderApllyMsg10192(1113));
			}
			return;
		}
		if (hisCharacter.getMyTeamManager().isTeam()) {
			character.sendMsg(new TeamLeaderApllyMsg10192(1114));
			return;
		}
		if (type == 0) {
			hisCharacter.sendMsg(SimpleResponse.failReasonMsg(10188, 40026));
			character.getMyTeamManager().removeIniviteTeamTime(hisId);
			return;
		}
		character.getMyTeamManager().accessApplyTeam(hisCharacter);
	}

}
