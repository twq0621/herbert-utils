package net.snake.gamemodel.gift.response;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

/**
 * 发送见面礼包消息
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksMeetMsg50714 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(GiftPacksMeetMsg50714.class);

	public GiftPacksMeetMsg50714(CharacterGiftpacks cgp, int time) {
		this.setMsgCode(50714);
		try {
			this.writeInt(cgp.getGiftPacksId());
			this.writeInt(time);
			this.writeUTF(cgp.getGp().getDescStrI18n());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
