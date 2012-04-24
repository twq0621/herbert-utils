package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class DelCharacterSkillMsg10290 extends ServerResponse {
	
	public DelCharacterSkillMsg10290(int skillid) {
		setMsgCode(10290);
		writeInt(skillid);
	}
}
