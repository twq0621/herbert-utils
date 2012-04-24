package net.snake.gamemodel.gm.response;

import net.snake.netio.ServerResponse;

/**
 * 告诉用户 另一个用户在别的线路登陆 10038
 * 
 * @author dev wutao
 * 
 */
public class TellPlayerOtherUserLoginResponse extends ServerResponse {
	private static final int MSGCODE = 10038;

	public TellPlayerOtherUserLoginResponse(int key, String... msg) {
		super();
		try {
			super.setMsgCode(MSGCODE);
			writeInterUTF(key, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
