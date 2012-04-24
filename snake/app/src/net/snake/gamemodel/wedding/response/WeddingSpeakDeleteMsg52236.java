/**
 * 
 */
package net.snake.gamemodel.wedding.response;

import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.netio.ServerResponse;


/**
 * 
 * 
 * @author serv_dev
 */

public class WeddingSpeakDeleteMsg52236 extends ServerResponse {

	/**
	 * @param delete
	 */
	public WeddingSpeakDeleteMsg52236(CouplesSpeak delete) {
        this.setMsgCode(52236);
	      this.writeInt(delete.getTempId());

	}

}
