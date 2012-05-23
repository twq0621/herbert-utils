package game.logic.account;

import lion.message.MyServerResponse;

public class LoginRequest extends MyServerResponse {
	public LoginRequest(int accountId, int roleId) {
		super();
		setMsgCode(10001);// 固定的消息号
		try {
			writeInt(accountId);
			writeInt(roleId);
			writeUTF("是咯好同志!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
