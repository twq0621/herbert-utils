package net.snake.gamemodel.friend.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * 
 */
public class PersonalsResponse52204 extends ServerResponse {

	public PersonalsResponse52204(byte type, int key, String... str) {
		setMsgCode(52204);
		try {
			if (type == 0) {
				writeByte(type);
				if (key != 0) {
					writeInterUTF(key, str);
				}
			} else {
				writeByte(type);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
