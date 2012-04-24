package net.snake.gamemodel.rankings.response;

import net.snake.netio.ServerResponse;

public class RankingResponse50398 extends ServerResponse {

	private static int msgcode = 50398;

	public RankingResponse50398(byte type, String str) {
		super.setMsgCode(msgcode);
		try {
			str = str.substring(0, str.length() - 1);
			writeByte(type);
			writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
