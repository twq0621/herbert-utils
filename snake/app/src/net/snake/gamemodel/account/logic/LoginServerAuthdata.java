package net.snake.gamemodel.account.logic;

import net.snake.commons.CertificationUtil;

/**
 * 
 * @author serv_dev 
 * Copyright (c) 2011 Kingnet
 */
public class LoginServerAuthdata {
	public int gameaccount;
	public int limit;
	public long time;

	public LoginServerAuthdata(String authdata) throws Exception {
		authdata = CertificationUtil.decodeBase64(authdata);
		String[] t = authdata.split("_");
		gameaccount = Integer.parseInt(t[0]);
		limit = Integer.parseInt(t[1]);
		time = Long.parseLong(t[2]);
	}
}
