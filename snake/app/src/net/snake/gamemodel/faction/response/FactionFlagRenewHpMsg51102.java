package net.snake.gamemodel.faction.response;

import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.netio.ServerResponse;

public class FactionFlagRenewHpMsg51102 extends ServerResponse {

	public FactionFlagRenewHpMsg51102(SceneBangqiMonster monster) {
		this.setMsgCode(51102);
		this.writeInt(monster.getId());
		this.writeInt(monster.getNowHp());
		this.writeInt(monster.getMaxHp());
	}

}
