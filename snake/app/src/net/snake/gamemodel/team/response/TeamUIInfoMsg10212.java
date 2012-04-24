package net.snake.gamemodel.team.response;


import net.snake.gamemodel.team.logic.MyTeamManager;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;


public class TeamUIInfoMsg10212 extends ServerResponse {

	public TeamUIInfoMsg10212(MyTeamManager teamManager) {
		this.setMsgCode(10212);
		Team t = teamManager.getMyTeam();
		try {
			if (t == null) {
				this.writeInt(-1);
			}else{
			    this.writeInt(t.getTeamId());
			}
			this.writeUTF(teamManager.getTeamDeclare());
			this.writeByte(teamManager.getAccessTeam());// 是否接受比人邀请
			this.writeByte(teamManager.getAccessApplyTeam());
			this.writeByte(teamManager.getTeamUI());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
