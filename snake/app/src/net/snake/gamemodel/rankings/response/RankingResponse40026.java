package net.snake.gamemodel.rankings.response;

import net.snake.netio.ServerResponse;


/**
 * 返回上榜和领取松果数量
 *
 */
public class RankingResponse40026 extends ServerResponse {

	private static int msgcode = 40026;


	public RankingResponse40026(byte type, byte chestCount) {
		super.setMsgCode(msgcode);
			writeByte(type);
			writeByte(chestCount);
		}
		
	}

	

