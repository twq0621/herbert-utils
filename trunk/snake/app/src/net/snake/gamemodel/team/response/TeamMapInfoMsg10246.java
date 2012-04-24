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
public class TeamMapInfoMsg10246 extends ServerResponse {

	public TeamMapInfoMsg10246(Team t,Hero character) {
		this.setMsgCode(10246);
		try {
			int sceneId=character.getScene();
			this.writeByte(t.getTeamPopulation());
			List<Hero> cs = t.getCharacterPlayers();
			for (Hero c : cs) {
				this.writeInt(c.getId());
				this.writeBoolean(sceneId==c.getScene());
				this.writeUTF(c.getSceneRef().getShowName());
				this.writeShort(c.getX());
				this.writeShort(c.getY());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
