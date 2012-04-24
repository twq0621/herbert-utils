package net.snake.ai.fight.upgrade.response;

import net.snake.netio.ServerResponse;


/**
 * 
 * 技能突破成功消息
 */
public class SkillPoMsg10288 extends ServerResponse{
	public SkillPoMsg10288(int skillid) {
		setMsgCode(10288);
			writeInt(skillid);
	}
}
