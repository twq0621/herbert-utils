package net.snake.gamemodel.hero.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 * 
 */
public class WorshipcontemptResponse50302ok extends ServerResponse {

	private static int msgcode = 50302;

	public WorshipcontemptResponse50302ok(byte type, byte bishihechongbai, int chongbai, int bishi) {
		setMsgCode(msgcode);
		writeByte(type);
		writeByte(bishihechongbai);
		writeInt(chongbai);
		writeInt(bishi);

	}

}
