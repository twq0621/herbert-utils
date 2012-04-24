package net.snake.ai.fight.upgrade.response;


import net.snake.netio.ServerResponse;


/**
 * 更新主角武功层数境界
 * @author serv_dev
 *
 */
public class WuGongSkillGradeMsg10294 extends ServerResponse {
	public WuGongSkillGradeMsg10294(int grade) {
		setMsgCode(10294);
			writeShort(grade);
	}
}
