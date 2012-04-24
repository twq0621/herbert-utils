package net.snake.gamemodel.heroext.channel.response;

/**
 * 
 * 
 * @author serv_dev
 */
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class ChannelResponse50204 extends ServerResponse {

	public ChannelResponse50204(Hero character, Short channelid) {
		setMsgCode(50204);
		writeInt(character.getId());
		writeShort(channelid);
		writeByte((byte) 10);
	}

}
