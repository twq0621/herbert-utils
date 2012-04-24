package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 自动追击目标使用平砍消息
 * @author serv_dev
 *
 */
public class AutoFightTargetMsg10906 extends ServerResponse {

	public AutoFightTargetMsg10906(int type,int targetId) {
		setMsgCode(10906);
	
		writeByte(type);
		writeInt(targetId);
	}
}
