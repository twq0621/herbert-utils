package net.snake.ai.fight.response;

import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;


/**
 * 怪物通知凶手和队友
 * @author serv_dev
 *
 */
public class MonsterDieMsg11168 extends ServerResponse{
	private static final int MSGCODE = 11168;
	public MonsterDieMsg11168(SceneMonster monster,net.snake.gamemodel.hero.bean.Hero character)
	{
		this.setMsgCode(MSGCODE);
		this.writeInt(monster.getId());
		this.writeInt(character.getId());
	}
}
	
