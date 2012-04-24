package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;
/**
 * 渡劫副本开始倒计时
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public class StartCountdownResp extends ServerResponse {
	public StartCountdownResp(int ms,int dujie) {
		super();
		setMsgCode(60302);
		writeInt(ms);
		writeByte(dujie);
	}
}
