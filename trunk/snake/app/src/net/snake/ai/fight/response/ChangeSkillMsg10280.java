package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 改变技能
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class ChangeSkillMsg10280 extends ServerResponse {
	public ChangeSkillMsg10280(int skillId) {
		setMsgCode(10280);
		writeInt(skillId);
	}
}
