package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;


/**
 * 取消持续效果小图标
 * @author serv_dev
 *
 */
public class CancelSustainEffectResponse extends ServerResponse{

	private static final int MSGCODE = 10142;
	public CancelSustainEffectResponse(byte type,int id,int effectId) {
		setMsgCode(MSGCODE);
		writeByte(type);
		writeInt(id);
		writeInt(effectId);
	}
}
