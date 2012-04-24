package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 被动技能被触发
 * @author serv_dev
 *
 */
public class TribBeiDongSkillMsg10102 extends ServerResponse {
	/**
	 * 角色roleType（byte）(1玩家 2怪物3坐骑4.npc好友),角色id(int)，效果ID(int)
	 * @param type
	 * @param character
	 * @param skillId
	 */
	public TribBeiDongSkillMsg10102(int type,int character,int skill) {
		setMsgCode(10102);
		writeByte(type);
		writeInt(character);
		writeInt(skill);
	}
}
