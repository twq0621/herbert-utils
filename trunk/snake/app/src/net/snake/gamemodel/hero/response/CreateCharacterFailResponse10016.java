package net.snake.gamemodel.hero.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev wutao
 * 
 */
public class CreateCharacterFailResponse10016 extends ServerResponse {

	public CreateCharacterFailResponse10016(String failreason) {

		setMsgCode(10016);
		try {
			writeByte(0);
			writeUTF(failreason);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public CreateCharacterFailResponse10016(int msgkey) {

		setMsgCode(10016);
		try {
			writeByte(0);
			writeInterUTF(msgkey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
