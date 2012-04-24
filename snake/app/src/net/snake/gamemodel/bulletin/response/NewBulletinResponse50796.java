package net.snake.gamemodel.bulletin.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class NewBulletinResponse50796 extends ServerResponse {
	public NewBulletinResponse50796(String string) {
		try {
			setMsgCode(50796);
			writeUTF(string);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
