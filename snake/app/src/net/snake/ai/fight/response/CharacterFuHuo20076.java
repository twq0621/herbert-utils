package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 角色复活
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class CharacterFuHuo20076 extends ServerResponse {
	private static final int MSGCODE = 20076; 
	//1 活着 0 死亡
	public CharacterFuHuo20076(Hero me){
		setMsgCode(MSGCODE);
		writeInt(me.getId());
	}	
}
