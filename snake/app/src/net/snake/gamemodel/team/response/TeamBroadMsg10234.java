package net.snake.gamemodel.team.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 组队广播玩家当前血最大血的变化
 * 
 * 
 */
public class TeamBroadMsg10234 extends ServerResponse {
	public TeamBroadMsg10234(Hero character) {
		this.setMsgCode(10234);
			this.writeInt(character.getId());
			this.writeInt(character.getNowHp());
			this.writeInt(
					character.getPropertyAdditionController().getExtraMaxHp());

	}

}
