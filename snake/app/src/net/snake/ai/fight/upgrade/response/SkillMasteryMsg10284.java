package net.snake.ai.fight.upgrade.response;


import net.snake.netio.ServerResponse;


/**
 * 技能熟练度改变
 * @author serv_dev
 *
 */
public class SkillMasteryMsg10284 extends ServerResponse {
	public SkillMasteryMsg10284(int skillId,int mastery) {
		setMsgCode(10284);
			writeInt(skillId);
			writeInt(mastery);
		
	}
}
