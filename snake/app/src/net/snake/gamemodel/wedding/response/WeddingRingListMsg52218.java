/**
 * 
 */
package net.snake.gamemodel.wedding.response;

import java.util.List;

import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;


/**
 * 婚戒购买列表 
 * 
 * @author serv_dev
 */

public class WeddingRingListMsg52218 extends ServerResponse {

	/**
	 * @param list
	 */
	public WeddingRingListMsg52218(List<WeddingRing> list) {
		this.setMsgCode(52218);
			this.writeByte(list.size());
			for (WeddingRing wr : list) {
                  this.writeInt(wr.getRingId());
                  this.writeInt(wr.getSaleCopper());
          		this.writeInt(wr.getSkillA());
    			this.writeInt(wr.getSkillB());
                  this.writeInt(wr.getMaleGood());
                  this.writeInt(wr.getFemaleGood());
			}

	}

}
