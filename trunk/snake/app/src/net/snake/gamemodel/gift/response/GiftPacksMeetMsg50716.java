package net.snake.gamemodel.gift.response;

import org.apache.log4j.Logger;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.netio.ServerResponse;

/**
 * 发送见面礼包可以领取消息
 * @author serv_dev
 *
 */
public class GiftPacksMeetMsg50716 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(GiftPacksMeetMsg50716.class);
	public GiftPacksMeetMsg50716(CharacterGiftpacks cgp) {
		this.setMsgCode(50716);
		try {
			this.writeInt(cgp.getGiftPacksId());
			this.writeUTF(cgp.getGp().getDescStrI18n());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
