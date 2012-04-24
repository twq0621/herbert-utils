package net.snake.gamemodel.across.response;

import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */

public class AcrossToken10116 extends ServerResponse {

	/**
	 * @param as
	 * @param sign
	 * @param auth
	 * @param newaccountId
	 * @param newcharacterId
	 * 
	 */
	public AcrossToken10116(String auth, String sign, AcrossServerDate as, int newcharacterId, int newaccountId) {
		super.setMsgCode(10116);

		try {
			writeByte(as.getServerId());
			String loginserverip = as.getLoginServerIp();
			String loginserverport = as.getLoginServerPort();
			writeUTF(loginserverip);
			writeUTF(loginserverport);
			writeUTF(as.getLoginservername());
			writeUTF(auth);
			writeUTF(sign);
			writeUTF(as.getChatServerIp());
			writeUTF(as.getChatServerPort());
			writeInt(newcharacterId);
			writeInt(newaccountId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
