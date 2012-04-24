package net.snake.gamemodel.hero.response;

import java.io.IOException;

import net.snake.netio.ServerResponse;


/**
 * 附加基本属性消息
 * @author serv_dev
 *
 */
public class PropertyAddMsg50602 extends ServerResponse {
	
	public PropertyAddMsg50602(int flag,int msgkey) {
		setMsgCode(50602);
		try {
			writeByte(flag);
			if (flag == 0) {
				writeInterUTF(msgkey);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
