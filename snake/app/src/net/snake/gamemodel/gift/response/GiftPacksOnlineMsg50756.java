package net.snake.gamemodel.gift.response;

import net.snake.netio.ServerResponse;

/**
 * 领取登入礼包是否成功
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksOnlineMsg50756 extends ServerResponse {
	public GiftPacksOnlineMsg50756(int type, int time) {
		setMsgCode(50756);
		writeInt(time);
		writeByte(type);

	}
}
