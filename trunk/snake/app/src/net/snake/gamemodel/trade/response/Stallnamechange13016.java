package net.snake.gamemodel.trade.response;


import net.snake.netio.ServerResponse;


public class Stallnamechange13016 extends ServerResponse {

	public Stallnamechange13016(String name) {
		setMsgCode(13016);
		try {
			writeUTF(name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
