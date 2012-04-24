package net.snake.gamemodel.instance.response;

import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


public class TeamEnterInstanceFailMsg10710 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(TeamEnterInstanceFailMsg10710.class);
	public TeamEnterInstanceFailMsg10710(int msgKey,String... vars) {
		this.setMsgCode(10710);
		try {
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
