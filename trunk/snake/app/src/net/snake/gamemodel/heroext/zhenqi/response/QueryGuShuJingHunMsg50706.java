package net.snake.gamemodel.heroext.zhenqi.response;

import net.snake.netio.ServerResponse;

/**
 * 古树精魄查询
 * 
 * @author jack
 * 
 */
public class QueryGuShuJingHunMsg50706 extends ServerResponse {

	public QueryGuShuJingHunMsg50706(int count, int max) {
		setMsgCode(50706);
		writeShort(1);
		writeShort(max);
	}
}
