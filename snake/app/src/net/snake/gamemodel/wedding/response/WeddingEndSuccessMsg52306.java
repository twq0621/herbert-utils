/**
 * 
 */
package net.snake.gamemodel.wedding.response;


import net.snake.netio.ServerResponse;


/**
 * 离婚通知领取半玉
 * @author serv_dev
 */

public class WeddingEndSuccessMsg52306 extends ServerResponse {

	/**
	 * @param otherId
	 */
	public WeddingEndSuccessMsg52306(int otherId) {
		this.setMsgCode(52306);
			this.writeInt(otherId);
	}

}
