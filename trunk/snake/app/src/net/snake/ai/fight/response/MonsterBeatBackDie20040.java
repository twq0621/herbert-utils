package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.logic.LastAttackerInfo;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;


/**
 * 怪物被击退死亡
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class MonsterBeatBackDie20040 extends ServerResponse {
	
	private static final int MSGCODE = 20040; 
	//1 活着 0 死亡
	public MonsterBeatBackDie20040(SceneMonster me) {
		setMsgCode(MSGCODE);
		writeInt(me.getId());
		LastAttackerInfo li = me.getLastAttackInfo();
		writeByte(li.getAttacker().getSpriteType());
		writeInt(li.getAttacker().getId());
	}
}
