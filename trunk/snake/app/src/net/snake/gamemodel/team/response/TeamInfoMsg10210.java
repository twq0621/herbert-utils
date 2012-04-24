package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;


/**
 * 通过队伍id获取队伍信息
 * 
 */
public class TeamInfoMsg10210 extends ServerResponse {

	public TeamInfoMsg10210(Team t) {
		this.setMsgCode(10210);
		try {
			if (t == null) {
				this.writeInt(-1);
				this.writeByte(0);
				return;
			}
			this.writeInt(t.getTeamId());
			List<Hero> cs = t.getCharacterPlayers();
			this.writeByte(cs.size());
			for (Hero c : cs) {

				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());

			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
