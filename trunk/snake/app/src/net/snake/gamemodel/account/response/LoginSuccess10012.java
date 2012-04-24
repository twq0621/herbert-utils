package net.snake.gamemodel.account.response;

import net.snake.gamemodel.account.bean.Account;
import net.snake.netio.ServerResponse;

public class LoginSuccess10012 extends ServerResponse {

	public LoginSuccess10012() {
		super();
		setMsgCode(10012);
	}

	public void setData(Account account) {
		int gameaccount = account.getId();
		byte fcm = account.getIsLimit();
		// String yyid = account.getYunyingId();
		int sid = Integer.parseInt(account.getServer());
		ServerResponse out = this;
		try {
			out.writeByte(1);
			out.writeInt(gameaccount);
			out.writeByte(fcm);
			// out.writeUTF(auth);
			// out.writeUTF(sign);
			out.writeInt(sid);
			// if (yyid == null) {
			// out.writeUTF("");
			// } else {
			// out.writeUTF(yyid);
			// }
			out.writeDouble(System.currentTimeMillis());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
