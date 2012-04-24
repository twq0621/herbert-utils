package net.snake.ai.fight.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 * 直接效果影响
 * 
 * @author serv_dev
 * 
 */
public class DirectEffectBroadResponse extends ServerResponse {
	public void sendSkillId(int id) {
		writeInt(id);
	}
	
	public void sendVisibleObjectId(int id) {
		writeInt(id);
	}
	
	

	/**
	 * @param type 1玩家  3怪物
	 * @throws IOException
	 */
	public void sendVisibleObjectType(byte type) {
		writeByte(type);
	}

	/**
	 * 影响类型byte(1.血量 2.魔法 3.怒气) 影响值(当前值)（int）
	 * @param type
	 * @param value
	 * @throws IOException
	 */
	public void sendEffect(int value,int value2){
		writeInt(value);
		writeInt(value2);
		
	}
	
	/**
	 * 是否被闪避(byte,2跳闪,1闪避,0不是)
	 * @param flag
	 * @throws IOException
	 */
	public void sendDodgeFlag(int flag) {
		writeByte(flag);
	}
	
	/**
	 * 是否为暴击
	 * @param flag
	 * @throws IOException
	 */
	public void sendBaojiFlag(int flag) {
		writeByte(flag);
	}
}
