package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

/**
 * 成为护法成功否
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieBecomeGuardResp extends ServerResponse {
	public DujieBecomeGuardResp(int suc) {
		super();
		setMsgCode(60310);
		writeByte(suc);
	}
}
