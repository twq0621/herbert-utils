/**
 * 
 */
package net.snake.gamemodel.faction.response.factioncity;

import net.snake.netio.ServerResponse;

/**
 * 请求当前时间申请攻城的攻城时间
 * 
 * @author serv_dev
 */

public class FactionCity51148 extends ServerResponse {
	public FactionCity51148(long time) {
		this.setMsgCode(51148);
		this.writeDouble(time);
	}

}
