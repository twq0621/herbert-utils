package net.snake.ai.fight.response;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.netio.ServerResponse;

/**
 * 扇形攻击的技能消息，取代原来的10086
 * 
 * @author jack
 * 
 */
public class SkillShowResponse20190 extends ServerResponse {
	private static final int MSGCODE = 20190;

	/**
	 * 技能ID(int),角色类型(byte),角色ID(int),目标点X(short),目标点Y(short)，扇形角度(short)
	 * @param fighterVO
	 * @param skill
	 */
	public SkillShowResponse20190(FighterVO fighterVO, Skill skill) {
		int skillId = skill.getId();
		int angle = skill.getAngle();
		int gjType = fighterVO.getSponsor().getSceneObjType();
		int gjId = fighterVO.getSponsor().getId();
		short targetX = fighterVO.getX();
		short targetY = fighterVO.getY();
		setMsgCode(MSGCODE);
		writeInt(skillId);
		writeByte(gjType);
		writeInt(gjId);
		writeShort(targetX);
		writeShort(targetY);
		writeShort(angle);
	}
}
