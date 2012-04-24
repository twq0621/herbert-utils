package net.snake.gamemodel.dujie.response;

import net.snake.netio.ServerResponse;

/**
 * 护法收益
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class HufaIncomeResp extends ServerResponse {
	public HufaIncomeResp(int income,int mx) {
		super();
		setMsgCode(60332);
		writeInt(income);
		writeInt(mx);
	}
}
