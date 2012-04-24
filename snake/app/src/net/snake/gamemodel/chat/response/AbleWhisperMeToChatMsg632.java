package net.snake.gamemodel.chat.response;

import net.snake.netio.ServerResponse;


/**
 * 告诉聊天服务器是否禁止私聊
 * @author serv_dev
 *
 */
public class AbleWhisperMeToChatMsg632 extends ServerResponse {

	/**
	 * 
	 * @param flag 1禁止私聊 0不禁止
	 */
	public AbleWhisperMeToChatMsg632(int character,byte flag) {
		setMsgCode(632);
		this.writeInt(character);
		this.writeByte(flag);
	}
}
