package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;



/**
 *  技能组合技
 * @author serv_dev
 *
 */
public class ZuHeiSkillMsg10658 extends ServerResponse {
	
	public ZuHeiSkillMsg10658(int skillId,int ownerId) {
		setMsgCode(10658);
		writeInt(skillId);
		writeInt(ownerId);
		
	}
}
