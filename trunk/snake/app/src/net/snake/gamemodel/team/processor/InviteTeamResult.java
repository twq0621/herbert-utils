package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.response.TeamInviteAcessorMsg10186;
import net.snake.gamemodel.team.response.TeamInvitorMsg10182;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 处理被邀请者是否同意队长邀请起加入 其组队 处理消息10185
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10185, accessLimit = 500)
public class InviteTeamResult extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		byte type = request.getByte();
		Integer hisId = request.getInt();
		Hero character = session.getCurrentCharacter(Hero.class);
		Hero hisCharacter = character.getVlineserver().getOnlineManager().getByID(hisId);
		if (hisCharacter == null) {
			session.sendMsg(new TeamInviteAcessorMsg10186(1115));
			return;
		}
		if (hisId == character.getId()) {
			session.sendMsg(new TeamInviteAcessorMsg10186(1116));
		}
		if (type == 0) {
			if (hisCharacter.getMyTeamManager().isTeamLeader() || !hisCharacter.getMyTeamManager().isTeam()) {
				hisCharacter.sendMsg(new TeamInvitorMsg10182(1142, character.getViewName()));
			}
			character.getMyTeamManager().removeIniviteTeamTime(hisId);
		} else {
			character.getMyTeamManager().accessInviteTeam(hisCharacter);
		}
	}

}
