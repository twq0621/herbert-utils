package net.snake.gamemodel.across.response;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.snake.netio.ServerResponse;


/**
 * 锁频状态
 */

public class LockActionStateMsg10600 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(LockActionStateMsg10600.class);
	/**
	 * @param i
	 */
	public LockActionStateMsg10600(int time) {
		this.setMsgCode(10600);
		try {
			this.writeByte(1);
			this.writeShort(time);
			this.writeInterUTF(1350);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
	}

	/**
	 * 
	 */
	public LockActionStateMsg10600() {
		this.setMsgCode(10600);
			this.writeByte(0);
	}

}
