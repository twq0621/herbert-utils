package net.snake.gamemodel.gift.response;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.netio.ServerResponse;

/**
 * 领取登入礼包是否成功
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksInviteMsg50734 extends ServerResponse {

	public GiftPacksInviteMsg50734(CharacterGiftpacks cgp) {
		this.setMsgCode(50734);
		this.writeInt(cgp.getGiftPacksId());

	}
}
