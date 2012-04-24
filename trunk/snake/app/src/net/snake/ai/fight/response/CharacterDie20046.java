package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 角色死亡
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class CharacterDie20046 extends ServerResponse {
	private static final int MSGCODE = 20046; 
	//1 活着 0 死亡
	public CharacterDie20046(Hero me){
		setMsgCode(MSGCODE);
		writeInt(me.getId());
	}	
}
