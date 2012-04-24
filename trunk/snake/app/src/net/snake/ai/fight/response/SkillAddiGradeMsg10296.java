package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 技能附加等级
 * @author serv_dev
 *
 */
public class SkillAddiGradeMsg10296 extends ServerResponse {
	public SkillAddiGradeMsg10296(int... skill) {
		setMsgCode(10296);
		writeShort(skill.length / 2);
		for (int i = 0; i < skill.length; i = i + 2) {
			writeInt(skill[i]);
			writeInt(skill[i + 1]);

		}
	}
}
