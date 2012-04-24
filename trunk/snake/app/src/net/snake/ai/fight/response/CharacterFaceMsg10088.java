package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 攻击、被攻击表情
 * @author serv_dev
 *
 */
public class CharacterFaceMsg10088 extends ServerResponse {
	
	
	public CharacterFaceMsg10088(int face,int id) {
		setMsgCode(10088);
		writeByte(face);
		writeInt(id);
	}
}
