package net.snake.ai.fight.response;

import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;

/**
 * 怪物死亡
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class MonsterDie20048 extends ServerResponse {

	private static final int MSGCODE = 20048;

	// 1 活着 0 死亡
	public MonsterDie20048(SceneMonster me) {
		setMsgCode(MSGCODE);
		writeInt(me.getId());
	}
}
