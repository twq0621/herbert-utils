package net.snake.gamemodel.across.response;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */

public class YuanfuToken10118 extends ServerResponse {

	/**
	 * @param as
	 * @param sign
	 * @param auth
	 * @param newaccountId
	 * @param newcharacterId
	 * 
	 */
	public YuanfuToken10118(String auth, String sign, AcrossEtc as, int newcharacterId, int newaccountId) {
		super.setMsgCode(10118);

		try {
			writeByte(as.getCharacterLineId());
			String loginserverip = as.getLoginServerIp();
			String loginserverport = as.getLoginport();
			writeUTF(loginserverip);
			writeUTF(loginserverport);
			writeUTF(as.getCharacterLineName());
			writeUTF(auth);
			writeUTF(sign);
			writeUTF(as.getChatServerIp());
			writeUTF(as.getChatport());
			writeInt(newcharacterId);
			writeInt(newaccountId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
