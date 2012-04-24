package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * 箭支用尽
 * 
 * @author serv_dev
 */
public class ArrowExhaust53042 extends ServerResponse {

	public ArrowExhaust53042(String arrowName) {
		setMsgCode(53042);
		try {
			writeUTF(arrowName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
