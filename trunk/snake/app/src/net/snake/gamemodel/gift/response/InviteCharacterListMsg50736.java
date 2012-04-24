package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class InviteCharacterListMsg50736 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(InviteCharacterListMsg50736.class);

	public InviteCharacterListMsg50736(List<CharacterCacheEntry> list) {
		this.setMsgCode(50736);
		try {
			this.writeByte(list.size());
			for (CharacterCacheEntry c : list) {
				this.writeInt(c.getId());
				this.writeUTF(c.getViewName());
				this.writeShort(c.getGrade());
				this.writeByte(c.getPopsinger());
				this.writeUTF("无");// 帮会
				this.writeByte(0);// 是否在线
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
