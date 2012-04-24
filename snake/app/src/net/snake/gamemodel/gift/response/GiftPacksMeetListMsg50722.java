package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class GiftPacksMeetListMsg50722 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(GiftPacksMeetListMsg50722.class);

	public GiftPacksMeetListMsg50722(CharacterGiftpacks nowGift, List<GiftPacks> list, Hero character) {
		setMsgCode(50722);
		try {
			writeUTF(character.getCreatetime().getTime() + "");
			writeByte(list.size());
			for (GiftPacks gp : list) {
				writeInt(gp.getGoodId());
				writeUTF(gp.getDescStrI18n());
				writeInt(gp.getTimeLimit() * 60);
				if (gp.getId() < nowGift.getGp().getId()) {
					writeByte(1);
				} else if (gp.getId() > nowGift.getGp().getId()) {
					writeByte(0);
				} else {
					if (nowGift.getIsOwner()) {
						writeByte(1);
					} else {
						writeByte(0);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
