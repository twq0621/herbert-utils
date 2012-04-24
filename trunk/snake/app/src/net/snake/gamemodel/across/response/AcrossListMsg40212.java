/**
 * 
 */
package net.snake.gamemodel.across.response;

import java.util.Collection;

import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */

public class AcrossListMsg40212 extends ServerResponse {

	public AcrossListMsg40212(Collection<AcrossServerDate> list) {
		this.setMsgCode(40212);
		try {
			this.writeByte(list.size());
			for (AcrossServerDate as : list) {
				this.writeByte(as.getServerId());
				this.writeUTF(as.getLoginServerIp());
				this.writeUTF(as.getLoginServerPort());
				this.writeUTF(as.getLoginservername());
				this.writeInt(as.getOnlineNum());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
