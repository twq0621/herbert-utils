/**
 * 
 */
package net.snake.gamemodel.wedding.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 发送结婚成功消息
 * @author serv_dev
 */

public class WeddingMsg52228 extends ServerResponse {

	/**
	 * @param wedder
	 */
	public WeddingMsg52228(Hero wedder) {
		this.setMsgCode(52228);
		try {
			this.writeInt(wedder.getId());
			this.writeByte(wedder.getHeadimg());
			this.writeUTF(wedder.getViewName());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
