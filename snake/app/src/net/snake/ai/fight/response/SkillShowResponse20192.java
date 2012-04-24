package net.snake.ai.fight.response;


import net.snake.ai.fight.bean.FighterVO;
import net.snake.netio.ServerResponse;

/**
 * 直线攻击的技能消息，取代原来的10086
 * @author jack
 *
 */
public class SkillShowResponse20192  extends ServerResponse {
	private static final int MSGCODE = 20192; 
	
	/**
	 * 技能ID(int),角色类型(byte),角色ID(int),目标点X(short),目标点Y(short)
	 * @param fighterVO
	 * @param skillId
	 */
	public SkillShowResponse20192(FighterVO fighterVO, int skillId) {
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

	}
}
