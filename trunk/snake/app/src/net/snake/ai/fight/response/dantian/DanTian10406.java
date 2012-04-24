package net.snake.ai.fight.response.dantian;

import net.snake.netio.ServerResponse;


public class DanTian10406 extends ServerResponse {

	public DanTian10406(int skillId, int charId) {
		setMsgCode(10406);
		writeInt(skillId);
		writeInt(charId);

	}
}
