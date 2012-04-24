package net.snake.gamemodel.hero.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 * 
 */
public class WorshipcontemptResponse50302 extends ServerResponse {

	private static int msgcode = 50302;

	public WorshipcontemptResponse50302(byte type, byte bishihechongbai, int key, String... msg) {
		setMsgCode(msgcode);
		try {
			writeByte(type);
			writeByte(bishihechongbai);
			writeInterUTF(key, msg);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
