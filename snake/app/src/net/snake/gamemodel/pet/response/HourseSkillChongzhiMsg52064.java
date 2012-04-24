package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 * 技能重置面板信息
 * 
 * @author serv_dev
 * 
 */
public class HourseSkillChongzhiMsg52064 extends ServerResponse {
	public HourseSkillChongzhiMsg52064(int gmodelId) {
		this.setMsgCode(52064);
		writeInt(gmodelId);
	}
}
