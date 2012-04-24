package net.snake.gamemodel.across.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */

public class AcrossTishiMsg10114 extends ServerResponse {

	/**
	 * @param i
	 * @param b
	 * @param string
	 */
	public AcrossTishiMsg10114(int serverId, byte type, String str) {
		this.setMsgCode(10114);
		try {
			this.writeByte(serverId);
			this.writeByte(type);
			this.writeUTF(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
