package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;

/**
 * 蛇魔技能消息，取代原来的10086
 * @author jack
 *
 */
public class SkillShowResponse20186  extends ServerResponse {
	private static final int MSGCODE = 20186; 
	/**
	 * 技能ID(int),角色类型(byte),角色ID(int),目标类型(byte),目标ID(int),目标点X(short),目标点Y(short)
	 * @param ref 关联技能标识    0普通1关联
	 * @param skillId 功方技能ID
	 * @param gjType 功方roleType
	 * @param gjId 攻方ID
	 * @param bjType 被功方roleType
	 * @param bjId 被攻方ID
	 */
	public SkillShowResponse20186(int skillId,byte gjType,int gjId,byte bjType,int bjId,short targetX,short targetY){
		setMsgCode(MSGCODE);
		writeInt(skillId);
		writeByte(gjType);
		writeInt(gjId);
		writeByte(bjType);
		writeInt(bjId);
		writeShort(targetX);
		writeShort(targetY);
	}
}
