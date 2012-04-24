package net.snake.gamemodel.team.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 当前蓝最大蓝的变化
 * 
 * 
 */
public class TeamBroadMsg10236 extends ServerResponse {
	public TeamBroadMsg10236(Hero character) {
		this.setMsgCode(10236);
		try {
			this.writeInt(character.getId());
			this.writeInt(character.getNowMp());
			this.writeInt(
					character.getPropertyAdditionController().getExtraMaxMp());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

}
