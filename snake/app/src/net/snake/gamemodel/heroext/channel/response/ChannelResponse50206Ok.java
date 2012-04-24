package net.snake.gamemodel.heroext.channel.response;

/**
 * 
 * 
 */
import net.snake.consts.CommonUseNumber;
import net.snake.netio.ServerResponse;

public class ChannelResponse50206Ok extends ServerResponse {

	public ChannelResponse50206Ok(int characterid, Short channelid, int msgkey, String... str) {
		setMsgCode(50206);
		try {
			writeInt(characterid);
			writeShort(channelid);
			writeByte(CommonUseNumber.byte1);
			writeInterUTF(msgkey, str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
