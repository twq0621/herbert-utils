package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;

/**
 *20188		闪避技能
 * @author jack
 *
 */
public class SkillShowResponse20188  extends ServerResponse {
	private static final int MSGCODE = 20188; 
	/**
	 角色类型(byte),角色ID(int),目标点X(short),目标点Y(short)
	 * @param ref 关联技能标识    0普通1关联
	 * @param skillId 功方技能ID
	 * @param gjType 功方roleType
	 * @param gjId 攻方ID
	 * @param bjType 被功方roleType
	 * @param bjId 被攻方ID
	 */
	public SkillShowResponse20188(byte gjType,int gjId,short targetX,short targetY){
		setMsgCode(MSGCODE);
		writeByte(gjType);
		writeInt(gjId);
		writeShort(targetX);
		writeShort(targetY);
	}
}
