package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 改变默认技能
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class ChangeSkillMsg10282 extends ServerResponse {
	public ChangeSkillMsg10282(int skillId) {
		setMsgCode(10282);
		writeInt(skillId);
	}
}
