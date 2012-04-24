package net.snake.gamemodel.team.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.Team;
import net.snake.netio.ServerResponse;


/**
 * 队伍进入场景头像显示列表队伍信息（数据全）
 * 
 * 
 */
public class TeamInfoMsg10208 extends ServerResponse {

	public TeamInfoMsg10208(Team t) {
		this.setMsgCode(10208);
		try {
			if (t == null) {
				this.writeInt(-1);
				this.writeUTF("");
				this.writeByte(0);
				return;
			}
			this.writeInt(t.getTeamId());
			this.writeUTF(t.getTeamDeclare());
			this.writeByte(t.getTeamPopulation());
			List<Hero> cs = t.getCharacterPlayers();
			for (Hero c : cs) {

				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());
				this.writeByte(c.getHeadimg());
				this.writeUTF(c.getMyFactionManager().getFactionName());
				this.writeInt(c.getNowHp());
				this.writeInt(c.getPropertyAdditionController().getExtraMaxHp());
				this.writeInt(c.getNowMp());
				this.writeInt(c.getPropertyAdditionController().getExtraMaxMp());
				this.writeInt(c.getScene());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
