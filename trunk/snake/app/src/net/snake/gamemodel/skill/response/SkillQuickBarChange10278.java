package net.snake.gamemodel.skill.response;

import net.snake.netio.ServerResponse;

public class SkillQuickBarChange10278 extends ServerResponse {

	public SkillQuickBarChange10278(int skillid, int quickbarindex) {
		setMsgCode(10278);
		ServerResponse out = this;
		out.writeInt(skillid);
		out.writeByte(quickbarindex);
	}
}
