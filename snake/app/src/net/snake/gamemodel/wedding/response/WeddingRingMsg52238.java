/**
 * 
 */
package net.snake.gamemodel.wedding.response;



import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;


/**
 * 返回自己玉佩属性
 * @author serv_dev
 */

public class WeddingRingMsg52238 extends ServerResponse {

	/**
	 * @param wr
	 */
	public WeddingRingMsg52238(WeddingRing wr) {
		this.setMsgCode(52238);
			this.writeInt(wr.getRingId());
			this.writeInt(wr.getSkillA());
			this.writeInt(wr.getSkillB());
			this.writeInt(wr.getMaleGood());
			this.writeInt(wr.getFemaleGood());

	}

}
