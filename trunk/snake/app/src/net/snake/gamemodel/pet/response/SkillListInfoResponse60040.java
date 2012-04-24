package net.snake.gamemodel.pet.response;

import java.util.List;

import net.snake.gamemodel.skill.bean.Skill;
import net.snake.netio.ServerResponse;

public class SkillListInfoResponse60040 extends ServerResponse {

	/**
	 * 16个灵宠技能id(int)
	 * 
	 * @param horse
	 */
	public SkillListInfoResponse60040(List<Skill> skills, int copper, int zhenqi) {
		this.setMsgCode(60040);
		this.writeInt(copper);
		this.writeInt(zhenqi);
		this.writeByte(skills.size());
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			writeInt(skill.getId());
		}
	}
}
