package net.snake.gamemodel.gift.response;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.netio.ServerResponse;

/**
 * 领取登入礼包是否成功
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksLoginMsg50744 extends ServerResponse {
	
	public GiftPacksLoginMsg50744(CharacterGiftpacks cgp) {
		this.setMsgCode(50744);
		this.writeInt(cgp.getGiftPacksId());

	}
}
