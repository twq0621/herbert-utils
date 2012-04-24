package net.snake.gamemodel.heroext.channel.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class ChannelResponse50208 extends ServerResponse {

	public ChannelResponse50208(Hero character, String channelid) {
		setMsgCode(50208);
		byte id = Byte.parseByte(channelid.substring(0, 1));
		writeInt(character.getId());
		writeByte(id);
	}

}
