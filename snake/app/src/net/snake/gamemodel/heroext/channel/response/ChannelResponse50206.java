package net.snake.gamemodel.heroext.channel.response;

/**
 * 
 * 
 */
import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

public class ChannelResponse50206 extends ServerResponse {

	public ChannelResponse50206(int characterid, Short channelid, int key, String... str) {
		setMsgCode(50206);
		try {

			writeInt(characterid);
			writeShort(channelid);
			writeByte(CommonUseNumber.byte0);
			writeInterUTF(key, str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
