/**
 * 
 */
package net.snake.gamemodel.wedding.response;


import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;


/**
 * 请求某玉佩属性
 * @author serv_dev
 */

public class WeddingRingMsg52216 extends ServerResponse {

	/**
	 * @param wr
	 */
	public WeddingRingMsg52216(WeddingRing wr) {
		this.setMsgCode(52216);
		try {
			this.writeInt(wr.getRingId());
			this.writeInt(wr.getSkillA());
			this.writeInt(wr.getSkillB());
			this.writeInt(wr.getMaleGood());
			this.writeInt(wr.getFemaleGood());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

}
