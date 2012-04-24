package net.snake.gamemodel.team.response;

import net.snake.netio.ServerResponse;

/**
 * 使用阵法是否成功消息
 * 
 */
public class TeamFightingMsg10254 extends ServerResponse {
	public TeamFightingMsg10254(int zhenfaId) {
		this.setMsgCode(10254);
		this.writeByte(zhenfaId);
	}
}
